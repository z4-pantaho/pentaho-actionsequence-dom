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
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionVariable;

public class SqlExecuteAction extends AbstractRelationalDbAction {


  public static final String COMPONENT_NAME = "SQLExecute"; //$NON-NLS-1$
  public static final String CONTINUE_ON_EXCEPTION = "continue_on_exception"; //$NON-NLS-1$
  public static final String FORCE_SINGLE_STATEMENT = "force_single_statement"; //$NON-NLS-1$
  public static final String MULTI_STATEMENT_SEPARATOR = "multi_statement_separator"; //$NON-NLS-1$
  
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

  public SqlExecuteAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public SqlExecuteAction() {
    super(COMPONENT_NAME);
  }

  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(JNDI_ELEMENT, ""); //$NON-NLS-1$
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public String[] getReservedOutputNames() {
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
    
  public void setContinueOnException(boolean value) {
    setInputValue(CONTINUE_ON_EXCEPTION, Boolean.toString(value)); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getContinueOnException() {
    Object continueOnException = getInputValue(CONTINUE_ON_EXCEPTION);
    if ((continueOnException != null) && (actionParameterMgr != null)) {
      continueOnException = actionParameterMgr.replaceParameterReferences(continueOnException.toString());
    }
    return continueOnException != null ? Boolean.parseBoolean(continueOnException.toString()) : false;
  }
  
  public void setContinueOnExceptionParam(IActionVariable variable) {
    setInputParam(CONTINUE_ON_EXCEPTION, variable);
  }
  
  public ActionInput getContinueOnExceptionParam() {
    return getInputParam(CONTINUE_ON_EXCEPTION);
  }
  
  public void setForceSingleStatement(boolean value) {
    setInputValue(FORCE_SINGLE_STATEMENT, Boolean.toString(value)); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getForceSingleStatement() {
    Object continueOnException = getInputValue(FORCE_SINGLE_STATEMENT);
    if ((continueOnException != null) && (actionParameterMgr != null)) {
      continueOnException = actionParameterMgr.replaceParameterReferences(continueOnException.toString());
    }
    return continueOnException != null ? Boolean.parseBoolean(continueOnException.toString()) : false;
  }
  
  public void setForceSingleStatementParam(IActionVariable variable) {
    setInputParam(FORCE_SINGLE_STATEMENT, variable);
  }
  
  public ActionInput getForceSingleStatementParam() {
    return getInputParam(FORCE_SINGLE_STATEMENT);
  }
  
  public void setMultiStatementSeparator(String cc) {
    setInputValue(MULTI_STATEMENT_SEPARATOR, cc);
  }
  
  public String getMultiStatementSeparator() {
    Object separator = getInputValue(MULTI_STATEMENT_SEPARATOR);
    if ((separator != null) && (actionParameterMgr != null)) {
      separator = actionParameterMgr.replaceParameterReferences(separator.toString());
    }
    return separator != null ? separator.toString() : ";";
  }
  
  public void setMultiStatementSeparatorParam(IActionVariable variable) {
    setInputParam(MULTI_STATEMENT_SEPARATOR, variable);
  }
  
  public ActionInput getMultiStatementSeparatorParam() {
    return getInputParam(MULTI_STATEMENT_SEPARATOR);
  }
}
