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

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.AbstractList;
import java.util.ArrayList;

import javax.activation.DataSource;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionResource;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionVariable;

public class JFreeReportAction extends ActionDefinition {

  public class StaticReportConfigItem {
    Element configurationElement;
    
    StaticReportConfigItem(Element configurationElement) {
      this.configurationElement = configurationElement;
    }
    
    public String getName() {
      return configurationElement.attributeValue(CONFIG_PARAM_NAME);
    }
    
    public String getValue() {
      return configurationElement.getText();
    }
    
    public void setName(String name) {
      configurationElement.addAttribute(CONFIG_PARAM_NAME, name);
    }
    
    public void setValue(String value) {
      configurationElement.setText(value);
    }
    
    public void delete() {
      configurationElement.detach();
    }
  }
  
  public class StaticReportConfig extends AbstractList {
    
    public Object add(String paramName, String paramValue) {
      Element element = DocumentHelper.makeElement(getElement(), ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + REPORT_CONFIG_ELEMENT);
      element.addElement(paramName);
      element.addAttribute(CONFIG_PARAM_NAME, paramName);
      element.setText(paramValue);
      return new StaticReportConfigItem(element);
    }

    public Object get(int i) {
      return new StaticReportConfigItem(getComponentDefElements(REPORT_CONFIG_ELEMENT + "/*")[i]);
    }

    public Object remove(int i) {
      StaticReportConfigItem staticReportConfigItem = (StaticReportConfigItem)get(i);
      staticReportConfigItem.delete();
      return staticReportConfigItem;
    }

    public int size() {
      return getComponentDefElements(REPORT_CONFIG_ELEMENT + "/*").length;
    }
  }
  
  public class DataJar {
    
    DataJar() {
    }
    
    public void setDataClass(String className) {
      setInputValue(JFreeReportAction.REPORT_DATA_JAR_CLASS_ELEMENT, className);
      if (className != null) {
        setDataParam(null);
        setDataComponent(null);
        
        // The following elements are deprecated within this component. Old style action sequences
        // may still have these elements in place. We'll clean them up now.
        setDriver(null);
        setConnection(null);
        setUserId(null);
        setPassword(null);
        setJndi(null);
        setQuery(null);
        // End deprecated functionality.
      }
    }
    
    public String getDataClass() {
      Object className = getInputValue(JFreeReportAction.REPORT_DATA_JAR_CLASS_ELEMENT);
      if ((className != null) && (actionParameterMgr != null)) {
        className = actionParameterMgr.replaceParameterReferences(className.toString());
      }
      return className != null ? className.toString() : (String)className;
    }
    
    public void setDataClassParam(IActionVariable variable) {
      setInputParam(JFreeReportAction.REPORT_DATA_JAR_CLASS_ELEMENT, variable);
      if (variable != null) {
        setDataParam(null);
        setDataComponent(null);
        
        // The following elements are deprecated within this component. Old style action sequences
        // may still have these elements in place. We'll clean them up now.
        setDriver(null);
        setConnection(null);
        setUserId(null);
        setPassword(null);
        setJndi(null);
        setQuery(null);
        // End deprecated functionality.
      }
    }
    
    public ActionInput getDataClassParam() {
      return getInputParam(JFreeReportAction.REPORT_DATA_JAR_CLASS_ELEMENT);

    }
    
    public ActionResource setJar(URI uri) {
      ActionResource actionResource = setResourceUri(JFreeReportAction.REPORT_DATA_JAR_ELEMENT, uri, "application/java-archive");
      if (uri != null) {
        setDataParam(null);
        setDataComponent(null);
        
        // The following elements are deprecated within this component. Old style action sequences
        // may still have these elements in place. We'll clean them up now.
        setDriver(null);
        setConnection(null);
        setUserId(null);
        setPassword(null);
        setJndi(null);
        setQuery(null);
        // End deprecated functionality.
      }
      return actionResource;
    }
    
    public ActionResource getJar() {
      return getResourceParam(JFreeReportAction.REPORT_DATA_JAR_ELEMENT);
    }
  }
  
