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

public class KettleTransformAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "KettleComponent"; //$NON-NLS-1$
  public static final String TRANSFORMATION_STEP_ELEMENT = "importstep" ; //$NON-NLS-1$
  public static final String TRANSFORMATION_FILE_ELEMENT = "transformation-file" ; //$NON-NLS-1$
  public static final String TRANSFORMATION_OUTPUT_ELEMENT = "transformation-output"; //$NON-NLS-1$
  public static final String REPOSITORY_DIRECTORY = "directory"; //$NON-NLS-1$
  public static final String REPOSITORY_TRANSFORMATION = "transformation"; //$NON-NLS-1$
  public static final String[] EXPECTED_RESOURCES = new String[]{TRANSFORMATION_FILE_ELEMENT};
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    TRANSFORMATION_STEP_ELEMENT, REPOSITORY_DIRECTORY, REPOSITORY_TRANSFORMATION
  };

  protected static final String[] EXPECTED_OUTPUTS = new String[] {
    TRANSFORMATION_OUTPUT_ELEMENT
  };
  
  public KettleTransformAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public KettleTransformAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    addResourceParam(TRANSFORMATION_FILE_ELEMENT);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public boolean accepts(Element element) {
    boolean result = false;
    if (super.accepts(element)) {
      result = (element.selectSingleNode(ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + TRANSFORMATION_FILE_ELEMENT) != null) //$NON-NLS-1$
        || (element.selectSingleNode(ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + REPOSITORY_TRANSFORMATION) != null) //$NON-NLS-1$
        || (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + REPOSITORY_TRANSFORMATION) != null); //$NON-NLS-1$
    }
    return result; 
  }
  
  public String[] getExpectedOutputs() {
    return EXPECTED_OUTPUTS;
  }
  
  public String[] getExpectedResources() {
    return EXPECTED_RESOURCES;
  }
  
  public void setTransformation(String value) {
    setInputValue(REPOSITORY_TRANSFORMATION, value);
  }
  
  public String getTransformation() {
    return getComponentDefinitionValue(REPOSITORY_TRANSFORMATION);
  }
  
  public void setTransformationVariable(IActionVariable variable) {
    setReferencedVariable(REPOSITORY_TRANSFORMATION, variable);
  }
  
  public IActionVariable getTransformationVariable() {
    return getReferencedVariable(REPOSITORY_TRANSFORMATION);
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
  
  public void setImportstep(String value) {
    setInputValue(TRANSFORMATION_STEP_ELEMENT, value);
  }
  
  public String getImportstep() {
    return getComponentDefinitionValue(TRANSFORMATION_STEP_ELEMENT);
  }
  
  public void setImportstepVariable(IActionVariable variable) {
    setReferencedVariable(TRANSFORMATION_STEP_ELEMENT, variable);
  }
  
  public IActionVariable getImportstepVariable() {
    return getReferencedVariable(TRANSFORMATION_STEP_ELEMENT);
  }
  
  public void setOutputResultSetName(String name) {
    setOutputName(TRANSFORMATION_OUTPUT_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE);
  }
  
  public String getOutputResultSetName() {
    return getOutputPublicName(TRANSFORMATION_OUTPUT_ELEMENT);
  }
  
  public ActionOutput getOutputResultSetVariable() {
    return getOutputParam(TRANSFORMATION_OUTPUT_ELEMENT);
  }
}
