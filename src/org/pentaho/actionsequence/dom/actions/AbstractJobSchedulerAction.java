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
import org.pentaho.actionsequence.dom.IActionVariable;

public abstract class AbstractJobSchedulerAction extends ActionDefinition {
  public static final String COMPONENT_NAME = "org.pentaho.component.JobSchedulerComponent"; //$NON-NLS-1$
  public static final String JOB_NAME_ELEMENT = "jobName"; //$NON-NLS-1$
  public static final String JOB_ACTION_ELEMENT = "jobAction"; //$NON-NLS-1$
  public static final String JOB_NAME = "job-name"; //$NON-NLS-1$
  
  protected AbstractJobSchedulerAction(Element actionDefElement) {
    super(actionDefElement);
  }
  
  protected AbstractJobSchedulerAction(String componentName) {
    super(componentName);
  }
  
  public void setJobName(String value) {
    setInputValue(JOB_NAME_ELEMENT, value);
  }
  
  public String getJobName() {
    return getComponentDefinitionValue(JOB_NAME_ELEMENT);
  }
  
  public void setJobNameVariable(IActionVariable variable) {
    setReferencedVariable(JOB_NAME_ELEMENT, variable);
  }
  
  public IActionVariable getJobNameVariable() {
    return getReferencedVariable(JOB_NAME_ELEMENT);
  }
  
}
