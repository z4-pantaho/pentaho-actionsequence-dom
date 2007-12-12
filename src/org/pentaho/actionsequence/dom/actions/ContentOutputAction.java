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
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInputVariable;

public class ContentOutputAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.ContentOutputComponent"; //$NON-NLS-1$
  public static final String CONTENT_INPUT_ELEMENT = "CONTENTOUTPUT"; //$NON-NLS-1$
  public static final String CONTENT_OUTPUT_ELEMENT = "content"; //$NON-NLS-1$
  public static final String MIME_TYPE_ELEMENT = "mime-type"; //$NON-NLS-1$
  public static final String CONTENT_INPUT = "input";
  public static final String CONTENT_OUTPUT = "output";
//  public static final String CONTENT_OUTPUT_ELEMENT = "content"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    CONTENT_INPUT_ELEMENT,
    MIME_TYPE_ELEMENT
  };

  public ContentOutputAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public ContentOutputAction() {
    super(COMPONENT_NAME);
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public void setMimeType(String value) {
    setInputValue(MIME_TYPE_ELEMENT, value);
  }
  
  public String getMimeType() {
    return getComponentDefinitionValue(MIME_TYPE_ELEMENT);
  }
  
  public void setMimeTypeParam(IActionInputVariable variable) {
    setInputParam(MIME_TYPE_ELEMENT, variable);
  }
  
  public ActionInput getMimeTypeParam() {
    return getInputParam(MIME_TYPE_ELEMENT);
  }
  
  public void setInputParam(IActionInputVariable variable) {
    setInputParam(CONTENT_INPUT_ELEMENT, variable);
  }
  
  public ActionInput getInputParam() {
    return getInputParam(CONTENT_INPUT_ELEMENT);
  }
  
  public String getOutputName() {
    return getPublicOutputName(CONTENT_OUTPUT_ELEMENT);
  }
  
  public ActionOutput getOutputParam() {
    return getOutputParam(CONTENT_OUTPUT_ELEMENT);
  }
  
  public void setOutputName(String name) {
    setOutputParam(CONTENT_OUTPUT_ELEMENT, name, ActionSequenceDocument.CONTENT_TYPE);
  }
  
  public ActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    
    ActionSequenceValidationError validationError = validateInputParam(CONTENT_INPUT_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing content input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Content input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Content input is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    validationError = validateInputParam(MIME_TYPE_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing content mime type parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Content mime type input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Content mime type input parameter is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    validationError = validateOutputParam(CONTENT_OUTPUT);
    if (validationError != null) {
      if (validationError.errorCode == ActionSequenceValidationError.OUTPUT_MISSING) {
        validationError.errorMsg = "Missing content ouput parameter.";
      }
      errors.add(validationError);
    }
    
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
}
