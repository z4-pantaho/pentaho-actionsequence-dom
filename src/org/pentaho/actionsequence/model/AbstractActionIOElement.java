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


/**
 * A wrapper class for an action definition input or output element.
 * @author Angelo Rodriguez
 *
 */
public abstract class AbstractActionIOElement extends AbstractIOElement implements IActionIOElement {
  String mapping;
  IActionDefinition actionDefinition;

  public AbstractActionIOElement(IActionDefinition parent) {
    this.actionDefinition = parent;
  }

  
  /**
   * @return the name to which the input/output is mapped. Returns an empty string if 
   * the input/output is not mapped.
   */
  public String getMapping() {
    return mapping == null || mapping.equals(getName()) ? "" : mapping;
  }
  
  /**
   * Sets the name to which the input/output is mapped. 
   * @param mapping the mapped name. If null any existing mapping is removed.
   */
  public void setMapping(String mapping) {
    if ((mapping == null) || (mapping.trim().length() == 0) || mapping.trim().equals(getName())) {
      if (this.mapping != null) {
        this.mapping = null;
      }
    } else {
      mapping = mapping.trim();
      if (!mapping.equals(this.mapping)) {
        this.mapping = mapping;
      }
    }
  }
  
  /**
   * @return the action definition to which this input/output belongs.
   */
  public IActionDefinition getActionDefinition() {
    return actionDefinition;
  }
}
