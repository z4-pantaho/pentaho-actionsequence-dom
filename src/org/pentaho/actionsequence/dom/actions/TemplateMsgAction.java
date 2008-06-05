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

import java.net.URI;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInputValueProvider;
import org.pentaho.actionsequence.dom.IActionInputVariable;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionResource;

public class TemplateMsgAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.TemplateComponent"; //$NON-NLS-1$
  public static final String TEMPLATE_ELEMENT = "template" ; //$NON-NLS-1$
  public static final String OUTPUT_MSG_ELEMENT = "output-message" ; //$NON-NLS-1$
  public static final String OUTPUT_STRING = "output-string"; //$NON-NLS-1$
  public static final String TEMPLATE_FILE = "template-file" ; //$NON-NLS-1$
  public static final String MIME_TYPE = "mime-type"; //$NON-NLS-1$ 
  public static final String EXTENSION = "extension"; //$NON-NLS-1$
  public static final String TEMPLATE_RESOURCE = "template-resource" ; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    TEMPLATE_ELEMENT
  };
  protected static final String[] EXPECTED_RESOURCES = new String[] {
    TEMPLATE_ELEMENT
  };

  public TemplateMsgAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public TemplateMsgAction() {
    super(COMPONENT_NAME);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getReservedOutputNames() {
    String expectedOutput = OUTPUT_MSG_ELEMENT;
    if (getOutputParam(expectedOutput) ==  null) { 
      IActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.STRING_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }
  
  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }

  protected void initNewActionDefinition() {
    setInputValue(TEMPLATE_ELEMENT, ""); //$NON-NLS-1$
  }

  public IActionResource setTemplateResource(URI uri, String mimeType) {
    IActionResource templateResource = setResourceUri(TEMPLATE_ELEMENT, uri, mimeType);
    // Cleaning up the template from input since we would be using 
    // template based on resource
    if (uri != null) {
    	setInputValue(TEMPLATE_ELEMENT, null);
    }
    return templateResource;
  }
	
  public IActionResource getTemplateResource() {
    return getResourceParam(TEMPLATE_ELEMENT);
  }

  public void setTemplate(IActionInputValueProvider value) {
	  setActionInputValue(TEMPLATE_ELEMENT, value);
	  
	// Cleaning up the resource since we would be using template based on input
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
    	setTemplateResource(null, null);
    }
  }
  
  public IActionInputValueProvider getTemplate() {
	  return getActionInputValue(TEMPLATE_ELEMENT);
  }

  public IActionInputValueProvider getMimeType() {
    return getActionInputValue(MIME_TYPE);
  }

  public void setMimeType(IActionInputValueProvider value) {
    setActionInputValue(MIME_TYPE, value);
  }

  public IActionInputValueProvider getExtension() {
	  return getActionInputValue(EXTENSION);
  }
  public void setExtension(IActionInputValueProvider value) {
    setActionInputValue(EXTENSION, value);
  }
  
  public void setOutputString(String publicOutputName) {
    setOutputParam(OUTPUT_MSG_ELEMENT, publicOutputName, ActionSequenceDocument.STRING_TYPE);
  }
  
  public IActionOutput getOutputString() {
    return getOutputParam(OUTPUT_MSG_ELEMENT);
  }
}
