package org.pentaho.actionsequence.dom;

import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

// This class is used to set an action input to a constant value.
public class ActionInputConstant implements IActionInputValueProvider {
  Object value;
  IActionParameterMgr actionParameterMgr;
  
  public ActionInputConstant(Object value) {
    this(value, null);
  }

  public ActionInputConstant(String value) {
    this(value, null);
  }
  
  public ActionInputConstant(Boolean value) {
    this(value, null);
  }
  
  public ActionInputConstant(boolean value) {
    this(value, null);
  }
  
  public ActionInputConstant(Integer value) {
    this(value, null);
  }
  
  public ActionInputConstant(int value) {
    this(value, null);
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(Object value, IActionParameterMgr actionParameterMgr) {
    this.value = value;
    this.actionParameterMgr = actionParameterMgr;
  }

  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(String value, IActionParameterMgr actionParameterMgr) {
    this.value = value;
    this.actionParameterMgr = actionParameterMgr;
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(Boolean value, IActionParameterMgr actionParameterMgr) {
    this.value = value;
    this.actionParameterMgr = actionParameterMgr;
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(boolean value, IActionParameterMgr actionParameterMgr) {
    this.value = new Boolean(value);
    this.actionParameterMgr = actionParameterMgr;
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(Integer value, IActionParameterMgr actionParameterMgr) {
    this.value = value;
    this.actionParameterMgr = actionParameterMgr;
  }
  
  // Not intended for general use. Use one parameter option.
  public ActionInputConstant(int value, IActionParameterMgr actionParameterMgr) {
    this.value = new Integer(value);
    this.actionParameterMgr = actionParameterMgr;
  }
  
  public Object getValue() {
    return value;
  }
  
  public String getStringValue() {
    return getStringValue(true, null);
  }
  
  public String getStringValue(boolean replaceParamReferences) {
    return getStringValue(replaceParamReferences, null);
  }
  
  public String getStringValue(boolean replaceParamReferences, String defaultValue) {
    Object theValue = value;
    if (replaceParamReferences && (actionParameterMgr != null) && (theValue != null)) {
      theValue = actionParameterMgr.replaceParameterReferences(theValue.toString());
    }
    return theValue != null ? theValue.toString() : defaultValue;
  }

  public String getStringValue(String defaultValue) {
    return getStringValue(true, defaultValue);
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

}
