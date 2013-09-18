package org.pentaho.actionsequence.dom.actions;

import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.IActionInput;

public class ConstantActionInputFilter implements IActionInputFilter {

  public boolean accepts(IActionInput actionInput) {
    return (actionInput instanceof ActionInputConstant);
  }

}
