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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * A wrapper class for an action definition resource element.
 * @author Angelo Rodriguez
 *
 */
public class ActionSequenceDocument {

    

  public static final String SUSPEND_SCHEDULER_CMND = "suspendScheduler"; //$NON-NLS-1$
  
  
  
  // Document header nodes
  public static final String ACTION_SEQUENCE = "action-sequence"; //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_NAME = "name"; //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_TITLE = "title";  //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_VERSION = "version"; //$NON-NLS-1$
  public static final String ACTION_SEQUENCE_LOGGING_LEVEL = "logging-level"; //$NON-NLS-1$
  public static final String[] LOGGING_LEVELS = new String[] { "TRACE", "DEBUG", "INFO", "WARN", "ERROR", "FATAL" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

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
  public static final String RESPONSE_INPUT_SOURCE = "response"; //$NON-NLS-1$
  public static final String SESSION_OUTPUT_SOURCE = "session"; //$NON-NLS-1$
  public static final String RUNTIME_OUTPUT_SOURCE = "runtime"; //$NON-NLS-1$
  public static final String GLOBAL_OUTPUT_SOURCE = "global"; //$NON-NLS-1$
  
  public static final String[] OUTPUT_DESTINATIONS = new String[]{ RUNTIME_OUTPUT_SOURCE, SESSION_OUTPUT_SOURCE, RESPONSE_INPUT_SOURCE, GLOBAL_OUTPUT_SOURCE };
  
  public static final String DOC_RESOURCES_NAME = "resources"; //$NON-NLS-1$

  // Data Types
  public static final String STRING_TYPE = "string"; //$NON-NLS-1$
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
  public static final String SQL_QUERY_TYPE = "sql-query"; //$NON-NLS-1$
  public static final String MDX_QUERY_TYPE = "mdx-query"; //$NON-NLS-1$
  public static final String HQL_QUERY_TYPE = "hql-query"; //$NON-NLS-1$
  public static final String XQUERY_TYPE = "xquery"; //$NON-NLS-1$
  
  public static final String PROPERTY_MAP_ENTRY = "entry"; //$NON-NLS-1$
  public static final String PROPERTY_MAP_ENTRY_KEY = "key"; //$NON-NLS-1$
  public static final String RESULTSET_DEFAULT_COLUMNS = "columns"; //$NON-NLS-1$
  public static final String RESULTSET_ROW = "row"; //$NON-NLS-1$
  public static final String DEFAULT_STRING_LIST_ITEM = "list-item"; //$NON-NLS-1$
  public static final String DEFAULT_VAL_NAME = "default-value"; //$NON-NLS-1$

  // Actions group nodes
  public static final String ACTIONS_NAME = "actions"; //$NON-NLS-1$
  public static final String LOOP_ON_NAME = "loop-on"; //$NON-NLS-1$
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

  public static final String[] IO_TYPES = new String[] { STRING_TYPE, LONG_TYPE, INTEGER_TYPE, BIGDECIMAL_TYPE, STRING_LIST_TYPE, LIST_TYPE, RESULTSET_TYPE, PROPERTY_MAP_TYPE, PROPERTY_MAP_LIST_TYPE, CONTENT_TYPE, OBJECT_TYPE };
  public static final String DOC_INPUTS_PATH = "/" + ACTION_SEQUENCE + "/" + DOC_INPUTS_NAME; //$NON-NLS-1$ //$NON-NLS-2$
  public static final String DOC_OUTPUTS_PATH = "/" + ACTION_SEQUENCE + "/" + DOC_OUTPUTS_NAME; //$NON-NLS-1$ //$NON-NLS-2$
  public static final String DOC_RESOURCES_PATH = "/" + ACTION_SEQUENCE + "/" + DOC_RESOURCES_NAME; //$NON-NLS-1$ //$NON-NLS-2$
  public static final String DOC_ACTIONS_PATH = "/" + ACTION_SEQUENCE + "/" + ACTIONS_NAME; //$NON-NLS-1$ //$NON-NLS-2$
  
  Document document;
  
  static HashMap listenersMap = new HashMap();
  
  /**
   * @param doc the document wrapped by this object
   */
  public ActionSequenceDocument(Document doc) {
    super();
    document = doc;
    
    // By convention the document should have one actions child
    // element that is not a loop. This code insures that this
    // is the case.
    List actions = document.selectNodes("/" + ACTION_SEQUENCE + "/" + ACTIONS_NAME); //$NON-NLS-1$ //$NON-NLS-2$
    Element masterAction = null;
    if (actions.size() == 1) {
      masterAction = (Element)actions.get(0);
      if ((masterAction.valueOf(LOOP_ON_NAME) != null) && (masterAction.valueOf(LOOP_ON_NAME).trim().length() > 0)) {
        masterAction = null;
      }
    }
    if (masterAction == null) {
      masterAction = document.addElement(ACTIONS_NAME);
      for (Iterator iter = actions.iterator(); iter.hasNext();) {
        masterAction.add(((Element)iter.next()).detach());
      }
    }
  }
  
  /**
   * @param xPath
   * @return the element identified by the specified XPath
   */
  public IActionSequenceElement getElement(String xPath) {
    IActionSequenceElement actionSequenceElement = null;
    Node node = getDocument().selectSingleNode(xPath);
    if (node instanceof Element) {
      Element element = (Element)node;
      if (element.getName().equals(ACTION_DEFINITION_NAME)) {
        actionSequenceElement = ActionDefinition.instance(element);
      } else if (element.getName().equals(ACTIONS_NAME)) {
        if (element.element(CONDITION_NAME) == null) {
          actionSequenceElement = new ActionLoop(element);
        } else {
          actionSequenceElement = new ActionIfStatement(element);
        }
      } else if (element.getParent().getPath().equals(DOC_INPUTS_PATH)) {
        actionSequenceElement = new ActionSequenceInput(element);
      } else if (element.getParent().getPath().equals(DOC_OUTPUTS_PATH)) {
        actionSequenceElement = new ActionSequenceOutput(element);
      } else if (element.getParent().getPath().equals(DOC_RESOURCES_PATH)) {
        actionSequenceElement = new ActionSequenceResource(element);
      } else if (element.getParent().getName().equals(ACTION_INPUTS_NAME)) {
        actionSequenceElement = new ActionInput(element);
      } else if (element.getParent().getName().equals(ACTION_OUTPUTS_NAME)) {
        actionSequenceElement = new ActionOutput(element);
      } else if (element.getParent().getName().equals(ACTION_RESOURCES_NAME)) {
        actionSequenceElement = new ActionResource(element);
      }
    }
    return actionSequenceElement;
  }
  
  /**
   * @return the document wrapped by this object
   */
  public Document getDocument() {
    return document;
  }
  
  /**
   * @return the action sequence description
   */
  public String getDescription() {
    String result = ""; //$NON-NLS-1$
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (actSeq != null) {
      Element subElement = (Element)actSeq.selectSingleNode(ACTION_SEQUENCE_DOCUMENTATION_DESCRIPT);
      if (subElement != null) {
        result = subElement.getText();
      }
    }
    return result;
  }
  
  /**
   * @return the help message 
   */
  public String getHelp() {
    String result = ""; //$NON-NLS-1$
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (actSeq != null) {
      Element subElement = (Element)actSeq.selectSingleNode(ACTION_SEQUENCE_DOCUMENTATION_HELP);
      if (subElement != null) {
        result = subElement.getText();
      }
    }
    return result;
  }
  
  /**
   * @return the action sequence version
   */
  public String getVersion() {
    String result = ""; //$NON-NLS-1$
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (actSeq != null) {
      Element subElement = (Element)actSeq.selectSingleNode(ACTION_SEQUENCE_VERSION);
      if (subElement != null) {
        result = subElement.getText();
      }
    }
    return result;
  }
  
  /**
   * @return the logging level being used when this action sequence is
   * executed.
   */
  public String getLoggingLevel() {
    String result = ""; //$NON-NLS-1$
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (actSeq != null) {
      Element subElement = (Element)actSeq.selectSingleNode(ACTION_SEQUENCE_LOGGING_LEVEL);
      if (subElement != null) {
        result = subElement.getText();
      }
    }
    return result;
  }
  
  /**
   * @return the action sequence author
   */
  public String getAuthor() {
    String result = ""; //$NON-NLS-1$
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (actSeq != null) {
      Element subElement = (Element)actSeq.selectSingleNode(ACTION_SEQUENCE_DOCUMENTATION_AUTHOR);
      if (subElement != null) {
        result = subElement.getText();
      }
    }
    return result;
  }
  
  /**
   * @return the type of results returned by the action sequence
   */
  public String getResultType() {
    String result = null; 
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (actSeq != null) {
      Element subElement = (Element)actSeq.selectSingleNode(ACTION_SEQUENCE_DOCUMENTATION_RESULT_TYPE);
      if (subElement != null) {
        result = subElement.getText();
      }
    }
    return result;
  }
  
  /**
   * @return the location of the icon used by the action sequence.
   */
  public String getIconLocation() {
    String result = ""; //$NON-NLS-1$
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (actSeq != null) {
      Element subElement = (Element)actSeq.selectSingleNode(ACTION_SEQUENCE_DOCUMENTATION_ICON);
      if (subElement != null) {
        result = subElement.getText().trim();
        int index = result.indexOf("|"); //$NON-NLS-1$
        if (index > 0) {
          result = result.substring(0, index);
        }
      }
    }
    return result;
  }
  
  /**
   * @return the location of the flyover icon used by the action sequence.
   */
  public String getFlyoverIconLocation() {
    String result = ""; //$NON-NLS-1$
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (actSeq != null) {
      Element subElement = (Element)actSeq.selectSingleNode(ACTION_SEQUENCE_DOCUMENTATION_ICON);
      if (subElement != null) {
        result = subElement.getText();
        int index = result.indexOf("|"); //$NON-NLS-1$
        if ((index >= 0) && (index < result.length() - 1)) {
          result = result.substring(index + 1);
        } else {
          result = ""; //$NON-NLS-1$
        }
      }
    }
    return result;
  }
  
  /**
   * @return the action sequence title
   */
  public String getTitle() {
    String result = ""; //$NON-NLS-1$
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (actSeq != null) {
      Element subElement = (Element)actSeq.selectSingleNode(ACTION_SEQUENCE_TITLE);
      if (subElement != null) {
        result = subElement.getText();
      }
    }
    return result;
  }
  
  /**
   * Sets the action sequence description.
   * @param value the description
   */
  public void setDescription(String value) {
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    Element subElement = DocumentHelper.makeElement(actSeq, ACTION_SEQUENCE_DOCUMENTATION_DESCRIPT);
    subElement.setText(value == null ? "" : value); //$NON-NLS-1$
    fireHeaderChanged(this);
  }
  
  /**
   * Sets the action sequence help message
   * @param value the help message
   */
  public void setHelp(String value) {
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    Element subElement = DocumentHelper.makeElement(actSeq, ACTION_SEQUENCE_DOCUMENTATION_HELP);
    subElement.setText(value == null ? "" : value); //$NON-NLS-1$
    fireHeaderChanged(this);
  }
  
  /**
   * Sets the action sequence version.
   * @param value the version
   */
  public void setVersion(String value) {
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    Element subElement = DocumentHelper.makeElement(actSeq, ACTION_SEQUENCE_VERSION);
    subElement.setText(value == null ? "" : value); //$NON-NLS-1$
    fireHeaderChanged(this);
  }
  
  /**
   * Sets the action sequence logging level
   * @param value the logging level
   */
  public void setLoggingLevel(String value) {
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    Element subElement = DocumentHelper.makeElement(actSeq, ACTION_SEQUENCE_LOGGING_LEVEL);
    subElement.setText(value == null ? "" : value); //$NON-NLS-1$
    fireHeaderChanged(this);
  }
  
  /**
   * Sets the action sequence author
   * @param value the author name
   */
  public void setAuthor(String value) {
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    Element subElement = DocumentHelper.makeElement(actSeq, ACTION_SEQUENCE_DOCUMENTATION_AUTHOR);
    subElement.setText(value == null ? "" : value); //$NON-NLS-1$
    fireHeaderChanged(this);
  }
  
  /**
   * Sets the action sequence result type
   * @param value the result type
   */
  public void setResultType(String value) {
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    if (value == null) {
      Node subElement = actSeq.selectSingleNode(ACTION_SEQUENCE_DOCUMENTATION_RESULT_TYPE);
      if (subElement != null) {
        subElement.detach();
        fireHeaderChanged(this);
      }
    } else {
      Element subElement = DocumentHelper.makeElement(actSeq, ACTION_SEQUENCE_DOCUMENTATION_RESULT_TYPE);
      subElement.setText(value == null ? "" : value); //$NON-NLS-1$
      fireHeaderChanged(this);
    }
  }
  
  /**
   * Sets the location of the icon for this action sequence
   * @param value the icon path
   */
  public void setIconLocation(String value) {
    String location = ""; //$NON-NLS-1$
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    Element subElement = DocumentHelper.makeElement(actSeq, ACTION_SEQUENCE_DOCUMENTATION_ICON);
    if (value != null) {
      location = value;
    }
    String flyOverIcon = getFlyoverIconLocation();
    if (flyOverIcon.length() > 0) {
      location = location + "|" + flyOverIcon; //$NON-NLS-1$
    }
    subElement.setText(location); 
    fireHeaderChanged(this);
  }
  
  /**
   * Sets the location of the icon for this action sequence
   * @param value the icon path
   */
  public void setFlyoverIconLocation(String value) {
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    Element subElement = DocumentHelper.makeElement(actSeq, ACTION_SEQUENCE_DOCUMENTATION_ICON);
    String location = getIconLocation();
    if ((value != null) && (value.trim().length() > 0)) {
      location = location + "|" + value; //$NON-NLS-1$
    }
    subElement.setText(location); 
    fireHeaderChanged(this);
  }
  
  /**
   * Sets the action sequence title.
   * @param value the title
   */
  public void setTitle(String value) {
    Element actSeq = (Element)document.selectSingleNode(ACTION_SEQUENCE);
    Element subElement = DocumentHelper.makeElement(actSeq, ACTION_SEQUENCE_TITLE);
    subElement.setText(value == null ? "" : value); //$NON-NLS-1$
    fireHeaderChanged(this);
  }
  
  
  /**
   * Removes the named action sequence input from the action sequence
   * @param inputName the input name
   */
  public void deleteInput(String inputName) {
    ActionSequenceInput input = getInput(inputName);
    if (input != null) {
      input.getElement().detach();
      fireIoRemoved(this, input);
    }
  }
  
  /**
   * @return the action sequence inputs of the specified type
   */
  public ActionSequenceInput[] getInputs(String[] types) {
    ActionSequenceInput[] allInputs = getInputs();
    List matchingInputs = new ArrayList();
    if (types == null) {
      matchingInputs.addAll(Arrays.asList(allInputs));
    } else {
      ArrayList typesList = new ArrayList(Arrays.asList(types));
      for (int i = 0; i < allInputs.length; i++) {
        if (typesList.contains(allInputs[i].getType())) {
          matchingInputs.add(allInputs[i]);
        }
     }
    }
    return (ActionSequenceInput[])matchingInputs.toArray(new ActionSequenceInput[0]);
  }
  
  /**
   * @return all the inputs to the action sequence
   */
  public ActionSequenceInput[] getInputs() {
    List inputsList =  document.getRootElement().selectNodes(DOC_INPUTS_NAME + "/*"); //$NON-NLS-1$
    ActionSequenceInput[] inputs = new ActionSequenceInput[inputsList.size()];
    int index = 0;
    for (Iterator iter = inputsList.iterator(); iter.hasNext();) {
      inputs[index++] = new ActionSequenceInput((Element)iter.next());
    }
    return inputs;
  }
  
  /**
   * @param inputName the input name.
   * @return the input with the given name or null if it does not exist
   */
  public ActionSequenceInput getInput(String inputName) {
    Element element = (Element)document.getRootElement().selectSingleNode(DOC_INPUTS_NAME + "/" + inputName); //$NON-NLS-1$
    return element != null ? new ActionSequenceInput(element) : null;
  }
  
  /**
   * Adds a new input this action sequence. If the input already 
   * exists the type of the input is set to the specified type.
   * 
   * @param inputName the input name
   * @param inputType the input type
   * @return the action sequence input
   */
  public ActionSequenceInput createInput(String inputName, String inputType) {
    ActionSequenceInput input = getInput(inputName);
    if (input == null) {  
      Element inputElement = DocumentHelper.makeElement(document.getRootElement(), DOC_INPUTS_NAME + "/" + inputName); //$NON-NLS-1$
      inputElement.addAttribute(AbstractParam.TYPE_NAME, inputType);
      input = new ActionSequenceInput(inputElement);
      fireIoAdded(input);
      if (inputType.equals(ActionSequenceDocument.STRING_TYPE)) {
        input.addSource(REQUEST_INPUT_SOURCE, inputName);
      }
    } else {
      input.setType(inputType);
    }
    return input;
  }
  
  /**
   * Removes the output of the given name from the action sequence.
   * @param outputName the output name.
   */
  public void deleteOutput(String outputName) {
    ActionSequenceOutput output = getOutput(outputName);
    if (output != null) {
      output.getElement().detach();
      fireIoRemoved(this, output);
    }
  }
  
  /**
   * @return an array of all the outputs from this action sequence.
   */
  public ActionSequenceOutput[] getOutputs() {
    List outputsList =  document.getRootElement().selectNodes(DOC_OUTPUTS_NAME + "/*"); //$NON-NLS-1$
    ActionSequenceOutput[] outputs = new ActionSequenceOutput[outputsList.size()];
    int index = 0;
    for (Iterator iter = outputsList.iterator(); iter.hasNext();) {
      outputs[index++] = new ActionSequenceOutput((Element)iter.next());
    }
    return outputs;
  }
  
  /**
   * @param outputName
   * @return the action sequence output with the given name or null if 
   * it does not exist.
   */
  public ActionSequenceOutput getOutput(String outputName) {
    Element element = (Element)document.getRootElement().selectSingleNode(DOC_OUTPUTS_NAME + "/" + outputName); //$NON-NLS-1$
    return element != null ? new ActionSequenceOutput(element) : null;
  }
  
  /**
   * Adds a new output to this action sequence. If the output already 
   * exists the type of the output is set to the specified type.
   * 
   * @param outputName the input name
   * @param outputType the input type
   * @return the action sequence output
   */
  public ActionSequenceOutput createOutput(String outputName, String outputType) {
    ActionSequenceOutput output = getOutput(outputName);
    if (output == null) {  
      Element outputElement = DocumentHelper.makeElement(document.getRootElement(), DOC_OUTPUTS_NAME + "/" + outputName); //$NON-NLS-1$
      outputElement.addAttribute(AbstractParam.TYPE_NAME, outputType);
      output = new ActionSequenceOutput(outputElement);
      fireIoAdded(output);
    } else {
      output.setType(outputType);
    }
    return output;
  }
  
  
  /**
   * Removes the named action sequence resource from the action sequence
   * @param resourceName the resource name
   */
  public void deleteResource(String resourceName) {
    ActionSequenceResource resource = getResource(resourceName);
    if (resource != null) {
      resource.getElement().detach();
      fireResourceRemoved(this, resource);
    }
  }
  
  /**
   * @return all the action sequence resources
   */
  public ActionSequenceResource[] getResources() {
    List resourcesList =  document.getRootElement().selectNodes(DOC_RESOURCES_NAME + "/*"); //$NON-NLS-1$
    ActionSequenceResource[] resources = new ActionSequenceResource[resourcesList.size()];
    int index = 0;
    for (Iterator iter = resourcesList.iterator(); iter.hasNext();) {
      resources[index++] = new ActionSequenceResource((Element)iter.next());
    }
    return resources;
  }
  
  /**
   * @param resourceName the resource name.
   * @return the resource with the given name or null if it does not exist
   */
  public ActionSequenceResource getResource(String resourceName) {
    Element element = (Element)document.getRootElement().selectSingleNode(DOC_RESOURCES_NAME + "/" + resourceName); //$NON-NLS-1$
    return element != null ? new ActionSequenceResource(element) : null;
  }
  
  /**
   * Adds a new resource to this action sequence. 
   * @param resourceName the resource name
   * @param resourceFileType the resource file type
   * @param filePath the resource file path
   * @param mimeType the resource mime type
   * @return the action sequence resource
   */
  public ActionSequenceResource createResource(String resourceName, String resourceFileType, String filePath, String mimeType) {
    ActionSequenceResource docResource = getResource(resourceName);
    if (docResource == null) {
      Element resourceElement = DocumentHelper.makeElement(document.getRootElement(), DOC_RESOURCES_NAME + "/" + resourceName); //$NON-NLS-1$
      Element fileTypeElement = resourceElement.addElement(resourceFileType);
      Element pathElement = fileTypeElement.addElement(ActionSequenceResource.RES_LOCATION_NAME);
      Element mimeElement = fileTypeElement.addElement(ActionSequenceResource.RES_MIME_TYPE_NAME);
      if (filePath != null) {
        pathElement.setText(filePath);
      }
      if (mimeType != null) {
        mimeElement.setText(mimeType);
      }
      docResource = new ActionSequenceResource(resourceElement);
      fireResourceAdded(docResource);
    }
    return docResource;
  }
  
  private ActionLoop getRootLoop() {
    return new ActionLoop((Element)document.selectSingleNode("/" + ACTION_SEQUENCE + "/" + ACTIONS_NAME)); //$NON-NLS-1$ //$NON-NLS-2$ 
  }
  
  /**
   * Create a new loop at the top level of the action sequence
   * @param loopOn the name of the parameter to loop on
   * @param index the index of where to insert the loop
   * @return the create action loop
   */
  public ActionLoop addLoop(String loopOn, int index) {
    return getRootLoop().addLoop(loopOn, index);
  }
  
  /**
   * Adds an action definition to the end of this documents list of 
   * children. This document becomes the new parent of the specified action definition.
   * @param actionDef the action definition to be added.
   */
  public void add(ActionDefinition actionDef) {
    getRootLoop().add(actionDef);
  }
  
  /**
   * Adds an action definition to this documents list of 
   * children. This document becomes the new parent of the specified action definition.
   * @param actionDef the action definition to be added.
   * @param index the index of where to add the new action definition. If index
   * is greater than the number of children then the new action definition is added
   * at the end of the list of children.
   */
  public void add(ActionDefinition actionDef, int index) {
    getRootLoop().add(actionDef, index);
  }
  
  /**
   * Adds an action control statement to the end of this documents list of 
   * children. This document becomes the new parent of the control statement.
   * @param controlStatement the control statement to be added.
   */
  public void add(ActionControlStatement controlStatement) {
    getRootLoop().add(controlStatement);
  }
  
  /**
   * Adds an action control statement to this documents list of 
   * children. This document becomes the new parent of the specified control statement.
   * @param controlStatement the control statement to be added.
   * @param index the index of where to add the new control statement. If index
   * is greater than the number of children then the new control statement is added
   * at the end of the list of children.
   */
  public void add(ActionControlStatement controlStatement, int index) {
    getRootLoop().add(controlStatement, index);
  }
  
  /**
   * Adds an action loop to the end of this documents list of 
   * children. 
   * @param loopOn the name of the paremater to loop on
   */
  public ActionLoop addLoop(String loopOn) {
    return getRootLoop().addLoop(loopOn);
  }
  
  /**
   * Adds an if statement to the end of this documents list of 
   * children. 
   * @param condition the if condition
   */
  public ActionIfStatement addIf(String condition) {
    return getRootLoop().addIf(condition);
  }
  
  /**
   * Create a new if statment at the top level of the action sequence
   * @param condition the if condition
   * @param index the index of where to insert the if statment
   * @return the createe if statment
   */
  public ActionIfStatement addIf(String condition, int index) {
    return getRootLoop().addIf(condition, index);
  }
  
  /**
   * @return all the top level action definitions and control statments in
   * the action sequence.
   */
  public IActionSequenceExecutableStatement[] getExecutableChildren() {
    return getRootLoop().getChildren();
  }
  
  /**
   * Adds a new child action definition to the end of this documents
   * list of children.
   * @param componentName the name of the component that processes
   * the action definition
   * @return the newly created action definition
   */
  public ActionDefinition addAction(int actionId) {
    return getRootLoop().addAction(actionId);
  }
  
  /** 
   * Creates a new action definition which conforms to the specifications of this template.
   * @param parent the parent of the new detail
   * @param index the index where the new element should be created
   */
  public ActionDefinition addAction(int actionId, int index) {
    return getRootLoop().addAction(actionId, index);
  }
  
  public void addListener(IActionSequenceDocumentListener listener) {
    ArrayList listenerList = (ArrayList)listenersMap.get(document);
    if (listenerList == null) {
      listenerList = new ArrayList();
      listenersMap.put(document, listenerList);
    }
    listenerList.add(listener);
  }
  
  public void removeListener(IActionSequenceDocumentListener listener) {
    ArrayList listenerList = (ArrayList)listenersMap.get(document);
    if (listenerList != null) {
      listenerList.remove(listener);
    }
  }
  
  protected static void fireIoAdded(final AbstractParam io) {
    ArrayList listenerList = (ArrayList)listenersMap.get(io.ioElement.getDocument());
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.ioAdded(io);
      }
    }
  }
  
  protected static void fireIoRemoved(final Object parent, final AbstractParam io) {
    Document doc = null;
    if (parent instanceof ActionDefinition) {
      doc = ((ActionDefinition)parent).actionDefElement.getDocument();
    } else if (parent instanceof ActionSequenceDocument) {
      doc = ((ActionSequenceDocument)parent).document;
    }
    if (doc != null) {
      ArrayList listenerList = (ArrayList)listenersMap.get(doc);
      if (listenerList != null) {
        Object[] listeners = listenerList.toArray();
        for (int i = 0; i < listeners.length; ++i) {
          final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
          l.ioRemoved(parent, io);
        }
      }
    }
  }
  
  protected static void fireIoRenamed(final AbstractParam io) {
    ArrayList listenerList = (ArrayList)listenersMap.get(io.ioElement.getDocument());
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.ioRenamed(io);
      }
    }
  }
  
  public static void fireIoChanged(final AbstractParam io) {
    ArrayList listenerList = (ArrayList)listenersMap.get(io.ioElement.getDocument());
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.ioChanged(io);
      }
    }
  }
  
  protected static void fireResourceAdded(final Object resource) {
    Document doc = null;
    if (resource instanceof ActionResource) {
      doc = ((ActionResource)resource).ioElement.getDocument();
    } else if (resource instanceof ActionSequenceResource) {
      doc = ((ActionSequenceResource)resource).ioElement.getDocument();
    }
    ArrayList listenerList = (ArrayList)listenersMap.get(doc);
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.resourceAdded(resource);
      }
    }
  }
  
  protected static void fireResourceRemoved(final Object parent, final Object resource) {
    Document doc = null;
    if (parent instanceof ActionDefinition) {
      doc = ((ActionDefinition)parent).actionDefElement.getDocument();
    } else if (parent instanceof ActionSequenceDocument) {
      doc = ((ActionSequenceDocument)parent).document;
    }
    ArrayList listenerList = (ArrayList)listenersMap.get(doc);
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.resourceRemoved(parent, resource);
      }
    }
  }
  
  protected static void fireResourceRenamed(final Object resource) {
    Document doc = null;
    if (resource instanceof ActionResource) {
      doc = ((ActionResource)resource).ioElement.getDocument();
    } else if (resource instanceof ActionSequenceResource) {
      doc = ((ActionSequenceResource)resource).ioElement.getDocument();
    }
    ArrayList listenerList = (ArrayList)listenersMap.get(doc);
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.resourceRenamed(resource);
      }
    }
  }
  
  public static void fireResourceChanged(final Object resource) {
    Document doc = null;
    if (resource instanceof ActionResource) {
      doc = ((ActionResource)resource).ioElement.getDocument();
    } else if (resource instanceof ActionSequenceResource) {
      doc = ((ActionSequenceResource)resource).ioElement.getDocument();
    }
    ArrayList listenerList = (ArrayList)listenersMap.get(doc);
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.resourceChanged(resource);
      }
    }
  }
  
  protected static void fireActionAdded(final ActionDefinition action) {
    ArrayList listenerList = (ArrayList)listenersMap.get(action.actionDefElement.getDocument());
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.actionAdded(action);
      }
    }
  }
  
  protected static void fireActionRemoved(final Object parent, final ActionDefinition action) {
    Document doc = null;
    if (parent instanceof ActionLoop) {
      doc = ((ActionLoop)parent).controlElement.getDocument();
    } else if (parent instanceof ActionSequenceDocument) {
      doc = ((ActionSequenceDocument)parent).document;
    }
    if (doc != null) {
      ArrayList listenerList = (ArrayList)listenersMap.get(doc);
      if (listenerList != null) {
        Object[] listeners = listenerList.toArray();
        for (int i = 0; i < listeners.length; ++i) {
          final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
          l.actionRemoved(parent, action);
        }
      }
    }
  }
  
  protected static void fireActionRenamed(final ActionDefinition action) {
    ArrayList listenerList = (ArrayList)listenersMap.get(action.actionDefElement.getDocument());
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.actionRenamed(action);
      }
    }
  }
  
  public static void fireHeaderChanged(final ActionSequenceDocument actionSequenceDocument) {
    ArrayList listenerList = (ArrayList)listenersMap.get(actionSequenceDocument.getDocument());
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.headerChanged(actionSequenceDocument);
      }
    }
  }
  
  public static void fireActionChanged(final ActionDefinition action) {
    ArrayList listenerList = (ArrayList)listenersMap.get(action.actionDefElement.getDocument());
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.actionChanged(action);
      }
    }
  }
  
  protected static void fireControlStatementAdded(final ActionControlStatement controlStatement) {
    ArrayList listenerList = (ArrayList)listenersMap.get(controlStatement.controlElement.getDocument());
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.controlStatementAdded(controlStatement);
      }
    }
  }
  
  protected static void fireControlStatementRemoved(final Object parent, final ActionControlStatement controlStatement) {
    Document doc = null;
    if (parent instanceof ActionControlStatement) {
      doc = ((ActionLoop)parent).controlElement.getDocument();
    } else if (parent instanceof ActionSequenceDocument) {
      doc = ((ActionSequenceDocument)parent).document;
    }
    if (doc != null) {
      ArrayList listenerList = (ArrayList)listenersMap.get(doc);
      if (listenerList != null) {
        Object[] listeners = listenerList.toArray();
        for (int i = 0; i < listeners.length; ++i) {
          final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
          l.controlStatementRemoved(parent, controlStatement);
        }
      }
    }
  }
  
  protected static void fireControlStatementChanged(final ActionControlStatement controlStatement) {
    ArrayList listenerList = (ArrayList)listenersMap.get(controlStatement.controlElement.getDocument());
    if (listenerList != null) {
      Object[] listeners = listenerList.toArray();
      for (int i = 0; i < listeners.length; ++i) {
        final IActionSequenceDocumentListener l = (IActionSequenceDocumentListener) listeners[i];
        l.controlStatementChanged(controlStatement);
      }
    }
  }
  
  public boolean equals(Object arg0) {
    boolean result = false;
    if (arg0 != null) {
      if (arg0.getClass() == this.getClass()) {
        ActionSequenceDocument doc = (ActionSequenceDocument)arg0;
        result = (doc.document != null ? doc.document.equals(this.document) : (doc == this));
      }
    }
    return result;
  }
  
  private List getAncestorExecutables(IActionSequenceElement actionDefOrControlStatement, boolean includeAncestorConrolStatements) {
    List prevDefs = new ArrayList();
    ActionControlStatement parentControlStatement = null;
    if (actionDefOrControlStatement instanceof ActionDefinition) {
      parentControlStatement = ((ActionDefinition)actionDefOrControlStatement).getParent();
    } else {
      parentControlStatement = ((ActionControlStatement)actionDefOrControlStatement).getParent();
    }
    if (parentControlStatement != null) {
      prevDefs.addAll(getAncestorExecutables(parentControlStatement, includeAncestorConrolStatements));
      IActionSequenceElement[] siblings = parentControlStatement.getChildren();
      for (int i = 0; i < siblings.length; i++) {
        if (siblings[i].equals(actionDefOrControlStatement)) {
          break;
        } else if (includeAncestorConrolStatements || (siblings[i] instanceof ActionDefinition)) {
          prevDefs.add(siblings[i]);
        }
      }
    } else {
      IActionSequenceElement[] siblings = actionDefOrControlStatement.getDocument().getExecutableChildren();
      for (int i = 0; i < siblings.length; i++) {
        if (siblings[i].equals(actionDefOrControlStatement)) {
          break;
        } else if (includeAncestorConrolStatements || (siblings[i] instanceof ActionDefinition)) {
          prevDefs.add(siblings[i]);
        }
      }
    }
    return prevDefs;
  }
  
  public IActionSequenceExecutableStatement[] getPrecedingExecutables(ActionDefinition actionDefinition) {
    return (IActionSequenceExecutableStatement[])getAncestorExecutables(actionDefinition, true).toArray(new IActionSequenceExecutableStatement[0]);
  }
  
  public IActionSequenceExecutableStatement[] getPrecedingExecutables(ActionControlStatement actionControlStatement) {
    return (IActionSequenceExecutableStatement[])getAncestorExecutables(actionControlStatement, true).toArray(new IActionSequenceExecutableStatement[0]);
  }
  
  public ActionDefinition[] getPrecedingActionDefinitions(ActionDefinition actionDefinition)
  {
    return (ActionDefinition[])getAncestorExecutables(actionDefinition, false).toArray(new ActionDefinition[0]);
  }
  
  public ActionDefinition[] getPrecedingActionDefinitions(ActionControlStatement controlStatement)
  {
    return (ActionDefinition[])getAncestorExecutables(controlStatement, false).toArray(new ActionDefinition[0]);
  }
  
  public IActionVariable[] getAvailInputVariables(ActionDefinition actionDefinition, String[] types) {
    List availParams = new ArrayList();
    availParams.addAll(Arrays.asList(getInputs(types)));
    ActionDefinition[] precedingActionDefs = getPrecedingActionDefinitions(actionDefinition);
    for (int i = 0; i < precedingActionDefs.length; i++) {
      availParams.addAll(Arrays.asList(precedingActionDefs[i].getOutputParams(types)));
    }
    return (IActionVariable[])availParams.toArray(new IActionVariable[0]);
  }
  
  public IActionVariable[] getAvailInputVariables(ActionControlStatement controlStatement) {
    List availParams = new ArrayList();
    if (controlStatement instanceof ActionLoop) {
      String[] types = new String[] {ActionSequenceDocument.PROPERTY_MAP_LIST_TYPE, ActionSequenceDocument.STRING_LIST_TYPE, ActionSequenceDocument.RESULTSET_TYPE};
      availParams.addAll(Arrays.asList(getInputs(types)));
      ActionDefinition[] precedingActionDefs = getPrecedingActionDefinitions(controlStatement);
      for (int i = 0; i < precedingActionDefs.length; i++) {
        availParams.addAll(Arrays.asList(precedingActionDefs[i].getOutputParams(types)));
      }
    } else {
      ActionSequenceInput[] actionSequenceInputs = getInputs();
      for (int i = 0; i < actionSequenceInputs.length; i++) {
        if (actionSequenceInputs[i].getName().indexOf('-') == -1) {
          availParams.add(actionSequenceInputs[i]);
        }
      }
      ActionDefinition[] precedingActionDefs = getPrecedingActionDefinitions(controlStatement);
      for (int i = 0; i < precedingActionDefs.length; i++) {
        ActionOutput[] actionOutputs = precedingActionDefs[i].getAllOutputParams();
        for (int j = 0; j < actionOutputs.length; j++) {
          if (actionOutputs[j].getPublicName().indexOf('-') == -1) {
            availParams.add(actionOutputs[j]);
          }
        }
      }
    }
    return (IActionVariable[])availParams.toArray(new IActionVariable[0]);
  }
  
  public ActionDefinition createAction(int actionId, int index) {
    return createAction(actionId, null, index);
  }
  
  public ActionDefinition createAction(int actionId, ActionControlStatement parentControlStatement, int index) {
    ActionDefinition actionDefinition = null;
    if (parentControlStatement != null) {
      actionDefinition = parentControlStatement.addAction(actionId, index);
    } else {
      actionDefinition = addAction(actionId, index);
    }
    return actionDefinition;
  }
  
  public IActionSequenceElement[] getReferencesTo(ActionSequenceInput actionSequenceInput) {
    ArrayList references = new ArrayList();
    if (this.equals(actionSequenceInput.getDocument())) {
      String name = actionSequenceInput.getName();
      String xpath1 = "//" + ActionSequenceDocument.ACTION_DEFINITION_NAME + "/" + ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + name + "[not(@" + ActionSequenceDocument.MAPPING_NAME + ")]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
      String xpath2 = "//" + ActionSequenceDocument.ACTION_DEFINITION_NAME + "/" + ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + "*[@" + ActionSequenceDocument.MAPPING_NAME + "='" + name + "']"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
      String xpath3 = "//" + ActionSequenceDocument.ACTIONS_NAME + "[@" + ActionSequenceDocument.LOOP_ON_NAME + "='" + name + "']"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$  
      List referencingElements = document.selectNodes(xpath1 + "|" + xpath2 + "|" + xpath3); //$NON-NLS-1$ //$NON-NLS-2$
      for (Iterator iter = referencingElements.iterator(); iter.hasNext();) {
        Element element = (Element)iter.next();
        if (element.getName().equals(ActionSequenceDocument.ACTIONS_NAME)) {
          references.add(new ActionLoop(element));
        } else {
          references.add(new ActionInput(element));
        }
      }
    }
    return (IActionSequenceElement[])references.toArray(new IActionSequenceElement[0]);
  }
  
  public IActionSequenceElement[] getBrokenReferences() {
    String xPath1 = "//" + ActionSequenceDocument.ACTION_DEFINITION_NAME + "/" + ActionSequenceDocument.ACTION_INPUTS_NAME + "/*"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    String xPath2 = "//" + ActionSequenceDocument.ACTIONS_NAME + "[@" + ActionSequenceDocument.LOOP_ON_NAME + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    String xPath3 = "//" + ActionSequenceDocument.ACTION_DEFINITION_NAME + "/" + ActionSequenceDocument.ACTION_RESOURCES_NAME + "/*"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    List allActionInputElements = document.selectNodes(xPath1 + "|" + xPath2 + "|" + xPath3); //$NON-NLS-1$ //$NON-NLS-2$ 
    ArrayList brokenReferences = new ArrayList();
    for (Iterator iterator = allActionInputElements.iterator(); iterator.hasNext();) {
      Element element = (Element)iterator.next();
      if (element.getName().equals(ActionSequenceDocument.ACTIONS_NAME)) {
        brokenReferences.add(new ActionLoop(element));
      } else if (element.getParent().getName().equals(ActionSequenceDocument.ACTION_INPUTS_NAME)) {
        brokenReferences.add(new ActionInput(element));
      } else if (element.getParent().getName().equals(ActionSequenceDocument.ACTION_RESOURCES_NAME)) {
        brokenReferences.add(new ActionResource(element));
      }
    }
    
    ActionSequenceInput[] actionSequenceInputs = getInputs();
    for (int i = 0; i < actionSequenceInputs.length; i++) {
      brokenReferences.removeAll(Arrays.asList(getReferencesTo(actionSequenceInputs[i])));
    }
    
    List allActionOutputElements = document.selectNodes("//" + ActionSequenceDocument.ACTION_DEFINITION_NAME + "/" + ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/*"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    ArrayList allActionOutputs = new ArrayList();
    for (Iterator iterator = allActionOutputElements.iterator(); iterator.hasNext();) {
      allActionOutputs.add(new ActionOutput((Element)iterator.next()));
    }
    
    for (Iterator iterator = allActionOutputs.iterator(); iterator.hasNext();) {
      brokenReferences.removeAll(Arrays.asList(getReferencesTo((ActionOutput)iterator.next())));
    }
    
    ActionSequenceResource[] actionSequenceResources = getResources();
    for (int i = 0; i < actionSequenceResources.length; i++) {
      brokenReferences.removeAll(Arrays.asList(getReferencesTo(actionSequenceResources[i])));
    }
    
    return (IActionSequenceElement[])brokenReferences.toArray(new IActionSequenceElement[0]);
  }
  
  public ActionResource[] getReferencesTo(ActionSequenceResource actionSequenceResource) {
    ArrayList actionInputs = new ArrayList();
    if (this.equals(actionSequenceResource.getDocument())) {
      String name = actionSequenceResource.getName();
      String xpath1 = "//" + ActionSequenceDocument.ACTION_DEFINITION_NAME + "/" + ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + name + "[not(@" + ActionSequenceDocument.MAPPING_NAME + ")]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
      String xpath2 = "//" + ActionSequenceDocument.ACTION_DEFINITION_NAME + "/" + ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + "*[@" + ActionSequenceDocument.MAPPING_NAME + "='" + name + "']"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
      List references = document.selectNodes(xpath1 + "|" + xpath2); //$NON-NLS-1$
      for (Iterator iter = references.iterator(); iter.hasNext();) {
        actionInputs.add(new ActionResource((Element)iter.next()));
      }
    }
    return (ActionResource[])actionInputs.toArray(new ActionResource[0]);
  }
  
  public IActionSequenceElement[] getReferencesTo(ActionOutput actionOutput) {
    ArrayList references = new ArrayList();
    if (this.equals(actionOutput.getDocument())) {
      ArrayList excludedActionDefs = new ArrayList();
      excludedActionDefs.add(actionOutput.getActionDefinition());
      excludedActionDefs.addAll(Arrays.asList(getPrecedingExecutables(actionOutput.getActionDefinition())));
      String name = actionOutput.getPublicName();
      String xpath1 = "//" + ActionSequenceDocument.ACTION_DEFINITION_NAME + "/" + ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + name + "[not(@" + ActionSequenceDocument.MAPPING_NAME + ")]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
      String xpath2 = "//" + ActionSequenceDocument.ACTION_DEFINITION_NAME + "/" + ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + "*[@" + ActionSequenceDocument.MAPPING_NAME + "='" + name + "']"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
      String xpath3 = "//" + ActionSequenceDocument.ACTIONS_NAME + "[@" + ActionSequenceDocument.LOOP_ON_NAME + "='" + name + "']"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$  
      List referencingElements = document.selectNodes(xpath1 + "|" + xpath2 + "|" + xpath3); //$NON-NLS-1$ //$NON-NLS-2$
      for (Iterator iter = referencingElements.iterator(); iter.hasNext();) {
        Element element = (Element)iter.next();
        IActionSequenceExecutableStatement executableStatement = null;
        if (element.getName().equals(ActionSequenceDocument.ACTIONS_NAME)) {
          executableStatement = new ActionLoop(element);
          if (!excludedActionDefs.contains(executableStatement)) {
            references.add(executableStatement);
          }
        } else {
          ActionInput actionInput = new ActionInput(element);
          executableStatement = actionInput.getActionDefinition();
          if (!excludedActionDefs.contains(executableStatement)) {
            references.add(actionInput);
          }
        }
      }
    }
    return (IActionSequenceElement[])references.toArray(new IActionSequenceElement[0]);
  }
}
