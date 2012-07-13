/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.continuuity.data.operation.executor.remote.stubs;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TQueueMeta implements org.apache.thrift.TBase<TQueueMeta, TQueueMeta._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TQueueMeta");

  private static final org.apache.thrift.protocol.TField NULLED_FIELD_DESC = new org.apache.thrift.protocol.TField("nulled", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField GLOBAL_HEAD_POINTER_FIELD_DESC = new org.apache.thrift.protocol.TField("globalHeadPointer", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField CURRENT_WRITE_POINTER_FIELD_DESC = new org.apache.thrift.protocol.TField("currentWritePointer", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField GROUPS_FIELD_DESC = new org.apache.thrift.protocol.TField("groups", org.apache.thrift.protocol.TType.LIST, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TQueueMetaStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TQueueMetaTupleSchemeFactory());
  }

  public boolean nulled; // required
  public long globalHeadPointer; // required
  public long currentWritePointer; // required
  public List<TGroupState> groups; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NULLED((short)1, "nulled"),
    GLOBAL_HEAD_POINTER((short)2, "globalHeadPointer"),
    CURRENT_WRITE_POINTER((short)3, "currentWritePointer"),
    GROUPS((short)4, "groups");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NULLED
          return NULLED;
        case 2: // GLOBAL_HEAD_POINTER
          return GLOBAL_HEAD_POINTER;
        case 3: // CURRENT_WRITE_POINTER
          return CURRENT_WRITE_POINTER;
        case 4: // GROUPS
          return GROUPS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __NULLED_ISSET_ID = 0;
  private static final int __GLOBALHEADPOINTER_ISSET_ID = 1;
  private static final int __CURRENTWRITEPOINTER_ISSET_ID = 2;
  private BitSet __isset_bit_vector = new BitSet(3);
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NULLED, new org.apache.thrift.meta_data.FieldMetaData("nulled", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.GLOBAL_HEAD_POINTER, new org.apache.thrift.meta_data.FieldMetaData("globalHeadPointer", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.CURRENT_WRITE_POINTER, new org.apache.thrift.meta_data.FieldMetaData("currentWritePointer", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.GROUPS, new org.apache.thrift.meta_data.FieldMetaData("groups", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TGroupState.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TQueueMeta.class, metaDataMap);
  }

  public TQueueMeta() {
  }

  public TQueueMeta(
    boolean nulled,
    long globalHeadPointer,
    long currentWritePointer,
    List<TGroupState> groups)
  {
    this();
    this.nulled = nulled;
    setNulledIsSet(true);
    this.globalHeadPointer = globalHeadPointer;
    setGlobalHeadPointerIsSet(true);
    this.currentWritePointer = currentWritePointer;
    setCurrentWritePointerIsSet(true);
    this.groups = groups;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TQueueMeta(TQueueMeta other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.nulled = other.nulled;
    this.globalHeadPointer = other.globalHeadPointer;
    this.currentWritePointer = other.currentWritePointer;
    if (other.isSetGroups()) {
      List<TGroupState> __this__groups = new ArrayList<TGroupState>();
      for (TGroupState other_element : other.groups) {
        __this__groups.add(new TGroupState(other_element));
      }
      this.groups = __this__groups;
    }
  }

  public TQueueMeta deepCopy() {
    return new TQueueMeta(this);
  }

  @Override
  public void clear() {
    setNulledIsSet(false);
    this.nulled = false;
    setGlobalHeadPointerIsSet(false);
    this.globalHeadPointer = 0;
    setCurrentWritePointerIsSet(false);
    this.currentWritePointer = 0;
    this.groups = null;
  }

  public boolean isNulled() {
    return this.nulled;
  }

  public TQueueMeta setNulled(boolean nulled) {
    this.nulled = nulled;
    setNulledIsSet(true);
    return this;
  }

  public void unsetNulled() {
    __isset_bit_vector.clear(__NULLED_ISSET_ID);
  }

  /** Returns true if field nulled is set (has been assigned a value) and false otherwise */
  public boolean isSetNulled() {
    return __isset_bit_vector.get(__NULLED_ISSET_ID);
  }

  public void setNulledIsSet(boolean value) {
    __isset_bit_vector.set(__NULLED_ISSET_ID, value);
  }

  public long getGlobalHeadPointer() {
    return this.globalHeadPointer;
  }

  public TQueueMeta setGlobalHeadPointer(long globalHeadPointer) {
    this.globalHeadPointer = globalHeadPointer;
    setGlobalHeadPointerIsSet(true);
    return this;
  }

  public void unsetGlobalHeadPointer() {
    __isset_bit_vector.clear(__GLOBALHEADPOINTER_ISSET_ID);
  }

  /** Returns true if field globalHeadPointer is set (has been assigned a value) and false otherwise */
  public boolean isSetGlobalHeadPointer() {
    return __isset_bit_vector.get(__GLOBALHEADPOINTER_ISSET_ID);
  }

  public void setGlobalHeadPointerIsSet(boolean value) {
    __isset_bit_vector.set(__GLOBALHEADPOINTER_ISSET_ID, value);
  }

  public long getCurrentWritePointer() {
    return this.currentWritePointer;
  }

  public TQueueMeta setCurrentWritePointer(long currentWritePointer) {
    this.currentWritePointer = currentWritePointer;
    setCurrentWritePointerIsSet(true);
    return this;
  }

  public void unsetCurrentWritePointer() {
    __isset_bit_vector.clear(__CURRENTWRITEPOINTER_ISSET_ID);
  }

  /** Returns true if field currentWritePointer is set (has been assigned a value) and false otherwise */
  public boolean isSetCurrentWritePointer() {
    return __isset_bit_vector.get(__CURRENTWRITEPOINTER_ISSET_ID);
  }

  public void setCurrentWritePointerIsSet(boolean value) {
    __isset_bit_vector.set(__CURRENTWRITEPOINTER_ISSET_ID, value);
  }

  public int getGroupsSize() {
    return (this.groups == null) ? 0 : this.groups.size();
  }

  public java.util.Iterator<TGroupState> getGroupsIterator() {
    return (this.groups == null) ? null : this.groups.iterator();
  }

  public void addToGroups(TGroupState elem) {
    if (this.groups == null) {
      this.groups = new ArrayList<TGroupState>();
    }
    this.groups.add(elem);
  }

  public List<TGroupState> getGroups() {
    return this.groups;
  }

  public TQueueMeta setGroups(List<TGroupState> groups) {
    this.groups = groups;
    return this;
  }

  public void unsetGroups() {
    this.groups = null;
  }

  /** Returns true if field groups is set (has been assigned a value) and false otherwise */
  public boolean isSetGroups() {
    return this.groups != null;
  }

  public void setGroupsIsSet(boolean value) {
    if (!value) {
      this.groups = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NULLED:
      if (value == null) {
        unsetNulled();
      } else {
        setNulled((Boolean)value);
      }
      break;

    case GLOBAL_HEAD_POINTER:
      if (value == null) {
        unsetGlobalHeadPointer();
      } else {
        setGlobalHeadPointer((Long)value);
      }
      break;

    case CURRENT_WRITE_POINTER:
      if (value == null) {
        unsetCurrentWritePointer();
      } else {
        setCurrentWritePointer((Long)value);
      }
      break;

    case GROUPS:
      if (value == null) {
        unsetGroups();
      } else {
        setGroups((List<TGroupState>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NULLED:
      return Boolean.valueOf(isNulled());

    case GLOBAL_HEAD_POINTER:
      return Long.valueOf(getGlobalHeadPointer());

    case CURRENT_WRITE_POINTER:
      return Long.valueOf(getCurrentWritePointer());

    case GROUPS:
      return getGroups();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NULLED:
      return isSetNulled();
    case GLOBAL_HEAD_POINTER:
      return isSetGlobalHeadPointer();
    case CURRENT_WRITE_POINTER:
      return isSetCurrentWritePointer();
    case GROUPS:
      return isSetGroups();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TQueueMeta)
      return this.equals((TQueueMeta)that);
    return false;
  }

  public boolean equals(TQueueMeta that) {
    if (that == null)
      return false;

    boolean this_present_nulled = true;
    boolean that_present_nulled = true;
    if (this_present_nulled || that_present_nulled) {
      if (!(this_present_nulled && that_present_nulled))
        return false;
      if (this.nulled != that.nulled)
        return false;
    }

    boolean this_present_globalHeadPointer = true;
    boolean that_present_globalHeadPointer = true;
    if (this_present_globalHeadPointer || that_present_globalHeadPointer) {
      if (!(this_present_globalHeadPointer && that_present_globalHeadPointer))
        return false;
      if (this.globalHeadPointer != that.globalHeadPointer)
        return false;
    }

    boolean this_present_currentWritePointer = true;
    boolean that_present_currentWritePointer = true;
    if (this_present_currentWritePointer || that_present_currentWritePointer) {
      if (!(this_present_currentWritePointer && that_present_currentWritePointer))
        return false;
      if (this.currentWritePointer != that.currentWritePointer)
        return false;
    }

    boolean this_present_groups = true && this.isSetGroups();
    boolean that_present_groups = true && that.isSetGroups();
    if (this_present_groups || that_present_groups) {
      if (!(this_present_groups && that_present_groups))
        return false;
      if (!this.groups.equals(that.groups))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(TQueueMeta other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    TQueueMeta typedOther = (TQueueMeta)other;

    lastComparison = Boolean.valueOf(isSetNulled()).compareTo(typedOther.isSetNulled());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNulled()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nulled, typedOther.nulled);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGlobalHeadPointer()).compareTo(typedOther.isSetGlobalHeadPointer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGlobalHeadPointer()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.globalHeadPointer, typedOther.globalHeadPointer);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCurrentWritePointer()).compareTo(typedOther.isSetCurrentWritePointer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCurrentWritePointer()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.currentWritePointer, typedOther.currentWritePointer);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGroups()).compareTo(typedOther.isSetGroups());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGroups()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.groups, typedOther.groups);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TQueueMeta(");
    boolean first = true;

    sb.append("nulled:");
    sb.append(this.nulled);
    first = false;
    if (!first) sb.append(", ");
    sb.append("globalHeadPointer:");
    sb.append(this.globalHeadPointer);
    first = false;
    if (!first) sb.append(", ");
    sb.append("currentWritePointer:");
    sb.append(this.currentWritePointer);
    first = false;
    if (!first) sb.append(", ");
    sb.append("groups:");
    if (this.groups == null) {
      sb.append("null");
    } else {
      sb.append(this.groups);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TQueueMetaStandardSchemeFactory implements SchemeFactory {
    public TQueueMetaStandardScheme getScheme() {
      return new TQueueMetaStandardScheme();
    }
  }

  private static class TQueueMetaStandardScheme extends StandardScheme<TQueueMeta> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TQueueMeta struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NULLED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.nulled = iprot.readBool();
              struct.setNulledIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // GLOBAL_HEAD_POINTER
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.globalHeadPointer = iprot.readI64();
              struct.setGlobalHeadPointerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CURRENT_WRITE_POINTER
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.currentWritePointer = iprot.readI64();
              struct.setCurrentWritePointerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // GROUPS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list48 = iprot.readListBegin();
                struct.groups = new ArrayList<TGroupState>(_list48.size);
                for (int _i49 = 0; _i49 < _list48.size; ++_i49)
                {
                  TGroupState _elem50; // optional
                  _elem50 = new TGroupState();
                  _elem50.read(iprot);
                  struct.groups.add(_elem50);
                }
                iprot.readListEnd();
              }
              struct.setGroupsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TQueueMeta struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(NULLED_FIELD_DESC);
      oprot.writeBool(struct.nulled);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(GLOBAL_HEAD_POINTER_FIELD_DESC);
      oprot.writeI64(struct.globalHeadPointer);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CURRENT_WRITE_POINTER_FIELD_DESC);
      oprot.writeI64(struct.currentWritePointer);
      oprot.writeFieldEnd();
      if (struct.groups != null) {
        oprot.writeFieldBegin(GROUPS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.groups.size()));
          for (TGroupState _iter51 : struct.groups)
          {
            _iter51.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TQueueMetaTupleSchemeFactory implements SchemeFactory {
    public TQueueMetaTupleScheme getScheme() {
      return new TQueueMetaTupleScheme();
    }
  }

  private static class TQueueMetaTupleScheme extends TupleScheme<TQueueMeta> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TQueueMeta struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetNulled()) {
        optionals.set(0);
      }
      if (struct.isSetGlobalHeadPointer()) {
        optionals.set(1);
      }
      if (struct.isSetCurrentWritePointer()) {
        optionals.set(2);
      }
      if (struct.isSetGroups()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetNulled()) {
        oprot.writeBool(struct.nulled);
      }
      if (struct.isSetGlobalHeadPointer()) {
        oprot.writeI64(struct.globalHeadPointer);
      }
      if (struct.isSetCurrentWritePointer()) {
        oprot.writeI64(struct.currentWritePointer);
      }
      if (struct.isSetGroups()) {
        {
          oprot.writeI32(struct.groups.size());
          for (TGroupState _iter52 : struct.groups)
          {
            _iter52.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TQueueMeta struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.nulled = iprot.readBool();
        struct.setNulledIsSet(true);
      }
      if (incoming.get(1)) {
        struct.globalHeadPointer = iprot.readI64();
        struct.setGlobalHeadPointerIsSet(true);
      }
      if (incoming.get(2)) {
        struct.currentWritePointer = iprot.readI64();
        struct.setCurrentWritePointerIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TList _list53 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.groups = new ArrayList<TGroupState>(_list53.size);
          for (int _i54 = 0; _i54 < _list53.size; ++_i54)
          {
            TGroupState _elem55; // optional
            _elem55 = new TGroupState();
            _elem55.read(iprot);
            struct.groups.add(_elem55);
          }
        }
        struct.setGroupsIsSet(true);
      }
    }
  }

}

