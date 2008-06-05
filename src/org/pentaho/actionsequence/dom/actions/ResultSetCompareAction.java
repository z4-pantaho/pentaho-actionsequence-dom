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
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInputValueProvider;
import org.pentaho.actionsequence.dom.IActionInputVariable;
import org.pentaho.actionsequence.dom.IActionOutput;

public class ResultSetCompareAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.ResultSetCompareComponent"; //$NON-NLS-1$
  public static final String COMPARE_FROM_ELEMENT = "result-set-from"; //$NON-NLS-1$
  public static final String COMPARE_TO_ELEMENT = "result-set-to"; //$NON-NLS-1$
  public static final String COMPARE_COLUMN_ELEMENT = "compare-column"; //$NON-NLS-1$
  public static final String OUTPUT_MISMATCHES_ELEMENT = "output_mismatches"; //$NON-NLS-1$
  public static final String STOP_ON_ERROR_ELEMENT = "stop-on-error"; //$NON-NLS-1$
  public static final String COMPARE_RESULT_ELEMENT = "compare-result"; //$NON-NLS-1$
  public static final String OUTPUT_COMPARE_RESULT = "output-compare-result"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    COMPARE_FROM_ELEMENT,
    COMPARE_COLUMN_ELEMENT,
    OUTPUT_MISMATCHES_ELEMENT,
    STOP_ON_ERROR_ELEMENT,
    COMPARE_TO_ELEMENT
  };
  
  public ResultSetCompareAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public ResultSetCompareAction() {
    super(COMPONENT_NAME);
  }

  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(COMPARE_RESULT_ELEMENT, COMPARE_RESULT_ELEMENT);
    setComponentDefinition(STOP_ON_ERROR_ELEMENT, Boolean.TRUE.toString());
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getReservedOutputNames() {
    String expectedOutput = COMPARE_RESULT_ELEMENT;
    String compDefVal = getComponentDefinitionValue(COMPARE_RESULT_ELEMENT);
    if (compDefVal != null) {
      expectedOutput = compDefVal;
    }
    return new String[]{expectedOutput};
  }
  
  public void setCompareColumnNum(IActionInputVariable value) {
    setActionInputValue(COMPARE_COLUMN_ELEMENT, value);
  }
  
  public IActionInputValueProvider getCompareColumnNum() {
    return getActionInputValue(COMPARE_COLUMN_ELEMENT);
  }
  
  public void setResultSet1(IActionInputVariable variable) {
    setActionInputValue(COMPARE_FROM_ELEMENT, variable);
  }
  
  public IActionInputValueProvider getResultSet1() {
    return getActionInputValue(COMPARE_FROM_ELEMENT);
  }
  
  public void setResultSet2(IActionInputVariable variable) {
    setActionInputValue(COMPARE_TO_ELEMENT, variable);
  }
  
  public IActionInputValueProvider getResultSet2() {
    return getActionInputValue(COMPARE_TO_ELEMENT);
  }

  public void setOutputMismatches(IActionInputValueProvider value) {
    setActionInputValue(OUTPUT_MISMATCHES_ELEMENT, value.getBooleanValue() ? new ActionInputConstant("true") : new ActionInputConstant("false")); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public IActionInputValueProvider getOutputMismatches() {
    return getActionInputValue(OUTPUT_MISMATCHES_ELEMENT);    
  }
  
  public void setStopOnError(IActionInputValueProvider value) {
    setInputValue(STOP_ON_ERROR_ELEMENT, value.getBooleanValue(true) ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public IActionInputValueProvider getStopOnError() {
    return getActionInputValue(STOP_ON_ERROR_ELEMENT);    
  }

  public void setOutputCompareResult(String publicOutputName) {
    String privateName = getComponentDefinitionValue(COMPARE_RESULT_ELEMENT);
    if ((privateName == null) || (privateName.trim().length() == 0)) {
      privateName = COMPARE_RESULT_ELEMENT;
    }  
    IActionOutput actionOutput = setOutputParam(privateName, publicOutputName, ActionSequenceDocument.STRING_TYPE);
    if (actionOutput == null) {
      setComponentDefinition(COMPARE_RESULT_ELEMENT, (String)null);
    } else {
      setComponentDefinition(COMPARE_RESULT_ELEMENT, privateName);
    }
  }
  
  public IActionOutput getOutputCompareResult() {
    String privateName = getComponentDefinitionValue(COMPARE_RESULT_ELEMENT);
    if ((privateName == null) || (privateName.trim().length() == 0)) {
      privateName = COMPARE_RESULT_ELEMENT;
    }  
    return getOutputParam(privateName);
  }
}
