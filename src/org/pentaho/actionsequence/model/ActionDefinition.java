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
package org.pentaho.actionsequence.model;

import java.util.ArrayList;
import java.util.List;

import org.pentaho.actionsequence.model.messages.Messages;

/**
 * A wrapper for an action definition element within an action sequence.
 * @author Angelo Rodriguez
 */
public class ActionDefinition implements IActionDefinition {
  
  IActionControlStatement parent;
  String componentName;
  String description;
  private static final String[] EXPECTED_INPUTS = new String[0];
  private static final String[] EXPECTED_OUTPUTS = new String[0];
  private static final String[] EXPECTED_RESOURCES= new String[0];
  ArrayList<IActionInput> inputs = new ArrayList<IActionInput>();
  ArrayList<IActionOutput> outputs = new ArrayList<IActionOutput>();
  ArrayList<IActionResource> resources = new ArrayList<IActionResource>();
  
  /**
   * @param actionDefElement the wrapped action definition element
   */
  public ActionDefinition() {
    this(Messages.getString("ActionDefinition.ENTER_CLASS_NAME")); //$NON-NLS-1$
    setDescription(Messages.getString("ActionDefinition.CUSTOM_ACTION_TITLE")); //$NON-NLS-1$
  }
  
  public ActionDefinition(IActionControlStatement parent) {
    super();
    this.parent = parent;
  }
  
