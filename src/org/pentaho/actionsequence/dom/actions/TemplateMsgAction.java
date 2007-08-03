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
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionVariable;

public class TemplateMsgAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.TemplateComponent"; //$NON-NLS-1$
  public static final String TEMPLATE_ELEMENT = "template" ; //$NON-NLS-1$
  public static final String OUTPUT_MSG_ELEMENT = "output-message" ; //$NON-NLS-1$
  public static final String OUTPUT_STRING = "output-string"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    TEMPLATE_ELEMENT
  };
  protected static final String[] EXPECTED_RESOURCES = new String[] {
    TEMPLATE_ELEMENT
  };

  public TemplateMsgAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public TemplateMsgAction() {
    super(COMPONENT_NAME);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getExpectedOutputs() {
    String expectedOutput = OUTPUT_MSG_ELEMENT;
    if (getOutputParam(expectedOutput) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.STRING_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }
  
  public String[] getExpectedResources() {
    return EXPECTED_RESOURCES;
  }

  protected void initNewActionDefinition() {
    setInputValue(TEMPLATE_ELEMENT, ""); //$NON-NLS-1$
  }

  public void setTemplate(String value) {
    setInputValue(TEMPLATE_ELEMENT, value);
  }
  
  public String getTemplate() {
    return getComponentDefinitionValue(TEMPLATE_ELEMENT);
  }
  
  public void setTemplateParam(IActionVariable variable) {
    setReferencedVariable(TEMPLATE_ELEMENT, variable);
  }
  
  public ActionInput getTemplateParam() {
    return getInputParam(TEMPLATE_ELEMENT);
  }
  
  public void setOutputStringName(String name) {
    setOutputName(OUTPUT_MSG_ELEMENT, name, ActionSequenceDocument.STRING_TYPE);
  }
  
  public String getOutputStringName() {
    return getOutputPublicName(OUTPUT_MSG_ELEMENT);
  }
  
  public ActionOutput getOutputStringParam() {
    return getOutputParam(OUTPUT_MSG_ELEMENT);
  }
}
