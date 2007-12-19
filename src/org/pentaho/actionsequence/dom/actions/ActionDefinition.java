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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.pentaho.actionsequence.dom.ActionControlStatement;
import org.pentaho.actionsequence.dom.ActionIfStatement;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionLoop;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionResource;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceInput;
import org.pentaho.actionsequence.dom.ActionSequenceResource;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputVariable;
import org.pentaho.actionsequence.dom.IActionSequenceExecutableStatement;
import org.pentaho.actionsequence.dom.ImplicitActionResource;
import org.pentaho.actionsequence.dom.messages.Messages;

/**
 * A wrapper for an action definition element within an action sequence.
 * @author Angelo Rodriguez
 */
public class ActionDefinition implements IActionSequenceExecutableStatement {
  
  public class ActionInputVariable implements IActionInputVariable {

    public String name;
    public String type;
    
    public ActionInputVariable() {
    }
    
    public ActionInputVariable(String name, String type) {
      this.name = name;
      this.type = type;
    }
    
    public String getType() {
      return type;
    }

    public String getVariableName() {
      return name;
    }

    public void setType(String type) {
      this.type = type;
    }

    public void setVariableName(String name) {
      this.name = name;
    }

    public Object getValue() {
      throw new UnsupportedOperationException();
    }

    public Boolean getBooleanValue() {
      throw new UnsupportedOperationException();
    }

    public Integer getIntValue() {
      throw new UnsupportedOperationException();
    }

    public String getStringValue() {
      throw new UnsupportedOperationException();
    }

    public String getStringValue(boolean replaceParamReferences) {
      throw new UnsupportedOperationException();
    }

    public ActionInput getInputParam() {
      throw new UnsupportedOperationException();
    }

    public boolean getBooleanValue(boolean defaultValue) {
      throw new UnsupportedOperationException();
    }

    public int getIntValue(int defaultValue) {
      throw new UnsupportedOperationException();
    }

    public String getStringValue(boolean replaceParamReferences, String defaultValue) {
      throw new UnsupportedOperationException();
    }

    public String getStringValue(String defaultValue) {
      throw new UnsupportedOperationException();
    }

  }
  
  private static final ActionSequenceValidationError[] EMPTY_ARRAY = new ActionSequenceValidationError[0];
  
  
  
  Element actionDefElement;
  IActionParameterMgr actionParameterMgr;
  private static final String[] EXPECTED_INPUTS = new String[0];
  private static final String[] EXPECTED_OUTPUTS = new String[0];
  private static final String[] EXPECTED_RESOURCES= new String[0];
  
  /**
   * @param actionDefElement the wrapped action definition element
   */
  protected ActionDefinition() {
    this(Messages.getString("ActionDefinition.ENTER_CLASS_NAME")); //$NON-NLS-1$
    setDescription(Messages.getString("ActionDefinition.CUSTOM_ACTION_TITLE")); //$NON-NLS-1$
  }
  
  protected ActionDefinition(Element actionDefElement, IActionParameterMgr actionParameterMgr) {
    super();
    this.actionDefElement = actionDefElement;
    this.actionParameterMgr = actionParameterMgr;
  }
  
  protected ActionDefinition(String componentName) {
    int i = componentName.lastIndexOf("."); //$NON-NLS-1$
    if ((i >= 0) && (i < (componentName.length() - 1))) {
      componentName = componentName.substring(i + 1);
    }
    actionDefElement = new DefaultElement(ActionSequenceDocument.ACTION_DEFINITION_NAME);
    actionDefElement.addElement(ActionSequenceDocument.COMPONENT_NAME).setText(componentName);
    actionDefElement.addElement(ActionSequenceDocument.COMPONENT_DEF_NAME);
    initNewActionDefinition();
  }
  
  /**
   * Sets the URI and mime type of the specified resource. If the resource does not exist it is created. If the URI is null the named resource is deleted.
   * 
   * @param privateParamName the name of the resource as it is known by the component processing this action definition.
   * @param URI the resource URI. May be null if you wish to delete the resource.
   * @param mimeType the resource mime type. Ignored if the resource URI is null.
   * @return the modified/created resource, or null if the resource is deleted.
   */
  public ActionResource setResourceUri(String privateResourceName, URI uri, String mimeType) {
    ActionResource actionResource = getResourceParam(privateResourceName);
    if (uri == null) {
      if (actionResource != null) {
        actionResource.setURI(null);
      }
      actionResource = null;
    } else {
      if (actionResource == null) {
        actionResource = addResourceParam(privateResourceName);
      }
      actionResource.setURI(uri);
      actionResource.setMimeType(mimeType);
    }
    return actionResource;
  }
  
  protected void initNewActionDefinition(){
    
  };

 
  /**
   * Compares the component name of the given action definition element with the given component name. It is the
   * short component names that are compared. The short name is the substring following the last "." in the
   * name provided. If there is no "." in the name the short name is the provided name.
   * 
   * @param actionDefinitionElement the action definition element whose component name is to be compared.
   * @param componentName the component name to compare to.
   * @return true if the short component names are equal or false otherwise.
   */
  protected static boolean hasComponentName(Element actionDefinitionElement, String componentName) {
    String elementComponentName = getComponentName(actionDefinitionElement);
    int index = elementComponentName.lastIndexOf("."); //$NON-NLS-1$
    if ((index >= 0) && (index < elementComponentName.length() - 1)) {
      elementComponentName = elementComponentName.substring(index + 1);
    }
    
    index = componentName.lastIndexOf("."); //$NON-NLS-1$
    if ((index >= 0) && (index < componentName.length() - 1)) {
      componentName = componentName.substring(index + 1);
    }
    
    return componentName.equals(elementComponentName);
  }
  
