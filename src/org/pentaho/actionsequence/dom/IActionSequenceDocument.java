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

import java.net.URI;

import org.dom4j.Document;
import org.pentaho.actionsequence.dom.actions.ActionDefinition;

/**
 * A wrapper class for an action definition resource element.
 * @author Angelo Rodriguez
 *
 */
public interface IActionSequenceDocument {

  public static final String SUSPEND_SCHEDULER_CMND = "suspendScheduler"; //$NON-NLS-1$
  
  // Document header nodes
  public static final String ACTION_SEQUENCE = "action-sequence"; //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_NAME = "name"; //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_TITLE = "title";  //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_VERSION = "version"; //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_LOGGING_LEVEL = "logging-level"; //$NON-NLS-1$
  public static final String LOG_LEVEL_TRACE = "TRACE"; //$NON-NLS-1$
  public static final String LOG_LEVEL_DEBUG = "DEBUG"; //$NON-NLS-1$
  public static final String LOG_LEVEL_INFO = "INFO"; //$NON-NLS-1$
  public static final String LOG_LEVEL_WARN = "WARN"; //$NON-NLS-1$
  public static final String LOG_LEVEL_ERROR = "ERROR"; //$NON-NLS-1$
  public static final String LOG_LEVEL_FATAL = "FATAL"; //$NON-NLS-1$
  public static final String[] LOGGING_LEVELS = new String[] { LOG_LEVEL_TRACE, LOG_LEVEL_DEBUG, LOG_LEVEL_INFO, LOG_LEVEL_WARN, LOG_LEVEL_ERROR, LOG_LEVEL_FATAL }; 

  public static final String ACTION_SEQUENCE_DOCUMENTATION = "documentation";      //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_DOCUMENTATION_AUTHOR = "documentation/author";      //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_DOCUMENTATION_DESCRIPT = "documentation/description";     //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_DOCUMENTATION_HELP = "documentation/help"; //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_DOCUMENTATION_RESULT_TYPE = "documentation/result-type"; //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_DOCUMENTATION_ICON = "documentation/icon"; //$NON-NLS-1$
  
  // Document Inputs nodes
  public static final String DOC_INPUTS_NAME = "inputs"; //$NON-NLS-1$
  public static final String INPUT_SOURCES_NAME = "sources"; //$NON-NLS-1$
  public static final String REQUEST_INPUT_SOURCE = "request"; //$NON-NLS-1$
  public static final String SESSION_INPUT_SOURCE = "session"; //$NON-NLS-1$
  public static final String RUNTIME_INPUT_SOURCE = "runtime"; //$NON-NLS-1$
  public static final String GLOBAL_INPUT_SOURCE = "global"; //$NON-NLS-1$
  public static final String SECURITY_INPUT_SOURCE = "security"; //$NON-NLS-1$
  
  public static final String[] INPUT_SOURCES = new String[] { REQUEST_INPUT_SOURCE, SESSION_INPUT_SOURCE, GLOBAL_INPUT_SOURCE, RUNTIME_INPUT_SOURCE, SECURITY_INPUT_SOURCE };
  
  // Document Outputs nodes
  public static final String DOC_OUTPUTS_NAME = "outputs"; //$NON-NLS-1$
  public static final String OUTPUTS_DESTINATIONS_NAME = "destinations"; //$NON-NLS-1$
  public static final String RESPONSE_OUTPUT_DESTINATION = "response"; //$NON-NLS-1$
  public static final String SESSION_OUTPUT_DESTINATION = "session"; //$NON-NLS-1$
  public static final String RUNTIME_OUTPUT_DESTINATION = "runtime"; //$NON-NLS-1$
  public static final String GLOBAL_OUTPUT_DESTINATION = "global"; //$NON-NLS-1$
  public static final String CONTENT_REPO_OUTPUT_DESTINATION = "contentrepo"; //$NON-NLS-1$
  
  public static final String[] OUTPUT_DESTINATIONS = new String[]{ RUNTIME_OUTPUT_DESTINATION, SESSION_OUTPUT_DESTINATION, RESPONSE_OUTPUT_DESTINATION, CONTENT_REPO_OUTPUT_DESTINATION, GLOBAL_OUTPUT_DESTINATION };
  
