package org.pentaho.actionsequence.dom;

public interface IActionInputVariable extends IActionInputValueProvider, IGenericIOElement {
  public String getVariableName();
  public String getType();
}
