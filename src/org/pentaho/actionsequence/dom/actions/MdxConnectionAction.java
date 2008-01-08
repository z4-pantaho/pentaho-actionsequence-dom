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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionResource;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInputValueProvider;
import org.pentaho.actionsequence.dom.IActionInputVariable;

public class MdxConnectionAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.MDXLookupRule"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String CONNECTION_PROPS = "connection-properties"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String DRIVER_ELEMENT = "driver"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String LOCATION_ELEMENT = "location"; //$NON-NLS-1$
  public static final String MDX_CONNECTION_ELEMENT = "mdx-connection-string"; //$NON-NLS-1$
  public static final String CATALOG_ELEMENT = "catalog"; //$NON-NLS-1$
  public static final String CATALOG_RESOURCE = "catalog-resource"; //$NON-NLS-1$
  public static final String ROLE_ELEMENT = "role"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String DEFAULT_CONNECTION_NAME = "shared_olap_connection"; //$NON-NLS-1$
  public static final String DEFAULT_LOCATION = "mondrian"; //$NON-NLS-1$
  public static final String OUTPUT_CONNECTION = "output-connection"; //$NON-NLS-1$
  public static final String PROPERTY = "property"; //$NON-NLS-1$
  public static final String KEY_NODE = "key"; //$NON-NLS-1$
  public static final String VALUE_NODE = "value"; //$NON-NLS-1$
  
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
    this(COMPONENT_NAME);
  }

  protected MdxConnectionAction(String componentName) {
    super(componentName);
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
  
  public void setLocation(IActionInputValueProvider value) {
    setActionInputValue(LOCATION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
    }
  }
  
  public IActionInputValueProvider getLocation() {
    return getActionInputValue(LOCATION_ELEMENT);
  }
  
  public void setUserId(IActionInputValueProvider value) {
    setActionInputValue(USER_ID_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setJndi(null);
      setConnectionProps(null);      
    }
  }
  
  public IActionInputValueProvider getUserId() {
    return getActionInputValue(USER_ID_ELEMENT);
  }
  
  public void setPassword(IActionInputValueProvider value) {
    setActionInputValue(PASSWORD_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setJndi(null);
      setConnectionProps(null);
    }
  }

  public IActionInputValueProvider getPassword() {
    return getActionInputValue(PASSWORD_ELEMENT);
  }
  
  public void setDriver(IActionInputValueProvider value) {
    setActionInputValue(DRIVER_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setJndi(null);
      setConnectionProps(null);
    }
  }
  
  public IActionInputValueProvider getDriver() {
    return getActionInputValue(DRIVER_ELEMENT);
  }
  
  public void setMdxConnectionString(IActionInputValueProvider value) {
    setActionInputValue(MDX_CONNECTION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
      setConnection(null);
      setConnectionProps(null);
      setLocation(null);
      setUserId(null);
      setPassword(null);
      setDriver(null);
      setRole(null);
    }
  }
  
  public IActionInputValueProvider getMdxConnectionString() {
    return getActionInputValue(MDX_CONNECTION_ELEMENT);
  }
  
  public void setRole(IActionInputValueProvider value) {
    setActionInputValue(ROLE_ELEMENT, value);
  }
  
  public IActionInputValueProvider getRole() {
    return getActionInputValue(ROLE_ELEMENT);
  }
  
  public void setConnection(IActionInputValueProvider value) {
    setActionInputValue(CONNECTION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
      setMdxConnectionString(null);
      setConnectionProps(null);
    }
  }
  
  public IActionInputValueProvider getConnection() {
    return getActionInputValue(CONNECTION_ELEMENT);
  }

  public void setConnectionProps(IActionInputValueProvider value) {
    if (value instanceof IActionInputVariable) {
      throw new IllegalArgumentException();
    } else if (value == null || value.getValue() == null) {
      clearComponentDef(CONNECTION_PROPS);      
    } else {
      clearComponentDef(CONNECTION_PROPS);
      /*
       * Get the hash map of the key, value pairs from the properties and
       * create an entry for each key inside element CONNECTION_PROPS
       */
      Properties properties = (Properties)value.getValue();
      Element compDefElement =(Element)actionDefElement.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME);
      Element connectionPropsElement = compDefElement.addElement(CONNECTION_PROPS);
      for (Iterator iter = properties.entrySet().iterator(); iter.hasNext();) {
        Map.Entry mapEntry = (Map.Entry)iter.next();
        Element propElement = connectionPropsElement.addElement(PROPERTY);
        propElement.addElement(KEY_NODE).setText(mapEntry.getKey().toString());
        propElement.addElement(VALUE_NODE).setText(mapEntry.getValue().toString());
      }
      setDriver(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
      setMdxConnectionString(null);
      setConnection(null);
    }
  }

  public IActionInputValueProvider getConnectionProps() {
    IActionInputValueProvider actionInput = IActionInputValueProvider.NULL_INPUT;
    Element connectionPropsElement = getComponentDefElement(CONNECTION_PROPS);
    if (connectionPropsElement != null) {
      Properties properties = new Properties();
      List propertyElements = connectionPropsElement.selectNodes(PROPERTY);
      for (Iterator iter = propertyElements.iterator(); iter.hasNext();) {
        Element propElement = (Element)iter.next();
        Element keyElement = propElement.element(KEY_NODE);
        Element valueElement = propElement.element(VALUE_NODE);
        if (keyElement != null) {
          properties.put(keyElement.getText(), valueElement != null ? valueElement.getText(): null);
        }
      }
      actionInput = new ActionInputConstant(properties);
    }
    return actionInput;
  }
  
  private void clearComponentDef (String key) {
    Element element = getComponentDefElement(key);
    if (element != null) {
      element.detach();
    }
  }  
  
  public void setJndi(IActionInputValueProvider value) {
    setActionInputValue(JNDI_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setMdxConnectionString(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setDriver(null);
      setConnectionProps(null);   
    }
  }
  
  public IActionInputValueProvider getJndi() {
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
  
  public ActionResource setCatalogResource(URI uri, String mimeType) {
    return setResourceUri(CATALOG_ELEMENT, uri, mimeType);
  }
  
  public ActionResource getCatalogResource() {
    return getResourceParam(CATALOG_ELEMENT);
  }
  
  public void setCatalog(IActionInputValueProvider value) {
    setActionInputValue(CATALOG_ELEMENT, value);
  }
  
  public IActionInputValueProvider getCatalog() {
    return getActionInputValue(CATALOG_ELEMENT);
  }

}
