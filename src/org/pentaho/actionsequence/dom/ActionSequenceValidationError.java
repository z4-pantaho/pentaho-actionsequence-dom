package org.pentaho.actionsequence.dom;

public class ActionSequenceValidationError {
  public static final int INPUT_OK = 0;;
  public static final int INPUT_MISSING = 1;
  public static final int INPUT_REFERENCES_UNKNOWN_VAR = 2;
  public static final int INPUT_UNINITIALIZED = 3;
  public static final int OUTPUT_MISSING = 4;
  
  public int errorCode;
  public String errorMsg;
  public ActionDefinition actionDefinition;
  public String parameterName;
}
