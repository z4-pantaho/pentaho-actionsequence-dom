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
import org.pentaho.actionsequence.dom.IActionVariable;

public class SubActionAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.SubActionComponent"; //$NON-NLS-1$
  public static final String SOLUTION_ELEMENT = "solution" ; //$NON-NLS-1$
  public static final String PATH_ELEMENT = "path" ; //$NON-NLS-1$
  public static final String ACTION_ELEMENT = "action" ; //$NON-NLS-1$
  public static final String PROXY_ELEMENT = "session-proxy" ; //$NON-NLS-1$
  public static final String PROXY_REF_ELEMENT = "session-proxy-ref"; //$NON-NLS-1$
  protected static final String[] EXPECTED_INPUTS = new String[] {
    SOLUTION_ELEMENT,
    PATH_ELEMENT,
    ACTION_ELEMENT,
    PROXY_ELEMENT,
    PROXY_REF_ELEMENT
  };
  
  public SubActionAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public SubActionAction() {
    super(COMPONENT_NAME);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public void setSolution(String value) {
    setInputValue(SOLUTION_ELEMENT, value);
  }
  
  public String getSolution() {
    return getComponentDefinitionValue(SOLUTION_ELEMENT);
  }
  
  public void setSolutionParam(IActionVariable variable) {
    setReferencedVariable(SOLUTION_ELEMENT, variable);
  }
  
  public ActionInput getSolutionParam() {
    return getInputParam(SOLUTION_ELEMENT);
  }
  
  public void setPath(String value) {
    setInputValue(PATH_ELEMENT, value);
  }
  
  public String getPath() {
    return getComponentDefinitionValue(PATH_ELEMENT);
  }
  
  public void setPathParam(IActionVariable variable) {
    setReferencedVariable(PATH_ELEMENT, variable);
  }
  
  public ActionInput getPathParam() {
    return getInputParam(PATH_ELEMENT);
  }

  public void setAction(String value) {
    setInputValue(ACTION_ELEMENT, value);
  }
  
  public String getAction() {
    return getComponentDefinitionValue(ACTION_ELEMENT);
  }
  
  public void setActionParam(IActionVariable variable) {
    setReferencedVariable(ACTION_ELEMENT, variable);
  }
  
  public ActionInput getActionParam() {
    return getInputParam(ACTION_ELEMENT);
  }
  
  public void setSessionProxy(String value) {
    setInputValue(PROXY_REF_ELEMENT, value);
    if (value == null) {
      setInputValue(PROXY_ELEMENT, null);
    } else {
      setInputValue(PROXY_ELEMENT, PROXY_REF_ELEMENT);
    }
  }
  
  public String getSessionProxy() {
    return getComponentDefinitionValue(PROXY_REF_ELEMENT);
  }
  
  public void setSessionProxyParam(IActionVariable variable) {
    setReferencedVariable(PROXY_REF_ELEMENT, variable);
    if (variable == null) {
      setInputValue(PROXY_ELEMENT, null);
    } else {
      setInputValue(PROXY_ELEMENT, PROXY_REF_ELEMENT);
    }
  }
  
  public ActionInput getSessionProxyParam() {
    return getInputParam(PROXY_REF_ELEMENT);
  }
}
