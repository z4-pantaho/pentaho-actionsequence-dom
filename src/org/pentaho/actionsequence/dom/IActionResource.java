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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import org.dom4j.Element;
import org.pentaho.commons.connection.IPentahoStreamSource;

public interface IActionResource extends IActionIOElement {

  /**
   * The Resource is a solution file
   */
  public static final int SOLUTION_FILE_RESOURCE = 1;

  /**
   * The resource is a URL
   */
  public static final int URL_RESOURCE = 2;

  /**
   * The resource is an arbitrary file
   */
  public static final int FILE_RESOURCE = 3;

  /**
   * The resource type is unknown
   */
  public static final int UNKNOWN_RESOURCE = 4;

  /**
   * The resource type is an embedded string
   */
  public static final int STRING = 5;

  /**
   * The resource type is embedded xml
   */
  public static final int XML = 6;

  public URI getUri();

  public void setURI( URI uri );

  public String getMimeType();

  public void setMimeType( String mimeType );

  public IPentahoStreamSource getDataSource() throws FileNotFoundException;

  public InputStream getInputStream() throws FileNotFoundException;

  public void delete();

  public void setMapping( String publicName );

  public void setName( String resourceName );

  public void setType( String ioType );

  public Element getElement();

  public String getMapping();

  public String getName();

  public String getPublicName();

  public String getType();

}
