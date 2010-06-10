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
package org.pentaho.actionsequence.model;



/**
 * A wrapper class for an action sequence resource element.
 * @author Angelo Rodriguez
 *
 */
public class ActionSequenceResource extends AbstractIOElement implements IActionSequenceResource {

  IActionSequenceDocument parent;
  String mimeType;
  String string;
  String xml;
  String uri;
  
  public ActionSequenceResource() {
  }
  
  public ActionSequenceResource(IActionSequenceDocument parent) {
    this.parent = parent;
  }
  
  /**
   * Sets the resource mime type.
   * @param mimeType the mime type
   */
  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }
  
  /**
   * @return the resource mime type
   */
  public String getMimeType() {
    return mimeType == null ? "" : mimeType;
  }
    
  /**
   * @return the resource file type
   */
  public String getType() {
    return type;
  }
  
  /**
   * Sets the resource file type
   * @param resourceType the resource file type
   */
  public void setType(String resourceType) {
    type = resourceType;
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
   */
  public IActionSequenceDocument getDocument() {
    return parent;
  }
  
  public String getUri() {
    return uri;
  }
  
  public void setUri(String uri) {
    this.uri = uri;
    xml = null;
    string = null;
  }
  
  public String getString() {
    return string;
  }
  
  public void setString(String string) {
    this.string = string;
    xml = null;
    uri = null;
  }
  
  public String getXml() {
    return xml;
  }
  
  public void setXml(String xml) {
    this.xml = xml;
    string = null;
    uri = null;
  }
}
