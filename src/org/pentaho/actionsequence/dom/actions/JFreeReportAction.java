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
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionVariable;

public class JFreeReportAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.jfree.JFreeReportComponent"; //$NON-NLS-1$
  public static final String JFREE_COMPONENT_SHORT_NAME = "JFreeReportComponent"; //$NON-NLS-1$
  public static final String JFREE_WIZ_COMPONENT_SHORT_NAME = "ReportWizardSpecComponent"; //$NON-NLS-1$
  public static final String OUTPUT_TYPE_ELEMENT = "output-type"; //$NON-NLS-1$
  public static final String REPORT_OUTPUT_ELEMENT = "report-output"; //$NON-NLS-1$
  public static final String DRIVER_ELEMENT = "driver"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String LIVE_CONNECTION_ELEMENT = "live"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String DATA_ELEMENT = "data"; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String XSL_ELEMENT = "xsl"; //$NON-NLS-1$
  public static final String REPORT_DEFINITION_ELEMENT = "report-definition"; //$NON-NLS-1$
  public static final String REPORT_WIZ_SPEC_ELEMENT = "report-spec"; //$NON-NLS-1$
  public static final String REPORT_DATA_SOURCE_ELEMENT = "source"; //$NON-NLS-1$
  public static final String REPORT_JAR_ELEMENT = "report-jar"; //$NON-NLS-1$
  public static final String MDX_DATA_SOURCE = "mdx"; //$NON-NLS-1$
  public static final String SQL_DATA_SOURCE = "sql"; //$NON-NLS-1$
  public static final String OUTPUT_REPORT = "output-report"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_RESOURCES = new String[] {
    REPORT_DEFINITION_ELEMENT, 
    REPORT_WIZ_SPEC_ELEMENT 
  };
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    DATA_ELEMENT, 
    DRIVER_ELEMENT,
    CONNECTION_ELEMENT,
    USER_ID_ELEMENT,
    PASSWORD_ELEMENT,
    JNDI_ELEMENT,
    QUERY_ELEMENT, 
    XSL_ELEMENT, 
    OUTPUT_TYPE_ELEMENT 
  };
  
  public JFreeReportAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public JFreeReportAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(OUTPUT_TYPE_ELEMENT, "html");//$NON-NLS-1$
  }

  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getExpectedOutputs() {
    String expectedOutput = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(expectedOutput) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }
  
  public String[] getExpectedResources() {
    return EXPECTED_RESOURCES;
  }

  protected boolean accepts(Element element) {
    boolean accepts = false;
    
    if (element.getName().equals(ActionSequenceDocument.ACTION_DEFINITION_NAME)) {
      String elementComponentName = getComponentName(element);
      int index = elementComponentName.lastIndexOf("."); //$NON-NLS-1$
      if ((index >= 0) && (index < elementComponentName.length() - 1)) {
        elementComponentName = elementComponentName.substring(index + 1);
      }
      
      accepts = elementComponentName.equals(JFREE_COMPONENT_SHORT_NAME) || 
          elementComponentName.equals(JFREE_WIZ_COMPONENT_SHORT_NAME);
    }
    return accepts;
  }
  
  
  public void setConnection(String value) {
    setInputValue(CONNECTION_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setData(null);
    }
  }
  
  public String getConnection() {
    return getComponentDefinitionValue(CONNECTION_ELEMENT);
  }
  
  public void setConnectionParam(IActionVariable variable) {
    setReferencedVariable(CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setData(null);
    }
  }
  
  public ActionInput getConnectionParam() {
    return getInputParam(CONNECTION_ELEMENT);
  }
  
  public void setUserId(String value) {
    setInputValue(USER_ID_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setData(null);
    }
  }
  
  public String getUserId() {
    return getComponentDefinitionValue(USER_ID_ELEMENT);
  }
  
  public void setUserIdParam(IActionVariable variable) {
    setReferencedVariable(USER_ID_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setData(null);
    }
  }
  
  public ActionInput getUserIdParam() {
    return getInputParam(USER_ID_ELEMENT);
  }
  
  public void setDriver(String value) {
    setInputValue(DRIVER_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setData(null);
    }
  }
  
  public String getDriver() {
    return getComponentDefinitionValue(DRIVER_ELEMENT);
  }
  
  public void setDriverParam(IActionVariable variable) {
    setReferencedVariable(DRIVER_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setData(null);
    }
  }
  
  public ActionInput getDriverParam() {
    return getInputParam(DRIVER_ELEMENT);
  }
  
  public void setPassword(String value) {
    setInputValue(PASSWORD_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setData(null);
    }
  }
  
  public String getPassword() {
    return getComponentDefinitionValue(PASSWORD_ELEMENT);
  }
  
  public void setPasswordParam(IActionVariable variable) {
    setReferencedVariable(PASSWORD_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setData(null);
    }
  }
  
  public ActionInput getPasswordParam() {
    return getInputParam(PASSWORD_ELEMENT);
  }
  
  public void setJndi(String value) {
    setInputValue(JNDI_ELEMENT, value);
    if (value != null) {
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setData(null);
    }
  }
  
  public String getJndi() {
    return getComponentDefinitionValue(JNDI_ELEMENT);
  }
  
  public void setJndiParam(IActionVariable variable) {
    setReferencedVariable(JNDI_ELEMENT, variable);
    if (variable != null) {
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setData(null);
    }
  }
  
  public ActionInput getJndiParam() {
    return getInputParam(JNDI_ELEMENT);
  }
  
  public void setOutputType(String value) {
    setInputValue(OUTPUT_TYPE_ELEMENT, value);
  }
  
  public String getOutputType() {
    return getComponentDefinitionValue(OUTPUT_TYPE_ELEMENT);
  }
  
  public void setOutputTypeParam(IActionVariable variable) {
    setReferencedVariable(OUTPUT_TYPE_ELEMENT, variable);
  }
  
  public ActionInput getOutputTypeParam() {
    return getInputParam(OUTPUT_TYPE_ELEMENT);
  }

  public void setData(String value) {
    setInputValue(DATA_ELEMENT, value);
    if (value != null) {
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
      setQuery(null);
    }
  }
  
  public String getData() {
    return getComponentDefinitionValue(DATA_ELEMENT);
  }
  
  public void setDataParam(IActionVariable variable) {
    setReferencedVariable(DATA_ELEMENT, variable);
    if (variable != null) {
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
      setQuery(null);
    }
  }
  
  public ActionInput getDataParam() {
    return getInputParam(DATA_ELEMENT);
  }
  
  public void setQuery(String value) {
    setInputValue(QUERY_ELEMENT, value);
  }
  
  public String getQuery() {
    return getComponentDefinitionValue(QUERY_ELEMENT);
  }
  
  public void setQueryParam(IActionVariable variable) {
    setReferencedVariable(QUERY_ELEMENT, variable);
  }
  
  public ActionInput getQueryParam() {
    return getInputParam(QUERY_ELEMENT);
  }
  
  public void setOutputReportName(String name) {
    setOutputName(REPORT_OUTPUT_ELEMENT, name, ActionSequenceDocument.CONTENT_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      ActionOutput[] actionOutputs = getOutputParams();
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
    return getOutputPublicName(privateOutputName);
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
    ActionSequenceValidationError validationError = validateInputParam(DATA_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing report data input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Report data input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Report data input parameter is uninitialized.";
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
          validationError.errorMsg = "Report definition input parameter is unitialized.";
          break;
      }
      errors.add(validationError);
    }
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
}
