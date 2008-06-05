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
import org.pentaho.actionsequence.dom.IActionInputValueProvider;

public class SecureFilterAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.SecureFilterComponent"; //$NON-NLS-1$
  public static final String TARGET_ELEMENT = "target" ; //$NON-NLS-1$
  public static final String XSL_ELEMENT = "xsl"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    TARGET_ELEMENT
  };
  
  public SecureFilterAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public SecureFilterAction() {
    super(COMPONENT_NAME);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public void setTarget(IActionInputValueProvider value) {
    setActionInputValue(TARGET_ELEMENT, value);
  }
  
  public IActionInputValueProvider getTarget() {
    return getActionInputValue(TARGET_ELEMENT);
  }
  
  public void setXsl(IActionInputValueProvider value) {
    setActionInputValue(XSL_ELEMENT, value);
  }
  
  public IActionInputValueProvider getXsl() {
    return getActionInputValue(XSL_ELEMENT);
  }
}
