/*
 * Copyright 2006 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the Mozilla Public License, Version 1.1, or any later version. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.mozilla.org/MPL/MPL-1.1.txt. The Original Code is the Pentaho 
 * BI Platform.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
*/
package org.pentaho.actionsequence.dom;

import java.net.URI;

import org.dom4j.Element;

/**
 * A wrapper class for an action sequence resource element.
 * @author Angelo Rodriguez
 *
 */
public interface IActionSequenceResource extends IAbstractIOElement {

  // Document Resources nodes
  public static final String SOLUTION_FILE_RESOURCE_TYPE = "solution-file"; //$NON-NLS-1$
  public static final String URL_RESOURCE_TYPE = "url"; //$NON-NLS-1$
  public static final String FILE_RESOURCE_TYPE = "file"; //$NON-NLS-1$
  public static final String RES_LOCATION_NAME = "location"; //$NON-NLS-1$
  public static final String RES_MIME_TYPE_NAME = "mime-type"; //$NON-NLS-1$
  
  public static final String SOLUTION_SCHEME = "solution"; //$NON-NLS-1$
  public static final String FILE_SCHEME = "file"; //$NON-NLS-1$
  
  /**
   * @return the resource name
   */
  public String getName();
  
  /**
   * Sets the resource name.
   * @param resourceName the resource name
   */
  public void setName(String resourceName);
  
  /**
   * Sets the resource mime type.
   * @param mimeType the mime type
   */
  public void setMimeType(String mimeType);
  
  /**
   * @return the resource mime type
   */
  public String getMimeType();
  
  /**
   * Sets the resource URI
   * @param uri the resource URI
   */
  public void setPath(String uri);
  
  /**
   * @return the resource URI
   */
  public String getPath();
  
  /**
   * @return the resource file type
   */
  public String getType();
  
  /**
   * Sets the resource file type
   * @param resourceType the resource file type
   */
  public void setType(String resourceType);
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#delete()
   */
  public void delete();
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getElement()
   */
  public Element getElement();
    
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
   */
  public IActionSequenceDocument getDocument();
  
  public URI getUri();
  
  public void setUri(URI uri);
}
