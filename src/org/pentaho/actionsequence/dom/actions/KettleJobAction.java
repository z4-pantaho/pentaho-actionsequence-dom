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

public class KettleJobAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "KettleComponent"; //$NON-NLS-1$
  
  public static final String JOB_FILE_ELEMENT = "job-file"; //$NON-NLS-1$
  public static final String REPOSITORY_DIRECTORY = "directory"; //$NON-NLS-1$
  public static final String REPOSITORY_JOB = "job"; //$NON-NLS-1$
  
  public static final String[] EXPECTED_RESOURCES = new String[]{JOB_FILE_ELEMENT};
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    REPOSITORY_DIRECTORY, REPOSITORY_JOB
  };
  
  public KettleJobAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public KettleJobAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    addResourceParam(JOB_FILE_ELEMENT);
  }

  public boolean accepts(Element element) {
    boolean result = false;
    if (super.accepts(element)) {
      result = (element.selectSingleNode(ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + JOB_FILE_ELEMENT) != null) //$NON-NLS-1$
        || (element.selectSingleNode(ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + REPOSITORY_JOB) != null) //$NON-NLS-1$
        || (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + REPOSITORY_JOB) != null); //$NON-NLS-1$
    }
    return result;
  }

  public String[] getExpectedResources() {
    return EXPECTED_RESOURCES;
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public void setJob(String value) {
    setInputValue(REPOSITORY_JOB, value);
  }
  
  public String getJob() {
    return getComponentDefinitionValue(REPOSITORY_JOB);
  }
  
  public void setJobVariable(IActionVariable variable) {
    setReferencedVariable(REPOSITORY_JOB, variable);
  }
  
  public IActionVariable getJobVariable() {
    return getReferencedVariable(REPOSITORY_JOB);
  }
  
  public void setDirectory(String value) {
    setInputValue(REPOSITORY_DIRECTORY, value);
  }
  
  public String getDirectory() {
    return getComponentDefinitionValue(REPOSITORY_DIRECTORY);
  }
  
  public void setDirectoryVariable(IActionVariable variable) {
    setReferencedVariable(REPOSITORY_DIRECTORY, variable);
  }
  
  public IActionVariable getDirectoryVariable() {
    return getReferencedVariable(REPOSITORY_DIRECTORY);
  }
}
