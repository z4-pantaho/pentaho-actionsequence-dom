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

import java.util.ArrayList;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionVariable;

public class PrintMapValsAction extends ActionDefinition {


  public static final String COMPONENT_NAME = "org.pentaho.component.UtilityComponent"; //$NON-NLS-1$
  public static final String PRINT_MAP_VALS_COMMAND = "getmapvalues"; //$NON-NLS-1$
  public static final String TARGET_MAP_XPATH = "getmapvalues/property-map"; //$NON-NLS-1$
  public static final String PROPERTY_MAP_ELEMENT = "property-map"; //$NON-NLS-1$
  public static final String MAP_KEY_XPATH = "getmapvalues/arg"; //$NON-NLS-1$
  public static final String KEY_PARAM_PREFIX = "key"; //$NON-NLS-1$

  public PrintMapValsAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public PrintMapValsAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(PRINT_MAP_VALS_COMMAND, "");//$NON-NLS-1$
  }
  
  public static boolean accepts(Element element) {
    boolean accepts = false;
    if (ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME)) {
      accepts = (element.selectNodes(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + PRINT_MAP_VALS_COMMAND).size() == 1)  //$NON-NLS-1$
          && (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + FormatMsgAction.FORMAT_MSG_COMMAND) == null)  //$NON-NLS-1$
          && (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + PrintParamAction.PRINT_PARAMS_COMMAND) == null)  //$NON-NLS-1$
          && (element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + CopyParamAction.COPY_PARAM_COMMAND) == null);       //$NON-NLS-1$
    }
    return accepts;
  }
  
  public void setMapParam(IActionVariable variable) {
    String mapParamName = getComponentDefinitionValue(TARGET_MAP_XPATH);
    if (variable == null) {
      setInputParam(mapParamName, (IActionVariable)null);
      setComponentDefinition(TARGET_MAP_XPATH, "", false);
    } else {
      if (!PROPERTY_MAP_ELEMENT.equals(mapParamName)) {
        setComponentDefinition(TARGET_MAP_XPATH, PROPERTY_MAP_ELEMENT, false);
      }
      setInputParam(PROPERTY_MAP_ELEMENT, variable);
    }   
  }
  
  public ActionInput getMapParam() {
    String mapParamName = getComponentDefinitionValue(TARGET_MAP_XPATH);
    ActionInput actionInput = null;
    if ((mapParamName == null) || (mapParamName.trim().length() == 0)) {
      actionInput = getInputParam(mapParamName);
    }
    return actionInput;
  }
  
  public Object[] getKeys() {
    return getKeys(true);
  }
  
  public Object[] getKeys(boolean evaluateInputParams) {
    ArrayList keys = new ArrayList();
    Element[] elements = getComponentDefElements(MAP_KEY_XPATH);
    for (int i = 0; i < elements.length; i++) {
      String keyParamName = elements[i].getText();
      ActionInput keyInputParam = getInputParam(keyParamName);
      if (keyInputParam != null) {
        if (evaluateInputParams) {
          Object key = keyInputParam.getValue();
          if (key != null) {
            keys.add(key);
          }
        } else {
          keys.add(keyInputParam);
        }
      }
    }
    return keys.toArray();
  }
  
  public void setKeys(Object[] keys) {
    Object[] oldKeys = getKeys(false);
    for (int i = 0; i < oldKeys.length; i++) {
      if (oldKeys[i] instanceof ActionInput) {
        ((ActionInput)oldKeys[i]).delete();
      }
    }
    setComponentDefinition(MAP_KEY_XPATH, new String[0]);
    
    ArrayList keyParamNames = new ArrayList();
    for (int i = 0; i < keys.length; i++) {
      if (keys[i] instanceof IActionVariable) {
        IActionVariable actionVariable = (IActionVariable)keys[i];
        keyParamNames.add(actionVariable.getVariableName());
        setInputParam(actionVariable.getVariableName(), actionVariable);
      } else {
        String keyParamName = getUniqueNameParam();
        keyParamNames.add(keyParamName);
        setInputValue(keyParamName, keys[i].toString());
      }
    }
    if (keyParamNames.size() > 0) {
      setComponentDefinition(MAP_KEY_XPATH, (String[])keyParamNames.toArray(new String[0]));
    }
  }
  
  private String getUniqueNameParam() {
    String name = null;
    boolean isUnique = false;
    for (int i = 1; !isUnique; i++) {
      name = KEY_PARAM_PREFIX + i;
      isUnique = (getInputParam(name) == null) && (getComponentDefElement(name) == null);
    }
    return name;
  }
}
