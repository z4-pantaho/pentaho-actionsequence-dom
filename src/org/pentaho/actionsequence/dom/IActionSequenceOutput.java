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

package org.pentaho.actionsequence.dom;

/**
 * Convenience class used to distinguish action sequence inputs from action sequence outputs.
 * 
 * @author Angelo Rodriguez
 * 
 */
public interface IActionSequenceOutput extends IAbstractIOElement {

  public static final String IS_OUTPUT_PARAM_ATTR = "is-output-parameter";

  public IActionSequenceOutputDestination[] getDestinations();

  public IActionSequenceOutputDestination addDestination( String destination, String name );

  public boolean isOutputParameter();

  public void setOutputParameter( boolean isOutputParameter );

}
