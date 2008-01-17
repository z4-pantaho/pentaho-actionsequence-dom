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

import java.util.ArrayList;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceResource;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;

public class XQueryConnectionAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.XQueryLookupRule"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String DEFAULT_CONNECTION_NAME = "shared_xquery_connection"; //$NON-NLS-1$

  protected static final String[] EXPECTED_OUTPUTS = new String[] {
    PREPARED_COMPONENT_ELEMENT,
  };
  
  public XQueryConnectionAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public XQueryConnectionAction() {
    super(COMPONENT_NAME);
  }
  
  public static boolean accepts(Element element) {
    boolean result = false;
    if (ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME)) {
      Element connectionOutput = (Element)element.selectSingleNode(ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/" + PREPARED_COMPONENT_ELEMENT); //$NON-NLS-1$
      result = (connectionOutput != null)
        && ActionSequenceDocument.XQUERY_CONNECTION_TYPE.equals(connectionOutput.attributeValue(ActionSequenceResource.TYPE_NAME));
    }
    return result;
  }
  
  public String[] getReservedInputNames() {
    return new String[0];
  }
  
  public String[] getReservedOutputNames() {
    return EXPECTED_OUTPUTS;
  }
  
  public String[] getReservedResourceNames() {
    return new String[0];
  }
  
  public void setOutputConnection(String publicOutputName) {
    setOutputParam(PREPARED_COMPONENT_ELEMENT, publicOutputName, ActionSequenceDocument.XQUERY_CONNECTION_TYPE);
  }
  
  public ActionOutput getOutputConnection() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }
  
  public ActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError= validateOutputParam(PREPARED_COMPONENT_ELEMENT);
    if (validationError != null) {
      if (validationError.errorCode == ActionSequenceValidationError.OUTPUT_MISSING) {
        validationError.errorMsg = "Missing output connection name.";
      }
      errors.add(validationError);
    }

    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);

  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setOutputConnection(DEFAULT_CONNECTION_NAME);
  }
  
}
