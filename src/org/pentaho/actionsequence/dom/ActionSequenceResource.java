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

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * A wrapper class for an action sequence resource element.
 * @author Angelo Rodriguez
 *
 */
public class ActionSequenceResource extends AbstractParam {

  // Document Resources nodes
  public static final String SOLUTION_FILE_RESOURCE_TYPE = "solution-file"; //$NON-NLS-1$
  public static final String URL_RESOURCE_TYPE = "url"; //$NON-NLS-1$
  public static final String FILE_RESOURCE_TYPE = "file"; //$NON-NLS-1$
  public static final String RES_LOCATION_NAME = "location"; //$NON-NLS-1$
  public static final String RES_MIME_TYPE_NAME = "mime-type"; //$NON-NLS-1$
  public static final String TYPE_NAME = "type"; //$NON-NLS-1$
  
  public ActionSequenceResource(Element resourceElement) {
    super(resourceElement);
  }
  
  /**
   * @return the resource name
   */
  public String getName() {
    return ioElement.getName();
  }
  
  /**
   * Sets the resource name.
   * @param resourceName the resource name
   */
  public void setName(String resourceName) {
    if (!ioElement.getName().equals(resourceName)) {
      ioElement.setName(resourceName);
      ActionSequenceDocument.fireResourceRenamed(this);
    }
  }
  
  /**
   * Sets the resource mime type.
   * @param mimeType the mime type
   */
  public void setMimeType(String mimeType) {
    String resType = getType();
    Element mimeElement = null;
    if (SOLUTION_FILE_RESOURCE_TYPE.equals(resType)) {
      mimeElement = (Element)ioElement.selectSingleNode(SOLUTION_FILE_RESOURCE_TYPE + "/" + RES_MIME_TYPE_NAME); //$NON-NLS-1$
    } else if (FILE_RESOURCE_TYPE.equals(resType)) {
      mimeElement = (Element)ioElement.selectSingleNode(FILE_RESOURCE_TYPE + "/" + RES_MIME_TYPE_NAME); //$NON-NLS-1$
    } else if (URL_RESOURCE_TYPE.equals(resType)) {
      mimeElement = (Element)ioElement.selectSingleNode(URL_RESOURCE_TYPE + "/" + RES_MIME_TYPE_NAME); //$NON-NLS-1$
    }
    
    if (mimeElement == null) {
      if (SOLUTION_FILE_RESOURCE_TYPE.equals(resType)) {
        mimeElement = DocumentHelper.makeElement(ioElement, SOLUTION_FILE_RESOURCE_TYPE + "/" + RES_MIME_TYPE_NAME); //$NON-NLS-1$
      } else if (FILE_RESOURCE_TYPE.equals(resType)) {
        mimeElement = DocumentHelper.makeElement(ioElement, FILE_RESOURCE_TYPE + "/" + RES_MIME_TYPE_NAME); //$NON-NLS-1$
      } else if (URL_RESOURCE_TYPE.equals(resType)) {
        mimeElement = DocumentHelper.makeElement(ioElement, URL_RESOURCE_TYPE + "/" + RES_MIME_TYPE_NAME); //$NON-NLS-1$
      }
      mimeElement.setText(mimeType);
      ActionSequenceDocument.fireResourceChanged(this);
    } else if (!mimeElement.getText().equals(mimeType)){
      mimeElement.setText(mimeType);
      ActionSequenceDocument.fireResourceChanged(this);
    }
  }
  
  /**
   * @return the resource mime type
   */
  public String getMimeType() {
    String mimeType = ""; //$NON-NLS-1$
    String resType = getType();
    Element mimeElement = null;
    if (SOLUTION_FILE_RESOURCE_TYPE.equals(resType)) {
      mimeElement = (Element)ioElement.selectSingleNode(SOLUTION_FILE_RESOURCE_TYPE + "/" + RES_MIME_TYPE_NAME); //$NON-NLS-1$
    } else if (FILE_RESOURCE_TYPE.equals(resType)) {
      mimeElement = (Element)ioElement.selectSingleNode(FILE_RESOURCE_TYPE + "/" + RES_MIME_TYPE_NAME); //$NON-NLS-1$
    } else if (URL_RESOURCE_TYPE.equals(resType)) {
      mimeElement = (Element)ioElement.selectSingleNode(URL_RESOURCE_TYPE + "/" + RES_MIME_TYPE_NAME); //$NON-NLS-1$
    }
    
    if (mimeElement != null) {
      mimeType = mimeElement.getText();
    }
    
    return mimeType;
  }
  
