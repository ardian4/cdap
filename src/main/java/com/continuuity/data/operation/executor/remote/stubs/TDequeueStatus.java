/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.continuuity.data.operation.executor.remote.stubs;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum TDequeueStatus implements org.apache.thrift.TEnum {
  SUCCESS(0),
  EMPTY(1),
  FAILURE(2),
  RETRY(3);

  private final int value;

  private TDequeueStatus(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static TDequeueStatus findByValue(int value) { 
    switch (value) {
      case 0:
        return SUCCESS;
      case 1:
        return EMPTY;
      case 2:
        return FAILURE;
      case 3:
        return RETRY;
      default:
        return null;
    }
  }
}
