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


public class ActionSequenceInputSource implements IActionSequenceInputSource {
  
  IActionSequenceInput parent;
  String name;
  String origin;
  
  protected ActionSequenceInputSource(IActionSequenceInput parent) {
    this.parent = parent;
    
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }
  
  public String getOrigin() {
    return origin;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public IActionSequenceInput getActionSequenceInput() {
    return parent;
  }
  
}
