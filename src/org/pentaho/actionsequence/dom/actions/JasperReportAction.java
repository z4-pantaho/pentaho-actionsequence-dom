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
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInputValueProvider;
import org.pentaho.actionsequence.dom.IActionInputVariable;

public class JasperReportAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.jasper.JasperReportsComponent"; //$NON-NLS-1$
  public static final String OUTPUT_TYPE_ELEMENT = "output-type"; //$NON-NLS-1$
  public static final String REPORT_OUTPUT_ELEMENT = "report-output"; //$NON-NLS-1$
  public static final String DRIVER_ELEMENT = "driver"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String REPORT_DEFINITION_ELEMENT = "report-definition"; //$NON-NLS-1$
  public static final String OUTPUT_REPORT = "output-report"; //$NON-NLS-1$
  

  public static final String[] EXPECTED_RESOURCES = new String[]{REPORT_DEFINITION_ELEMENT};
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    OUTPUT_TYPE_ELEMENT,
    DRIVER_ELEMENT,
    CONNECTION_ELEMENT,
    USER_ID_ELEMENT,
    PASSWORD_ELEMENT,
    JNDI_ELEMENT
  };
  
  public JasperReportAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public JasperReportAction() {
    super(COMPONENT_NAME);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(OUTPUT_TYPE_ELEMENT, "html");//$NON-NLS-1$
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getReservedOutputNames() {
    String expectedOutput = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(expectedOutput) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }
  
  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }
  
  public void setConnection(IActionInputValueProvider value) {
    setActionInputValue(CONNECTION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
    }
  }
  
  public IActionInputValueProvider getConnection() {
    return getActionInputValue(CONNECTION_ELEMENT);
  }
  
  public void setUserId(IActionInputValueProvider value) {
    setActionInputValue(USER_ID_ELEMENT, value);
		if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
    }
  }
  
  public IActionInputValueProvider getUserId() {
    return getActionInputValue(USER_ID_ELEMENT);
  }

  public void setDriver(IActionInputValueProvider value) {
    setActionInputValue(DRIVER_ELEMENT, value);
		if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
    }
  }
  
  public IActionInputValueProvider getDriver() {
    return getActionInputValue(DRIVER_ELEMENT);
  }
  
  public void setPassword(IActionInputValueProvider value) {
    setActionInputValue(PASSWORD_ELEMENT, value);
		if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {

      setJndi(null);
    }
  }
  
  public IActionInputValueProvider getPassword() {
    return getActionInputValue(PASSWORD_ELEMENT);
  }

  public void setJndi(IActionInputValueProvider value) {
    setActionInputValue(JNDI_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
    }
  }
  
  public IActionInputValueProvider getJndi() {
    return getActionInputValue(JNDI_ELEMENT);
  }
  
  public void setOutputType(IActionInputValueProvider value) {
    setActionInputValue(OUTPUT_TYPE_ELEMENT, value);
  }
  
  public IActionInputValueProvider getOutputType() {
    return getActionInputValue(OUTPUT_TYPE_ELEMENT);
  }
  
  public void setOutputTypeParam(IActionInputVariable variable) {
    setInputParam(OUTPUT_TYPE_ELEMENT, variable);
  }
  
  public ActionInput getOutputTypeParam() {
    return getInputParam(OUTPUT_TYPE_ELEMENT);
  }
  
  public void setOutputReportName(String name) {
    setOutputParam(REPORT_OUTPUT_ELEMENT, name, ActionSequenceDocument.CONTENT_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      ActionOutput[] actionOutputs = getAllOutputParams();
      for (int i = 0; i < actionOutputs.length; i++) {
        if (actionOutputs[i].getType().equals(ActionSequenceDocument.CONTENT_TYPE)
            && !actionOutputs[i].getName().equals(REPORT_OUTPUT_ELEMENT)) {
          actionOutputs[i].delete();
        }
      }
    }
  }
  
  public String getOutputReportName() {
    String privateOutputName = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(privateOutputName) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        privateOutputName = actionOutputs[0].getName();
      }
    }
    return getPublicOutputName(privateOutputName);
  }
  
  public ActionOutput getOutputReportParam() {
    String privateOutputName = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(privateOutputName) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        privateOutputName = actionOutputs[0].getName();
      }
    }
    return getOutputParam(REPORT_OUTPUT_ELEMENT);
  }
  
  public ActionSequenceValidationError[] validate() {
    
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInputParam(DRIVER_ELEMENT);
    if (validationError == null) {
      validationError = validateInputParam(CONNECTION_ELEMENT);
      if (validationError != null) {
        switch (validationError.errorCode) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing database connection input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Database connection input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Database connection input parameter is uninitialized.";
            break;
        }
        errors.add(validationError);
      }
      
      validationError = validateInputParam(USER_ID_ELEMENT);
      if (validationError != null) {
        switch (validationError.errorCode) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing database login input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Database login input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Database login input parameter is uninitialized.";
            break;
        }
        errors.add(validationError);
      }
    } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_MISSING) {
      validationError = validateInputParam(JNDI_ELEMENT);
      if (validationError != null) {
        switch (validationError.errorCode) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing database connection input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Database connection input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Database connection input parameter is uninitialized.";
            break;
        }
        errors.add(validationError);
      }
    } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR) {
      validationError.errorMsg = "Database driver input parameter references unknown variable.";
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
    
    validationError = validateResourceParam(REPORT_DEFINITION_ELEMENT);
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
    
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
  
  public ActionResource setReportDefinition(URI uri, String mimeType) {
    return setResourceUri(REPORT_DEFINITION_ELEMENT, uri, mimeType);
  }
  
  public ActionResource getReportDefinition() {
    return getResourceParam(REPORT_DEFINITION_ELEMENT);
  }
}
