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
package org.pentaho.actionsequence.dom;

import java.util.ArrayList;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

/**
 * A wrapper class for an action loop.
 * @author Angelo Rodriguez
 *
 */
public class ActionLoop extends ActionControlStatement implements IActionLoop {

  public ActionLoop(Element loopElement, IActionParameterMgr actionInputProvider) {
    super(loopElement, actionInputProvider);
  }

  /**
   * Set the name of the parameter that is being looped on.
   * @param loopOn the parameter name. If null the loop parameter is removed.
   */
  public void setLoopOn(String loopOn) {
    Attribute attr = controlElement.attribute(ActionSequenceDocument.LOOP_ON_NAME);
    if (loopOn == null) {
      if (attr != null) {
        attr.detach();
        ActionSequenceDocument.fireControlStatementChanged(this);
      }
    } else {
      loopOn = loopOn.trim();
      if (attr == null) {
        controlElement.addAttribute(ActionSequenceDocument.LOOP_ON_NAME, loopOn);
        attr = controlElement.attribute(ActionSequenceDocument.LOOP_ON_NAME);
        ActionSequenceDocument.fireControlStatementChanged(this);
      } else if (!loopOn.equals(attr.getValue())) {
        attr.setValue(loopOn);
        ActionSequenceDocument.fireControlStatementChanged(this);
      }
    }
  }
  
  /**
   * @return loopOn the name of the parameter that is being looped on
   */
  public String getLoopOn() {
    return controlElement.attributeValue(ActionSequenceDocument.LOOP_ON_NAME);
  }

  protected IActionSequenceValidationError[] validateThis() {
    ArrayList errors = new ArrayList();
    String loopOn = getLoopOn();
    if (loopOn.trim().length() == 0) {
      errors.add("Missing loop variable.");
    } else {
      IActionInputVariable[] actionVariables = getDocument().getAvailInputVariables(this);
      boolean isValid = false;
      for (int i = 0; (i < actionVariables.length) && !isValid; i++) {
        isValid = actionVariables[i].getVariableName().equals(loopOn);
      }
      if (!isValid) {
        errors.add("Loop references unknown variable.");
      }
    }
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
  
}
