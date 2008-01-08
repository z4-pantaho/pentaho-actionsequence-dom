package org.pentaho.actionsequence.dom;

import org.pentaho.actionsequence.dom.actions.ActionDefinition;

public interface IActionIOElement extends IGenericIOElement {
  public String getMapping();
  
  /**
   * Sets the name to which the input/output is mapped. 
   * @param mapping the mapped name. If null any existing mapping is removed.
   */
  public void setMapping(String mapping);
  
  /**
   * @return the action definition to which this input/output belongs.
   */
  public ActionDefinition getActionDefinition();
}
