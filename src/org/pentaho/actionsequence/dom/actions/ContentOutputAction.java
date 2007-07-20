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
package org.pentaho.actionsequence.dom.actions;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionDefinition;
import org.pentaho.actionsequence.dom.IActionVariable;

public class ContentOutputAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.ContentOutputComponent"; //$NON-NLS-1$
  public static final String CONTENT_OUTPUT_ELEMENT = "CONTENTOUTPUT"; //$NON-NLS-1$
  public static final String MIME_TYPE_ELEMENT = "mime-type"; //$NON-NLS-1$
  public static final String CONTENT_TO_OUTPUT = "content-to-output"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    CONTENT_OUTPUT_ELEMENT,
    MIME_TYPE_ELEMENT
  };

  public ContentOutputAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public ContentOutputAction() {
    super(COMPONENT_NAME);
  }

  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public void setMimeType(String value) {
    setInputValue(MIME_TYPE_ELEMENT, value);
  }
  
  public String getMimeType() {
    return getComponentDefinitionValue(MIME_TYPE_ELEMENT);
  }
  
  public void setMimeTypeVariable(IActionVariable variable) {
    setReferencedVariable(MIME_TYPE_ELEMENT, variable);
  }
  
  public IActionVariable getMimeTypeVariable() {
    return getReferencedVariable(MIME_TYPE_ELEMENT);
  }
  
  public void setContentToOutputVariable(IActionVariable variable) {
    setReferencedVariable(CONTENT_OUTPUT_ELEMENT, variable);
  }
  
  public IActionVariable getContentToOutputVariable() {
    return getReferencedVariable(CONTENT_OUTPUT_ELEMENT);
  }
}
