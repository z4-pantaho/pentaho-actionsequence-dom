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

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionDefinition;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionVariable;

public class SqlExecuteAction extends ActionDefinition {


  public static final String COMPONENT_NAME = "SQLExecute"; //$NON-NLS-1$
  public static final String DRIVER_ELEMENT = "driver"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String QUERY_RESULT_ELEMENT = "query-result"; //$NON-NLS-1$
  public static final String OUTPUT_NAME_ELEMENT = "output-name"; //$NON-NLS-1$
  public static final String MAPPED_QUERY_OUTPUT_NAME = "query_result"; //$NON-NLS-1$
  public static final String CONTINUE_ON_EXCEPTION = "continue_on_exception"; //$NON-NLS-1$
  public static final String FORCE_SINGLE_STATEMENT = "force_single_statement"; //$NON-NLS-1$
  public static final String MULTI_STATEMENT_SEPARATOR = "multi_statement_separator"; //$NON-NLS-1$
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    DRIVER_ELEMENT,
    CONNECTION_ELEMENT,
    USER_ID_ELEMENT,
    PASSWORD_ELEMENT, 
    JNDI_ELEMENT, 
    QUERY_ELEMENT,
    CONTINUE_ON_EXCEPTION,
    FORCE_SINGLE_STATEMENT,
    MULTI_STATEMENT_SEPARATOR
  };

  public SqlExecuteAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public SqlExecuteAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(JNDI_ELEMENT, ""); //$NON-NLS-1$
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
  
  public void setConnection(String value) {
    setInputValue(CONNECTION_ELEMENT, value);
    if (value != null) {
      setJndi(null);
    }
  }
  
  public String getConnection() {
    return getComponentDefinitionValue(CONNECTION_ELEMENT);
  }
  
  public void setConnectionParam(IActionVariable variable) {
    setReferencedVariable(CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
    }
  }
  
  public ActionInput getConnectionParam() {
    return getInputParam(CONNECTION_ELEMENT);
  }
  
  public void setUserId(String value) {
    setInputValue(USER_ID_ELEMENT, value);
    if (value != null) {
      setJndi(null);
    }
  }
  
  public String getUserId() {
    return getComponentDefinitionValue(USER_ID_ELEMENT);
  }
  
  public void setUserIdParam(IActionVariable variable) {
    setReferencedVariable(USER_ID_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
    }
  }
  
  public ActionInput getUserIdParam() {
    return getInputParam(USER_ID_ELEMENT);
  }
  
  public void setDriver(String value) {
    setInputValue(DRIVER_ELEMENT, value);
    if (value != null) {
      setJndi(null);
    }
  }
  
  public String getDriver() {
    return getComponentDefinitionValue(DRIVER_ELEMENT);
  }
  
  public void setDriverParam(IActionVariable variable) {
    setReferencedVariable(DRIVER_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
    }
  }
  
  public ActionInput getDriverParam() {
    return getInputParam(DRIVER_ELEMENT);
  }
  
  public void setPassword(String value) {
    setInputValue(PASSWORD_ELEMENT, value);
    if (value != null) {
      setJndi(null);
    }
  }
  
  public String getPassword() {
    return getComponentDefinitionValue(PASSWORD_ELEMENT);
  }
  
  public void setPasswordParam(IActionVariable variable) {
    setReferencedVariable(PASSWORD_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
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
    }
  }
  
  public ActionInput getJndiParam() {
    return getInputParam(JNDI_ELEMENT);
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

  public void setMultiStatementSeparator(String value) {
    setInputValue(MULTI_STATEMENT_SEPARATOR, value);
  }
  
  public String getMultiStatementSeparator() {
    return getComponentDefinitionValue(MULTI_STATEMENT_SEPARATOR);
  }
  
  public void setMultiStatementSeparatorParam(IActionVariable variable) {
    setReferencedVariable(MULTI_STATEMENT_SEPARATOR, variable);
  }
  
  public ActionInput getMultiStatementSeparatorParam() {
    return getInputParam(MULTI_STATEMENT_SEPARATOR);
  }
  
  public void setOutputResultSetName(String name) {
    setOutputName(QUERY_RESULT_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE);
  }
  
  public String getOutputResultSetName() {
    return getOutputPublicName(QUERY_RESULT_ELEMENT);
  }
  
  public ActionOutput getOutputResultSetParam() {
    return getOutputParam(QUERY_RESULT_ELEMENT);
  }
}
