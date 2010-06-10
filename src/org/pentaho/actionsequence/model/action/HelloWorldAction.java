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
package org.pentaho.actionsequence.model.action;

import org.pentaho.actionsequence.model.ActionDefinition;
import org.pentaho.actionsequence.model.IActionControlStatement;
import org.pentaho.actionsequence.model.IActionInput;
import org.pentaho.actionsequence.model.IActionInputSource;

public class HelloWorldAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.HelloWorldComponent"; //$NON-NLS-1$
  public static final String QUOTE_ELEMENT = "quote"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    QUOTE_ELEMENT
  };
  
  public HelloWorldAction(IActionControlStatement parent) {
    super(parent);
  }

  public HelloWorldAction() {
    super(COMPONENT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public void setQuote(IActionInputSource value) {
    setActionInputValue(QUOTE_ELEMENT, value);
  }
  
  public IActionInput getQuote() {
    return getInput(QUOTE_ELEMENT);
  }
  
}
