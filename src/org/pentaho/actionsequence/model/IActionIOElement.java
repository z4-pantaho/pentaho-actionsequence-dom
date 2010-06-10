package org.pentaho.actionsequence.model;


public interface IActionIOElement extends IAbstractIOElement {
  public String getMapping();
  
  /**
   * Sets the name to which the input/output is mapped. 
   * @param mapping the mapped name. If null any existing mapping is removed.
   */
  public void setMapping(String mapping);
  
  /**
   * @return the action definition to which this input/output belongs.
   */
  public IActionDefinition getActionDefinition();
}

