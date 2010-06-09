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
package org.pentaho.actionsequence;


/**
 * A wrapper class for an action sequence input or output element.
 * @author Angelo Rodriguez
 *
 */
public interface IAbstractIOElement  {

  public static final String TYPE_NAME = "type"; //$NON-NLS-1$
  
  public String getName();
  
  /**
   * Sets the name of the action sequence input/output
   * @param ioName the input/output name
   */
  public void setName(String ioName);
  
  /**
   * @return the type of input/output 
   */
  public String getType();
  
  /**
   * Sets the type of the IO type.
   * @param ioType the io type
   */
  public void setType(String ioType);
}
