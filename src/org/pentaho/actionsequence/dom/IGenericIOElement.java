package org.pentaho.actionsequence.dom;


public interface IGenericIOElement extends IActionSequenceElement{

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
