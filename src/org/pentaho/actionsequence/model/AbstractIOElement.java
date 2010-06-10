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
 * A wrapper class for an action sequence input or output element.
 * @author Angelo Rodriguez
 *
 */
public abstract class AbstractIOElement implements IAbstractIOElement {

  String name;
  String type;
  
  /**
   * @return the name of the action sequence input/output
   */
  public String getName() {
    return name;
  }
  
  /**
   * Sets the name of the action sequence input/output
   * @param ioName the input/output name
   */
  public void setName(String ioName) {
    ioName = ioName.trim();
    if (ioName.split("\\s+").length > 1) { //$NON-NLS-1$
      throw new IllegalArgumentException("No spaces allowed in name"); //$NON-NLS-1$
    }
    if (!ioName.equals(getName())) {
      this.name = ioName;
    }
  }
  
  /**
   * @return the type of input/output 
   */
  public String getType() {
    return type;
  }
  
  /**
   * Sets the type of the IO type.
   * @param ioType the io type
   */
  public void setType(String ioType) {
    if (!ioType.equals(type)) {
      type = ioType;
    }
  }
}
