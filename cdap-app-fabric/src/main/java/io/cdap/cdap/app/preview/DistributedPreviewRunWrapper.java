/*
 * Copyright © 2020 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.cdap.cdap.app.preview;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.cdap.cdap.api.common.Bytes;
import io.cdap.cdap.common.conf.CConfiguration;
import io.cdap.cdap.common.conf.Constants;
import io.cdap.cdap.internal.app.preview.PreviewRunWrapper;
import io.cdap.cdap.proto.id.ProgramId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Distributed implementation of {@link PreviewRunWrapper}.
 */
public class DistributedPreviewRunWrapper implements PreviewRunWrapper {
  private static final Logger LOG = LoggerFactory.getLogger(DistributedPreviewRunWrapper.class);
  private static final Gson GSON = new Gson();
  private final Path programIdFilePath;

  @Inject
  DistributedPreviewRunWrapper(@Named(PreviewConfigModule.PREVIEW_CCONF) CConfiguration cConf) {
    programIdFilePath = Paths.get(cConf.get(Constants.CFG_LOCAL_DATA_DIR), "program.id").toAbsolutePath();
  }

  @Override
  public void init(ProgramId programId) {
    // write to temp file
    try {
      Files.write(programIdFilePath, Bytes.toBytes(GSON.toJson(programId)));
    } catch (IOException e) {
      LOG.warn("Error saving the program id to file.", e);
    }
  }

  @Override
  public void destroy() {
    // delete the temp file
    try {
      Files.delete(programIdFilePath);
    } catch (IOException e) {
      LOG.warn("Error deleting file containing preview program id.", e);
    }
  }
}
