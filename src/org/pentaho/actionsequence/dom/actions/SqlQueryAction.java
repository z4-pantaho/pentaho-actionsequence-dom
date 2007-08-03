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

public class SqlQueryAction extends ActionDefinition {


  public static final String QUERY_RESULT_OUTPUT_NAME = "query-result"; //$NON-NLS-1$
  
  public static final String COMPONENT_NAME = "org.pentaho.component.SQLLookupRule"; //$NON-NLS-1$
  public static final String DRIVER_ELEMENT = "driver"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String LIVE_CONNECTION_ELEMENT = "live"; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String QUERY_RESULT_ELEMENT = "query-result"; //$NON-NLS-1$
  public static final String OUTPUT_NAME_ELEMENT = "output-name"; //$NON-NLS-1$
  public static final String DEFAULT_QUERY_RESULTS_NAME = "query_result"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String SQL_CONNECTION = "sql-connection"; //$NON-NLS-1$
  public static final String DB_URL_NAME = "db-url"; //$NON-NLS-1$
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  public static final String OUTPUT_PREPARED_STATEMENT = "output-prepared_statement"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    DRIVER_ELEMENT,
    CONNECTION_ELEMENT,
    USER_ID_ELEMENT,
    PASSWORD_ELEMENT, 
    JNDI_ELEMENT, 
    QUERY_ELEMENT,
    LIVE_CONNECTION_ELEMENT
  };

  public SqlQueryAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public SqlQueryAction() {
    super(COMPONENT_NAME);
  }
  
  protected boolean accepts(Element element) {
    return super.accepts(element)
    && ((element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + QUERY_ELEMENT) != null) //$NON-NLS-1$
        || (element.selectSingleNode(ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + QUERY_ELEMENT) != null)); //$NON-NLS-1$
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setJndi(""); //$NON-NLS-1$
    setQuery(""); //$NON-NLS-1$
    setOutputResultSetName(DEFAULT_QUERY_RESULTS_NAME);
    setLive(true);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }

  public String[] getExpectedOutputs() {
    String expectedOutput = QUERY_RESULT_ELEMENT;
    String compDefVal = getComponentDefinitionValue(OUTPUT_NAME_ELEMENT);
    if (compDefVal != null) {
      expectedOutput = compDefVal;
    } else if (getOutputParam(expectedOutput) == null) {
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.RESULTSET_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }
  
  public void setDbUrl(String value) {
    setInputValue(CONNECTION_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setSqlConnectionParam(null);
    }
  }
  
  public String getDbUrl() {
    return getComponentDefinitionValue(CONNECTION_ELEMENT);
  }
  
  public void setDbUrlParam(IActionVariable variable) {
    setReferencedVariable(CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setSqlConnectionParam(null);
    }
  }
  
  public ActionInput getDbUrlParam() {
    return getInputParam(CONNECTION_ELEMENT);
  }
  
  public void setUserId(String value) {
    setInputValue(USER_ID_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setSqlConnectionParam(null);
    }
  }
  
  public String getUserId() {
    return getComponentDefinitionValue(USER_ID_ELEMENT);
  }
  
  public void setUserIdParam(IActionVariable variable) {
    setReferencedVariable(USER_ID_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setSqlConnectionParam(null);
    }
  }
  
  public ActionInput getUserIdParam() {
    return getInputParam(USER_ID_ELEMENT);
  }
  
  public void setDriver(String value) {
    setInputValue(DRIVER_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setSqlConnectionParam(null);
    }
  }
  
  public String getDriver() {
    return getComponentDefinitionValue(DRIVER_ELEMENT);
  }
  
  public void setDriverParam(IActionVariable variable) {
    setReferencedVariable(DRIVER_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setSqlConnectionParam(null);
    }
  }
  
  public ActionInput getDriverParam() {
    return getInputParam(DRIVER_ELEMENT);
  }
  
  public void setPassword(String value) {
    setInputValue(PASSWORD_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setSqlConnectionParam(null);
    }
  }
  
  public String getPassword() {
    return getComponentDefinitionValue(PASSWORD_ELEMENT);
  }
  
  public void setPasswordParam(IActionVariable variable) {
    setReferencedVariable(PASSWORD_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setSqlConnectionParam(null);
    }
  }
  
  public ActionInput getPasswordParam() {
    return getInputParam(PASSWORD_ELEMENT);
  }
  
  public void setJndi(String value) {
    setInputValue(JNDI_ELEMENT, value);
    if (value != null) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
      setSqlConnectionParam(null);
    }
  }
  
  public String getJndi() {
    return getComponentDefinitionValue(JNDI_ELEMENT);
  }
  
  public void setJndiParam(IActionVariable variable) {
    setReferencedVariable(JNDI_ELEMENT, variable);
    if (variable != null) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
      setSqlConnectionParam(null);
    }
  }
  
  public ActionInput getJndiParam() {
    return getInputParam(JNDI_ELEMENT);
  }
  
  public void setLive(boolean value) {
    setInputValue(LIVE_CONNECTION_ELEMENT, value ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getLive() {
    String value = getComponentDefinitionValue(LIVE_CONNECTION_ELEMENT);
    return (value != null) && value.trim().toLowerCase().equals("true"); //$NON-NLS-1$
  }
  
  public void setLiveParam(IActionVariable variable) {
    setReferencedVariable(LIVE_CONNECTION_ELEMENT, variable);
  }
  
  public ActionInput getLiveParam() {
    return getInputParam(LIVE_CONNECTION_ELEMENT);
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
  
  public void setOutputResultSetName(String name) {
    setOutputName(QUERY_RESULT_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputPreparedStatementName(null);
    }
  }
  
  public String getOutputResultSetName() {
    return getOutputPublicName(QUERY_RESULT_ELEMENT);
  }
  
  public ActionOutput getOutputResultSetParam() {
    return getOutputParam(QUERY_RESULT_ELEMENT);
  }
  
  public void setOutputPreparedStatementName(String name) {
    setOutputName(PREPARED_COMPONENT_ELEMENT, name, ActionSequenceDocument.SQL_QUERY_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputResultSetName(null);
      ActionOutput[] actionOutputs = getOutputParams();
      for (int i = 0; i < actionOutputs.length; i++) {
        if (!actionOutputs[i].getType().equals(ActionSequenceDocument.SQL_QUERY_TYPE)) {
          actionOutputs[i].delete();
        }
      }
    }
  }
  
  public String getOutputPreparedStatementName() {
    return getOutputPublicName(PREPARED_COMPONENT_ELEMENT);
  }
  
  public ActionOutput getOutputPreparedStatementParam() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }
  
  public void setSqlConnectionParam(IActionVariable variable) {
    setReferencedVariable(PREPARED_COMPONENT_ELEMENT, variable);
    if (variable != null) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
    }
  }
  
  public ActionInput getSqlConnectionParam() {
    return getInputParam(PREPARED_COMPONENT_ELEMENT);
  }
  
  public ActionSequenceValidationError[] validate() {
    
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
        if (validationError.errorCode == ActionSequenceValidationError.INPUT_MISSING) {
          validationError = validateInputParam(PREPARED_COMPONENT_ELEMENT);
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
      }
    } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR) {
      validationError.errorMsg = "Database connection input parameter references unknown variable.";
      errors.add(validationError);
    } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED) {
      validationError.errorMsg = "Database connection input parameter is uninitialized.";
      errors.add(validationError);
    } else {
      errors.add(validationError);
    }
    
    validationError = validateInputParam(QUERY_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing query input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Query input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Query input parameter is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    validationError = validateOutputParam(PREPARED_COMPONENT_ELEMENT);
    if (validationError != null) {
      validationError = validateOutputParam(QUERY_RESULT_ELEMENT);
      if (validationError != null) {
        validationError.errorMsg = "Missing query results output parameter.";
        errors.add(validationError);
      }
    }
    
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
}
