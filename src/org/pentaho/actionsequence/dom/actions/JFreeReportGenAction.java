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
import org.pentaho.actionsequence.dom.IActionInputVariable;

public class JFreeReportGenAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "com.pentaho.component.JFreeReportGeneratorComponent"; //$NON-NLS-1$
  public static final String RESULT_SET_ELEMENT = "result-set"; //$NON-NLS-1$
  public static final String COMPONENT_SETTINGS_ELEMENT = "component-settings"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    RESULT_SET_ELEMENT,
    COMPONENT_SETTINGS_ELEMENT
  };
  
  public JFreeReportGenAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public JFreeReportGenAction() {
    super(COMPONENT_NAME);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public void setResultSet(String value) {
    setInputValue(RESULT_SET_ELEMENT, value);
  }
  
  public String getResultSet() {
    return getComponentDefinitionValue(RESULT_SET_ELEMENT);
  }
  
  public void setResultSetParam(IActionInputVariable variable) {
    setInputParam(RESULT_SET_ELEMENT, variable);
  }
  
  public ActionInput getResultSetParam() {
    return getInputParam(RESULT_SET_ELEMENT);
  }
  
  public void setComponentSettings(String value) {
    setInputValue(COMPONENT_SETTINGS_ELEMENT, value);
  }
  
  public String getComponentSettings() {
    return getComponentDefinitionValue(COMPONENT_SETTINGS_ELEMENT);
  }
  
  public void setComponentSettingsParam(IActionInputVariable variable) {
    setInputParam(COMPONENT_SETTINGS_ELEMENT, variable);
  }
  
  public ActionInput getComponentSettingsParam() {
    return getInputParam(COMPONENT_SETTINGS_ELEMENT);
  }
}
