/*
 * Copyright 2006 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the Mozilla Public License, Version 1.1, or any later version. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.mozilla.org/MPL/MPL-1.1.txt. The Original Code is the Pentaho 
 * BI Platform.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
*/
package org.pentaho.actionsequence.dom.actions;

import java.util.ArrayList;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionDefinition;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionVariable;

public class JavascriptAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.JavascriptRule"; //$NON-NLS-1$
  public static final String SCRIPT_ELEMENT = "script"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    SCRIPT_ELEMENT 
  };

  public JavascriptAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public JavascriptAction() {
    super(COMPONENT_NAME);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public void setScript(String value) {
    setInputValue(SCRIPT_ELEMENT, value);
  }
  
  public String getScript() {
    return getComponentDefinitionValue(SCRIPT_ELEMENT);
  }
  
  public void setScriptParam(IActionVariable variable) {
    setReferencedVariable(SCRIPT_ELEMENT, variable);
  }
  
  public ActionInput getScriptParam() {
    return getInputParam(SCRIPT_ELEMENT);
  }
  
  public ActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInputParam(SCRIPT_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing javascript input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Javascript input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Javascript input parameter is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    if (getOutputParams().length == 0) {
      validationError = new ActionSequenceValidationError();
      validationError.errorCode = ActionSequenceValidationError.OUTPUT_MISSING;
      validationError.errorMsg = "Missing javascript output parameter.";
      validationError.actionDefinition = this;
      errors.add(validationError);
    }
    
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
}