  public static final String DOC_RESOURCES_NAME = "resources"; //$NON-NLS-1$

  // Data Types
  public static final String STRING_TYPE = "s" +
  		"tring"; //$NON-NLS-1$
  public static final String LONG_TYPE = "long"; //$NON-NLS-1$
  public static final String INTEGER_TYPE = "integer"; //$NON-NLS-1$
  public static final String BIGDECIMAL_TYPE = "bigdecimal"; //$NON-NLS-1$
  public static final String LIST_TYPE = "list"; //$NON-NLS-1$
  public static final String OBJECT_TYPE = "object"; //$NON-NLS-1$
  public static final String DATE_TYPE = "date"; //$NON-NLS-1$
  public static final String RESULTSET_TYPE = "result-set"; //$NON-NLS-1$
  public static final String STRING_LIST_TYPE = "string-list"; //$NON-NLS-1$
  public static final String PROPERTY_MAP_TYPE = "property-map"; //$NON-NLS-1$
  public static final String PROPERTY_MAP_LIST_TYPE = "property-map-list"; //$NON-NLS-1$
  public static final String CONTENT_TYPE = "content"; //$NON-NLS-1$
  public static final String RESOURCE_TYPE = "resource"; //$NON-NLS-1$
  public static final String SQL_CONNECTION_TYPE = "sql-connection"; //$NON-NLS-1$
  public static final String MDX_CONNECTION_TYPE = "mdx-connection"; //$NON-NLS-1$
  public static final String XQUERY_CONNECTION_TYPE = "xquery-connection"; //$NON-NLS-1$
  public static final String SQL_QUERY_TYPE = "sql-query"; //$NON-NLS-1$
  public static final String MDX_QUERY_TYPE = "mdx-query"; //$NON-NLS-1$
  public static final String HQL_QUERY_TYPE = "hql-query"; //$NON-NLS-1$
  public static final String XQUERY_TYPE = "xquery"; //$NON-NLS-1$
  public static final String INPUT_STREAM_TYPE = "inputstream"; //$NON-NLS-1$
  
  public static final String PROPERTY_MAP_ENTRY = "entry"; //$NON-NLS-1$
  public static final String PROPERTY_MAP_ENTRY_KEY = "key"; //$NON-NLS-1$
  public static final String RESULTSET_DEFAULT_COLUMNS = "columns"; //$NON-NLS-1$
  public static final String RESULTSET_ROW = "row"; //$NON-NLS-1$
  public static final String DEFAULT_STRING_LIST_ITEM = "list-item"; //$NON-NLS-1$
  public static final String DEFAULT_VAL_NAME = "default-value"; //$NON-NLS-1$

  // Actions group nodes
  public static final String ACTIONS_NAME = "actions"; //$NON-NLS-1$
  public static final String LOOP_ON_NAME = "loop-on"; //$NON-NLS-1$
  public static final String PEEK_ONLY_NAME = "peek-only"; //$NON-NLS-1$
  public static final String CONDITION_NAME = "condition"; //$NON-NLS-1$

  // Action definition nodes
  public static final String ACTION_DEFINITION_NAME = "action-definition"; //$NON-NLS-1$
  public static final String ACTION_TYPE_NAME = "action-type"; //$NON-NLS-1$
  public static final String COMPONENT_DEF_NAME = "component-definition"; //$NON-NLS-1$
  public static final String COMPONENT_NAME = "component-name"; //$NON-NLS-1$
  public static final String ACTION_INPUTS_NAME = "action-inputs"; //$NON-NLS-1$
  public static final String ACTION_RESOURCES_NAME = "action-resources"; //$NON-NLS-1$
  public static final String ACTION_OUTPUTS_NAME = "action-outputs"; //$NON-NLS-1$
  public static final String MAPPING_NAME = "mapping"; //$NON-NLS-1$

