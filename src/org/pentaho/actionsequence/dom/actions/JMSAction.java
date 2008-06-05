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

public class JMSAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.JMSComponent"; //$NON-NLS-1$
  public static final String SOLUTION_NAME_ELEMENT = "solution-name"; //$NON-NLS-1$
  public static final String ACTION_PATH_ELEMENT = "action-path"; //$NON-NLS-1$
  public static final String ACTION_NAME_ELEMENT = "action-name"; //$NON-NLS-1$
  public static final String QUEUE_NAME_ELEMENT = "jms-queue-name"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    SOLUTION_NAME_ELEMENT,
    ACTION_PATH_ELEMENT,
    ACTION_NAME_ELEMENT,
    QUEUE_NAME_ELEMENT 
  };

  public JMSAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public JMSAction() {
    super(COMPONENT_NAME);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public void setSolutionName(String value) {
    setInputValue(SOLUTION_NAME_ELEMENT, value);
  }
  
  public String getSolutionName() {
    return getComponentDefinitionValue(SOLUTION_NAME_ELEMENT);
  }
  
  public void setSolutionNameParam(IActionInputVariable variable) {
    setInputParam(SOLUTION_NAME_ELEMENT, variable);
  }
  
  public IActionInput getSolutionNameParam() {
    return getInputParam(SOLUTION_NAME_ELEMENT);
  }
  
  public void setActionPath(String value) {
    setInputValue(ACTION_PATH_ELEMENT, value);
  }
  
  public String getActionPath() {
    return getComponentDefinitionValue(ACTION_PATH_ELEMENT);
  }
  
  public void setActionPathParam(IActionInputVariable variable) {
    setInputParam(ACTION_PATH_ELEMENT, variable);
  }
  
  public IActionInput getActionPathParam() {
    return getInputParam(ACTION_PATH_ELEMENT);
  }
  
  public void setActionName(String value) {
    setInputValue(ACTION_NAME_ELEMENT, value);
  }
  
  public String getActionName() {
    return getComponentDefinitionValue(ACTION_NAME_ELEMENT);
  }
  
  public void setActionNameParam(IActionInputVariable variable) {
    setInputParam(ACTION_NAME_ELEMENT, variable);
  }
  
  public IActionInput getActionNameParam() {
    return getInputParam(ACTION_NAME_ELEMENT);
  }
  
  public void setJmsQueueName(String value) {
    setInputValue(QUEUE_NAME_ELEMENT, value);
  }
  
  public String getJmsQueueName() {
    return getComponentDefinitionValue(QUEUE_NAME_ELEMENT);
  }
  
  public void setJmsQueueNameParam(IActionInputVariable variable) {
    setInputParam(QUEUE_NAME_ELEMENT, variable);
  }
  
  public IActionInput getJmsQueueNameParam() {
    return getInputParam(QUEUE_NAME_ELEMENT);
  }
}
