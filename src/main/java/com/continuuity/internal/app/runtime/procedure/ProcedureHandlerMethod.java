package com.continuuity.internal.app.runtime.procedure;

import com.continuuity.api.annotation.Handle;
import com.continuuity.data.dataset.DataSetContext;
import com.continuuity.api.procedure.Procedure;
import com.continuuity.api.procedure.ProcedureContext;
import com.continuuity.api.procedure.ProcedureRequest;
import com.continuuity.api.procedure.ProcedureResponder;
import com.continuuity.api.procedure.ProcedureResponse;
import com.continuuity.api.procedure.ProcedureSpecification;
import com.continuuity.app.program.Program;
import com.continuuity.app.runtime.RunId;
import com.continuuity.common.logging.LoggingContextAccessor;
import com.continuuity.internal.app.runtime.DataFabricFacade;
import com.continuuity.internal.app.runtime.DataSets;
import com.continuuity.internal.io.InstantiatorFactory;
import com.continuuity.internal.app.runtime.DataFabricFacadeFactory;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 *
 */
final class ProcedureHandlerMethod implements HandlerMethod {

  private static final Logger LOG = LoggerFactory.getLogger(ProcedureHandlerMethod.class);

  private static final String HANDLER_METHOD_PREFIX = "handle";
  private static final String ANY_METHOD = "";

  private final Procedure procedure;
  private final Map<String, HandlerMethod> handlers;
  private final BasicProcedureContext context;

  ProcedureHandlerMethod(Program program, RunId runId, int instanceId,
                                DataFabricFacadeFactory txAgentSupplierFactory) throws ClassNotFoundException {

    DataFabricFacade txAgentSupplier = txAgentSupplierFactory.createDataFabricFacadeFactory(program);
    DataSetContext dataSetContext = txAgentSupplier.getDataSetContext();

    ProcedureSpecification procedureSpec = program.getSpecification().getProcedures().get(program.getProgramName());
    context = new BasicProcedureContext(program, runId, instanceId,
                                        DataSets.createDataSets(dataSetContext, procedureSpec.getDataSets()),
                                        procedureSpec);

    TypeToken<? extends Procedure> procedureType = (TypeToken<? extends Procedure>)TypeToken.of(program.getMainClass());
    procedure = new InstantiatorFactory(false).get(procedureType).create();
    context.injectFields(procedure);
    handlers = createHandlerMethods(procedure, procedureType, txAgentSupplier);

    // TODO: It's a bit hacky, since we know there is one instance per execution handler thread.
    LoggingContextAccessor.setLoggingContext(context.getLoggingContext());
  }

  public Procedure getProcedure() {
    return procedure;
  }

  public ProcedureContext getContext() {
    return context;
  }

  public void init() {
    try {
      LOG.info("Initializing procedure: " + context);
      procedure.initialize(context);
      LOG.info("Procedure initialized: " + context);
    } catch (Throwable t) {
      LOG.error("Procedure throws exception during init.", t);
      throw Throwables.propagate(t);
    }
  }

  @Override
  public void handle(ProcedureRequest request, ProcedureResponder responder) {
    HandlerMethod handlerMethod = handlers.get(request.getMethod());
    if (handlerMethod == null) {
      LOG.error("Unsupport procedure method " + request.getMethod() + " on procedure " + procedure.getClass());
      context.getSystemMetrics().counter("query.failed", 1);
      try {
        responder.stream(new ProcedureResponse(ProcedureResponse.Code.NOT_FOUND));
      } catch (IOException e) {
        throw Throwables.propagate(e);
      }
      return;
    }

    try {
      handlerMethod.handle(request, responder);
      context.getSystemMetrics().counter("query.success", 1);
    } catch (Throwable t) {
      context.getSystemMetrics().counter("query.failed", 1);
      throw Throwables.propagate(t);
    }
  }

  private Map<String, HandlerMethod> createHandlerMethods(Procedure procedure,
                                                          TypeToken<? extends Procedure> procedureType,
                                                          DataFabricFacade txAgentSupplier) {

    ImmutableMap.Builder<String, HandlerMethod> result = ImmutableMap.builder();

    // Walk up the hierarchy of procedure class.
    for (TypeToken<?> type : procedureType.getTypes().classes()) {
      if (type.getRawType().equals(Object.class)) {
        break;
      }

      // Gather all handler method
      for (Method method : type.getRawType().getDeclaredMethods()) {
        Handle handleAnnotation = method.getAnnotation(Handle.class);
        if (!method.getName().startsWith(HANDLER_METHOD_PREFIX) && handleAnnotation == null) {
          continue;
        }

        Set<String> methodNames;
        if (handleAnnotation == null || handleAnnotation.value().length == 0) {
          methodNames = ImmutableSet.of(ANY_METHOD);
        } else {
          methodNames = ImmutableSet.copyOf(handleAnnotation.value());
        }

        for (String methodName : methodNames) {
          result.put(methodName, new ReflectionHandlerMethod(procedure, method, txAgentSupplier));
        }
      }
    }

    return result.build();
  }

  private void setField(Procedure procedure, Field field, Object value) {
    if (!field.isAccessible()) {
      field.setAccessible(true);
    }
    try {
      field.set(procedure, value);
    } catch (IllegalAccessException e) {
      throw Throwables.propagate(e);
    }
  }
}
