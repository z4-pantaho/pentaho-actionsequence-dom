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

public class CopyParamAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.UtilityComponent"; //$NON-NLS-1$
  public static final String COPY_FROM_ELEMENT = "copy-from"; //$NON-NLS-1$
  public static final String COPY_TO_ELEMENT = "copy-to"; //$NON-NLS-1$
  public static final String COPY_FROM_XPATH = "copy/from"; //$NON-NLS-1$
  public static final String COPY_RETURN_XPATH = "copy/return"; //$NON-NLS-1$
  public static final String COPY_PARAM_COMMAND = "copy"; //$NON-NLS-1$
  public static final String OUTPUT_COPY = "output-copy"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    COPY_FROM_ELEMENT
  };
  
  public CopyParamAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public CopyParamAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(COPY_FROM_XPATH, COPY_FROM_ELEMENT);
    setComponentDefinition(COPY_RETURN_XPATH, COPY_TO_ELEMENT);
  }
  
  public String[] getExpectedInputs() {
    String inputName = getComponentDefinitionValue(COPY_FROM_XPATH); 
    if ((inputName == null) || (inputName.trim().length() == 0)) {
      inputName = COPY_FROM_ELEMENT;
    }  
    return new String[]{inputName};
  }
  
  public String[] getExpectedOutputs() {
    String outputName = getComponentDefinitionValue(COPY_RETURN_XPATH);
    if ((outputName == null) || (outputName.trim().length() == 0)) {
      outputName = COPY_TO_ELEMENT;
    }  
    return new String[]{outputName};
  }

  public boolean accepts(Element element) {
    boolean accepts = false;
    if (super.accepts(element)) {
      accepts = (element.selectNodes(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + COPY_PARAM_COMMAND).size() == 1)  //$NON-NLS-1$
          && (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + FormatMsgAction.FORMAT_MSG_COMMAND) == null)  //$NON-NLS-1$
          && (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + PrintParamAction.PRINT_PARAMS_COMMAND) == null)  //$NON-NLS-1$
          && (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + PrintMapValsAction.PRINT_MAP_VALS_COMMAND) == null);       //$NON-NLS-1$
    }
    return accepts;
  }
  
  public void setCopyFromVariable(IActionVariable variable) {
    if (!COPY_FROM_ELEMENT.equals(getComponentDefinitionValue(CopyParamAction.COPY_FROM_XPATH))) {
      setComponentDefinition(CopyParamAction.COPY_FROM_XPATH, COPY_FROM_ELEMENT, false);
    }
    setReferencedVariable(COPY_FROM_ELEMENT, variable);
    ActionOutput actionOutput = getOutputCopyVariable();
    if (actionOutput != null) {
      actionOutput.setType(variable.getType());
    }
  }
  
  public IActionVariable getCopyFromVariable() {
    String copyFromVarName = getComponentDefinitionValue(CopyParamAction.COPY_FROM_XPATH);
    if ((copyFromVarName == null) || (copyFromVarName.trim().length() == 0)) {
      copyFromVarName = COPY_FROM_ELEMENT;
    }
    return getReferencedVariable(copyFromVarName);
  }
  
  public void setOutputCopyName(String name) {
    String privateName = getComponentDefinitionValue(COPY_RETURN_XPATH);
    if ((privateName == null) || (privateName.trim().length() == 0)) {
      privateName = COPY_TO_ELEMENT;
    }
    IActionVariable copyFrom = getCopyFromVariable();
    ActionOutput actionOutput = setOutputName(privateName, name, copyFrom != null ? copyFrom.getType() : ActionSequenceDocument.STRING_TYPE);
    if (actionOutput == null) {
      setComponentDefinition(COPY_RETURN_XPATH, (String)null);
    } else {
      setComponentDefinition(COPY_RETURN_XPATH, privateName);
    }
  }
  
  public String getOutputCopyName() {
    String privateName = getComponentDefinitionValue(COPY_RETURN_XPATH);
    if ((privateName == null) || (privateName.trim().length() == 0)) {
      privateName = COPY_TO_ELEMENT;
    }  
    return getOutputPublicName(privateName);
  }
  
  public ActionOutput getOutputCopyVariable() {
    String privateName = getComponentDefinitionValue(COPY_RETURN_XPATH);
    if ((privateName == null) || (privateName.trim().length() == 0)) {
      privateName = COPY_TO_ELEMENT;
    }  
    return getOutputParam(privateName);
  }
}
