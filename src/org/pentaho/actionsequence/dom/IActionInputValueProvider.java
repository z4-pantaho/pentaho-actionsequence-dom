package org.pentaho.actionsequence.dom;

public interface IActionInputValueProvider {
  public static final ActionInputConstant NULL_INPUT = new ActionInputConstant((Object)null);
  
  public Object getValue();
  public String getStringValue();
  public String getStringValue(String defaultValue);
  public String getStringValue(boolean replaceParamReferences);
  public String getStringValue(boolean replaceParamReferences, String defaultValue);
  public Boolean getBooleanValue();
  public boolean getBooleanValue(boolean defaultValue);
  public Integer getIntValue();
  public int getIntValue(int defaultValue);
}
