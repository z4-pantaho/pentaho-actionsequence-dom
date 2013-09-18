package org.pentaho.actionsequence.dom.actions;

import org.pentaho.actionsequence.dom.IActionInput;

public interface IActionInputFilter {
  public boolean accepts(IActionInput actionInput);
}
