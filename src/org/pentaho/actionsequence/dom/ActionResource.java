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

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * A wrapper class for an action definition resource element.
 * @author Angelo Rodriguez
 *
 */
public class ActionResource extends ActionParam {

  public static final String UNMAPPED_RESOURCE = "_UNMAPPED_"; //$NON-NLS-1$
  
  public ActionResource(Element resourceElement) {
    super(resourceElement);
    this.ioElement = resourceElement;
  }

  /**
   * @return the name of the resource
   */
  public String getName() {
    return ioElement.getName();
  }
  
  /**
   * Sets the name of the resource
   * @param resourceName the resource name
   */
  public void setName(String resourceName) {
    if (!ioElement.getName().equals(resourceName)) {
      ioElement.setName(resourceName);
      ActionSequenceDocument.fireResourceRenamed(this);
    }
  }
  
  /**
   * @return the name to which the resource is mapped. Returns an empty string if 
   * the input/output is not mapped.
   */
  public String getMapping() {
    String name = ""; //$NON-NLS-1$
    Attribute attr = ioElement.attribute(ActionSequenceDocument.MAPPING_NAME);
    if (attr != null) {
      name = attr.getValue().trim();
      if (name.equals(getName())) {
        name = ""; //$NON-NLS-1$
      }
    }
    return name;
  }
  
  /**
   * Sets the name to which the resource is mapped. 
   * @param mapping the mapped name. If null any existing mapping is removed.
   */
  public void setMapping(String mapping) {
    if ((mapping == null) || (mapping.trim().length() == 0) || mapping.trim().equals(getName())) {
      if (ioElement.attribute(ActionSequenceDocument.MAPPING_NAME) != null) {
        ioElement.addAttribute(ActionSequenceDocument.MAPPING_NAME, null);
        ActionSequenceDocument.fireResourceChanged(this);
      }
    } else {
      mapping = mapping.trim();
      if (!mapping.equals(ioElement.attributeValue(ActionSequenceDocument.MAPPING_NAME))) {
        ioElement.addAttribute(ActionSequenceDocument.MAPPING_NAME, mapping);
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
  
  public boolean equals(Object arg0) {
    boolean result = false;
    if (arg0 != null) {
      if (arg0.getClass() == this.getClass()) {
        ActionResource resource = (ActionResource)arg0;
        result = (resource.ioElement != null ? resource.ioElement.equals(this.ioElement) : (resource == this));
      }
    }
    return result;
  }

  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getElement()
   */
  public Element getElement() {
    return ioElement;
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
  
  /**
   * @return the mapped name if it exists, otherwise the resource name is returned.
   */
  public String getPublicName() {
    String mapping = ioElement.attributeValue(ActionSequenceDocument.MAPPING_NAME);
    return ((mapping != null) && (mapping.trim().length() > 0)) ? mapping.trim() : ioElement.getName();
  }
  
  public ActionDefinition getActionDefinition() {
    ActionDefinition actionDefinition = null;
    if (ioElement != null) {
      Element ancestorElement = ioElement.getParent();
      if (ancestorElement != null) {
        ancestorElement = ancestorElement.getParent();
        if ((ancestorElement != null) && ancestorElement.getName().equals(ActionSequenceDocument.ACTION_DEFINITION_NAME)) {
          actionDefinition = ActionDefinition.instance(ancestorElement);
        }
      }
    }
    return actionDefinition;
  }

  public String getType() {
    return null;
  }

  public void setType(String ioType) {
    throw new UnsupportedOperationException();
  }
}
