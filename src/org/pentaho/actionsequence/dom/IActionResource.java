package org.pentaho.actionsequence.dom;

import java.io.FileNotFoundException;
import java.net.URI;

import javax.activation.DataSource;

public interface IActionResource {
  public URI getUri();
  public void setURI(URI uri);
  public String getMimeType();
  public void setMimeType(String mimeType);
  public DataSource getDataSource() throws FileNotFoundException;
}
