package org.pentaho.actionsequence.dom;

import org.dom4j.Element;

public class SimpleActionInputVariable implements IActionInputVariable {

  public String name;
  public String type;
  
  public SimpleActionInputVariable() {
  }
  
  public SimpleActionInputVariable(String name, String type) {
    this.name = name;
    this.type = type;
  }
  
  public String getType() {
    return type;
  }

  public String getVariableName() {
    return name;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setVariableName(String name) {
    this.name = name;
  }

  public Boolean getBooleanValue() {
    return null;
  }

  public boolean getBooleanValue(boolean defaultValue) {
    return defaultValue;
  }

  public Integer getIntValue() {
    return null;
  }

  public int getIntValue(int defaultValue) {
    return defaultValue;
  }

  public String getStringValue() {
    return null;
  }

  public String getStringValue(boolean replaceParamReferences, String defaultValue) {
    return defaultValue;
  }

  public String getStringValue(boolean replaceParamReferences) {
    return null;
  }

  public String getStringValue(String defaultValue) {
    return defaultValue;
  }

  public Object getValue() {
    return null;
  }

  public String getName() {
   return name;
  }

  public void setName(String ioName) {
    name = ioName;
    
  }

  public void delete() {
  }

  public ActionSequenceDocument getDocument() {
    return null;
  }

  public Element getElement() {
    return null;
  }
}