  /**
   * Determines whether this action definition is capable of processing the provided action definition element.
   * 
   * @param actionDefinitionElement the action definition element.
   * @return true if this action definition can process the element, or false otherwise.
   */
  public static boolean accepts(Element actionDefinitionElement) {
    return actionDefinitionElement.getName().equals(ActionSequenceDocument.ACTION_DEFINITION_NAME);
  }
  
  /**
   * Returns the component name of the provided action definition element.
   * 
   * @param actionDefinitionElement the action definition element.
   * @return the element's component name.
   */
  protected static String getComponentName(Element actionDefinitionElement) {
    Element componentClassElement = actionDefinitionElement.element(ActionSequenceDocument.COMPONENT_NAME);
    return componentClassElement == null ? null : componentClassElement.getText();
  }
  
  /**
   * Returns the component name of this action definition.
   * 
   * @return the action definition's component name.
   */
  public String getComponentName() {
    return getComponentName(actionDefElement);
  }
  
  /**
   * Sets the component name of this action definition.
   * 
   * @param name the component name.
   */
  public void setComponentName(String name) {
    Element componentClassName = actionDefElement.element(ActionSequenceDocument.COMPONENT_NAME);
    if (componentClassName == null) {
      componentClassName = actionDefElement.addElement(ActionSequenceDocument.COMPONENT_NAME);
      componentClassName.setText(name);
      ActionSequenceDocument.fireActionRenamed(this);
    } else if (!componentClassName.getText().equals(name)) {
      componentClassName.setText(name);
      ActionSequenceDocument.fireActionRenamed(this);
    }
  }
  
  /**
   * Adds a new input parameter to this action definition. If the input already 
   * exists the type of the input is set to the specified type.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
   * @param inputType the input type
   * @return the action input
   */
  public ActionInput addInputParam(String privateParamName, String inputType) {
    ActionInput input = getInputParam(privateParamName);
    Element inputElement;
    if (input == null) {
      Element[] componentDefs = getComponentDefElements(privateParamName);
      for (int i = 0; i < componentDefs.length; i++) {
        componentDefs[i].detach();
      }
      inputElement = DocumentHelper.makeElement(actionDefElement, ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + privateParamName); //$NON-NLS-1$
      inputElement.addAttribute(ActionSequenceResource.TYPE_NAME, inputType);
      input = new ActionInput(inputElement, actionParameterMgr);
      ActionSequenceDocument.fireIoAdded(input);
    } else {
      input.setType(inputType);
    }
    return input;
  }
  
  public void setActionInputValue(String inputPrivateName, IActionInput value) {
    if ((value == null) || (value instanceof ActionInputConstant)) {
      setInputValue(inputPrivateName, value != null ? value.getStringValue(false) : null);
    } else if (value instanceof IActionInputVariable) {
      setInputParam(inputPrivateName, (IActionInputVariable)value);
    }
  }
  
  /**
   * Sets the value of the named action input to the specified constant value. A child element
   * of the component definition section is created and assigned the provided name. The text of this child element
   * is assigned the provided value. If there is an input parameter of the same name in the action inputs section,
   * the input parameter is deleted. If the value is null, both the input parameter and the component definition
   * child element are deleted.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
   * @param value the value to be assigned. May be null.
   */
  public void setInputValue(String privateParamName, String value) {
    setInputValue(privateParamName, value, true);
  }
  
  /**
   * Creates or modifies the named input parameter to refer to the named variable. The referenced variable should
   * be the name of an input to the parent action sequence document or the name of an output from an action
   * definition that precedes this action definition in the document. If the component definition section of this
   * action definition contains a child element with the given private name, that element is removed from the 
   * component definition section. If the referenced variable name is null, the named action input is deleted, and
   * the component definition section is left untouched.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
   * @param value the value to be assigned. May be null.
   */
  public ActionInput setInputParam(String privateParamName, String referencedVariableName) {
    ActionInput actionInput = null;
    if (referencedVariableName == null) {
      actionInput = getInputParam(privateParamName);
      if (actionInput != null) {
        actionInput.delete();
      }     
    } else {
      IActionInputVariable mappedElement = null;
      IActionInputVariable[] availInputs = getAvailInputVariables();
      
      for (int i = 0; i < availInputs.length; i++) {
        if (availInputs[i].getVariableName().equals(referencedVariableName)) {
          mappedElement = availInputs[i];
          break;
        }
      }
      String type = (mappedElement != null ? mappedElement.getType() : ActionSequenceDocument.STRING_TYPE);
      actionInput = addInputParam(privateParamName, type);
      actionInput.setMapping(referencedVariableName);
    }
    return actionInput;
  }
  
  /**
   * Sets the value of the named action input to the specified constant value. A child element
   * of the component definition section is created and assigned the provided name. The text of this child element
   * is assigned the provided value. If there is an input parameter of the same name in the action inputs section,
   * the input parameter is deleted. If the value is null, both the input parameter and the component definition
   * child element are deleted.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
   * @param value the value to be assigned. May be null.
   * @param useCData indicates whether a CDATA node should be used to store the value.
   */
  public void setInputValue(String privateParamName, String value, boolean useCData) {
    removeInputParam(privateParamName);
    if (value == null) {
      removeComponentDefinitions(privateParamName);
    } else {
      setComponentDefinition(privateParamName, value, useCData);
    }
  }
  
