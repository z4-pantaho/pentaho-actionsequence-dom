package org.pentaho.actionsequence.model;

import java.io.Serializable;


// This class is used to set an action input to a constant value.
public class ActionInputConstant implements IActionInput, IActionInputSource, Serializable {
  Object value;
  String inputName;
  
  public ActionInputConstant() {
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(Object value) {
    this.value = value;
  }

  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(String value) {
    this.value = value;
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(Boolean value) {
    this.value = value;
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(boolean value) {
    this.value = new Boolean(value);
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(Integer value) {
    this.value = value;
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(int value) {
    this.value = new Integer(value);
  }
  
  public Object getValue() {
    return value;
  }
  
  public void setValue(Object value) {
    this.value = value;
  }
  
  public String getStringValue() {
    return getStringValue(null);
  }
  
  public String getStringValue(String defaultValue) {
    Object theValue = value;
    return theValue != null ? theValue.toString() : defaultValue;
  }

  public Boolean getBooleanValue() {
    Boolean boolValue = null;
    String stringValue = getStringValue();
    if (stringValue != null) {
      boolValue = new Boolean(stringValue);
    }
    return boolValue;
  }
  
  public boolean getBooleanValue(boolean defaultValue) {
    Boolean boolValue = getBooleanValue();
    return boolValue != null ? boolValue.booleanValue() : defaultValue;
  }

  public Integer getIntValue() {
    Integer intValue = null;
    String stringValue = getStringValue();
    if (stringValue != null) {
      try {
        intValue = new Integer(Integer.parseInt(stringValue));
      } catch (NumberFormatException e) {
      }
    }
    return intValue;
  }
  
  public int getIntValue(int defaultValue) {
    Integer intValue = getIntValue();
    return intValue != null ? intValue.intValue() : defaultValue;
  }

  public boolean equals(Object obj) {
    return value != null && (obj instanceof ActionInputConstant) && value.equals(((ActionInputConstant)obj).getValue());
  }

  public String getName() {
    return inputName;
  }

  public void setName(String name) {
    inputName = name;
  }
  
  public String getType() {
    return "";
  }

  public String getVariableName() {
    return getName();
  }


}
