package org.pentaho.actionsequence.dom.actions;

import java.util.ArrayList;
import java.util.Arrays;

import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInput;

public class ActionInputTypeFilter implements IActionInputFilter {

  ArrayList types = new ArrayList();
  boolean includeConstants = false;
  
  public ActionInputTypeFilter(String[] types, boolean includeConstants) {
    if (types != null) {
      this.types.addAll(Arrays.asList(types));
    }
    this.includeConstants = includeConstants;
  }
  
  public ActionInputTypeFilter(String[] types) {
    this(types, false);
  }
  
  public ActionInputTypeFilter(String type) {
    this(new String[]{type}, false);
  }
  
  public boolean accepts(IActionInput actionInput) {
    boolean result = false;
    if (includeConstants && (actionInput instanceof ActionInputConstant)) {
      ActionInputConstant constant = (ActionInputConstant)actionInput;
      if (types.contains(ActionSequenceDocument.STRING_TYPE)) {
        result = constant.getValue() instanceof String;
      } else if (types.contains(ActionSequenceDocument.LONG_TYPE)
          || types.contains(ActionSequenceDocument.INTEGER_TYPE)
          || types.contains(ActionSequenceDocument.BIGDECIMAL_TYPE)) {
        if (constant.getValue() instanceof String) {
          try {
            Integer.parseInt(constant.getStringValue());
            result = true;
          } catch (Exception ex) {
            // Do nothing. Method will return false.
          }
        } 
      }
    } else {
      result = (actionInput instanceof ActionInput) && (types.contains(((ActionInput)actionInput).getType()));
    }
    return result;
  }

}
