package org.pentaho.actionsequence.dom.actions;

import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.IActionInput;

public class VariableActionInputFilter implements IActionInputFilter {

  public boolean accepts(IActionInput actionInput) {
    return (actionInput instanceof ActionInput);
  }

}
