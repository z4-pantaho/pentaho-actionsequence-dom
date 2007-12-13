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

import java.net.URI;
import java.util.ArrayList;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionResource;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceResource;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInput;

public class KettleJobAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "KettleComponent"; //$NON-NLS-1$
  
  public static final String JOB_FILE_ELEMENT = "job-file"; //$NON-NLS-1$
  public static final String REPOSITORY_DIRECTORY = "directory"; //$NON-NLS-1$
  public static final String REPOSITORY_JOB = "job"; //$NON-NLS-1$
  
  public static final String[] EXPECTED_RESOURCES = new String[]{JOB_FILE_ELEMENT};
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    REPOSITORY_DIRECTORY, REPOSITORY_JOB
  };
  
  public KettleJobAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public KettleJobAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    addResourceParam(JOB_FILE_ELEMENT);
  }

  public static boolean accepts(Element element) {
    boolean result = false;
    if (ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME)) {
      result = (element.selectSingleNode(ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + JOB_FILE_ELEMENT) != null) //$NON-NLS-1$
        || (element.selectSingleNode(ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + REPOSITORY_JOB) != null) //$NON-NLS-1$
        || (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + REPOSITORY_JOB) != null); //$NON-NLS-1$
    }
    return result;
  }

  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public void setJob(IActionInput value) {
    setActionInputValue(REPOSITORY_JOB, value);
  }
  
  public IActionInput getJob() {
    return getActionInputValue(REPOSITORY_JOB);
  }
  
  public void setDirectory(IActionInput value) {
    setActionInputValue(REPOSITORY_DIRECTORY, value);
  }
  
  public IActionInput getDirectory() {
    return getActionInputValue(REPOSITORY_DIRECTORY);
  }
  
  public ActionSequenceValidationError[] validate() {
    
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInputParam(REPOSITORY_DIRECTORY);
    if (validationError == null) {
      validationError = validateResourceParam(JOB_FILE_ELEMENT);
      if (validationError != null) {
        switch (validationError.errorCode) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing job file location input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Job file location input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Job file location input parameter is uninitialized.";
            break;
        }
        errors.add(validationError);
      }
    } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_MISSING) {
      validationError = validateInputParam(JOB_FILE_ELEMENT);
      if (validationError != null) {
        switch (validationError.errorCode) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing job file location input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Job file location input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Job file location input parameter is uninitialized.";
            break;
        }
        errors.add(validationError);
      }
    } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR) {
      validationError.errorMsg = "Repository directory input parameter references unknown variable.";
      errors.add(validationError);
    } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED) {
      validationError.errorMsg = "Repository directory input parameter is uninitialized.";
      errors.add(validationError);
    } else {
      errors.add(validationError);
    }
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
  
  public ActionResource setJobFile(URI uri, String mimeType) {
    // We never want to get rid of the kettle job resource element. 
    // That's what's used to differentiate a kettle transformation action from a kettle job action.
    // If the uri is null we'll either delete the action sequence resource that is referenced
    // of map the resource to an invalid name.
    ActionResource actionResource = null;
    if (uri == null) {
      actionResource = getResourceParam(JOB_FILE_ELEMENT);
      if (actionResource != null) {
        ActionSequenceDocument actionSequenceDocument = getDocument();
        ActionSequenceResource actionSequenceResource = actionSequenceDocument.getResource(actionResource.getPublicName());
        ActionResource[] actionResources = actionSequenceDocument.getReferencesTo(actionSequenceResource);
        if ((actionResources.length == 1) && actionResources[0].equals(actionResource)) {
          actionSequenceResource.delete();
        } else {
          actionResource.setMapping("NULL_MAPPING");
        }
      }
      actionResource = null;
    } else {
      actionResource = setResourceUri(JOB_FILE_ELEMENT, uri, mimeType);
    }
    return actionResource;
  }
  
  public ActionResource getJobFile() {
    return getResourceParam(JOB_FILE_ELEMENT);
  }
  
}
