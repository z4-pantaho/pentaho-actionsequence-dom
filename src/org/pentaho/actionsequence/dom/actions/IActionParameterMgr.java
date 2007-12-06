package org.pentaho.actionsequence.dom.actions;

import java.io.FileNotFoundException;

import javax.activation.DataSource;

import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionResource;

public interface IActionParameterMgr {
  public Object getInputValue(ActionInput actionInput);
  public String replaceParameterReferences(String inputString);
  public DataSource getDataSource(ActionResource actionResource) throws FileNotFoundException;
  public DataSource getDataSource(ActionInput actionInput);
  public void setOutputValue(ActionOutput actionOutput, Object value);
}
