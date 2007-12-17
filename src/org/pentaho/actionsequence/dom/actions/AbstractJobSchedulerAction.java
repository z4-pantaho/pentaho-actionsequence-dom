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
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInput;

public abstract class AbstractJobSchedulerAction extends ActionDefinition {
  public static final String COMPONENT_NAME = "org.pentaho.component.JobSchedulerComponent"; //$NON-NLS-1$
  public static final String JOB_NAME_ELEMENT = "jobName"; //$NON-NLS-1$
  public static final String JOB_ACTION_ELEMENT = "jobAction"; //$NON-NLS-1$
  public static final String JOB_NAME = "job-name"; //$NON-NLS-1$
  private static final String DEFAULT_STR = "default"; //$NON-NLS-1$
  
  protected AbstractJobSchedulerAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }
  
  protected AbstractJobSchedulerAction(String componentName) {
    super(componentName);
  }
  
  public void setJobName(IActionInput value) {
    setActionInputValue(JOB_NAME_ELEMENT, value);
  }
  
  public IActionInput getJobName() {
   return getActionInputValue(JOB_NAME_ELEMENT);
  }
  
  
  public ActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInputParam(JOB_ACTION_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing job action input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Job action input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Job action input is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    validationError = validateInputParam(JOB_NAME_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing job name input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Job name input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Job name input is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
}
