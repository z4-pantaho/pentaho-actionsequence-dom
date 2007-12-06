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
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionResource;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionVariable;

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
    setJndi(""); //$NON-NLS-1$
    setQuery(""); //$NON-NLS-1$
    setLocation(DEFAULT_LOCATION);
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
  
  public void setLocation(String value) {
    setInputValue(LOCATION_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
    }
  }
  
  public String getLocation() {
    return getComponentDefinitionValue(LOCATION_ELEMENT);
  }
  
  public void setLocationParam(IActionVariable variable) {
    setInputParam(LOCATION_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
    }
  }
  
  public ActionInput getLocationParam() {
    return getInputParam(LOCATION_ELEMENT);
  }
  
  public void setUserId(String value) {
    setInputValue(USER_ID_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getUserId() {
    return getComponentDefinitionValue(USER_ID_ELEMENT);
  }
  
  public void setUserIdParam(IActionVariable variable) {
    setInputParam(USER_ID_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public ActionInput getUserIdParam() {
    return getInputParam(USER_ID_ELEMENT);
  }
  
  public void setPassword(String value) {
    setInputValue(PASSWORD_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getPassword() {
    return getComponentDefinitionValue(PASSWORD_ELEMENT);
  }
  
  public void setPasswordParam(IActionVariable variable) {
    setInputParam(PASSWORD_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public ActionInput getPasswordParam() {
    return getInputParam(PASSWORD_ELEMENT);
  }
  
  public void setMdxConnectionString(String value) {
    setInputValue(MDX_CONNECTION_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setConnection(null);
      setLocation(null);
      setUserId(null);
      setPassword(null);
      setRole(null);
      setConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getMdxConnectionString() {
    return getComponentDefinitionValue(MDX_CONNECTION_ELEMENT);
  }
  
  public void setMdxConnectionStringParam(IActionVariable variable) {
    setInputParam(MDX_CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setConnection(null);
      setLocation(null);
      setUserId(null);
      setPassword(null);
      setRole(null);
      setConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public ActionInput getMdxConnectionStringParam() {
    return getInputParam(MDX_CONNECTION_ELEMENT);
  }
  
  public void setRole(String value) {
    setInputValue(ROLE_ELEMENT, value);
  }
  
  public String getRole() {
    return getComponentDefinitionValue(ROLE_ELEMENT);
  }
  
  public void setRoleParam(IActionVariable variable) {
    setInputParam(ROLE_ELEMENT, variable);
  }
  
  public ActionInput getRoleParam() {
    return getInputParam(ROLE_ELEMENT);
  }
  
  public void setConnection(String value) {
    setInputValue(CONNECTION_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getConnection() {
    return getComponentDefinitionValue(CONNECTION_ELEMENT);
  }
  
  public void setConnectionParam(IActionVariable variable) {
    setInputParam(CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setJndi(null);
      setMdxConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public ActionInput getConnectionParam() {
    return getInputParam(CONNECTION_ELEMENT);
  }
  
  public void setJndi(String value) {
    setInputValue(JNDI_ELEMENT, value);
    if (value != null) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setMdxConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public String getJndi() {
    return getComponentDefinitionValue(JNDI_ELEMENT);
  }
  
  public void setJndiParam(IActionVariable variable) {
    setInputParam(JNDI_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setMdxConnectionParam(null);
      if ((getLocation() == null) && (getLocationParam() == null)) {
        setLocation(DEFAULT_LOCATION);
      }    
    }
  }
  
  public ActionInput getJndiParam() {
    return getInputParam(JNDI_ELEMENT);
  }
  
  public void setQuery(String value) {
    setInputValue(QUERY_ELEMENT, value);
  }
  
  public String getQuery() {
    return getComponentDefinitionValue(QUERY_ELEMENT);
  }
  
  public void setQueryParam(IActionVariable variable) {
    setInputParam(QUERY_ELEMENT, variable);
  }
  
  public ActionInput getQueryParam() {
    return getInputParam(QUERY_ELEMENT);
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
  
  public void setMdxConnectionParam(IActionVariable variable) {
    setInputParam(PREPARED_COMPONENT_ELEMENT, variable);
    if (variable != null) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
      setLocation(null);
    }
  }
  
  public ActionInput getMdxConnectionParam() {
    return getInputParam(PREPARED_COMPONENT_ELEMENT);
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
