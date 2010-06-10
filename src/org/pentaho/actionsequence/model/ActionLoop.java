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
package org.pentaho.actionsequence.model;

import java.io.Serializable;


/**
 * A wrapper class for an action loop.
 * @author Angelo Rodriguez
 *
 */
public class ActionLoop extends ActionControlStatement implements IActionLoop, Serializable {

  String loopOn;
  Boolean loopUsingPeek;
  IActionSequenceDocument parent;
  
  ActionLoop(IActionSequenceDocument parent) {
    super(null);
    this.parent = parent;
  }
  
  public ActionLoop(IActionControlStatement parent) {
    super(parent);
  }

  public String getLoopOn() {
    return loopOn;
  }

  public void setLoopOn(String loopOn) {
    this.loopOn = loopOn;
  }

  public Boolean getLoopUsingPeek() {
    return loopUsingPeek;
  }

  public void setLoopUsingPeek(Boolean loopUsingPeek) {
    this.loopUsingPeek = loopUsingPeek;
  }

  public IActionSequenceDocument getActionSequence() {
    IActionSequenceDocument actionSequence = null;
    if (this.parent != null) {
      actionSequence = parent;
    } else {
      actionSequence = super.getActionSequence();
    }
    return actionSequence;
  }
}
