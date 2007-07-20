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

public class BirtReportAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.birt.BIRTReportComponent"; //$NON-NLS-1$
  public static final String OUTPUT_TYPE_ELEMENT = "output-type"; //$NON-NLS-1$
  public static final String REPORT_OUTPUT_ELEMENT = "report-output"; //$NON-NLS-1$
  public static final String REPORT_DEFINITION_ELEMENT = "report-definition"; //$NON-NLS-1$
  public static final String OUTPUT_REPORT = "output-report"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {OUTPUT_TYPE_ELEMENT};
  public static final String[] EXPECTED_RESOURCES = new String[]{REPORT_DEFINITION_ELEMENT};
  
  public BirtReportAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public BirtReportAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(OUTPUT_TYPE_ELEMENT, "html");//$NON-NLS-1$
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }

  public String[] getExpectedOutputs() {
    String privateOutputName = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(privateOutputName) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        privateOutputName = actionOutputs[0].getName();
      }
    }
    return new String[]{privateOutputName};
  }
  
  public String[] getExpectedResources() {
    return EXPECTED_RESOURCES;
  }
  
  public void setOutputType(String value) {
    setInputValue(OUTPUT_TYPE_ELEMENT, value);
  }
  
  public String getOutputType() {
    return getComponentDefinitionValue(OUTPUT_TYPE_ELEMENT);
  }
  
  public void setOutputTypeVariable(IActionVariable variable) {
    setReferencedVariable(OUTPUT_TYPE_ELEMENT, variable);
  }
  
  public IActionVariable getOutputTypeVariable() {
    return getReferencedVariable(OUTPUT_TYPE_ELEMENT);
  }
  
  public void setOutputReportName(String name) {
    setOutputName(REPORT_OUTPUT_ELEMENT, name, ActionSequenceDocument.CONTENT_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      ActionOutput[] actionOutputs = getOutputParams();
      for (int i = 0; i < actionOutputs.length; i++) {
        if (actionOutputs[i].getType().equals(ActionSequenceDocument.CONTENT_TYPE)
            && !actionOutputs[i].getName().equals(REPORT_OUTPUT_ELEMENT)) {
          actionOutputs[i].delete();
        }
      }
    }
  }
  
  public String getOutputReportName() {
    String privateOutputName = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(privateOutputName) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        privateOutputName = actionOutputs[0].getName();
      }
    }
    return getOutputPublicName(privateOutputName);
  }
  
  public ActionOutput getOutputReportVariable() {
    String privateOutputName = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(privateOutputName) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        privateOutputName = actionOutputs[0].getName();
      }
    }
    return getOutputParam(REPORT_OUTPUT_ELEMENT);
  }
}
