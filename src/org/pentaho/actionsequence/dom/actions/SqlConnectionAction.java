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
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceResourceDom;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInputValueProvider;
import org.pentaho.actionsequence.dom.IActionInputVariable;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionSequenceValidationError;

public class SqlConnectionAction extends ActionDefinition {

  public static final String QUERY_RESULT_OUTPUT_NAME = "query-result"; //$NON-NLS-1$
  
  public static final String COMPONENT_NAME = "org.pentaho.component.SQLLookupRule"; //$NON-NLS-1$
  public static final String DRIVER_ELEMENT = "driver"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String DEFAULT_CONNECTION_NAME = "shared_sql_connection"; //$NON-NLS-1$
  public static final String DB_URL_NAME = "db-url"; //$NON-NLS-1$
  public static final String OUTPUT_CONNECTION = "output-connection"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    DRIVER_ELEMENT,
    CONNECTION_ELEMENT,
    USER_ID_ELEMENT,
    PASSWORD_ELEMENT, 
    JNDI_ELEMENT, 
  };

  public SqlConnectionAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public SqlConnectionAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setJndi(new ActionInputConstant("")); //$NON-NLS-1$
    setOutputConnection(DEFAULT_CONNECTION_NAME);
  }
  
  public static boolean accepts(Element element) {
    boolean result = false;
    if (ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME)) {
      Element connectionOutput = (Element)element.selectSingleNode(ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/" + PREPARED_COMPONENT_ELEMENT); //$NON-NLS-1$
      result = (connectionOutput != null)
        && ActionSequenceDocument.SQL_CONNECTION_TYPE.equals(connectionOutput.attributeValue(ActionSequenceResourceDom.TYPE_NAME));
    }
    return result;
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public String[] getReservedOutputNames() {
    return new String[]{PREPARED_COMPONENT_ELEMENT};
  }
  
  public void setDbUrl(IActionInputValueProvider value) {
    setActionInputValue(CONNECTION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
    }
  }
  
  public IActionInputValueProvider getDbUrl() {
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
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
    }
  }
  
  public IActionInputValueProvider getJndi() {
    return getActionInputValue(JNDI_ELEMENT);
  }
  
  
  public void setOutputConnection(String publicOutputName) {
    setOutputParam(PREPARED_COMPONENT_ELEMENT, publicOutputName, ActionSequenceDocument.SQL_CONNECTION_TYPE);
  }
  
  public IActionOutput getOutputConnection() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }
  
  public IActionSequenceValidationError[] validate() {
    
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInputParam(CONNECTION_ELEMENT);
    if (validationError == null) {
      validationError = validateInputParam(DRIVER_ELEMENT);
      if (validationError != null) {
        switch (validationError.errorCode) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing database driver input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Database driver input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Database driver input parameter is uninitialized.";
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
      validationError.errorMsg = "Database connection input parameter references unknown variable.";
      errors.add(validationError);
    } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED) {
      validationError.errorMsg = "Database connection input parameter is uninitialized.";
      errors.add(validationError);
    }
    
    validationError = validateOutputParam(PREPARED_COMPONENT_ELEMENT);
    if (validationError != null) {
      if (validationError.errorCode == ActionSequenceValidationError.OUTPUT_MISSING) {
        validationError.errorMsg = "Missing output connection name.";
      }
      errors.add(validationError);
    }

    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
}