  public static final String[] IO_TYPES = new String[] { STRING_TYPE, LONG_TYPE, INTEGER_TYPE, BIGDECIMAL_TYPE, STRING_LIST_TYPE, LIST_TYPE, RESULTSET_TYPE, PROPERTY_MAP_TYPE, PROPERTY_MAP_LIST_TYPE, CONTENT_TYPE, OBJECT_TYPE, INPUT_STREAM_TYPE };
  public static final String DOC_INPUTS_PATH = "/" + ACTION_SEQUENCE + "/" + DOC_INPUTS_NAME; //$NON-NLS-1$ //$NON-NLS-2$
  public static final String DOC_OUTPUTS_PATH = "/" + ACTION_SEQUENCE + "/" + DOC_OUTPUTS_NAME; //$NON-NLS-1$ //$NON-NLS-2$
  public static final String DOC_RESOURCES_PATH = "/" + ACTION_SEQUENCE + "/" + DOC_RESOURCES_NAME; //$NON-NLS-1$ //$NON-NLS-2$
  public static final String DOC_ACTIONS_PATH = "/" + ACTION_SEQUENCE + "/" + ACTIONS_NAME; //$NON-NLS-1$ //$NON-NLS-2$
  
  /**
   * @param xPath
   * @return the element identified by the specified XPath
   */
  public IActionSequenceElement getElement(String xPath);
  
  /**
   * @return the document wrapped by this object
   */
  public Document getDocument();
  
  /**
   * @return the action sequence description
   */
  public String getDescription();
  
  /**
   * @return the help message 
   */
  public String getHelp();
  
  /**
   * @return the action sequence version
   */
  public String getVersion();
  
  /**
   * @return the logging level being used when this action sequence is
   * executed.
   */
  public String getLoggingLevel();
  
  /**
   * @return the action sequence author
   */
  public String getAuthor();
  
  /**
   * @return the type of results returned by the action sequence
   */
  public String getResultType();
  
  /**
   * @return the location of the icon used by the action sequence.
   */
  public String getIconLocation();
  
  /**
   * @return the location of the flyover icon used by the action sequence.
   */
  public String getFlyoverIconLocation();
  
  /**
   * @return the action sequence title
   */
  public String getTitle();
  
  /**
   * Sets the action sequence description.
   * @param value the description
   */
  public void setDescription(String value);
  
  /**
   * Sets the action sequence help message
   * @param value the help message
   */
  public void setHelp(String value);
    
  /**
   * Sets the action sequence version.
   * @param value the version
   */
  public void setVersion(String value);
  
  /**
   * Sets the action sequence logging level
   * @param value the logging level
   */
  public void setLoggingLevel(String value);
  
  /**
   * Sets the action sequence author
   * @param value the author name
   */
  public void setAuthor(String value);
  
  /**
   * Sets the action sequence result type
   * @param value the result type
   */
  public void setResultType(String value);
  
  /**
   * Sets the location of the icon for this action sequence
   * @param value the icon path
   */
  public void setIconLocation(String value);
  
  /**
   * Sets the location of the icon for this action sequence
   * @param value the icon path
   */
  public void setFlyoverIconLocation(String value);
  
  /**
   * Sets the action sequence title.
   * @param value the title
   */
  public void setTitle(String value);
  
  /**
   * Removes the named action sequence input from the action sequence
   * @param inputName the input name
   */
  public void deleteInput(String inputName);
  
  /**
   * @return the action sequence inputs of the specified type
   */
  public IActionSequenceInput[] getInputs(String[] types);
  
  /**
   * @return all the inputs to the action sequence
   */
  public IActionSequenceInput[] getInputs();
  
  /**
   * @param inputName the input name.
   * @return the input with the given name or null if it does not exist
   */
  public IActionSequenceInput getInput(String inputName);
  
  /**
   * Adds a new input this action sequence. If the input already 
   * exists the type of the input is set to the specified type.
   * 
   * @param inputName the input name
   * @param inputType the input type
   * @return the action sequence input
   */
  public IActionSequenceInput createInput(String inputName, String inputType);
  
  /**
   * Removes the output of the given name from the action sequence.
   * @param outputName the output name.
   */
  public void deleteOutput(String outputName);
  
  /**
   * @return an array of all the outputs from this action sequence.
   */
  public IActionSequenceOutput[] getOutputs();
  
  /**
   * @param outputName
   * @return the action sequence output with the given name or null if 
   * it does not exist.
   */
  public IActionSequenceOutput getOutput(String outputName);
  