  public IActionInput getActionInputValue(String privateParamName) {
    IActionInput inputParam = getInputParam(privateParamName);
    
    if ((inputParam == null) || (inputParam.getValue() == null)) {
      String componentDefValue = getComponentDefinitionValue(privateParamName);
      if (componentDefValue == null) {
        if (inputParam == null) {
          inputParam = IActionInput.NULL_INPUT;
        }
      } else {
        inputParam = new ActionInputConstant(componentDefValue, actionParameterMgr);
      }
    }
    
    return inputParam;
  }
  
  /**
   * Returns the value of the named action input. The value is determined by looking for a child element of the
   * component definition element with the given name, and returning its text value.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
   * @return the assigned value, or null if no value has been assigned.
   */
//  public Object getInputValue(String privateParamName) {
//    Object value = null;
//    ActionInput actionInput = getInputParam(privateParamName);
//    if (actionInput != null) {
//      value = actionInput.getValue();
//    }
//    if (value == null) {
//      value = getComponentDefinitionValue(privateParamName);
//    }
//    return value;
//  }
  
  /**
   * Creates an input resource with the given name. No operation is performed if the resource already exists.
   * 
   * @param privateResourceName the name of the resource as it is known by this action definition (the element name).
   * @return the newly created or existing resource.
   */
  public ActionResource addResourceParam(String privateResourceName) {
    return addResourceParam(privateResourceName, null);
  }
  
  /**
   * Creates an input resource with the given name. The resource will reference the specified action sequence resource.
   * No operation is performed if the resource already exists.
   * 
   * @param privateResourceName the name of the resource as it is known by this action definition (the element name).
   * @return the newly created or existing resource.
   */
  public ActionResource addResourceParam(String privateResourceName, String referencedActionSequenceResource) {
    ActionResource resource = getResourceParam(privateResourceName);
    Element resourceElement;
    if (resource == null) {
      resourceElement = DocumentHelper.makeElement(actionDefElement, ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + privateResourceName); //$NON-NLS-1$
      resourceElement.addAttribute(ActionSequenceResource.TYPE_NAME, ActionSequenceDocument.RESOURCE_TYPE);
      resource = new ActionResource(resourceElement, actionParameterMgr);
      if ((referencedActionSequenceResource != null) && (referencedActionSequenceResource.trim().length() > 0)) {
        resource.setMapping(referencedActionSequenceResource);
      }
      ActionSequenceDocument.fireResourceAdded(resource);
    }
    return resource;
  }
  
  /**
   * @return the resources referenced by this action definition
   */
  public ActionResource[] getAllResourceParams() {
    List resourcesList =  actionDefElement.selectNodes(ActionSequenceDocument.ACTION_RESOURCES_NAME + "/*"); //$NON-NLS-1$
    ActionResource[] resources = new ActionResource[resourcesList.size()];
    int index = 0;
    for (Iterator iter = resourcesList.iterator(); iter.hasNext();) {
      resources[index++] = new ActionResource((Element)iter.next(), actionParameterMgr);
    }
    return resources;
  }
  
  /**
   * Return the named resource of null if none exists. If the resource is not explicitly listed in the action resources
   * section of this action definition, an implicit resource is created if one exists. These implicit resources are detected
   * by looking at the action sequence resources listed at the top of the action sequence document with the given name. If an
   * action sequence resource is found an implicit reference is created and returned by this method.
   * section of this action definition will be returned
   * 
   * @param privateResourceName the resource name
   * @return the resource with the given name or null if none exists.
   */
  public ActionResource getResourceParam(String privateResourceName) {
    return getResourceParam(privateResourceName, true);
  }
  
  /**
   * Return the named resource of null if none exists. If the resource is not explicitly listed in the action resources
   * section of this action definition, an implicit resource is created if one exists. These implicit resources are detected
   * by looking at the action sequence resources listed at the top of the action sequence document with the given name. If an
   * action sequence resource is found an implicit reference is created and returned by this method.
   * section of this action definition will be returned
   * 
   * @param privateResourceName the resource name
   * @param includeImplicitResource whether to include implicit resource references.
   * @return the resource with the given name or null if none exists.
   */
  public ActionResource getResourceParam(String privateResourceName, boolean includeImplicitResource) {
    Element inputElement = (Element)actionDefElement.selectSingleNode(ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + privateResourceName); //$NON-NLS-1$
    ActionResource actionResource = null;
    if (inputElement == null) {
      if (includeImplicitResource) {
        ActionSequenceDocument document = getDocument();
        if (document != null) {
          ActionSequenceResource actionSequenceResource = getDocument().getResource(privateResourceName);
          if (actionSequenceResource != null) {
            actionResource = new ImplicitActionResource(this, privateResourceName, actionParameterMgr);
          }
        }
      }
    } else {
      actionResource = new ActionResource(inputElement, actionParameterMgr);
    }
    return actionResource;
  }
  
