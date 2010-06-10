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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A wrapper class for an action definition input or output element.
 * @author Angelo Rodriguez
 *
 */
public class ActionSequenceInput extends AbstractIOElement implements IActionSequenceInput {

  public static final int REQUEST_INPUT_SOURCE_ID = 1;
  public static final int SESSION_INPUT_SOURCE_ID = 2;
  public static final int RUNTIME_INPUT_SOURCE_ID = 3;
  public static final int GLOBAL_INPUT_SOURCE_ID = 4;
  
  IActionSequenceDocument parent;
  Object defaultValue;
  ArrayList<IActionSequenceInputSource> sources = new ArrayList<IActionSequenceInputSource>();
  
  
  public ActionSequenceInput(IActionSequenceDocument parent) {
    this.parent = parent;
  }
  
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.ActionSequenceIO#setType(java.lang.String)
   */
  public void setType(String ioType) {
    if ((ioType != null) && !ioType.equals(getType())) {
      super.setType(ioType);
      defaultValue = null;
    }
  }
  
  /**
   * Sets the input default value.
   * @param defValue the default value
   */
  public void setDefaultValue(String defValue) {
    defaultValue = defValue;
  }
  
  /**
   * Sets the input default value.
   * @param defValue the default value
   */
  public void setDefaultValue(String[] defValue) {
    defaultValue = defValue;
  }
  
  /**
   * Sets the input default value.
   * @param paramMap the default value
   */
  public void setDefaultValue(HashMap paramMap) {
    defaultValue = paramMap;
  }
  
//  /**
//   * Sets the input default value.
//   * @param defValue the default value
//   */
//  public void setDefaultValue(TableModel defValue) {
//    defaultValue = defValue;
//  }
  
  /**
   * @return the default value or null if none exists.
   */
  public Object getDefaultValue() {
    return defaultValue;
  }
  
  public List<IActionSequenceInputSource> getSources() {
    return sources;
  }
  
  public IActionSequenceInputSource addSource(String origin, String name) {
    IActionSequenceInputSource actionSequenceInputSource = new ActionSequenceInputSource(this);
    actionSequenceInputSource.setOrigin(origin);
    actionSequenceInputSource.setName(name);
    sources.add(actionSequenceInputSource);
    return actionSequenceInputSource;
  }
  
  public IActionSequenceInputSource addSource(int index, String origin, String name) {
    if (index >= getSources().size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    IActionSequenceInputSource actionSequenceInputSource = new ActionSequenceInputSource(this);
    actionSequenceInputSource.setOrigin(origin);
    actionSequenceInputSource.setName(name);
    sources.add(index, actionSequenceInputSource);
    return actionSequenceInputSource;
  }


  public String getVariableName() {
    return getName();
  }

}
