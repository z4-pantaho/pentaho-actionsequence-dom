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
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceResource;
import org.pentaho.actionsequence.dom.IActionVariable;

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

  public SqlConnectionAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public SqlConnectionAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setJndi(""); //$NON-NLS-1$
    setOutputConnectionName(DEFAULT_CONNECTION_NAME);
  }
  
  protected boolean accepts(Element element) {
    boolean result = super.accepts(element);
    if (result) {
      Element connectionOutput = (Element)element.selectSingleNode(ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/" + PREPARED_COMPONENT_ELEMENT); //$NON-NLS-1$
      result = (connectionOutput != null)
        && ActionSequenceDocument.SQL_CONNECTION_TYPE.equals(connectionOutput.attributeValue(ActionSequenceResource.TYPE_NAME));
    }
    return result;
  }

  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }

  public String[] getExpectedOutputs() {
    return new String[]{PREPARED_COMPONENT_ELEMENT};
  }
  
  public void setDbUrl(String value) {
    setInputValue(CONNECTION_ELEMENT, value);
    if (value != null) {
      setJndi(null);
    }
  }
  
  public String getDbUrl() {
    return getComponentDefinitionValue(CONNECTION_ELEMENT);
  }
  
  public void setDbUrlVariable(IActionVariable variable) {
    setReferencedVariable(CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
    }
  }
  
  public IActionVariable getDbUrlVariable() {
    return getReferencedVariable(CONNECTION_ELEMENT);
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
  
  public void setUserIdVariable(IActionVariable variable) {
    setReferencedVariable(USER_ID_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
    }
  }
  
  public IActionVariable getUserIdVariable() {
    return getReferencedVariable(USER_ID_ELEMENT);
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
  
  public void setDriverVariable(IActionVariable variable) {
    setReferencedVariable(DRIVER_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
    }
  }
  
  public IActionVariable getDriverVariable() {
    return getReferencedVariable(DRIVER_ELEMENT);
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
  
  public void setPasswordVariable(IActionVariable variable) {
    setReferencedVariable(PASSWORD_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
    }
  }
  
  public IActionVariable getPasswordVariable() {
    return getReferencedVariable(PASSWORD_ELEMENT);
  }
  
  public void setJndi(String value) {
    setInputValue(JNDI_ELEMENT, value);
    if (value != null) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
    }
  }
  
  public String getJndi() {
    return getComponentDefinitionValue(JNDI_ELEMENT);
  }
  
  public void setJndiVariable(IActionVariable variable) {
    setReferencedVariable(JNDI_ELEMENT, variable);
    if (variable != null) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
    }
  }
  
  public IActionVariable getJndiVariable() {
    return getReferencedVariable(JNDI_ELEMENT);
  }
  
  public void setOutputConnectionName(String name) {
    setOutputName(PREPARED_COMPONENT_ELEMENT, name, ActionSequenceDocument.SQL_CONNECTION_TYPE);
  }
  
  public String getOutputConnectionName() {
    return getOutputPublicName(PREPARED_COMPONENT_ELEMENT);
  }
  
  public ActionOutput getOutputConnectionVariable() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }
}