  /**
   * Returns all the inputs that are listed in the action inputs section of this action definition.
   * Inputs that have been assigned a constant value appear in the component definition section and will
   * not be returned by this method.
   * 
   * @return the inputs list in the action inputs section
   */
  public ActionInput[] getAllInputParams() {
    List inputsList =  actionDefElement.selectNodes(ActionSequenceDocument.ACTION_INPUTS_NAME + "/*"); //$NON-NLS-1$
    ActionInput[] inputs = new ActionInput[inputsList.size()];
    int index = 0;
    for (Iterator iter = inputsList.iterator(); iter.hasNext();) {
      inputs[index++] = new ActionInput((Element)iter.next(), actionParameterMgr);
    }
    return inputs;
  }
  
  /**
   * Returns the input in the action inputs section of this action definition that the specified name. 
   * Inputs that have been assigned a constant value appear in the component definition section and will
   * not be returned by this method.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
   * @return the input with the specified name or null if none exists.
   */
  public ActionInput getInputParam(String privateInputName) {
    Element inputElement = (Element)actionDefElement.selectSingleNode(ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + privateInputName); //$NON-NLS-1$
    return inputElement == null ? null : new ActionInput(inputElement, actionParameterMgr);
  }
  
  /**
   * Returns all the inputs that are listed in the action inputs section of this action definition that have any
   * one of the specified types. Inputs that have been assigned a constant value appear in the component definition section and will
   * not be returned by this method.
   * 
   * @param types data types of the desired inputs.
   * @return the inputs of the specified types listed in the action inputs section of this action definition
   */
  public ActionInput[] getInputParams(String[] types) {
    ActionInput[] allInputs = getAllInputParams();
    List matchingInputs = new ArrayList();
    if (types == null) {
      matchingInputs.addAll(Arrays.asList(allInputs));
    } else {
      for (int outIdx = 0; outIdx < allInputs.length; outIdx++) {
        for (int  typeIdx= 0; typeIdx < types.length; typeIdx++) {
          if (types[typeIdx].equals(allInputs[outIdx].getType())) {
            matchingInputs.add(allInputs[outIdx]);
            break;
          }
        }
      }
    }
    return (ActionInput[])matchingInputs.toArray(new ActionInput[0]);
  }
  
  /**
   * Returns all the inputs that are listed in the action inputs section of this action definition that have the specified types. Inputs that have been assigned a constant value appear in the component definition section and will
   * not be returned by this method.
   * 
   * @param type data type of the desired inputs.
   * @return the inputs listed in the action inputs section of the specified type
   */
  public ActionInput[] getInputParams(String type) {
    return getInputParams(new String[]{type});
  }
  
