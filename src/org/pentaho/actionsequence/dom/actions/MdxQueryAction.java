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
import org.pentaho.actionsequence.dom.IActionVariable;

public class MdxQueryAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.MDXLookupRule"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String LOCATION_ELEMENT = "location"; //$NON-NLS-1$
  public static final String MDX_CONNECTION_ELEMENT = "mdx-connection-string"; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String QUERY_RESULTS_ELEMENT = "query-results"; //$NON-NLS-1$
  public static final String CATALOG_ELEMENT = "catalog"; //$NON-NLS-1$
  public static final String ROLE_ELEMENT = "role"; //$NON-NLS-1$
  public static final String DEFAULT_LOCATION = "mondrian"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String DEFAULT_QUERY_RESULTS_NAME = "query_result"; //$NON-NLS-1$
  public static final String MDX_CONNECTION = "mdx-connection"; //$NON-NLS-1$
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  public static final String OUTPUT_PREPARED_STATEMENT = "output-prepared_statement"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    LOCATION_ELEMENT,
    CONNECTION_ELEMENT,
    USER_ID_ELEMENT,
    PASSWORD_ELEMENT,
    MDX_CONNECTION_ELEMENT,
    ROLE_ELEMENT,
    QUERY_ELEMENT,
    JNDI_ELEMENT
  };

  protected static final String[] EXPECTED_RESOURCES = new String[] {
    CATALOG_ELEMENT
  };
  
  public MdxQueryAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public MdxQueryAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setJndi(""); //$NON-NLS-1$
    setQuery(""); //$NON-NLS-1$
    setLocation(DEFAULT_LOCATION);
    setOutputResultSetName(DEFAULT_QUERY_RESULTS_NAME);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  protected boolean accepts(Element element) {
    return super.accepts(element)
      && ((element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + QUERY_ELEMENT) != null) //$NON-NLS-1$
          || (element.selectSingleNode(ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + QUERY_ELEMENT) != null)); //$NON-NLS-1$
  }
  
  public String[] getExpectedOutputs() {
    String expectedOutput = QUERY_RESULTS_ELEMENT;
    if (getOutputParam(expectedOutput) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.RESULTSET_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }
  
  public String[] getExpectedResources() {
    return EXPECTED_RESOURCES;
  }
  
  public void setLocation(String value) {
    setInputValue(LOCATION_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
    }
  }
  
  public String getLocation() {
    return getComponentDefinitionValue(LOCATION_ELEMENT);
  }
  
  public void setLocationVariable(IActionVariable variable) {
    setReferencedVariable(LOCATION_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
    }
  }
  
  public IActionVariable getLocationVariable() {
    return getReferencedVariable(LOCATION_ELEMENT);
  }
  
  public void setUserId(String value) {
    setInputValue(USER_ID_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getUserId() {
    return getComponentDefinitionValue(USER_ID_ELEMENT);
  }
  
  public void setUserIdVariable(IActionVariable variable) {
    setReferencedVariable(USER_ID_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public IActionVariable getUserIdVariable() {
    return getReferencedVariable(USER_ID_ELEMENT);
  }
  
  public void setPassword(String value) {
    setInputValue(PASSWORD_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getPassword() {
    return getComponentDefinitionValue(PASSWORD_ELEMENT);
  }
  
  public void setPasswordVariable(IActionVariable variable) {
    setReferencedVariable(PASSWORD_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public IActionVariable getPasswordVariable() {
    return getReferencedVariable(PASSWORD_ELEMENT);
  }
  
  public void setMdxConnectionString(String value) {
    setInputValue(MDX_CONNECTION_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setConnection(null);
      setLocation(null);
      setUserId(null);
      setPassword(null);
      setRole(null);
      setConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getMdxConnectionString() {
    return getComponentDefinitionValue(MDX_CONNECTION_ELEMENT);
  }
  
  public void setMdxConnectionStringVariable(IActionVariable variable) {
    setReferencedVariable(MDX_CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setConnection(null);
      setLocation(null);
      setUserId(null);
      setPassword(null);
      setRole(null);
      setConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public IActionVariable getMdxConnectionStringVariable() {
    return getReferencedVariable(MDX_CONNECTION_ELEMENT);
  }
  
  public void setRole(String value) {
    setInputValue(ROLE_ELEMENT, value);
  }
  
  public String getRole() {
    return getComponentDefinitionValue(ROLE_ELEMENT);
  }
  
  public void setRoleVariable(IActionVariable variable) {
    setReferencedVariable(ROLE_ELEMENT, variable);
  }
  
  public IActionVariable getRoleVariable() {
    return getReferencedVariable(ROLE_ELEMENT);
  }
  
  public void setConnection(String value) {
    setInputValue(CONNECTION_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getConnection() {
    return getComponentDefinitionValue(CONNECTION_ELEMENT);
  }
  
  public void setConnectionVariable(IActionVariable variable) {
    setReferencedVariable(CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public IActionVariable getConnectionVariable() {
    return getReferencedVariable(CONNECTION_ELEMENT);
  }
  
  public void setJndi(String value) {
    setInputValue(JNDI_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setMdxConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getJndi() {
    return getComponentDefinitionValue(JNDI_ELEMENT);
  }
  
  public void setJndiVariable(IActionVariable variable) {
    setReferencedVariable(JNDI_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setMdxConnectionVariable(null);
      if ((getLocation() == null) && (getLocationVariable() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public IActionVariable getJndiVariable() {
    return getReferencedVariable(JNDI_ELEMENT);
  }
  
  public void setQuery(String value) {
    setInputValue(QUERY_ELEMENT, value);
  }
  
  public String getQuery() {
    return getComponentDefinitionValue(QUERY_ELEMENT);
  }
  
  public void setQueryVariable(IActionVariable variable) {
    setReferencedVariable(QUERY_ELEMENT, variable);
  }
  
  public IActionVariable getQueryVariable() {
    return getReferencedVariable(QUERY_ELEMENT);
  }
  
  public void setOutputResultSetName(String name) {
    setOutputName(QUERY_RESULTS_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputPreparedStatementName(null);
    }
  }
  
  public String getOutputResultSetName() {
    return getOutputPublicName(QUERY_RESULTS_ELEMENT);
  }
  
  public ActionOutput getOutputResultSetVariable() {
    return getOutputParam(QUERY_RESULTS_ELEMENT);
  }
  
  public void setOutputPreparedStatementName(String name) {
    setOutputName(PREPARED_COMPONENT_ELEMENT, name, ActionSequenceDocument.MDX_QUERY_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputResultSetName(null);
    }
  }
  
  public String getOutputPreparedStatementName() {
    return getOutputPublicName(PREPARED_COMPONENT_ELEMENT);
  }  
  
  public ActionOutput getOutputPreparedStatementVariable() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }
  
  public void setMdxConnectionVariable(IActionVariable variable) {
    setReferencedVariable(PREPARED_COMPONENT_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
      setLocation(null);
    }
  }
  
  public IActionVariable getMdxConnectionVariable() {
    return getReferencedVariable(PREPARED_COMPONENT_ELEMENT);
  }
}
