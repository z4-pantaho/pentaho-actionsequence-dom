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
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionVariable;

public class PivotViewAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.PivotViewComponent"; //$NON-NLS-1$

  public static final String MODE_ELEMENT = "mode"; //$NON-NLS-1$
  public static final String VIEWER_ELEMENT = "viewer"; //$NON-NLS-1$
  public static final String MODEL_ELEMENT = "model"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String OPTIONS_ELEMENT = "options"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String MDX_ELEMENT = "mdx"; //$NON-NLS-1$
  public static final String TITLE_ELEMENT = "title"; //$NON-NLS-1$
  public static final String URL_ELEMENT = "url"; //$NON-NLS-1$
  public static final String EXECUTE_MODE = "execute"; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String ROLE_ELEMENT = "role"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    MODE_ELEMENT,
    VIEWER_ELEMENT,
    MODEL_ELEMENT, 
    JNDI_ELEMENT,
    QUERY_ELEMENT
  };
  
  public PivotViewAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public PivotViewAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(MODE_ELEMENT, EXECUTE_MODE);
    addOutputParam(OPTIONS_ELEMENT, ActionSequenceDocument.LIST_TYPE);
    addOutputParam(MODEL_ELEMENT, ActionSequenceDocument.STRING_TYPE);
    addOutputParam(CONNECTION_ELEMENT, ActionSequenceDocument.STRING_TYPE);
    addOutputParam(MDX_ELEMENT, ActionSequenceDocument.STRING_TYPE);
    addOutputParam(TITLE_ELEMENT, ActionSequenceDocument.STRING_TYPE);
    addOutputParam(URL_ELEMENT, ActionSequenceDocument.STRING_TYPE);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public void setMode(String value) {
    setInputValue(MODE_ELEMENT, value);
  }
  
  public String getMode() {
    return getComponentDefinitionValue(MODE_ELEMENT);
  }
  
  public void setModeVariable(IActionVariable variable) {
    setReferencedVariable(MODE_ELEMENT, variable);
  }
  
  public IActionVariable getModeVariable() {
    return getReferencedVariable(MODE_ELEMENT);
  }

  public void setViewer(String value) {
    setInputValue(VIEWER_ELEMENT, value);
  }
  
  public String getViewer() {
    return getComponentDefinitionValue(VIEWER_ELEMENT);
  }
  
  public void setViewerVariable(IActionVariable variable) {
    setReferencedVariable(VIEWER_ELEMENT, variable);
  }
  
  public IActionVariable getViewerVariable() {
    return getReferencedVariable(VIEWER_ELEMENT);
  }

  public void setModel(String value) {
    setInputValue(MODEL_ELEMENT, value);
  }
  
  public String getModel() {
    return getComponentDefinitionValue(MODEL_ELEMENT);
  }
  
  public void setModelVariable(IActionVariable variable) {
    setReferencedVariable(MODEL_ELEMENT, variable);
  }
  
  public IActionVariable getModelVariable() {
    return getReferencedVariable(MODEL_ELEMENT);
  }

  public void setJndi(String value) {
    setInputValue(JNDI_ELEMENT, value);
  }
  
  public String getJndi() {
    return getComponentDefinitionValue(JNDI_ELEMENT);
  }
  
  public void setJndiVariable(IActionVariable variable) {
    setReferencedVariable(JNDI_ELEMENT, variable);
  }
  
  public IActionVariable getJndiVariable() {
    return getReferencedVariable(JNDI_ELEMENT);
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
}
