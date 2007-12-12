package org.pentaho.actionsequence.dom;

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
    throw new UnsupportedOperationException();
  }

  public boolean getBooleanValue(boolean defaultValue) {
    throw new UnsupportedOperationException();
  }

  public Integer getIntValue() {
    throw new UnsupportedOperationException();
  }

  public int getIntValue(int defaultValue) {
    throw new UnsupportedOperationException();
  }

  public String getStringValue() {
    throw new UnsupportedOperationException();
  }

  public String getStringValue(boolean replaceParamReferences, String defaultValue) {
    throw new UnsupportedOperationException();
  }

  public String getStringValue(boolean replaceParamReferences) {
    throw new UnsupportedOperationException();
  }

  public String getStringValue(String defaultValue) {
    throw new UnsupportedOperationException();
  }

  public Object getValue() {
    throw new UnsupportedOperationException();
  }
}
