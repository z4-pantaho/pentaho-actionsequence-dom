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
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionVariable;

public class StartScheduledJobAction extends AbstractJobSchedulerAction{

  public static final String START_SCHED_JOB_CMND = "startJob"; //$NON-NLS-1$
  public static final String SOLUTION_ELEMENT = "solution"; //$NON-NLS-1$
  public static final String PATH_ELEMENT = "path"; //$NON-NLS-1$
  public static final String ACTION_ELEMENT = "action"; //$NON-NLS-1$
  public static final String TRIGGER_TYPE_ELEMENT = "triggerType"; //$NON-NLS-1$
  public static final String TRIGGER_NAME_ELEMENT = "triggerName"; //$NON-NLS-1$
  public static final String REPEAT_INTERVAL_ELEMENT = "repeatInterval"; //$NON-NLS-1$
  public static final String REPEAT_COUNT_ELEMENT = "repeatCount"; //$NON-NLS-1$
  public static final String CRON_STRING_ELEMENT = "cronString"; //$NON-NLS-1$
  public static final String CRON_TRIGGER = "cron"; //$NON-NLS-1$
  public static final String SIMPLE_TRIGGER = "simple"; //$NON-NLS-1$
  public static final String REPEAT_INTERVAL = "repeat-interval"; //$NON-NLS-1$
  public static final String REPEAT_COUNT = "repeat-count"; //$NON-NLS-1$
  public static final String CRON_STRING = "cron-string"; //$NON-NLS-1$
  public static final String TRIGGER_NAME = "trigger-name"; //$NON-NLS-1$
  
  
  static final String[] EXPECTED_INPUTS = new String[] {
      JOB_ACTION_ELEMENT,
      SOLUTION_ELEMENT,
      PATH_ELEMENT,
      ACTION_ELEMENT,
      TRIGGER_TYPE_ELEMENT,
      TRIGGER_NAME_ELEMENT,
      REPEAT_INTERVAL_ELEMENT,
      REPEAT_COUNT_ELEMENT,
      JOB_NAME_ELEMENT,
      CRON_STRING_ELEMENT
  };
  
  

  public StartScheduledJobAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public StartScheduledJobAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(JOB_ACTION_ELEMENT, START_SCHED_JOB_CMND);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  protected boolean accepts(Element element) {
    boolean result = false;
    if (super.accepts(element)) {
      element = (Element)element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + JOB_ACTION_ELEMENT); //$NON-NLS-1$
      result = (element != null) && element.getText().equals(START_SCHED_JOB_CMND);
    }
    return result;
  }
  
  public void setCronString(String value) {
    setInputValue(CRON_STRING_ELEMENT, value);
    if (value != null) {
      setTriggerType(CRON_TRIGGER);
      setRepeatCount(null);
      setRepeatInterval(null);
    } else if (CRON_TRIGGER.equals(getTriggerType())) {
      setTriggerType(null);
    }
  }
  
  public String getCronString() {
    return getComponentDefinitionValue(CRON_STRING_ELEMENT);
  }
  
  public void setCronStringParam(IActionVariable variable) {
    setReferencedVariable(CRON_STRING_ELEMENT, variable);
    if (variable != null) {
      setTriggerType(CRON_TRIGGER);
      setRepeatCount(null);
      setRepeatInterval(null);
    } else if (CRON_TRIGGER.equals(getTriggerType())) {
      setTriggerType(null);
    }
  }
  
  public ActionInput getCronStringParam() {
    return getInputParam(CRON_STRING_ELEMENT);
  }
  
  public void setSolution(String value) {
    setInputValue(SOLUTION_ELEMENT, value);
  }
  
  public String getSolution() {
    return getComponentDefinitionValue(SOLUTION_ELEMENT);
  }
  
  public void setSolutionParam(IActionVariable variable) {
    setReferencedVariable(SOLUTION_ELEMENT, variable);
  }
  
  public ActionInput getSolutionParam() {
    return getInputParam(SOLUTION_ELEMENT);
  }
  
  public void setPath(String value) {
    setInputValue(PATH_ELEMENT, value);
  }
  
  public String getPath() {
    return getComponentDefinitionValue(PATH_ELEMENT);
  }
  
  public void setPathParam(IActionVariable variable) {
    setReferencedVariable(PATH_ELEMENT, variable);
  }
  
  public ActionInput getPathParam() {
    return getInputParam(PATH_ELEMENT);
  }
  
  public void setAction(String value) {
    setInputValue(ACTION_ELEMENT, value);
  }
  
  public String getAction() {
    return getComponentDefinitionValue(ACTION_ELEMENT);
  }
  
  public void setActionParam(IActionVariable variable) {
    setReferencedVariable(ACTION_ELEMENT, variable);
  }
  
  public ActionInput getActionParam() {
    return getInputParam(ACTION_ELEMENT);
  }
  
  public void setTriggerName(String value) {
    setInputValue(TRIGGER_NAME_ELEMENT, value);
  }
  
  public String getTriggerName() {
    return getComponentDefinitionValue(TRIGGER_NAME_ELEMENT);
  }
  
  public void setTriggerNameParam(IActionVariable variable) {
    setReferencedVariable(TRIGGER_NAME_ELEMENT, variable);
  }
  
  public ActionInput getTriggerNameParam() {
    return getInputParam(TRIGGER_NAME_ELEMENT);
  }
  
  public void setRepeatCount(String value) {
    setInputValue(REPEAT_COUNT_ELEMENT, value);
    if (value != null) {
      setTriggerType(SIMPLE_TRIGGER);
      setCronString(null);
    } else if (SIMPLE_TRIGGER.equals(getTriggerType())) {
      setTriggerType(null);
    }
  }
  
  public String getRepeatCount() {
    return getComponentDefinitionValue(REPEAT_COUNT_ELEMENT);
  }
  
  public void setRepeatCountParam(IActionVariable variable) {
    setReferencedVariable(REPEAT_COUNT_ELEMENT, variable);
    if (variable != null) {
      setTriggerType(SIMPLE_TRIGGER);
      setCronString(null);
    } else if (SIMPLE_TRIGGER.equals(getTriggerType())) {
      setTriggerType(null);
    }
  }
  
  public ActionInput getRepeatCountParam() {
    return getInputParam(REPEAT_COUNT_ELEMENT);
  }
  
  public void setRepeatInterval(String value) {
    setInputValue(REPEAT_INTERVAL_ELEMENT, value);
    if (value != null) {
      setTriggerType(SIMPLE_TRIGGER);
      setCronString(null);
    } else if (SIMPLE_TRIGGER.equals(getTriggerType())) {
      setTriggerType(null);
    }
  }
  
  public String getRepeatInterval() {
    return getComponentDefinitionValue(REPEAT_INTERVAL_ELEMENT);
  }
  
  public void setRepeatIntervalParam(IActionVariable variable) {
    setReferencedVariable(REPEAT_INTERVAL_ELEMENT, variable);
    if (variable != null) {
      setTriggerType(SIMPLE_TRIGGER);
      setCronString(null);
    } else if (SIMPLE_TRIGGER.equals(getTriggerType())) {
      setTriggerType(null);
    }
  }
  
  public ActionInput getRepeatIntervalParam() {
    return getInputParam(REPEAT_INTERVAL_ELEMENT);
  }
  
  private void setTriggerType(String triggerType) {
    setComponentDefinition(TRIGGER_TYPE_ELEMENT, triggerType);
  }
  
  public String getTriggerType() {
    return getComponentDefinitionValue(TRIGGER_TYPE_ELEMENT);
  }
}
