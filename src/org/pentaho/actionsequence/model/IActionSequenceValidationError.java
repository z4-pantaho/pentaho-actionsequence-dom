package org.pentaho.actionsequence.model;


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
