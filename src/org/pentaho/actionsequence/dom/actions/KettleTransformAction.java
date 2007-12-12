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
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionResource;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceResource;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInputVariable;

public class KettleTransformAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "KettleComponent"; //$NON-NLS-1$
  public static final String TRANSFORMATION_STEP_ELEMENT = "importstep" ; //$NON-NLS-1$
  public static final String TRANSFORMATION_FILE_ELEMENT = "transformation-file" ; //$NON-NLS-1$
  public static final String TRANSFORMATION_OUTPUT_ELEMENT = "transformation-output"; //$NON-NLS-1$
  public static final String REPOSITORY_DIRECTORY = "directory"; //$NON-NLS-1$
  public static final String REPOSITORY_TRANSFORMATION = "transformation"; //$NON-NLS-1$
  public static final String[] EXPECTED_RESOURCES = new String[]{TRANSFORMATION_FILE_ELEMENT};
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    TRANSFORMATION_STEP_ELEMENT, REPOSITORY_DIRECTORY, REPOSITORY_TRANSFORMATION
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
    addResourceParam(TRANSFORMATION_FILE_ELEMENT);
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
  
  public void setTransformation(String value) {
    setInputValue(REPOSITORY_TRANSFORMATION, value);
  }
  
  public String getTransformation() {
    return getComponentDefinitionValue(REPOSITORY_TRANSFORMATION);
  }
  
  public void setTransformationParam(IActionInputVariable variable) {
    setInputParam(REPOSITORY_TRANSFORMATION, variable);
  }
  
  public ActionInput getTransformationParam() {
    return getInputParam(REPOSITORY_TRANSFORMATION);
  }
  
  public void setDirectory(String value) {
    setInputValue(REPOSITORY_DIRECTORY, value);
  }
  
  public String getDirectory() {
    return getComponentDefinitionValue(REPOSITORY_DIRECTORY);
  }
  
  public void setDirectoryParam(IActionInputVariable variable) {
    setInputParam(REPOSITORY_DIRECTORY, variable);
  }
  
  public ActionInput getDirectoryParam() {
    return getInputParam(REPOSITORY_DIRECTORY);
  }
  
  public void setImportstep(String value) {
    setInputValue(TRANSFORMATION_STEP_ELEMENT, value);
  }
  
  public String getImportstep() {
    return getComponentDefinitionValue(TRANSFORMATION_STEP_ELEMENT);
  }
  
  public void setImportstepParam(IActionInputVariable variable) {
    setInputParam(TRANSFORMATION_STEP_ELEMENT, variable);
  }
  
  public ActionInput getImportstepParam() {
    return getInputParam(TRANSFORMATION_STEP_ELEMENT);
  }
  
  public void setOutputResultSetName(String name) {
    setOutputParam(TRANSFORMATION_OUTPUT_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE);
  }
  
  public String getOutputResultSetName() {
    return getPublicOutputName(TRANSFORMATION_OUTPUT_ELEMENT);
  }
  
  public ActionOutput getOutputResultSetParam() {
    return getOutputParam(TRANSFORMATION_OUTPUT_ELEMENT);
  }
  
  public ActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInputParam(REPOSITORY_DIRECTORY);
    if (validationError == null) {
      validationError = validateResourceParam(TRANSFORMATION_FILE_ELEMENT);
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
      validationError = validateInputParam(TRANSFORMATION_FILE_ELEMENT);
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
  
  public ActionResource setTransformationFile(URI uri, String mimeType) {
    ActionResource actionResource = getResourceParam(TRANSFORMATION_FILE_ELEMENT);
    // We never want to get rid of the kettle transformation resource element. 
    // That's what's used to differentiate a kettle transformation action from a kettle job action.
    // If the uri is null we'll either delete the action sequence resource that is referenced
    // of map the resource to an invalid name.
    if (uri == null) {
      actionResource = getResourceParam(TRANSFORMATION_FILE_ELEMENT);
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
      actionResource = setResourceUri(TRANSFORMATION_FILE_ELEMENT, uri, mimeType);
    }    
    return actionResource;
  }
  
  public ActionResource getTransformationFile() {
    ActionResource actionResource = getResourceParam(TRANSFORMATION_FILE_ELEMENT);
    if (actionResource.getMapping().equals("NULL_MAPPING")) {
      actionResource = null;
    }
    return actionResource;
  }
  
}
