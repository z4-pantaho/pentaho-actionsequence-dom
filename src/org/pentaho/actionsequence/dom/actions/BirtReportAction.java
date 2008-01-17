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
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionResource;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInputValueProvider;

public class BirtReportAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.birt.BIRTReportComponent"; //$NON-NLS-1$
  public static final String OUTPUT_TYPE_ELEMENT = "output-type"; //$NON-NLS-1$
  public static final String REPORT_OUTPUT_ELEMENT = "report-output"; //$NON-NLS-1$
  public static final String REPORT_DEFINITION_ELEMENT = "report-definition"; //$NON-NLS-1$
  public static final String OUTPUT_REPORT = "output-report"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {OUTPUT_TYPE_ELEMENT};
  public static final String[] EXPECTED_RESOURCES = new String[]{REPORT_DEFINITION_ELEMENT};
  
  public BirtReportAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public BirtReportAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(OUTPUT_TYPE_ELEMENT, "html");//$NON-NLS-1$
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public String[] getReservedOutputNames() {
    String privateOutputName = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(privateOutputName) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        privateOutputName = actionOutputs[0].getName();
      }
    }
    return new String[]{privateOutputName};
  }
  
  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }
  
  public void setOutputType(IActionInputValueProvider value) {
    setActionInputValue(OUTPUT_TYPE_ELEMENT, value);
  }
  
  public IActionInputValueProvider getOutputType() {
    return getActionInputValue(OUTPUT_TYPE_ELEMENT);
  }
  
  public void setOutputReport(String publicOutputName) {
    setOutputParam(REPORT_OUTPUT_ELEMENT, publicOutputName, ActionSequenceDocument.CONTENT_TYPE);
    if ((publicOutputName != null) && (publicOutputName.trim().length() > 0)) {
      ActionOutput[] actionOutputs = getAllOutputParams();
      for (int i = 0; i < actionOutputs.length; i++) {
        if (actionOutputs[i].getType().equals(ActionSequenceDocument.CONTENT_TYPE)
            && !actionOutputs[i].getName().equals(REPORT_OUTPUT_ELEMENT)) {
          actionOutputs[i].delete();
        }
      }
    }
  }
  
  public ActionOutput getOutputReport() {
    ActionOutput actionOutput = getOutputParam(REPORT_OUTPUT_ELEMENT);
    
    if (actionOutput ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        actionOutput = actionOutputs[0];
      }
    }
    return actionOutput;
  }
  
  public ActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateResourceParam(REPORT_DEFINITION_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing report definition input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Report definition input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Report definition input parameter is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    validationError = validateInputParam(OUTPUT_TYPE_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing report format input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Report format input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Report format input parameter is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
        
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  public ActionResource setReportDefinition(URI uri, String mimeType) {
    return setResourceUri(REPORT_DEFINITION_ELEMENT, uri, mimeType);
  }
  
  public ActionResource getReportDefinition() {
    return getResourceParam(REPORT_DEFINITION_ELEMENT);
  }
}