  /**
   * Adds a new output to this action sequence. If the output already 
   * exists the type of the output is set to the specified type.
   * 
   * @param outputName the input name
   * @param outputType the input type
   * @return the action sequence output
   */
  public IActionSequenceOutput createOutput(String outputName, String outputType);
    
  /**
   * Removes the named action sequence resource from the action sequence
   * @param resourceName the resource name
   */
  public void deleteResource(String resourceName);
  
  /**
   * @return all the action sequence resources
   */
  public IActionSequenceResource[] getResources();
  
  /**
   * @param resourceName the resource name.
   * @return the resource with the given name or null if it does not exist
   */
  public IActionSequenceResource getResource(String resourceName);
  
  /**
   * Adds a new resource to this action sequence. 
   * @param resourceName the resource name
   * @param resourceFileType the resource file type
   * @param filePath the resource file path
   * @param mimeType the resource mime type
   * @return the action sequence resource
   */
  public IActionSequenceResource setResourceUri(String resourceName, URI uri, String mimeType);
  
  public IActionLoop getRootLoop();
  
  /**
   * Create a new loop at the top level of the action sequence
   * @param loopOn the name of the parameter to loop on
   * @param index the index of where to insert the loop
   * @return the create action loop
   */
  public IActionLoop addLoop(String loopOn, int index);
  
  /**
   * Adds an action loop to the end of this documents list of 
   * children. 
   * @param loopOn the name of the paremater to loop on
   */
  public IActionLoop addLoop(String loopOn);

  /**
   * Adds an if statement to the end of this documents list of 
   * children. 
   * @param condition the if condition
   */
  public IActionIfStatement addIf(String condition);
  
  /**
   * Create a new if statment at the top level of the action sequence
   * @param condition the if condition
   * @param index the index of where to insert the if statment
   * @return the createe if statment
   */
  public IActionIfStatement addIf(String condition, int index);
  
  /**
   * @return all the top level action definitions and control statments in
   * the action sequence.
   */
  public IActionSequenceExecutableStatement[] getExecutableChildren();
  
  /**
   * Adds a new child action definition to the end of this documents
   * list of children.
   * @param componentName the name of the component that processes
   * the action definition
   * @return the newly created action definition
   * @throws IllegalAccessException 
   * @throws InstantiationException 
   */
  public IActionDefinition addAction(Class actionDefinitionClass);
  
  /** 
   * Creates a new action definition which conforms to the specifications of this template.
   * @param parent the parent of the new detail
   * @param index the index where the new element should be created
   * @throws IllegalAccessException 
   * @throws InstantiationException 
   */
  public IActionDefinition addAction(Class actionDefinitionClass, int index);
  
  public void addListener(IActionSequenceDocumentListener listener);
  
  public void removeListener(IActionSequenceDocumentListener listener);
  
  
  public IActionSequenceExecutableStatement[] getPrecedingExecutables(IActionDefinition actionDefinition);
  
  public IActionSequenceExecutableStatement[] getPrecedingExecutables(IActionControlStatement actionControlStatement);
  
  public IActionDefinition[] getPrecedingActionDefinitions(IActionDefinition actionDefinition);
  
  public IActionDefinition[] getPrecedingActionDefinitions(IActionControlStatement controlStatement);
  
  public IActionInputVariable[] getAvailInputVariables(IActionDefinition actionDefinition, String[] types);
  
  public IActionInputVariable[] getAvailInputVariables(IActionDefinition actionDefinition, String type);
  
  public IActionInputVariable[] getAvailInputVariables(IActionControlStatement controlStatement);
  
  public IActionSequenceElement[] getReferencesTo(IActionSequenceInput actionSequenceInput);
  
  public IActionSequenceElement[] getBrokenReferences();
    
  public IActionResource[] getReferencesTo(IActionSequenceResource actionSequenceResource);
  
  public IActionSequenceElement[] getReferencesTo(IActionOutput actionOutput);  
  
  public IActionSequenceValidationError[] validate();
    
  public String toString();
  
  public void moveStatement(IActionSequenceExecutableStatement statementToMove, IActionControlStatement newParentControlStatement);
  
  public void moveStatement(IActionSequenceExecutableStatement statementToMove, IActionControlStatement newParentControlStatement, int index);
}
