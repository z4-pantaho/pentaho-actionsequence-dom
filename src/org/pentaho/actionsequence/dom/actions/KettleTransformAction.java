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
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputSource;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionResource;
import org.pentaho.actionsequence.dom.IActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionSequenceResource;
import org.pentaho.actionsequence.dom.IActionSequenceValidationError;

public class KettleTransformAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "KettleComponent"; //$NON-NLS-1$
  public static final String TRANSFORMATION_STEP_ELEMENT = "importstep" ; //$NON-NLS-1$
  public static final String TRANSFORMATION_FILE_ELEMENT = "transformation-file" ; //$NON-NLS-1$
  public static final String TRANSFORMATION_OUTPUT_ELEMENT = "transformation-output"; //$NON-NLS-1$
  public static final String REPOSITORY_DIRECTORY = "directory"; //$NON-NLS-1$
  public static final String REPOSITORY_TRANSFORMATION = "transformation"; //$NON-NLS-1$
  public static final String[] EXPECTED_RESOURCES = new String[]{TRANSFORMATION_FILE_ELEMENT};
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  public static final String NULL_MAPPING = "NULL_MAPPING";
  public static final String LOGGING_LEVEL = "logging-level";
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    TRANSFORMATION_STEP_ELEMENT, REPOSITORY_DIRECTORY, REPOSITORY_TRANSFORMATION,LOGGING_LEVEL
  };

  protected static final String[] EXPECTED_OUTPUTS = new String[] {
    TRANSFORMATION_OUTPUT_ELEMENT
  };
  
  public KettleTransformAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public KettleTransformAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    addResource(TRANSFORMATION_FILE_ELEMENT);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public static boolean accepts(Element element) {
    boolean result = false;
    if (ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME)) {
      result = (element.selectSingleNode(ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + TRANSFORMATION_FILE_ELEMENT) != null) //$NON-NLS-1$
        || (element.selectSingleNode(ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + REPOSITORY_TRANSFORMATION) != null) //$NON-NLS-1$
        || (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + REPOSITORY_TRANSFORMATION) != null); //$NON-NLS-1$
    }
    return result; 
  }
  
  public String[] getReservedOutputNames() {
    return EXPECTED_OUTPUTS;
  }
  
  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }
  
  public void setTransformation(IActionInputSource value) {
    setActionInputValue(REPOSITORY_TRANSFORMATION, value);
  }
  
  public IActionInput getTransformation() {
    return getInput(REPOSITORY_TRANSFORMATION);
  }
  
  public void setLoggingLevel(IActionInputSource value) {
	    setActionInputValue(LOGGING_LEVEL, value);
	  }
	  
  public IActionInput getLoggingLevel() {
    return getInput(LOGGING_LEVEL);
  }
  
  public void setDirectory(IActionInputSource value) {
    setActionInputValue(REPOSITORY_DIRECTORY, value);
  }
  
  public IActionInput getDirectory() {
    return getInput(REPOSITORY_DIRECTORY);
  }
  
  public void setImportstep(IActionInputSource value) {
    setActionInputValue(TRANSFORMATION_STEP_ELEMENT, value);
  }
  
  public IActionInput getImportstep() {
    return getInput(TRANSFORMATION_STEP_ELEMENT);
  }
  
  public void setOutputResultSet(String publicOutputName) {
    setOutput(TRANSFORMATION_OUTPUT_ELEMENT, publicOutputName, ActionSequenceDocument.RESULTSET_TYPE);
  }
  
  public IActionOutput getOutputResultSet() {
    return getOutput(TRANSFORMATION_OUTPUT_ELEMENT);
  }
  
  public IActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInput(REPOSITORY_DIRECTORY);
    if (validationError == null) {
      validationError = validateResource(TRANSFORMATION_FILE_ELEMENT);
      if (validationError != null) {
        switch (validationError.errorCode) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing transformation file location input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Transformation file location input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Transformation file location input parameter is unitialized.";
            break;
        }
        errors.add(validationError);
      }
    } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_MISSING) {
      validationError = validateInput(TRANSFORMATION_FILE_ELEMENT);
      if (validationError != null) {
        switch (validationError.errorCode) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing transformation file location input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Transformation file location input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Transformation file location input parameter is uninitialized.";
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
  
  public IActionResource setTransformationFile(URI uri, String mimeType) {
    IActionResource actionResource = getResource(TRANSFORMATION_FILE_ELEMENT);
    // We never want to get rid of the kettle transformation resource element. 
    // That's what's used to differentiate a kettle transformation action from a kettle job action.
    // If the uri is null we'll either delete the action sequence resource that is referenced
    // of map the resource to an invalid name.
    if (uri == null) {
      actionResource = getResource(TRANSFORMATION_FILE_ELEMENT);
      if (actionResource != null) {
        IActionSequenceDocument actionSequenceDocument = getDocument();
        IActionSequenceResource actionSequenceResource = actionSequenceDocument.getResource(actionResource.getPublicName());
        IActionResource[] actionResources = actionSequenceDocument.getReferencesTo(actionSequenceResource);
        if ((actionResources.length == 1) && actionResources[0].equals(actionResource)) {
          actionSequenceResource.delete();
        } else {
          actionResource.setMapping(NULL_MAPPING);
        }
      }
      actionResource = null;
    } else {
      actionResource = setResourceUri(TRANSFORMATION_FILE_ELEMENT, uri, mimeType);
    }    
    return actionResource;
  }
  
  public IActionResource getTransformationFile() {
    IActionResource actionResource = getResource(TRANSFORMATION_FILE_ELEMENT);
    if ((actionResource != null) && NULL_MAPPING.equals(actionResource.getMapping())) {
      actionResource = null;
    }
    return actionResource;
  }
  
}
