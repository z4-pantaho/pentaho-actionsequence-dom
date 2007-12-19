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
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionResource;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputVariable;

public class MdxQueryAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.MDXLookupRule"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String LOCATION_ELEMENT = "location"; //$NON-NLS-1$
  public static final String MDX_CONNECTION_ELEMENT = "mdx-connection-string"; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String QUERY_RESULTS_ELEMENT = "query-results"; //$NON-NLS-1$
  public static final String CATALOG_ELEMENT = "catalog"; //$NON-NLS-1$
  public static final String ROLE_ELEMENT = "role"; //$NON-NLS-1$
  public static final String DEFAULT_LOCATION = "mondrian"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String DEFAULT_QUERY_RESULTS_NAME = "query_result"; //$NON-NLS-1$
  public static final String MDX_CONNECTION = "mdx-connection"; //$NON-NLS-1$
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  public static final String OUTPUT_PREPARED_STATEMENT = "output-prepared_statement"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    LOCATION_ELEMENT,
    CONNECTION_ELEMENT,
    USER_ID_ELEMENT,
    PASSWORD_ELEMENT,
    MDX_CONNECTION_ELEMENT,
    ROLE_ELEMENT,
    QUERY_ELEMENT,
    JNDI_ELEMENT
  };

  protected static final String[] EXPECTED_RESOURCES = new String[] {
    CATALOG_ELEMENT
  };
  
  public MdxQueryAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public MdxQueryAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setJndi(new ActionInputConstant("")); //$NON-NLS-1$
    setQuery(new ActionInputConstant("")); //$NON-NLS-1$
    setLocation(new ActionInputConstant(DEFAULT_LOCATION));
    setOutputResultSetName(DEFAULT_QUERY_RESULTS_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element)
      && hasComponentName(element, COMPONENT_NAME)
      && ((element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + QUERY_ELEMENT) != null) //$NON-NLS-1$
          || (element.selectSingleNode(ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + QUERY_ELEMENT) != null)); //$NON-NLS-1$
  }
  
  public String[] getReservedOutputNames() {
    String expectedOutput = QUERY_RESULTS_ELEMENT;
    if (getOutputParam(expectedOutput) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.RESULTSET_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }
  
  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }
  
  public void setLocation(IActionInput value) {
    setActionInputValue(LOCATION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value instanceof ActionInputConstant) && (value.getValue() != null))) {
      setMdxConnectionString(null);
    }
  }
  
  public IActionInput getLocation() {
    return getActionInputValue(LOCATION_ELEMENT);
  }
  
  public void setUserId(IActionInput value) {
    setActionInputValue(USER_ID_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value instanceof ActionInputConstant) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnection(null);
      if (getLocation() == IActionInput.NULL_INPUT) {
        setLocation(new ActionInputConstant(DEFAULT_LOCATION));
      }    
    }
  }
  
  public IActionInput getUserId() {
    return getActionInputValue(USER_ID_ELEMENT);
  }
  
  public void setPassword(IActionInput value) {
    setActionInputValue(PASSWORD_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value instanceof ActionInputConstant) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnection(null);
      if (getLocation() == IActionInput.NULL_INPUT) {
        setLocation(new ActionInputConstant(DEFAULT_LOCATION));
      }    
    }
  }
  
  public IActionInput getPassword() {
    return getActionInputValue(PASSWORD_ELEMENT);
  }
  
  public void setMdxConnectionString(IActionInput value) {
    setActionInputValue(MDX_CONNECTION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value instanceof ActionInputConstant) && (value.getValue() != null))) {
      setJndi(null);
      setConnection(null);
      setLocation(null);
      setUserId(null);
      setPassword(null);
      setRole(null);
      if (getLocation() == IActionInput.NULL_INPUT) {
        setLocation(new ActionInputConstant(DEFAULT_LOCATION));
      }    
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
    if ((value instanceof IActionInputVariable) || ((value instanceof ActionInputConstant) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnection(null);
      if (getLocation() == IActionInput.NULL_INPUT) {
        setLocation(new ActionInputConstant(DEFAULT_LOCATION));
      }    
    }
  }
  
  public IActionInput getConnection() {
    return getActionInputValue(CONNECTION_ELEMENT);
  }
  
  public void setJndi(IActionInput value) {
    setActionInputValue(JNDI_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value instanceof ActionInputConstant) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setMdxConnection(null);
      if (getLocation() == IActionInput.NULL_INPUT) {
        setLocation(new ActionInputConstant(DEFAULT_LOCATION));
      }    
    }
  }
  
  public IActionInput getJndi() {
    return getActionInputValue(JNDI_ELEMENT);
  }
  
  public void setQuery(IActionInput value) {
    setActionInputValue(QUERY_ELEMENT, value);
  }
  
  public IActionInput getQuery() {
    return getActionInputValue(QUERY_ELEMENT);
  }
  
  public void setOutputResultSetName(String name) {
    setOutputParam(QUERY_RESULTS_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputPreparedStatementName(null);
    }
  }
  
  public String getOutputResultSetName() {
    return getPublicOutputName(QUERY_RESULTS_ELEMENT);
  }
  
  public ActionOutput getOutputResultSetParam() {
    return getOutputParam(QUERY_RESULTS_ELEMENT);
  }
  
  public void setOutputPreparedStatementName(String name) {
    setOutputParam(PREPARED_COMPONENT_ELEMENT, name, ActionSequenceDocument.MDX_QUERY_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputResultSetName(null);
    }
  }
  
  public String getOutputPreparedStatementName() {
    return getPublicOutputName(PREPARED_COMPONENT_ELEMENT);
  }  
  
  public ActionOutput getOutputPreparedStatementParam() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }
  
  public void setMdxConnection(IActionInputVariable variable) {
    setActionInputValue(PREPARED_COMPONENT_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
      setLocation(null);
    }
  }
  
  public IActionInput getMdxConnection() {
    return getActionInputValue(PREPARED_COMPONENT_ELEMENT);
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
          if (validationError.errorCode == ActionSequenceValidationError.INPUT_MISSING) {
            validationError = validateInputParam(PREPARED_COMPONENT_ELEMENT);
            if (validationError != null) {
              switch (validationError.errorCode) {
                case ActionSequenceValidationError.INPUT_MISSING:
                  validationError.errorMsg = "Missing database connection input parameter.";
                  break;
                case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
                  validationError.errorMsg = "Database connection input parameter references unknown variable.";
                  break;
                case ActionSequenceValidationError.INPUT_UNINITIALIZED:
                  validationError.errorMsg = "Database connection input parameter is uninitialized.";
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
        } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR) {
          validationError.errorMsg = "Database connection input parameter references unknown variable.";
          errors.add(validationError);
        } else if (validationError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED) {
          validationError.errorMsg = "Database connection input parameter is uninitialized.";
          errors.add(validationError);
        }
      }
    }
    
    validationError = validateInputParam(QUERY_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing query input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Query input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Query input parameter is uninitialized.";
          break;
      }
      errors.add(validationError);
    }

    validationError = validateOutputParam(PREPARED_COMPONENT_ELEMENT);
    if (validationError != null) {
      validationError = validateOutputParam(QUERY_RESULTS_ELEMENT);
      if (validationError != null) {
        validationError.errorMsg = "Missing query results output parameter.";
        errors.add(validationError);
      }
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