  protected ActionDefinition(String componentName) {
    setComponentName(componentName);
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
  public IActionResource setResourceUri(String privateResourceName, String uri, String mimeType) {
    IActionResource actionResource = getResource(privateResourceName);
    if (uri == null) {
      if (actionResource != null) {
        actionResource.setURI(null);
      }
      actionResource = null;
    } else {
      if (actionResource == null) {
        actionResource = addResource(privateResourceName);
      }
      actionResource.setURI(uri);
      actionResource.setMimeType(mimeType);
    }
    return actionResource;
  }
  
  protected void initNewActionDefinition(){
    
  };

 
  
  /**
   * Returns the component name of this action definition.
   * 
   * @return the action definition's component name.
   */
  public String getComponentName() {
    return componentName;
  }
  
  /**
   * Sets the component name of this action definition.
   * 
   * @param name the component name.
   */
  public void setComponentName(String name) {
    if (!name.equals(componentName)) {
      componentName = name;
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
  public ActionInput addInput(String privateParamName, String inputType) {
    ActionInput input = (ActionInput)getInputParam(privateParamName);
    if (input == null) {
      input = new ActionInput(this);
      input.setName(privateParamName);
      input.setType(inputType);
      inputs.add(input);
    } else {
      input.setType(inputType);
    }
    return input;
  }
  
  public void setActionInputValue(String inputPrivateName, IActionInputSource value) {
    if ((value == null) || (value instanceof ActionInputConstant)) {
      setInputValue(inputPrivateName, value != null ? ((ActionInputConstant)value).getStringValue() : null);
    } else {
      setInputParam(inputPrivateName, (IActionInputVariable)value);
    }
  }
  
  public void setActionInputValue(String inputPrivateName, ActionInput actionInput) {
    setInputParam(inputPrivateName, actionInput.getReferencedVariableName(), actionInput.getType());
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
  public IActionInput setInputParam(String privateParamName, String referencedVariableName, String type) {
    ActionInput actionInput = null;
    if (referencedVariableName == null) {
      actionInput = (ActionInput)getInputParam(privateParamName);
      if (actionInput != null) {
        inputs.remove(actionInput);
      }     
    } else {
      actionInput = addInput(privateParamName, type != null ? type : ActionSequenceDocument.STRING_TYPE);
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
   */
  public void setInputValue(String privateParamName, String value) {
    IActionInput input = getInput(privateParamName);
    if (input instanceof ActionInput) {
      inputs.remove(input);
      ActionInputConstant constant = new ActionInputConstant(value);
      constant.setName(privateParamName);
      inputs.add(constant);
    } else if (input instanceof ActionInputConstant) {
      ((ActionInputConstant)input).setValue(value);
    } else {
      ActionInputConstant constant = new ActionInputConstant(value);
      constant.setName(privateParamName);
      inputs.add(constant);
    }
  }
  
  public IActionInput getInput(String privateParamName) {
    
    IActionInput inputParam = IActionInput.NULL_INPUT;
    for (IActionInput input : getInputs()) {
      if (input.getName().equals(privateParamName)) {
        inputParam = input;
        break;
      }
    }
    
    return inputParam;
  }
  
  
  /**
   * Creates an input resource with the given name. No operation is performed if the resource already exists.
   * 
   * @param privateResourceName the name of the resource as it is known by this action definition (the element name).
   * @return the newly created or existing resource.
   */
  public IActionResource addResource(String privateResourceName) {
    return addResource(privateResourceName, null);
  }
  
  /**
   * Creates an input resource with the given name. The resource will reference the specified action sequence resource.
   * No operation is performed if the resource already exists.
   * 
   * @param privateResourceName the name of the resource as it is known by this action definition (the element name).
   * @return the newly created or existing resource.
   */
   public IActionResource addResource(String privateResourceName, String referencedActionSequenceResource) {
    IActionResource resource = getResource(privateResourceName);
    if (resource == null) {
      resource = new ActionResource(this);
      if ((referencedActionSequenceResource != null) && (referencedActionSequenceResource.trim().length() > 0)) {
        resource.setMapping(referencedActionSequenceResource);
      }
    }
    return resource;
  }
  
  /**
   * @return the resources referenced by this action definition
   */
  public List<IActionResource> getResources() {
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
  public IActionResource getResource(String privateResourceName) {
    IActionResource resource = null;
    for (IActionResource tmpResource : resources) {
      if (tmpResource.getName().equals(privateResourceName)) {
        resource = tmpResource;
      }
    }
    return resource;
  }
  
  /**
   * Returns all the inputs that are listed in the action inputs section of this action definition.
   * Inputs that have been assigned a constant value appear in the component definition section and will
   * not be returned by this method.
   * 
   * @return the inputs list in the action inputs section
   */
  public List<IActionInput> getVariableInputs() {
    List<IActionInput> variableInputs = new ArrayList<IActionInput>();
    for (IActionInput input : inputs) {
      if (!(input instanceof ActionInputConstant)) {
        variableInputs.add(input);
      }
    }
    return variableInputs;
  }
  
  protected List<ActionInputConstant> getConstantInputs() {
    List<ActionInputConstant> constantInputs = new ArrayList<ActionInputConstant>();
    for (IActionInput input : inputs) {
      if (!(input instanceof ActionInputConstant)) {
        constantInputs.add((ActionInputConstant)input);
      }
    }
    return constantInputs;
  }
  
  public List<IActionInput> getInputs() {
    return inputs;
  }
  
  public List<IActionInput> getInputs(IActionInputFilter actionInputFilter) {
    List<IActionInput> filteredInputs = new ArrayList<IActionInput>();
    for (IActionInput input : inputs) {
      if ((actionInputFilter == null) || actionInputFilter.accepts(input)) {
        filteredInputs.add(input);
      }
    }
    return filteredInputs;
  }
  
  /**
   * Returns the input in the action inputs section of this action definition that the specified name. 
   * Inputs that have been assigned a constant value appear in the component definition section and will
   * not be returned by this method.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
   * @return the input with the specified name or null if none exists.
   */
  public IActionInput getInputParam(String privateInputName) {
    IActionInput input = null;
    for (IActionInput tmpInput : inputs) {
      if (tmpInput.getName().equals(privateInputName)) {
        input = tmpInput;
        break;
      }
    }
    return input;
  }
  
  
  public IActionOutput addOutput(String privateParamName, String outputType) {
    IActionOutput output = getOutput(privateParamName);
    if (output == null) {
      output = new ActionOutput(this);
      output.setName(privateParamName);
      output.setType(outputType);
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
  public List<IActionOutput> getOutputs() {
    return outputs;
  }
  
  /**
   * Returns the output, with the specified name, listed in the action outputs section of this action definition.
   * 
   * @param privateParamName the name of the param as it is known by this action definition (the output element name).
   * @return the named output or null if none exists.
   */
  public IActionOutput getOutput(String privateParamName) {
    IActionOutput output = null;
    for (IActionOutput tmpOutput : outputs) {
      if (tmpOutput.getName().equals(privateParamName)) {
        output = tmpOutput;
        break;
      }
    }
    return output;
  }
  
  /**
   * Returns all the outputs that are listed in the action outputs section of this action definition that have the specified types. 
   * 
   * @param types data types of the desired outputs.
   * @return the outputs listed in the action outputs section of the specified type
   */
  public List<IActionOutput> getOutputs(String[] types) {
    List matchingOutputs = new ArrayList();
    if (types == null) {
      matchingOutputs.addAll(outputs);
    } else {
      for (int outIdx = 0; outIdx < outputs.size(); outIdx++) {
        for (int  typeIdx= 0; typeIdx < types.length; typeIdx++) {
          if (types[typeIdx].equals(outputs.get(outIdx).getType())) {
            matchingOutputs.add(outputs.get(outIdx));
            break;
          }
        }
      }
    }
    return matchingOutputs;
  }
  
  /**
   * Returns all the outputs that are listed in the action outputs section of this action definition that have the specified type. 
   * 
   * @param type data types of the desired outputs.
   * @return the outputs listed in the action outputs section of the specified type
   */
  public List<IActionOutput> getOutputs(String type) {
    return getOutputs(new String[]{type});
  }
  
  /**
   * @return the action definition description
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * Set the action definition description
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
  /**
   * @return the action control statement (if or loop) that contains this action definition or null if there is no parent control statement.
   */
  public IActionControlStatement getParent() {
    return parent;
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
   */
  public IActionSequenceDocument getDocument() {
    return parent.getActionSequence();
  }
  
      
  /**
   * Removes all inputs from this action definition
   */
  public void deleteAllInputs() {
    inputs.clear();
  }
  
  /**
   * Removes all output from this action definition
   */
  public void deleteAllOutputs() {
    outputs.clear();
  }
  
  /**
   * Removes all resources from this action definition
   */
  public void deleteAllResources() {
    resources.clear();
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
  private IActionInput setInputParam(String privateParamName, IActionInputVariable referencedVariable) {
    IActionInput actionInput = null;
    if (referencedVariable != null) {
      actionInput = setInputParam(privateParamName, referencedVariable.getVariableName(), referencedVariable.getType());
    } else {
      removeInput(privateParamName);
    }
    return actionInput;
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
  protected IActionOutput setOutput(String privateParamName, String publicParamName, String outputType) {
    IActionOutput actionOutput = null;
    if ((publicParamName == null) || (publicParamName.trim().length() == 0)) {
      removeOutput(privateParamName);
    } else {
      actionOutput = addOutput(privateParamName, outputType); 
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
    IActionOutput actionOutput = getOutput(privateName);
    if (actionOutput != null) {
      publicName = actionOutput.getPublicName();
    }
    return publicName;
  }
  
  public void setParent(IActionControlStatement parent) {
    this.parent = parent;
  }
  
  public void removeInput(String privateParamName) {
    for (IActionInput input : inputs) {
      if (input.getName().equals(privateParamName)) {
        inputs.remove(input);
        break;
      }
    }
  }
  
  public void removeOutput(String privateParamName) {
    for (IActionOutput output : outputs) {
      if (output.getName().equals(privateParamName)) {
        outputs.remove(output);
        break;
      }
    }
  }
  
  /**
   * Removes a resource from this action definition
   * @param privateResourceName the name of the resource to be removed.
   */
  public void removeResource(String privateResourceName) {
    for (IActionResource resource : resources) {
      if (resource.getName().equals(privateResourceName)) {
        resources.remove(resource);
        break;
      }
    }
  }
}
