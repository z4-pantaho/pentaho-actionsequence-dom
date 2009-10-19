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