  public class ReportDefinitionJar {
    
    ReportDefinitionJar() {
    }
    
    public void setReportLocation(String location) {
      setInputValue(REPORT_LOC_IN_JAR_ELEMENT, location);
      if (location != null) {
        if (!getComponentName().endsWith("JFreeReportComponent")) {
          setComponentName("JFreeReportComponent");
        }
        setReportDefinition(null, null);
        setReportDefinitionParam(null);
      }
    }
    
    public String getReportLocation() {
      Object location = getInputValue(REPORT_LOC_IN_JAR_ELEMENT);
      if ((location != null) && (actionParameterMgr != null)) {
        location = actionParameterMgr.replaceParameterReferences(location.toString());
      }
      return location != null ? location.toString() : (String)location;
    }
    
    public void setReportLocationParam(IActionVariable variable) {
      setInputParam(REPORT_LOC_IN_JAR_ELEMENT, variable);
      if (variable != null) {
        if (!getComponentName().endsWith("JFreeReportComponent")) {
          setComponentName("JFreeReportComponent");
        }
        setReportDefinition(null, null);
        setReportDefinitionParam(null);
      }
    }
    
    public ActionInput getReportLocationParam() {
      return getInputParam(REPORT_LOC_IN_JAR_ELEMENT);

    }
    
    public ActionResource setJar(URI uri) {
      ActionResource actionResource = setResourceUri(REPORT_JAR_ELEMENT, uri, "application/java-archive");
      if (uri != null) {
        if (!getComponentName().endsWith("JFreeReportComponent")) {
          setComponentName("JFreeReportComponent");
        }
        setReportDefinition(null, null);
        setReportDefinitionParam(null);
     }
      return actionResource;
    }
    
    public ActionResource getJar() {
      return getResourceParam(JFreeReportAction.REPORT_JAR_ELEMENT);
    }
  }
  
  public static final String COMPONENT_NAME = "org.pentaho.jfree.JFreeReportComponent"; //$NON-NLS-1$
  public static final String JFREE_COMPONENT_SHORT_NAME = "JFreeReportComponent"; //$NON-NLS-1$
  public static final String JFREE_WIZ_COMPONENT_SHORT_NAME = "ReportWizardSpecComponent"; //$NON-NLS-1$
  public static final String OUTPUT_TYPE_ELEMENT = "output-type"; //$NON-NLS-1$
  public static final String REPORT_OUTPUT_ELEMENT = "report-output"; //$NON-NLS-1$
  public static final String DRIVER_ELEMENT = "driver"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String LIVE_CONNECTION_ELEMENT = "live"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String DATA_ELEMENT = "data"; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String XSL_ELEMENT = "xsl"; //$NON-NLS-1$
  public static final String REPORT_DEFINITION_ELEMENT = "report-definition"; //$NON-NLS-1$
  public static final String REPORT_WIZ_SPEC_ELEMENT = "report-spec"; //$NON-NLS-1$
  public static final String REPORT_DATA_SOURCE_ELEMENT = "source"; //$NON-NLS-1$
  public static final String REPORT_JAR_ELEMENT = "report-jar"; //$NON-NLS-1$
  public static final String REPORT_LOC_IN_JAR_ELEMENT = "report-location";
  public static final String MDX_DATA_SOURCE = "mdx"; //$NON-NLS-1$
  public static final String SQL_DATA_SOURCE = "sql"; //$NON-NLS-1$
  public static final String OUTPUT_REPORT = "output-report"; //$NON-NLS-1$
  public static final String REPORT_DATA_JAR_ELEMENT = "report-jar"; //$NON-NLS-1$
  public static final String RESOURCE_NAME_ELEMENT = "resource-name";
  public static final String REPORT_DATA_JAR_CLASS_ELEMENT  = "class-location";
  public static final String CONFIG_PARAM_NAME  = "name";
  private static final String REPORT_CONFIG_ELEMENT = "report_configuration_parameters"; //$NON-NLS-1$
  private static final String REPORT_CONFIG_INPUT_PARAM = "config_parameters"; //$NON-NLS-1$
  public static final String CREATE_PRIVATE_COPY_ELEMENT = "create_private_report"; //$NON-NLS-1$
  public static final String REPORT_GEN_PRIORITY_ELEMENT = "report-priority"; //$NON-NLS-1$
  public static final String PRINTER_NAME_ELEMENT = "printer-name"; //$NON-NLS-1$
  public static final String HTML_CONTENT_HANDLER_ELEMENT = "content-handler"; //$NON-NLS-1$
  public static final String REPORT_GEN_YIELD_RATE_ELEMENT = "yield-rate"; //$NON-NLS-1$
  
