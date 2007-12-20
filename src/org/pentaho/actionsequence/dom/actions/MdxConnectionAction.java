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
import java.util.ArrayList;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionResource;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputVariable;

public class MdxConnectionAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.MDXLookupRule"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String DRIVER_ELEMENT = "driver"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String LOCATION_ELEMENT = "location"; //$NON-NLS-1$
  public static final String MDX_CONNECTION_ELEMENT = "mdx-connection-string"; //$NON-NLS-1$
  public static final String CATALOG_ELEMENT = "catalog"; //$NON-NLS-1$
  public static final String ROLE_ELEMENT = "role"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String DEFAULT_CONNECTION_NAME = "shared_olap_connection"; //$NON-NLS-1$
  public static final String DEFAULT_LOCATION = "mondrian"; //$NON-NLS-1$
  public static final String OUTPUT_CONNECTION = "output-connection"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    LOCATION_ELEMENT,
    CONNECTION_ELEMENT,
    USER_ID_ELEMENT,
    PASSWORD_ELEMENT,
    DRIVER_ELEMENT,
    MDX_CONNECTION_ELEMENT,
    ROLE_ELEMENT,
    JNDI_ELEMENT
  };

  protected static final String[] EXPECTED_RESOURCES = new String[] {
    CATALOG_ELEMENT
  };
  
  public MdxConnectionAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public MdxConnectionAction() {
    super(COMPONENT_NAME);
  }

  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setJndi(new ActionInputConstant("")); //$NON-NLS-1$
    setLocation(new ActionInputConstant(DEFAULT_LOCATION));
    setOutputConnectionName(DEFAULT_CONNECTION_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getReservedOutputNames() {
    return new String[]{PREPARED_COMPONENT_ELEMENT};
  }
  
  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }
  
  public void setLocation(IActionInput value) {
    setActionInputValue(LOCATION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
    }
  }
  
  public IActionInput getLocation() {
    return getActionInputValue(LOCATION_ELEMENT);
  }
  
  public void setUserId(IActionInput value) {
    setActionInputValue(USER_ID_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setJndi(null);
    }
  }
  
  public IActionInput getUserId() {
    return getActionInputValue(USER_ID_ELEMENT);
  }
  
  public void setPassword(IActionInput value) {
    setActionInputValue(PASSWORD_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setJndi(null);
    }
  }

  public IActionInput getPassword() {
    return getActionInputValue(PASSWORD_ELEMENT);
  }
  
  public void setDriver(IActionInput value) {
    setActionInputValue(DRIVER_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setJndi(null);
    }
  }
  
  public IActionInput getDriver() {
    return getActionInputValue(DRIVER_ELEMENT);
  }
  
  public void setMdxConnectionString(IActionInput value) {
    setActionInputValue(MDX_CONNECTION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
      setConnection(null);
      setLocation(null);
      setUserId(null);
      setPassword(null);
      setDriver(null);
      setRole(null);
    }
  }
  
  public IActionInput getMdxConnectionString() {
    return getActionInputValue(MDX_CONNECTION_ELEMENT);
  }
  
  public void setRole(IActionInput value) {
    setActionInputValue(ROLE_ELEMENT, value);
  }
  
  public IActionInput getRole() {
    return getActionInputValue(ROLE_ELEMENT);
  }
  
  public void setConnection(IActionInput value) {
    setActionInputValue(CONNECTION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
      setMdxConnectionString(null);
    }
  }
  
  public IActionInput getConnection() {
    return getActionInputValue(CONNECTION_ELEMENT);
  }
  
  
  public void setJndi(IActionInput value) {
    setActionInputValue(JNDI_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setDriver(null);
    }
  }
  
  public IActionInput getJndi() {
    return getActionInputValue(JNDI_ELEMENT);
  }
  
  public void setOutputConnectionName(String name) {
    setOutputParam(PREPARED_COMPONENT_ELEMENT, name, ActionSequenceDocument.MDX_CONNECTION_TYPE);
  }
  
  public String getOutputConnectionName() {
    return getPublicOutputName(PREPARED_COMPONENT_ELEMENT);
  }
  
  public ActionOutput getOutputConnectionParam() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }
  
  public ActionSequenceValidationError[] validate() {
    
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInputParam(MDX_CONNECTION_ELEMENT);
    if (validationError != null) {
      if (validationError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR) {
        validationError.errorMsg = "Database connection input parameter references unknown variable.";
        errors.add(validationError);
      } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED) {
        validationError.errorMsg = "Database connection input parameter is uninitialized.";
        errors.add(validationError);
      } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_MISSING) {
        validationError = validateResourceParam(CATALOG_ELEMENT);
        if (validationError != null) {
          switch (validationError.errorCode) {
            case ActionSequenceValidationError.INPUT_MISSING:
              validationError.errorMsg = "Missing mondrian schema input parameter.";
              break;
            case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
              validationError.errorMsg = "Mondrian schema input parameter references unknown variable.";
              break;
            case ActionSequenceValidationError.INPUT_UNINITIALIZED:
              validationError.errorMsg = "Mondrian schema input parameter is uninitialized.";
              break;
          }
          errors.add(validationError);
        }
        
        validationError = validateInputParam(CONNECTION_ELEMENT);
        if (validationError.errorCode == ActionSequenceValidationError.INPUT_MISSING) {
          validationError = validateInputParam(JNDI_ELEMENT);
          if (validationError != null) {
            switch (validationError.errorCode) {
              case ActionSequenceValidationError.INPUT_MISSING:
                validationError.errorMsg = "Missing database connection input parameter.";
                break;
              case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
                validationError.errorMsg = "Database connection input parameter references unknown variable.";
                break;
              case ActionSequenceValidationError.INPUT_UNINITIALIZED:
                validationError.errorMsg = "Database connection input parameter in unitialized.";
                break;
            }
            errors.add(validationError);
          }
        } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR) {
          validationError.errorMsg = "Database connection input parameter references unknown variable.";
          errors.add(validationError);
        } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED) {
          validationError.errorMsg = "Database connection input parameter is uninitialized.";
          errors.add(validationError);
        }
      }
    }
    
    validationError = validateOutputParam(PREPARED_COMPONENT_ELEMENT);
    if (validationError != null) {
      if (validationError.errorCode == ActionSequenceValidationError.OUTPUT_MISSING) {
        validationError.errorMsg = "Missing output connection name.";
      }
      errors.add(validationError);
    }
    

    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
  
  public ActionResource setCatalog(URI uri, String mimeType) {
    return setResourceUri(CATALOG_ELEMENT, uri, mimeType);
  }
  
  public ActionResource getCatalog() {
    return getResourceParam(CATALOG_ELEMENT);
  }
}
