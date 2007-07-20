package org.pentaho.actionsequence.dom;

public class SimpleActionInputVariable implements IActionVariable {

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
}
