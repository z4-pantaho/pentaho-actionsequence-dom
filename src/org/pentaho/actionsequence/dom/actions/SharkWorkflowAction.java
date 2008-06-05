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
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputVariable;

public class SharkWorkflowAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.SharkWorkflowComponent"; //$NON-NLS-1$
  public static final String PACKAGE_NAME_ELEMENT = "package-name" ; //$NON-NLS-1$
  public static final String NEW_INSTANCE_ELEMENT = "new-instance" ; //$NON-NLS-1$
  public static final String PROCESS_NAME_ELEMENT = "process-name" ; //$NON-NLS-1$
  protected static final String[] EXPECTED_INPUTS = new String[] {
    PACKAGE_NAME_ELEMENT,
    NEW_INSTANCE_ELEMENT,
    PROCESS_NAME_ELEMENT
  };
  
  public SharkWorkflowAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public SharkWorkflowAction() {
    super(COMPONENT_NAME);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public void setPackageName(String value) {
    setInputValue(PACKAGE_NAME_ELEMENT, value);
  }
  
  public String getPackageName() {
    return getComponentDefinitionValue(PACKAGE_NAME_ELEMENT);
  }
  
  public void setPackageNameParam(IActionInputVariable variable) {
    setInputParam(PACKAGE_NAME_ELEMENT, variable);
  }
  
  public IActionInput getPackageNameParam() {
    return getInputParam(PACKAGE_NAME_ELEMENT);
  }

  public void setProcessName(String value) {
    setInputValue(PROCESS_NAME_ELEMENT, value);
  }
  
  public String getProcessName() {
    return getComponentDefinitionValue(PROCESS_NAME_ELEMENT);
  }
  
  public void setProcessNameParam(IActionInputVariable variable) {
    setInputParam(PROCESS_NAME_ELEMENT, variable);
  }
  
  public IActionInput getProcessNameParam() {
    return getInputParam(PROCESS_NAME_ELEMENT);
  }
  
  public void setNewInstance(boolean value) {
    setInputValue(NEW_INSTANCE_ELEMENT, value ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getNewInstance() {
    String value = getComponentDefinitionValue(NEW_INSTANCE_ELEMENT);
    return (value != null) && value.trim().toLowerCase().equals("true"); //$NON-NLS-1$
  }
  
  public void setNewInstanceParam(IActionInputVariable variable) {
    setInputParam(NEW_INSTANCE_ELEMENT, variable);
  }
  
  public IActionInput getNewInstanceParam() {
    return getInputParam(NEW_INSTANCE_ELEMENT);
  }
}
