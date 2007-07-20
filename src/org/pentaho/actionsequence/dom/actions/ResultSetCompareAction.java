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

public class ResultSetCompareAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.ResultSetCompareComponent"; //$NON-NLS-1$
  public static final String COMPARE_FROM_ELEMENT = "result-set-from"; //$NON-NLS-1$
  public static final String COMPARE_TO_ELEMENT = "result-set-to"; //$NON-NLS-1$
  public static final String COMPARE_COLUMN_ELEMENT = "compare-column"; //$NON-NLS-1$
  public static final String OUTPUT_MISMATCHES_ELEMENT = "output_mismatches"; //$NON-NLS-1$
  public static final String STOP_ON_ERROR_ELEMENT = "stop-on-error"; //$NON-NLS-1$
  public static final String COMPARE_RESULT_ELEMENT = "compare-result"; //$NON-NLS-1$
  public static final String OUTPUT_STRING = "output-string"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    COMPARE_FROM_ELEMENT,
    COMPARE_COLUMN_ELEMENT,
    OUTPUT_MISMATCHES_ELEMENT,
    STOP_ON_ERROR_ELEMENT,
    COMPARE_TO_ELEMENT
  };
  
  public ResultSetCompareAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public ResultSetCompareAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(COMPARE_RESULT_ELEMENT, COMPARE_RESULT_ELEMENT);
    setComponentDefinition(STOP_ON_ERROR_ELEMENT, Boolean.TRUE.toString());
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getExpectedOutputs() {
    String expectedOutput = COMPARE_RESULT_ELEMENT;
    String compDefVal = getComponentDefinitionValue(COMPARE_RESULT_ELEMENT);
    if (compDefVal != null) {
      expectedOutput = compDefVal;
    }
    return new String[]{expectedOutput};
  }
  
  public void setCompareColumn(String value) {
    setInputValue(COMPARE_COLUMN_ELEMENT, value);
  }
  
  public String getCompareColumn() {
    return getComponentDefinitionValue(COMPARE_COLUMN_ELEMENT);
  }
  
  public void setCompareColumnVariable(IActionVariable variable) {
    setReferencedVariable(COMPARE_COLUMN_ELEMENT, variable);
  }
  
  public IActionVariable getCompareColumnVariable() {
    return getReferencedVariable(COMPARE_COLUMN_ELEMENT);
  }
  
  public void setResultSetFromVariable(IActionVariable variable) {
    setReferencedVariable(COMPARE_FROM_ELEMENT, variable);
  }
  
  public IActionVariable getResultSetFromVariable() {
    return getReferencedVariable(COMPARE_FROM_ELEMENT);
  }
  
  public void setResultSetToVariable(IActionVariable variable) {
    setReferencedVariable(COMPARE_TO_ELEMENT, variable);
  }
  
  public IActionVariable getResultSetToVariable() {
    return getReferencedVariable(COMPARE_TO_ELEMENT);
  }

  public void setOutputMismatches(boolean value) {
    setInputValue(OUTPUT_MISMATCHES_ELEMENT, value ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getOutputMismatches() {
    String value = getComponentDefinitionValue(OUTPUT_MISMATCHES_ELEMENT);
    return (value != null) && value.trim().toLowerCase().equals("true"); //$NON-NLS-1$
  }
  
  public void setOutputMismatchesVariable(IActionVariable variable) {
    setReferencedVariable(OUTPUT_MISMATCHES_ELEMENT, variable);
  }
  
  public IActionVariable getOutputMismatchesVariable() {
    return getReferencedVariable(OUTPUT_MISMATCHES_ELEMENT);
  }
  
  public void setStopOnError(boolean value) {
    setInputValue(STOP_ON_ERROR_ELEMENT, value ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getStopOnError() {
    String value = getComponentDefinitionValue(STOP_ON_ERROR_ELEMENT);
    return (value != null) && value.trim().toLowerCase().equals("true"); //$NON-NLS-1$
  }
  
  public void setStopOnErrorVariable(IActionVariable variable) {
    setReferencedVariable(STOP_ON_ERROR_ELEMENT, variable);
  }
  
  public IActionVariable getStopOnErrorVariable() {
    return getReferencedVariable(STOP_ON_ERROR_ELEMENT);
  }
  
  public void setOutputStringName(String name) {
    String privateName = getComponentDefinitionValue(COMPARE_RESULT_ELEMENT);
    if ((privateName == null) || (privateName.trim().length() == 0)) {
      privateName = COMPARE_RESULT_ELEMENT;
    }  
    ActionOutput actionOutput = setOutputName(privateName, name, ActionSequenceDocument.STRING_TYPE);
    if (actionOutput == null) {
      setComponentDefinition(COMPARE_RESULT_ELEMENT, (String)null);
    } else {
      setComponentDefinition(COMPARE_RESULT_ELEMENT, privateName);
    }
  }
  
  public String getOutputStringName() {
    String privateName = getComponentDefinitionValue(COMPARE_RESULT_ELEMENT);
    if ((privateName == null) || (privateName.trim().length() == 0)) {
      privateName = COMPARE_RESULT_ELEMENT;
    }  
    return getOutputPublicName(privateName);
  }
  
  public ActionOutput getOutputStringVariable() {
    String privateName = getComponentDefinitionValue(COMPARE_RESULT_ELEMENT);
    if ((privateName == null) || (privateName.trim().length() == 0)) {
      privateName = COMPARE_RESULT_ELEMENT;
    }  
    return getOutputParam(privateName);
  }
}