  /**
   * Sets the resource URI
   * @param uri the resource URI
   */
  public void setUri(String uri) {
    String resType = getType();
    Element pathElement = null;
    if (SOLUTION_FILE_RESOURCE_TYPE.equals(resType)) {
      pathElement = (Element)ioElement.selectSingleNode(SOLUTION_FILE_RESOURCE_TYPE + "/" + RES_LOCATION_NAME); //$NON-NLS-1$
    } else if (FILE_RESOURCE_TYPE.equals(resType)) {
      pathElement = (Element)ioElement.selectSingleNode(FILE_RESOURCE_TYPE + "/" + RES_LOCATION_NAME); //$NON-NLS-1$
    } else if (URL_RESOURCE_TYPE.equals(resType)) {
      pathElement = (Element)ioElement.selectSingleNode(URL_RESOURCE_TYPE + "/" + RES_LOCATION_NAME); //$NON-NLS-1$
    }
    
    if (pathElement == null) {
      if (SOLUTION_FILE_RESOURCE_TYPE.equals(resType)) {
        pathElement = DocumentHelper.makeElement(ioElement, SOLUTION_FILE_RESOURCE_TYPE + "/" + RES_LOCATION_NAME); //$NON-NLS-1$
      } else if (FILE_RESOURCE_TYPE.equals(resType)) {
        pathElement = DocumentHelper.makeElement(ioElement, FILE_RESOURCE_TYPE + "/" + RES_LOCATION_NAME); //$NON-NLS-1$
      } else if (URL_RESOURCE_TYPE.equals(resType)) {
        pathElement = DocumentHelper.makeElement(ioElement, URL_RESOURCE_TYPE + "/" + RES_LOCATION_NAME); //$NON-NLS-1$
      }
      pathElement.setText(uri);
      ActionSequenceDocument.fireResourceChanged(this);
    } else if (!pathElement.getText().equals(uri)){
      pathElement.setText(uri);
      ActionSequenceDocument.fireResourceChanged(this);
    }
  }
  
  /**
   * @return the resource URI
   */
  public String getUri() {
    String uri = ""; //$NON-NLS-1$
    String resType = getType();
    Element pathElement = null;
    if (SOLUTION_FILE_RESOURCE_TYPE.equals(resType)) {
      pathElement = (Element)ioElement.selectSingleNode(SOLUTION_FILE_RESOURCE_TYPE + "/" + RES_LOCATION_NAME); //$NON-NLS-1$
    } else if (FILE_RESOURCE_TYPE.equals(resType)) {
      pathElement = (Element)ioElement.selectSingleNode(FILE_RESOURCE_TYPE + "/" + RES_LOCATION_NAME); //$NON-NLS-1$
    } else if (URL_RESOURCE_TYPE.equals(resType)) {
      pathElement = (Element)ioElement.selectSingleNode(URL_RESOURCE_TYPE + "/" + RES_LOCATION_NAME); //$NON-NLS-1$
    }
    
    if (pathElement != null) {
      uri = pathElement.getText();
    }
    
    return uri;
  }
  
  /**
   * @return the resource file type
   */
  public String getType() {
    Element solutionFileElement = ioElement.element(SOLUTION_FILE_RESOURCE_TYPE);
    Element fileElement = ioElement.element(FILE_RESOURCE_TYPE);
    Element urlElement = ioElement.element(URL_RESOURCE_TYPE);
    String resourceType = null;
    if ((solutionFileElement != null) && (fileElement == null) && (urlElement == null)) {
      resourceType = SOLUTION_FILE_RESOURCE_TYPE;
    } else if ((solutionFileElement == null) && (fileElement != null) && (urlElement == null)) {
      resourceType = FILE_RESOURCE_TYPE;
    } else if ((solutionFileElement == null) && (fileElement == null) && (urlElement != null)) {
      resourceType = URL_RESOURCE_TYPE;
    }
    return resourceType;
  }
  
  /**
   * Sets the resource file type
   * @param resourceType the resource file type
   */
  public void setType(String resourceType) {
    if (SOLUTION_FILE_RESOURCE_TYPE.equals(resourceType) || FILE_RESOURCE_TYPE.equals(resourceType) || URL_RESOURCE_TYPE.equals(resourceType)) {
      Element solutionFileElement = ioElement.element(SOLUTION_FILE_RESOURCE_TYPE);
      Element fileElement = ioElement.element(FILE_RESOURCE_TYPE);
      Element urlElement = ioElement.element(URL_RESOURCE_TYPE);
      if (getType() == null) {
        if (solutionFileElement != null) {
          solutionFileElement.detach();
          solutionFileElement = null;
        }
        if (fileElement != null) {
          fileElement.detach();
          fileElement = null;
        }
        if (urlElement != null) {
          urlElement.detach();
          urlElement = null;
        }
      }
      
      Element existingElement = solutionFileElement;
      if (existingElement == null) {
        existingElement = fileElement;
      }
      if (existingElement == null) {
        existingElement = urlElement;
      }
      
      if (existingElement == null) {
        existingElement = ioElement.addElement(resourceType);
        existingElement.addElement(RES_LOCATION_NAME);
        existingElement.addElement(RES_MIME_TYPE_NAME);
        ActionSequenceDocument.fireResourceChanged(this);
      } else {
        existingElement.setName(resourceType);
        ActionSequenceDocument.fireResourceChanged(this);
      }
    }
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#delete()
   */
  public void delete() {
    Document doc = ioElement.getDocument();
    if (doc != null) {
      ioElement.detach();
      ActionSequenceDocument.fireResourceRemoved(new ActionSequenceDocument(doc), this);
    }
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getElement()
   */
  public Element getElement() {
    return ioElement;
  }
  
  public boolean equals(Object arg0) {
    boolean result = false;
    if (arg0 != null) {
      if (arg0.getClass() == this.getClass()) {
        ActionSequenceResource resource = (ActionSequenceResource)arg0;
        result = (resource.ioElement != null ? resource.ioElement.equals(this.ioElement) : (resource == this));
      }
    }
    return result;
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
   */
  public ActionSequenceDocument getDocument() {
    ActionSequenceDocument doc = null;
    if ((ioElement != null) && (ioElement.getDocument() != null)) {
      doc = new ActionSequenceDocument(ioElement.getDocument());
    }
    return doc;
  }
}
