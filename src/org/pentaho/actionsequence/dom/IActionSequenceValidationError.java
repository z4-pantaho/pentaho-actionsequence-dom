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

public interface IActionSequenceValidationError {
  public static final int INPUT_OK = 0;;
  public static final int INPUT_MISSING = 1;
  public static final int INPUT_REFERENCES_UNKNOWN_VAR = 2;
  public static final int INPUT_UNINITIALIZED = 3;
  public static final int OUTPUT_MISSING = 4;

  public int getErrorCode();

  public String getErrorMsg();

  public IActionDefinition getActionDefinition();

  public String getParameterName();
}
