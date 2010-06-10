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

import java.io.Serializable;
import java.util.List;

/**
 * A wrapper class for an action definition resource element.
 * @author Angelo Rodriguez
 *
 */

public class ActionResource extends AbstractActionIOElement implements IActionResource, Serializable {
  
  public ActionResource(IActionDefinition parent) {
    super(parent);
  }
    
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
   */
  public IActionSequenceDocument getDocument() {
    return getActionDefinition().getParent().getActionSequence();
  }
  
  /**
   * @return the mapped name if it exists, otherwise the resource name is returned.
   */
  public String getPublicName() {
    String mapping = getMapping();
    return ((mapping != null) && (mapping.trim().length() > 0)) ? mapping.trim() : getName();
  }
  
  public String getType() {
    return null;
  }

  public void setType(String ioType) {
    throw new UnsupportedOperationException();
  }
  
  public String getUri() {
    String uri = null;
    IActionSequenceResource actionSequenceResource = getDocument().getResource(getPublicName());
    if (actionSequenceResource != null) {
      uri = actionSequenceResource.getUri();
    }
    return uri;
  }
  
  public String getMimeType() {
    String mimeType = null;
    IActionSequenceResource actionSequenceResource = getDocument().getResource(getPublicName());
    if (actionSequenceResource != null) {
      mimeType = actionSequenceResource.getMimeType();
    }
    return mimeType;
  }
  
  public void setURI(String uri) {
    String logicalName = getPublicName();
    IActionSequenceResource actionSequenceResource = getDocument().getResource(logicalName);
    if (uri == null) {
      IActionSequenceDocument document = getDocument();
      getDocument().getResources().remove(actionSequenceResource);
      if ((actionSequenceResource != null) && (document.getReferencesTo(actionSequenceResource).size() == 0)) {
    	  document.setResourceUri(logicalName, null, null);
      }
    } else {
      if (actionSequenceResource == null) {
        getDocument().setResourceUri(logicalName, uri, "text/plain");
      } else {
        String mimeType = actionSequenceResource.getMimeType();
        List<IActionResource> actionResources = getDocument().getReferencesTo(actionSequenceResource);
        if ((actionResources.size() == 1) && actionResources.get(0).equals(this)) {
          getDocument().setResourceUri(logicalName, uri, mimeType);
        } else {
          logicalName = createLogicalName(getName());
          setMapping(logicalName);
          getDocument().setResourceUri(logicalName, uri, mimeType);
        }
      }
    }
  }
  
  public void setMimeType(String mimeType) {
    String logicalName = getPublicName();
    IActionSequenceResource actionSequenceResource = getDocument().getResource(logicalName);
    if (actionSequenceResource != null) {
      List<IActionResource> actionResources = getDocument().getReferencesTo(actionSequenceResource);
      if ((actionResources.size() == 1) && actionResources.get(0).equals(this)) {
        getDocument().setResourceUri(logicalName, actionSequenceResource.getUri(), mimeType);
      } else {
        logicalName = createLogicalName(getName());
        setMapping(logicalName);
        getDocument().setResourceUri(logicalName, actionSequenceResource.getUri(), mimeType);
      }
    }
  }
  
  private String createLogicalName(String resourceName) {
    String logicalName;
    logicalName = resourceName;
    int index = 1;
    while (getDocument().getResource(logicalName) != null) {
      logicalName = resourceName + index;
      index++;
    }
    return logicalName;
  }
}