  private DataJar dataJar = new DataJar();
  private ReportDefinitionJar reportJar = new ReportDefinitionJar();
  private StaticReportConfig staticReportConfig = new StaticReportConfig();
  
  protected static final String[] EXPECTED_RESOURCES = new String[] {
    REPORT_DEFINITION_ELEMENT, 
    REPORT_WIZ_SPEC_ELEMENT 
  };
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    DATA_ELEMENT, 
    DRIVER_ELEMENT,
    CONNECTION_ELEMENT,
    USER_ID_ELEMENT,
    PASSWORD_ELEMENT,
    JNDI_ELEMENT,
    QUERY_ELEMENT, 
    XSL_ELEMENT, 
    OUTPUT_TYPE_ELEMENT 
  };
  
  public JFreeReportAction(Element actionDefElement, IActionParameterMgr actionParameterMgr) {
    super(actionDefElement, actionParameterMgr);
  }

  public JFreeReportAction() {
    super(COMPONENT_NAME);
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(OUTPUT_TYPE_ELEMENT, "html");//$NON-NLS-1$
  }

  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getReservedOutputNames() {
    String expectedOutput = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(expectedOutput) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }
  
  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }

  public static boolean accepts(Element element) {
    boolean accepts = false;
    if (ActionDefinition.accepts(element)) {
      String elementComponentName = getComponentName(element);
      int index = elementComponentName.lastIndexOf("."); //$NON-NLS-1$
      if ((index >= 0) && (index < elementComponentName.length() - 1)) {
        elementComponentName = elementComponentName.substring(index + 1);
      }
      
      accepts = elementComponentName.equals(JFREE_COMPONENT_SHORT_NAME) || 
          elementComponentName.equals(JFREE_WIZ_COMPONENT_SHORT_NAME);
    }
    return accepts;
  }
  
  
  /**
   * @deprecated 
   */
  public void setConnection(String value) {
    setInputValue(CONNECTION_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public String getConnection() {
    Object connection = getInputValue(CONNECTION_ELEMENT);
    if ((connection != null) && (actionParameterMgr != null)) {
      connection = actionParameterMgr.replaceParameterReferences(connection.toString());
    }
    return connection != null ? connection.toString() : (String)connection;
  }
  
  /**
   * @deprecated 
   */
  public void setConnectionParam(IActionVariable variable) {
    setInputParam(CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public ActionInput getConnectionParam() {
    return getInputParam(CONNECTION_ELEMENT);
  }
  
  /**
   * @deprecated 
   */
  public void setUserId(String value) {
    setInputValue(USER_ID_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public String getUserId() {
    Object userId = getInputValue(USER_ID_ELEMENT);
    if ((userId != null) && (actionParameterMgr != null)) {
      userId = actionParameterMgr.replaceParameterReferences(userId.toString());
    }
    return userId != null ? userId.toString() : (String)userId;
  }
  
  /**
   * @deprecated 
   */
  public void setUserIdParam(IActionVariable variable) {
    setInputParam(USER_ID_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public ActionInput getUserIdParam() {
    return getInputParam(USER_ID_ELEMENT);
  }
  
  /**
   * @deprecated 
   */
  public void setDriver(String value) {
    setInputValue(DRIVER_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public String getDriver() {
    Object driver = getInputValue(DRIVER_ELEMENT);
    if ((driver != null) && (actionParameterMgr != null)) {
      driver = actionParameterMgr.replaceParameterReferences(driver.toString());
    }
    return driver != null ? driver.toString() : (String)driver;
  }
  
  /**
   * @deprecated 
   */
  public void setDriverParam(IActionVariable variable) {
    setInputParam(DRIVER_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public ActionInput getDriverParam() {
    return getInputParam(DRIVER_ELEMENT);
  }
  
  /**
   * @deprecated 
   */
  public void setPassword(String value) {
    setInputValue(PASSWORD_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public String getPassword() {
    Object password = getInputValue(PASSWORD_ELEMENT);
    if ((password != null) && (actionParameterMgr != null)) {
      password = actionParameterMgr.replaceParameterReferences(password.toString());
    }
    return password != null ? password.toString() : (String)password;
  }
  
  /**
   * @deprecated 
   */
  public void setPasswordParam(IActionVariable variable) {
    setInputParam(PASSWORD_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public ActionInput getPasswordParam() {
    return getInputParam(PASSWORD_ELEMENT);
  }
  
  /**
   * @deprecated 
   */
  public void setJndi(String value) {
    setInputValue(JNDI_ELEMENT, value);
    if (value != null) {
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public String getJndi() {
    Object jndiName = getInputValue(JNDI_ELEMENT);
    if ((jndiName != null) && (actionParameterMgr != null)) {
      jndiName = actionParameterMgr.replaceParameterReferences(jndiName.toString());
    }
    return jndiName != null ? jndiName.toString() : (String)jndiName;
  }
  
  /**
   * @deprecated 
   */
  public void setJndiParam(IActionVariable variable) {
    setInputParam(JNDI_ELEMENT, variable);
    if (variable != null) {
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public ActionInput getJndiParam() {
    return getInputParam(JNDI_ELEMENT);
  }
  
  public void setOutputType(String value) {
    setInputValue(OUTPUT_TYPE_ELEMENT, value);
    if (value != null) {
      setPrinterName(null);
    }
  }
  
  public String getOutputType() {
    Object outputType = getInputValue(OUTPUT_TYPE_ELEMENT);
    if ((outputType != null) && (actionParameterMgr != null)) {
      outputType = actionParameterMgr.replaceParameterReferences(outputType.toString());
    }
    return outputType != null ? outputType.toString() : (String)outputType;
  }
  
  public void setOutputTypeParam(IActionVariable variable) {
    setInputParam(OUTPUT_TYPE_ELEMENT, variable);
    if (variable != null) {
      setPrinterName(null);
    }
  }
  
  public ActionInput getOutputTypeParam() {
    return getInputParam(OUTPUT_TYPE_ELEMENT);
  }
  
  public void setPrinterName(String value) {
    setInputValue(PRINTER_NAME_ELEMENT, value);
    if (value != null) {
      setOutputType(null);
    }
  }
  
  public String getPrinterName() {
    Object printerName = getInputValue(PRINTER_NAME_ELEMENT);
    if ((printerName != null) && (actionParameterMgr != null)) {
      printerName = actionParameterMgr.replaceParameterReferences(printerName.toString());
    }
    return printerName != null ? printerName.toString() : (String)printerName;
  }
  
  public void setPrinterNameParam(IActionVariable variable) {
    setInputParam(PRINTER_NAME_ELEMENT, variable);
    if (variable != null) {
      setOutputType(null);
    }
  }
  
  public ActionInput getPrinterNameParam() {
    return getInputParam(PRINTER_NAME_ELEMENT);
  }

  public Object getData() {
    return getInputValue(DATA_ELEMENT);
  }
  
  public void setDataParam(IActionVariable variable) {
    setInputParam(DATA_ELEMENT, variable);
    if (variable != null) {
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
      // The following elements are deprecated within this component. Old style action sequences
      // may still have these elements in place. We'll clean them up now.
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
      setQuery(null);
      // End deprecated functionality.
    }
  }
  
  public ActionInput getDataParam() {
    return getInputParam(DATA_ELEMENT);
  }
  
  /**
   * @deprecated 
   */
  public void setQuery(String value) {
    setInputValue(QUERY_ELEMENT, value);
    if (value != null) {
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public String getQuery() {
    Object query = getInputValue(QUERY_ELEMENT);
    if ((query != null) && (actionParameterMgr != null)) {
      query = actionParameterMgr.replaceParameterReferences(query.toString());
    }
    return query != null ? query.toString() : (String)query;
  }
  
  /**
   * @deprecated 
   */
  public void setQueryParam(IActionVariable variable) {
    setInputParam(QUERY_ELEMENT, variable);
    if (variable != null) {
      setDataParam(null);
      setDataComponent(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
    }
  }
  
  /**
   * @deprecated 
   */
  public ActionInput getQueryParam() {
    return getInputParam(QUERY_ELEMENT);
  }
  
  public void setOutputReportName(String name) {
    setOutputParam(REPORT_OUTPUT_ELEMENT, name, ActionSequenceDocument.CONTENT_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      ActionOutput[] actionOutputs = getAllOutputParams();
      for (int i = 0; i < actionOutputs.length; i++) {
        if (actionOutputs[i].getType().equals(ActionSequenceDocument.CONTENT_TYPE)
            && !actionOutputs[i].getName().equals(REPORT_OUTPUT_ELEMENT)) {
          actionOutputs[i].delete();
        }
      }
    }
  }
  
  public String getOutputReportName() {
    String privateOutputName = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(privateOutputName) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        privateOutputName = actionOutputs[0].getName();
      }
    }
    return getPublicOutputName(privateOutputName);
  }
  
  public ActionOutput getOutputReportParam() {
    String privateOutputName = REPORT_OUTPUT_ELEMENT;
    if (getOutputParam(privateOutputName) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        privateOutputName = actionOutputs[0].getName();
      }
    }
    return getOutputParam(REPORT_OUTPUT_ELEMENT);
  }
  
  public ActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInputParam(DATA_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing report data input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Report data input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Report data input parameter is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    validationError = validateResourceParam(REPORT_DEFINITION_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing report definition input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Report definition input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Report definition input parameter is unitialized.";
          break;
      }
      errors.add(validationError);
    }
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
  
  public ActionResource setReportDefinition(URI uri, String mimeType) {
    ActionResource actionResource = null;
    if (uri == null) {
      setResourceUri(REPORT_DEFINITION_ELEMENT, null, mimeType);
      setResourceUri(REPORT_WIZ_SPEC_ELEMENT, null, mimeType);
    } else {
      String componentName = "JFreeReportComponent"; //$NON-NLS-1$
      if (uri.getSchemeSpecificPart().indexOf(".xreportspec") >= 0) {
        componentName = "ReportWizardSpecComponent"; //$NON-NLS-1$
      }
      if (!getComponentName().endsWith(componentName)) {
        setComponentName(componentName);
      }
      if ("JFreeReportComponent".equals(componentName)) { //$NON-NLS-1$
        setResourceUri(JFreeReportAction.REPORT_WIZ_SPEC_ELEMENT, null, null);
        actionResource = setResourceUri(REPORT_DEFINITION_ELEMENT, uri, mimeType);         
      } else if ("ReportWizardSpecComponent".equals(componentName)) { //$NON-NLS-1$
        setResourceUri(JFreeReportAction.REPORT_DEFINITION_ELEMENT, null, null);
        actionResource = setResourceUri(REPORT_WIZ_SPEC_ELEMENT, uri, mimeType);         
      }
      setReportDefinitionParam(null);
      getReportDefinitionJar().setJar(null);
      getReportDefinitionJar().setReportLocation(null);
    }
    return actionResource;
  }
  
  public ActionResource getReportDefinition() {
    ActionResource actionResource = null;
    if (getComponentName().endsWith("JFreeReportComponent")) {
      actionResource = getResourceParam(REPORT_DEFINITION_ELEMENT);
    } else if (getComponentName().endsWith("ReportWizardSpecComponent")){
      actionResource = getResourceParam(REPORT_WIZ_SPEC_ELEMENT);
    }
    if (actionResource == null) {
      Object resourceName = getInputValue(RESOURCE_NAME_ELEMENT);
      if (resourceName != null) {
        actionResource = getResourceParam(resourceName.toString());
      }
    }
    return actionResource;
  }
  
  public DataSource getReportDefinitionDataSource() throws FileNotFoundException {
    ActionResource reportDefinition = getReportDefinition();
    return reportDefinition != null ? reportDefinition.getDataSource() : null;
  }
  
  public void setReportDefinitionParam(IActionVariable variable) {
    setInputParam(REPORT_DEFINITION_ELEMENT, variable);
    if (variable != null) {
      if (!getComponentName().endsWith("JFreeReportComponent")) {
        setComponentName("JFreeReportComponent");
      }
      setReportDefinition(null, null);
      getReportDefinitionJar().setJar(null);
      getReportDefinitionJar().setReportLocation(null);
    }
  }
  
  public ActionInput getReportDefinitionParam() {
    return getInputParam(REPORT_DEFINITION_ELEMENT);
  }
  
  public void setDataComponent(String componentClassName) {
    setInputValue(REPORT_DATA_SOURCE_ELEMENT, componentClassName);
    if (componentClassName != null) {
      setDataParam(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
      
      // The following elements are deprecated within this component. Old style action sequences
      // may still have these elements in place. We'll clean them up now.
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
      setQuery(null);
      // End deprecated functionality.
    }
  }
  
  public String getDataComponent() {
    Object componentClassName = getInputValue(REPORT_DATA_SOURCE_ELEMENT);
    if ((componentClassName != null) && (actionParameterMgr != null)) {
      componentClassName = actionParameterMgr.replaceParameterReferences(componentClassName.toString());
    }
    return componentClassName != null ? componentClassName.toString() : (String)componentClassName;
  }
  
  
  public void setDataComponentParam(IActionVariable variable) {
    setInputParam(REPORT_DATA_SOURCE_ELEMENT, variable);
    if (variable != null) {
      setDataParam(null);
      getDataJar().setJar(null);
      getDataJar().setDataClass(null);
      
      // The following elements are deprecated within this component. Old style action sequences
      // may still have these elements in place. We'll clean them up now.
      setDriver(null);
      setConnection(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
      setQuery(null);
      // End deprecated functionality.
    }
  }
  
  public ActionInput getDataComponentParam() {
    return getInputParam(REPORT_DATA_SOURCE_ELEMENT);

  }
  
  public DataJar getDataJar() {
    return dataJar;
  }
  
  public ReportDefinitionJar getReportDefinitionJar() {
    return reportJar;
  }
    
  public void setReportConfigParam(IActionVariable variable) {
    setInputParam(REPORT_CONFIG_INPUT_PARAM, variable);
  }
  
  public ActionInput getReportConfigParam() {
    return getInputParam(REPORT_CONFIG_INPUT_PARAM);
  }
  
  public Object getReportConfig() {
    Object reportConfig = getInputValue(REPORT_CONFIG_INPUT_PARAM);
    if (reportConfig == null) {
      reportConfig = staticReportConfig;
    }
    return reportConfig;
  }
  
  public void setCreatePrivateCopy(boolean value) {
    setInputValue(CREATE_PRIVATE_COPY_ELEMENT, value ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getCreatePrivateCopy() {
    Object live = getInputValue(CREATE_PRIVATE_COPY_ELEMENT);
    if ((live != null) && (actionParameterMgr != null)) {
      live = actionParameterMgr.replaceParameterReferences(live.toString());
    }
    return live != null ? Boolean.parseBoolean(live.toString()) : false;
  }
  
  public void setCreatePrivateCopyParam(IActionVariable variable) {
    setInputParam(CREATE_PRIVATE_COPY_ELEMENT, variable);
  }
  
  public ActionInput getCreatePrivateCopyParam() {
    return getInputParam(CREATE_PRIVATE_COPY_ELEMENT);
  }
  
  public void setReportGenerationPriority(String value) {
    setInputValue(REPORT_GEN_PRIORITY_ELEMENT, null); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public String getReportGenerationPriority() {
    Object priority = getInputValue(REPORT_GEN_PRIORITY_ELEMENT);
    if ((priority != null) && (actionParameterMgr != null)) {
      priority = actionParameterMgr.replaceParameterReferences(priority.toString());
    }
    return priority != null ? priority.toString() : null;
  }
  
  public void setReportGenerationPriorityParam(IActionVariable variable) {
    setInputParam(REPORT_GEN_PRIORITY_ELEMENT, variable);
  }
  
  public ActionInput getReportGenerationPriorityParam() {
    return getInputParam(REPORT_GEN_PRIORITY_ELEMENT);
  }
  
  public void setHtmlContentHandlerUrlPattern(String value) {
    setInputValue(HTML_CONTENT_HANDLER_ELEMENT, null); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public String getHtmlContentHandlerUrlPattern() {
    Object priority = getInputValue(HTML_CONTENT_HANDLER_ELEMENT);
    if ((priority != null) && (actionParameterMgr != null)) {
      priority = actionParameterMgr.replaceParameterReferences(priority.toString());
    }
    return priority != null ? priority.toString() : null;
  }
  
  public void setHtmlContentHandlerUrlPatternParam(IActionVariable variable) {
    setInputParam(HTML_CONTENT_HANDLER_ELEMENT, variable);
  }
  
  public ActionInput getHtmlContentHandlerUrlPatternParam() {
    return getInputParam(HTML_CONTENT_HANDLER_ELEMENT);
  }
  
  public void setReportGenerationYieldRate(int value) {
    setInputValue(REPORT_GEN_YIELD_RATE_ELEMENT, null); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public int getReportGenerationYieldRate() {
    int yieldRate = 0;
    Object value = getInputValue(REPORT_GEN_YIELD_RATE_ELEMENT);
    if (value instanceof Number) {
      Number n = (Number) value;
      if (n.intValue() > 0) {
        yieldRate = n.intValue();
      }
    } else if (value != null) {
      try {
        yieldRate = Integer.parseInt(value.toString());
      } catch (Exception ex) {
        //Do nothing.
      }
    }
    return yieldRate;
  }
  
  public void setReportGenerationYieldRateParam(IActionVariable variable) {
    setInputParam(REPORT_GEN_YIELD_RATE_ELEMENT, variable);
  }
  
  public ActionInput getReportGenerationYieldRateParam() {
    return getInputParam(REPORT_GEN_YIELD_RATE_ELEMENT);
  }
  
  
  public ActionInput[] getSubreportQueryParams() {
    ArrayList subreportQueryParams = new ArrayList();
    ActionInput mainReportData = getDataParam();
    ActionInput[] actionInputs = getInputParams(new String[] {ActionSequenceDocument.RESULTSET_TYPE, ActionSequenceDocument.MDX_QUERY_TYPE, ActionSequenceDocument.SQL_QUERY_TYPE, ActionSequenceDocument.XQUERY_TYPE, ActionSequenceDocument.HQL_QUERY_TYPE});
    for (int i = 0; i < actionInputs.length; i++) {
      if (!actionInputs[i].equals(mainReportData)) {
        subreportQueryParams.add(actionInputs[i]);
      }
    }
    return (ActionInput[])subreportQueryParams.toArray(new ActionInput[0]);
  }
  
  public void setSubReportQueryParams(IActionVariable[] subQueryVariables) {
    ActionInput[] actionInputs = getSubreportQueryParams();
    for (int i = 0; i < actionInputs.length; i++) {
      actionInputs[i].delete();
    }
    if (subQueryVariables != null) {
      for (int i = 0; i < subQueryVariables.length; i++) {
        setInputParam(subQueryVariables[i].getVariableName(), subQueryVariables[i]);
      }
    }
  }
  
  
}