  public ActionOutput addOutputParam(String privateParamName, String outputType) {
    ActionOutput output = getOutputParam(privateParamName);
    Element outputElement;
    if (output == null) {
      outputElement = DocumentHelper.makeElement(actionDefElement, ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/" + privateParamName); //$NON-NLS-1$
      outputElement.addAttribute(ActionSequenceResource.TYPE_NAME, outputType);
      output = new ActionOutput(outputElement, actionParameterMgr);
      ActionSequenceDocument.fireIoAdded(output);
    } else {
      output.setType(outputType);
    }
    return output;
  }
  
  /**
   * Returns all the outputs that are listed in the action outputs section of this action definition.
   * 
   * @return the outputs list in the action input name
   */
  public ActionOutput[] getAllOutputParams() {
    List outputsList =  actionDefElement.selectNodes(ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/*"); //$NON-NLS-1$
    ActionOutput[] outputs = new ActionOutput[outputsList.size()];
    int index = 0;
    for (Iterator iter = outputsList.iterator(); iter.hasNext();) {
      outputs[index++] = new ActionOutput((Element)iter.next(), actionParameterMgr);
    }
    return outputs;
  }
  
  /**
   * Returns the output, with the specified name, listed in the action outputs section of this action definition.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the output element name).
   * @return the named output or null if none exists.
   */
  public ActionOutput getOutputParam(String privateParamName) {
    Element outputElement = (Element)actionDefElement.selectSingleNode(ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/" + privateParamName); //$NON-NLS-1$
    return outputElement == null ? null : new ActionOutput(outputElement, actionParameterMgr);
  }
  
  /**
   * Returns all the outputs that are listed in the action outputs section of this action definition that have the specified types. 
   * 
   * @param types data types of the desired outputs.
   * @return the outputs listed in the action outputs section of the specified type
   */
  public ActionOutput[] getOutputParams(String[] types) {
    ActionOutput[] allOutputs = getAllOutputParams();
    List matchingOutputs = new ArrayList();
    if (types == null) {
      matchingOutputs.addAll(Arrays.asList(allOutputs));
    } else {
      for (int outIdx = 0; outIdx < allOutputs.length; outIdx++) {
        for (int  typeIdx= 0; typeIdx < types.length; typeIdx++) {
          if (types[typeIdx].equals(allOutputs[outIdx].getType())) {
            matchingOutputs.add(allOutputs[outIdx]);
            break;
          }
        }
      }
    }
    return (ActionOutput[])matchingOutputs.toArray(new ActionOutput[0]);
  }
  
  /**
   * Returns all the outputs that are listed in the action outputs section of this action definition that have the specified type. 
   * 
   * @param type data types of the desired outputs.
   * @return the outputs listed in the action outputs section of the specified type
   */
  public ActionOutput[] getOutputParams(String type) {
    return getOutputParams(new String[]{type});
  }
  
  /**
   * @return the action definition description
   */
  public String getDescription() {
    Element descriptionElement = actionDefElement.element(ActionSequenceDocument.ACTION_TYPE_NAME);
    return descriptionElement != null ? descriptionElement.getText() : ""; //$NON-NLS-1$
  }
  
  /**
   * Set the action definition description
   * @param description the description
   */
  public void setDescription(String description) {
    if (description == null) {
      description = ""; //$NON-NLS-1$
    }
    Element descriptionElement = actionDefElement.element(ActionSequenceDocument.ACTION_TYPE_NAME);
    if (descriptionElement == null) {
      descriptionElement = actionDefElement.addElement(ActionSequenceDocument.ACTION_TYPE_NAME);
      descriptionElement.setText(description);
      ActionSequenceDocument.fireActionRenamed(this);
    } else if (!descriptionElement.getText().equals(description)) {
      descriptionElement.setText(description);
      ActionSequenceDocument.fireActionRenamed(this);
    }
  }
  
  public boolean equals(Object arg0) {
    boolean result = false;
    if (arg0 instanceof ActionDefinition) {
      ActionDefinition actionDef = (ActionDefinition)arg0;
      result = (actionDef.actionDefElement != null ? actionDef.actionDefElement.equals(this.actionDefElement) : (actionDef == this));
    }
    return result;
  }
  
  /**
   * @return the action control statement (if or loop) that contains this action definition or null if there is no parent control statement.
   */
  public ActionControlStatement getParent() {
    ActionControlStatement controlStatement = null;
    if (actionDefElement != null) {
      Element ancestorElement = actionDefElement.getParent();
      if ((ancestorElement != null) 
          && ancestorElement.getName().equals(ActionSequenceDocument.ACTIONS_NAME)
          && !ancestorElement.getPath().equals(ActionSequenceDocument.DOC_ACTIONS_PATH)) {
        if (ancestorElement.element(ActionSequenceDocument.CONDITION_NAME) != null) {
          controlStatement = new ActionIfStatement(ancestorElement, actionParameterMgr);
        } else {
          controlStatement = new ActionLoop(ancestorElement, actionParameterMgr);
        }
      }
    }
    return controlStatement;
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getElement()
   */
  public Element getElement() {
    return actionDefElement;
  }
  
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#delete()
   */
  public void delete() {
    Document doc = actionDefElement.getDocument();
    if (doc != null) {
      actionDefElement.detach();
      ActionSequenceDocument.fireActionRemoved(new ActionSequenceDocument(doc, actionParameterMgr), this);
    }
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
   */
  public ActionSequenceDocument getDocument() {
    ActionSequenceDocument doc = null;
    if ((actionDefElement != null) && (actionDefElement.getDocument() != null)) {
      doc = new ActionSequenceDocument(actionDefElement.getDocument(), actionParameterMgr);
    }
    return doc;
  }
  
  /**
   * The value of the component definition element at the specified XPath
   * @param compDefXpath the XPath of the element relative to the component definition element.
   * @return the value of the element or null if the element does not exist
   */
  public String getComponentDefinitionValue(String compDefXpath) {
    Element componentDef = getComponentDefElement(compDefXpath);
    return componentDef != null ? componentDef.getText() : null;
  }
  
  /**
   * The values of the component definition elements at the specified XPath
   * @param compDefXpath the XPath of the elements relative to the component definition element.
   * @return the values of the elements
   */
  public String[] getComponentDefinitionValues(String compDefXpath) {
    Element[] componentDefs = getComponentDefElements(compDefXpath);
    String[] values = new String[componentDefs.length];
    for (int i = 0; i < values.length; i++) {
      values[i] = componentDefs[i].getText();
    }
    return values;
  }
  
  /**
   * The component definition elements at the specified XPath
   * @param compDefXpath the XPath of the elements relative to the component definition element.
   * @return the elements
   */
  public Element[] getComponentDefElements(String compDefXpath) {
    return (Element[])actionDefElement.selectNodes(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + compDefXpath).toArray(new Element[0]); //$NON-NLS-1$
  }
  
  /**
   * The component definition element at the specified XPath
   * @param compDefXpath the XPath of the elements relative to the component definition element.
   * @return the element or null if the element does not exist.
   */
  public Element getComponentDefElement(String compDefXpath) {
    return actionDefElement == null ? null : (Element)actionDefElement.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + compDefXpath); //$NON-NLS-1$
  }

  /**
   * Sets the value of the component definition element at the specified XPath.
   * @param compDefXpath the XPath of the element relative to the component definition element.
   * @param value the value to be assigned to the element
   */
  public void setComponentDefinition(String compDefXpath, String value) {
    setComponentDefinition(compDefXpath, value, false);
  }
  
  /**
   * Sets the value of the component definition elements at the specified XPath.
   * @param compDefXpath the XPath of the element relative to the component definition element.
   * @param values the value to be assigned to the elements
   */
  public void setComponentDefinition(String compDefXpath, String[] values) {
    boolean changed = false;
    Element[] componentDefs = getComponentDefElements(compDefXpath);
    for (int i = 0; i < componentDefs.length; i++) {
      componentDefs[i].detach();
    }
    if (componentDefs.length > 0) {
      changed = true;
    }
    if (values.length > 0){
      Element componentDef = DocumentHelper.makeElement(actionDefElement, ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + compDefXpath); //$NON-NLS-1$
      componentDef.setText(values[0]);
      Element parent = componentDef.getParent();
      for (int i = 1; i < values.length; i++) {
        parent.addElement(componentDef.getName()).setText(values[i]);
      }
      changed = true;
    }
    if (changed) {
      ActionSequenceDocument.fireActionChanged(this);
    }
  }
  
  /**
   * Sets the attribute value of the component definition element at the specified XPath.
   * @param compDefXpath the XPath of the element relative to the component definition element.
   * @param attributeName the attribute name
   * @param value the value to be assigned to the attribute
   */
  public void setComponentDefinitionAttribute(String compDefXpath, String attributeName, String value) {
    Element componentDef = getComponentDefElement(compDefXpath);
    if (componentDef == null) {
      if (value != null) {
        componentDef = DocumentHelper.makeElement(actionDefElement, ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + compDefXpath); //$NON-NLS-1$
      }
    }
    if (componentDef != null) {
      componentDef.addAttribute(attributeName, value);
      ActionSequenceDocument.fireActionChanged(this);
    }
  }
  
  /**
   * Sets the value of the component definition element at the specified XPath.
   * @param compDefXpath the XPath of the element relative to the component definition element.
   * @param value the value to be assigned to the element
   * @param useCData whether a CDATA node should be used to save the value
   */
  public void setComponentDefinition(String compDefXpath, String value, boolean useCData) {
    if (value == null) {
      Element[] componentDefs = getComponentDefElements(compDefXpath);
      for (int i = 0; i < componentDefs.length; i++) {
        componentDefs[i].detach();
      }
      if (componentDefs.length > 0) {
        ActionSequenceDocument.fireActionChanged(this);
      }
    } else {
      Element componentDef = getComponentDefElement(compDefXpath);
      if (componentDef == null) {
        componentDef = DocumentHelper.makeElement(actionDefElement, ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + compDefXpath); //$NON-NLS-1$
      }
        componentDef.clearContent(); 
      if (useCData) {
        componentDef.addCDATA( value );
      } else {
        componentDef.setText( value );
      }
      ActionSequenceDocument.fireActionChanged(this);
    }
  }
  
  /**
   * Returns the list of all ActionSequenceInputs and ActionOutputs that are available as inputs to 
   * this action definition.
   * @return
   */
  public IActionInputVariable[] getAvailInputVariables() {
    return getDocument().getAvailInputVariables(this, (String[])null);
  }
  
  /**
   * Returns the list of ActionSequenceInputs and ActionOutputs that are available as inputs to 
   * this action definition.
   * @param types the desired input types
   * @return
   */
  public IActionInputVariable[] getAvailInputVariables(String[] types) {
    return getDocument().getAvailInputVariables(this, types);
  }
  
  /**
   * Returns the list of ActionSequenceInputs and ActionOutputs that are available as inputs to 
   * this action definition.
   * @param types the desired input type
   * @return
   */
  public IActionInputVariable[] getAvailInputVariables(String type) {
    return getDocument().getAvailInputVariables(this, new String[]{type});
  }
  
  /**
   * Removes an output from this action definition
   * @param privateParamName the name of the output to be removed.
   */
  private void removeOutputParam(String privateParamName) {
    ActionOutput actionOutput = getOutputParam(privateParamName);
    if (actionOutput != null) {
      actionOutput.delete();
    }
  }
  
  /**
   * Removes an input from this action definition
   * @param inputName the name of the input to be removed.
   */
  public void removeInputParam(String privateParamName) {
    ActionInput actionInput = getInputParam(privateParamName);
    if (actionInput != null) {
      actionInput.delete();
    }
  }
  
  /**
   * Renames the named action input. No operation is performed if the input does not exist. Any
   * inputs defined in the component definition section that refer to the input using the
   * {oldName} parameter reference are also modified to refer to {newName}. 
   * @param oldName the name of the input to be renamed.
   * @param newName the new input name.
   */
  public void renameInputParam(String oldName, String newName) {
    if (!oldName.equals(newName)) {
      ActionInput actionInput = getInputParam(oldName);
      if (actionInput != null) {
        Element componentDefElement = actionDefElement.element(ActionSequenceDocument.COMPONENT_DEF_NAME);
        try {
          if (componentDefElement != null) {
            String xmlString = componentDefElement.asXML();
            if (xmlString.indexOf("{" + oldName + "}") >= 0) { //$NON-NLS-1$ //$NON-NLS-2$
              xmlString = xmlString.replaceAll("\\{" + oldName + "\\}", "{" + newName + "}"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
              Document document = DocumentHelper.parseText(xmlString);
              actionDefElement.remove(componentDefElement);
              actionDefElement.add(document.getRootElement());
            }
          }
          actionInput.setName(newName);
        } catch (DocumentException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
  
  /**
   * Removes a resource from this action definition
   * @param privateResourceName the name of the resource to be removed.
   */
  public void removeResourceParam(String privateResourceName) {
    ActionResource actionResource = getResourceParam(privateResourceName);
    if (actionResource != null) {
      actionResource.delete();
    }
  }
  
  /**
   * Removes all inputs from this action definition
   */
  public void clearInputParams() {
    ActionInput[] actionInputs = getAllInputParams();
    for (int i = 0; i < actionInputs.length; i++) {
      actionInputs[i].delete();
    }
  }
  
  /**
   * Removes all output from this action definition
   */
  public void clearOutputParams() {
    ActionOutput[] actionOutputs = getAllOutputParams();
    for (int i = 0; i < actionOutputs.length; i++) {
      actionOutputs[i].delete();
    }
  }
  
  /**
   * Removes all resources from this action definition
   */
  public void clearResourceParams() {
    ActionResource[] actionResources = getAllResourceParams();
    for (int i = 0; i < actionResources.length; i++) {
      actionResources[i].delete();
    }
  }
  
  /**
   * Removes all component definition elements at the specified XPath
   * 
   * @param compDefXpath the XPath of the elements relative to the component definition element.
   */
  public void removeComponentDefinitions(String compDefXpath) {
    Element[] componentDefs = getComponentDefElements(compDefXpath);
    for (int i = 0; i < componentDefs.length; i++) {
      componentDefs[i].detach();
    }
    if (componentDefs.length > 0) {
      ActionSequenceDocument.fireActionChanged(this);
    }
  }
  
  /**
   * Removes all component definition child elements from this action definition. 
   */
  public void removeComponentDefinitions() {
    if (actionDefElement != null) {
      Element componentDefElement = actionDefElement.element(ActionSequenceDocument.COMPONENT_DEF_NAME);
      if (componentDefElement != null) {
        List elements = componentDefElement.elements();
        if (elements.size() > 0) {
          elements.clear();
          ActionSequenceDocument.fireActionChanged(this);
        }
      }
    }
  }
  
  
  /**
   * Moves the given input to the specified position in the action inputs list.
   * @param actionInput the input to be moved.
   * @param index the new input position
   */
  public void setInputParamIndex(ActionInput actionInput, int index) {
    if (this.equals(actionInput.getActionDefinition())) {
      Element inputsParent = actionDefElement.element(ActionSequenceDocument.ACTION_INPUTS_NAME);
      List inputs = inputsParent.elements();
      int currentIndex = inputs.indexOf(actionInput.getElement());
      if (currentIndex != index) {
        List elements = inputsParent.elements();
        elements.remove(currentIndex);
        if (index > elements.size() - 1) {
          elements.add(actionInput.getElement());
        } else {
          elements.add(index, actionInput.getElement());
        }
        ActionSequenceDocument.fireActionChanged(this);
      }
    }
  }
  
  
  /**
   * Returns all the private input names that are reserved for use by this action definition.
   * Inputs with these names are used for a particular purpose by this action definition.
   * 
   * @return the reserved input names
   */
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  /**
   * Returns all the private outputs names that are reserved for use by this action definition.
   * Outputs with these names are used for a particular purpose by this action definition.
   * 
   * @return the reserved output names
   */
  public String[] getReservedOutputNames() {
    return EXPECTED_OUTPUTS;
  }

  /**
   * Returns all the private resource names that are reserved for use by this action definition.
   * Resources with these names are used for a particular purpose by this action definition.
   * 
   * @return the reserved output names
   */
  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }
  
  /**
   * Returns the all action definitions that precede this action definition in the action sequence document.
   * 
   * @return the preceding action definitions
   */
  public ActionDefinition[] getPrecedingActionDefinitions() {
    return getDocument().getPrecedingActionDefinitions(this);
  }

  /**
   * Returns the all action definitions, loops, and if statements that precede this action definition in the action sequence document.
   * 
   * @return the preceding executable statements
   */
  public IActionSequenceExecutableStatement[] getPrecedingExecutableStatements() {
    return getDocument().getPrecedingExecutables(this);
  }
  
  /**
   * Creates or modifies the named input parameter to refer to the provided variable. The referenced variable should
   * be an action sequence input of the parent action sequence document or an action output from an action
   * definition that precedes this action definition in the document. If the component definition section of this
   * action definition contains a child element with the given private name, that element is removed from the 
   * component definition section. If the referenced variable name is null, the named action input is deleted, and
   * the component definition section is left untouched.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
   * @param referencedVariable the variable to be referenced. May be null.
   */
  protected ActionInput setInputParam(String privateParamName, IActionInputVariable referencedVariable) {
    ActionInput actionInput = null;
    if (referencedVariable != null) {
      actionInput = setInputParam(privateParamName, referencedVariable.getVariableName());
      if (actionInput != null) {
        actionInput.setType(referencedVariable.getType());
      }
    } else {
      removeInputParam(privateParamName);
    }
    return actionInput;
  }
  
  private IActionInputVariable getReferencedInputVariable(String privateInputName) {
    ActionInputVariable variable = null;
    ActionInput actionInput = getInputParam(privateInputName);
    if (actionInput != null) {
      variable = new ActionInputVariable();
      variable.setVariableName(actionInput.getReferencedVariableName());
      variable.setType(actionInput.getType());
    }
    return variable; 
  }
  
  /**
   * Creates the named output parameter, and assigns the output parameter the specified public name and type. 
   * The public name is the name used by succeeding action definitions to refer to this ouput. If the output already exists
   * it is given the specified public name. If the public name is null or zero length the output is 
   * deleted.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the output element name).
   * @param publicParamName the public name of the output. May be null.
   * @param outputType the output type. Ignored if the publicParamName is null.
   * @return the created/modified action output.
   */
  protected ActionOutput setOutputParam(String privateParamName, String publicParamName, String outputType) {
    ActionOutput actionOutput = null;
    if ((publicParamName == null) || (publicParamName.trim().length() == 0)) {
      removeOutputParam(privateParamName);
    } else {
      actionOutput = addOutputParam(privateParamName, outputType); 
      actionOutput.setMapping(publicParamName);
    }
    return actionOutput;
  }
  
  /**
   * Returns the public name of the output with the specified private name.
   * 
   * @param privateName the name of the param as it is known by this action definition (the output element name).
   * @param outputType the output type. Ignored if the publicParamName is null.
   * @return the output public name or null if the output does not exist.
   */
  protected String getPublicOutputName(String privateName) {
    String publicName = null;
    ActionOutput actionOutput = getOutputParam(privateName);
    if (actionOutput != null) {
      publicName = actionOutput.getPublicName();
    }
    return publicName;
  }
  
  /**
   * Verifies that there is an action input or component definition defined for the specified parameter.
   * If an action input exists the method insures that is references a valid variable.
   * 
   * @param privateParamName the name of the param to be validated.
   * @return null if no errors are detected or the validation error otherwise.
   */
  protected ActionSequenceValidationError validateInputParam(String privateParamName) {
    int errorCode = ActionSequenceValidationError.INPUT_OK;
    IActionInputVariable variable = getReferencedInputVariable(privateParamName);
    if (variable == null) {
      if (getComponentDefElement(privateParamName) == null) {
        errorCode = ActionSequenceValidationError.INPUT_MISSING;
      }
    } else {
      IActionInputVariable[] availableInputVariables = getDocument().getAvailInputVariables(this, variable.getType());
      if (availableInputVariables.length == 0) {
        errorCode = ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR;
      } else {
        errorCode = ActionSequenceValidationError.INPUT_UNINITIALIZED;
        for (int i = 0; (i < availableInputVariables.length) && (errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED); i++) {
          if (availableInputVariables[i] instanceof ActionSequenceInput) {
            ActionSequenceInput actionSequenceInput = (ActionSequenceInput)availableInputVariables[i];
            if (actionSequenceInput.getDefaultValue() != null) {
              errorCode = ActionSequenceValidationError.INPUT_OK;
            }
          } else if (availableInputVariables[i] instanceof ActionOutput) {
            errorCode = ActionSequenceValidationError.INPUT_OK;
          }
        }
      }
    }
    ActionSequenceValidationError validationError = null;
    if (errorCode != ActionSequenceValidationError.INPUT_OK) {
      validationError = new ActionSequenceValidationError();
      validationError.actionDefinition = this;
      validationError.errorCode = errorCode;
      validationError.parameterName = privateParamName;
      switch (errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing input.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Input references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Input is uninitialized.";
          break;
      }
    }
    return validationError;
  }
  
  /**
   * Verifies that there is an action input or action resource defined for the specified parameter.
   * If so the method verifies that the input/resource references a valid variable/action sequence resource.
   * 
   * @param privateResourceName the name of the param to be validated.
   * @return null if no errors are detected or the validation error otherwise.
   */
  protected ActionSequenceValidationError validateResourceParam(String privateResourceName) {
    ActionSequenceValidationError validationError = validateInputParam(privateResourceName);
    if (validationError != null) {
      validationError = null;
      int errorCode = ActionSequenceValidationError.INPUT_OK;
      ActionResource actionResource = getResourceParam(privateResourceName);
      if (actionResource == null) {
        errorCode = ActionSequenceValidationError.INPUT_MISSING;
      } else {
        errorCode = ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR;
      }
      if (errorCode != ActionSequenceValidationError.INPUT_OK) {
        validationError = new ActionSequenceValidationError();
        validationError.actionDefinition = this;
        validationError.errorCode = errorCode;
        validationError.parameterName = privateResourceName;
        switch (errorCode) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing input.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Input references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Input is uninitialized.";
            break;
        }
      }
    }
    return validationError;
  }
  
  /**
   * This is the default implementation for validating an action definition. By default no errors are returned.
   * Subclasses of this class should override this method to validate the necessary inputs, outputs, and resources.
   * 
   * @return the validation errors that were detected.
   */
  public ActionSequenceValidationError[] validate() {
    return EMPTY_ARRAY;
  }
  
  /**
   * Verifies that there is an action output defined with the given name.
   * 
   * @param privateParamName the name of the param to be validated.
   * @return null if no errors are detected or the validation error otherwise.
   */
  protected ActionSequenceValidationError validateOutputParam(String privateParamName) {
    ActionSequenceValidationError validationError = null;
    if (getOutputParam(privateParamName) == null) {
      validationError = new ActionSequenceValidationError();
      validationError.actionDefinition = this;
      validationError.errorCode = ActionSequenceValidationError.OUTPUT_MISSING;
      validationError.parameterName = privateParamName;
      validationError.errorMsg = "Missing output.";
    }
    return validationError;
  }
  
  /**
   * Moves this action definition to the specified if/loop statement. If the parent control statement is null this action 
   * definition will be a top level action in the action sequence document.
   * 
   * @param newParentControlStatement the new parent if/loop statement. May be null.
   * @param index the index of this action definition within the parent control statement.
   */
  public void moveTo(ActionControlStatement newParentControlStatement, int index) {
    if (newParentControlStatement == null) {
      newParentControlStatement = getDocument().getRootLoop();
    }
    newParentControlStatement.add(this, index);
  }
  
  /**
   * Moves this action definition to the specified if/loop statement. This action definition will
   * be the last child of parent control statement. If the parent control statement is null this action 
   * definition will be a top level action in the action sequence document.
   * 
   * @param newParentControlStatement the new parent if/loop statement. May be null.
   */
  public void moveTo(ActionControlStatement newParentControlStatement) {
    if (newParentControlStatement == null) {
      newParentControlStatement = getDocument().getRootLoop();
    }
    newParentControlStatement.add(this);
  }
  
  public IActionParameterMgr getActionParameterMgr() {
    return actionParameterMgr;
  }
}
