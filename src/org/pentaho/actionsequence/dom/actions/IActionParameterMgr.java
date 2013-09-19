/*
* Copyright 2002 - 2013 Pentaho Corporation.  All rights reserved.
* 
* This software was developed by Pentaho Corporation and is provided under the terms
* of the Mozilla Public License, Version 1.1, or any later version. You may not use
* this file except in compliance with the license. If you need a copy of the license,
* please go to http://www.mozilla.org/MPL/MPL-1.1.txt. TThe Initial Developer is Pentaho Corporation.
*
* Software distributed under the Mozilla Public License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to
* the license for the specific language governing your rights and limitations.
*/

package org.pentaho.actionsequence.dom.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionResource;
import org.pentaho.commons.connection.IPentahoStreamSource;

public interface IActionParameterMgr {
  public Object getInputValue(IActionInput actionInput);
  public String replaceParameterReferences(String inputString);
  public IPentahoStreamSource getDataSource(IActionResource actionResource) throws FileNotFoundException;
  public InputStream getInputStream(IActionResource actionResource) throws FileNotFoundException;
  public IPentahoStreamSource getDataSource(IActionInput actionInput);
  public void setOutputValue(IActionOutput actionOutput, Object value);
  public String getString(IActionResource actionResource) throws IOException;
}
