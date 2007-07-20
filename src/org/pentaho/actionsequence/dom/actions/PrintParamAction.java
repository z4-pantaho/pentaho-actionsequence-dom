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
import org.pentaho.actionsequence.dom.IActionSequenceElement;

public class PrintParamAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.UtilityComponent"; //$NON-NLS-1$
  public static final String PRINT_PARAMS_COMMAND = "print"; //$NON-NLS-1$
  public static final String DELIMITER_ELEMENT = "delimiter"; //$NON-NLS-1$
  
  public PrintParamAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public PrintParamAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(PRINT_PARAMS_COMMAND, "");//$NON-NLS-1$
  }
  
  public boolean accepts(Element element) {
    boolean accepts = false;
    if (super.accepts(element)) {
      accepts = (element.selectNodes(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + PRINT_PARAMS_COMMAND).size() == 1)  //$NON-NLS-1$
          && (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + FormatMsgAction.FORMAT_MSG_COMMAND) == null)  //$NON-NLS-1$
          && (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + PrintMapValsAction.PRINT_MAP_VALS_COMMAND) == null)  //$NON-NLS-1$
          && (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + CopyParamAction.COPY_PARAM_COMMAND) == null);       //$NON-NLS-1$
    }
    return accepts;
  }
  
  public boolean accepts(IActionSequenceElement actionDef) {
    return actionDef instanceof PrintParamAction;
  }
  
  public void setDelimiter(String value) {
    if (value != null) {
      value = value.trim();
      if (value.length() > 0) {
        if (!value.startsWith("\"") || !value.endsWith("\"")) { //$NON-NLS-1$ //$NON-NLS-2$
          value = "\"" + value + "\""; //$NON-NLS-1$ //$NON-NLS-2$
        }
      } else {
        value = null;
      }
    }
    setComponentDefinition(PRINT_PARAMS_COMMAND + "/" + DELIMITER_ELEMENT, value); //$NON-NLS-1$
  }
  
  public String getDelimiter() {
    String delimiter = getComponentDefinitionValue(PRINT_PARAMS_COMMAND + "/" + DELIMITER_ELEMENT); //$NON-NLS-1$
    if (delimiter != null) {
      if (delimiter.startsWith("\"") && delimiter.endsWith("\"")) { //$NON-NLS-1$ //$NON-NLS-2$
        if (delimiter.length() < 3) {
          delimiter = ""; //$NON-NLS-1$
        } else {
          delimiter = delimiter.substring(1, delimiter.length() - 1);
        }
      }
    }
    return delimiter;
  }
}
