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
 *
 * @created Jun 17, 2005 
 * @author James Dixon
 * 
 */

package org.pentaho.actionsequence.dom;

import java.net.URI;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.actions.IActionInputFilter;
import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

/**
 * The ActionDefinition represents the definition and metadata for a single
 * action execution, which is equivalent to one execution of any given
 * Component.
 * <p>
 * The ActionDefinition is derived from the solution's action sequence document.
 * One ActionDefinition is handed to the appropriate Component, and provides all
 * the necessary inputs, outputs and resources for that Component to execute.
 */
public interface IActionDefinition extends IActionSequenceExecutableStatement {

	  /**
	   * Sets the URI and mime type of the specified resource. If the resource does not exist it is created. If the URI is null the named resource is deleted.
	   * 
	   * @param privateParamName the name of the resource as it is known by the component processing this action definition.
	   * @param URI the resource URI. May be null if you wish to delete the resource.
	   * @param mimeType the resource mime type. Ignored if the resource URI is null.
	   * @return the modified/created resource, or null if the resource is deleted.
	   */
	  public IActionResource setResourceUri(String privateResourceName, URI uri, String mimeType);
	 
	  /**
	   * Returns the component name of this action definition.
	   * 
	   * @return the action definition's component name.
	   */
	  public String getComponentName();
	  
	  /**
	   * Sets the component name of this action definition.
	   * 
	   * @param name the component name.
	   */
	  public void setComponentName(String name);
	  
	  /**
	   * Adds a new input parameter to this action definition. If the input already 
	   * exists the type of the input is set to the specified type.
	   * 
	   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
	   * @param inputType the input type
	   * @return the action input
	   */
	  public IActionInput addInput(String privateParamName, String inputType);
	  
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
	  public void setInputValue(String privateParamName, String value);
	  
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
	  public IActionInput setInputParam(String privateParamName, String referencedVariableName, String type);
	  
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
	  public void setInputValue(String privateParamName, String value, boolean useCData);
	  
	  /**
	   * Creates an input resource with the given name. No operation is performed if the resource already exists.
	   * 
	   * @param privateResourceName the name of the resource as it is known by this action definition (the element name).
	   * @return the newly created or existing resource.
	   */
	  public IActionResource addResource(String privateResourceName);
	  
	  /**
	   * Creates an input resource with the given name. The resource will reference the specified action sequence resource.
	   * No operation is performed if the resource already exists.
	   * 
	   * @param privateResourceName the name of the resource as it is known by this action definition (the element name).
	   * @return the newly created or existing resource.
	   */
	  public IActionResource addResource(String privateResourceName, String referencedActionSequenceResource);
	  
	  /**
	   * @return the resources referenced by this action definition
	   */
	  public IActionResource[] getResources();
	  
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
	  public IActionResource getResource(String privateResourceName);
	  
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
	  public IActionResource getResource(String privateResourceName, boolean includeImplicitResource);
	  
	  /**
	   * Returns all the inputs that are listed in the action inputs section of this action definition.
	   * Inputs that have been assigned a constant value appear in the component definition section and will
	   * not be returned by this method.
	   * 
	   * @return the inputs list in the action inputs section
	   */
	  public IActionInput[] getInputs();
	  
	  /**
	   * Returns the input in the action inputs section of this action definition that the specified name. 
	   * Inputs that have been assigned a constant value appear in the component definition section and will
	   * not be returned by this method.
	   * 
	   * @param privateParamName the name of the param as it is known by this action definition (the input element name).
	   * @return the input with the specified name or null if none exists.
	   */
	  public IActionInput getInput(String privateInputName);
	  
	  /**
	   * Returns all the inputs that are listed in the action inputs section of this action definition that are accepted
	   * by the given filter.
	   * 
	   * @param actionInputFilter the input filter
	   * @return the inputs of the specified types listed in the action inputs section of this action definition
	   */
	  public IActionInput[] getInputs(IActionInputFilter actionInputFilter);
	  
	  public IActionOutput addOutput(String privateParamName, String outputType);
	  
	  /**
	   * Returns all the outputs that are listed in the action outputs section of this action definition.
	   * 
	   * @return the outputs list in the action input name
	   */
	  public IActionOutput[] getOutputs();
	  
	  /**
	   * Returns the output, with the specified name, listed in the action outputs section of this action definition.
	   * 
	   * @param privateParamName the name of the param as it is known by this action definition (the output element name).
	   * @return the named output or null if none exists.
	   */
	  public IActionOutput getOutput(String privateParamName);
	  
	  /**
	   * Returns all the outputs that are listed in the action outputs section of this action definition that have the specified types. 
	   * 
	   * @param types data types of the desired outputs.
	   * @return the outputs listed in the action outputs section of the specified type
	   */
	  public IActionOutput[] getOutputs(String[] types);
	  
	  /**
	   * Returns all the outputs that are listed in the action outputs section of this action definition that have the specified type. 
	   * 
	   * @param type data types of the desired outputs.
	   * @return the outputs listed in the action outputs section of the specified type
	   */
	  public IActionOutput[] getOutputs(String type);
	  
	  /**
	   * @return the action definition description
	   */
	  public String getDescription();
	  
	  /**
	   * Set the action definition description
	   * @param description the description
	   */
	  public void setDescription(String description);
	  
	  public boolean equals(Object arg0);
	  
	  /**
	   * @return the action control statement (if or loop) that contains this action definition or null if there is no parent control statement.
	   */
	  public IActionControlStatement getParent();
	  
	  /* (non-Javadoc)
	   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getElement()
	   */
	  public Element getElement();
	  	  
	  /* (non-Javadoc)
	   * @see org.pentaho.designstudio.dom.IActionSequenceElement#delete()
	   */
	  public void delete();
	  
	  /* (non-Javadoc)
	   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
	   */
	  public IActionSequenceDocument getDocument();
	  
	  /**
	   * The value of the component definition element at the specified XPath
	   * @param compDefXpath the XPath of the element relative to the component definition element.
	   * @return the value of the element or null if the element does not exist
	   */
	  public String getComponentDefinitionValue(String compDefXpath);
	  
	  /**
	   * The values of the component definition elements at the specified XPath
	   * @param compDefXpath the XPath of the elements relative to the component definition element.
	   * @return the values of the elements
	   */
	  public String[] getComponentDefinitionValues(String compDefXpath);
	  
	  /**
	   * The component definition elements at the specified XPath
	   * @param compDefXpath the XPath of the elements relative to the component definition element.
	   * @return the elements
	   */
	  public Element[] getComponentDefElements(String compDefXpath);

	  /**
	   * The component definition element at the specified XPath
	   * @param compDefXpath the XPath of the elements relative to the component definition element.
	   * @return the element or null if the element does not exist.
	   */
	  public Element getComponentDefElement(String compDefXpath);

	  /**
	   * Sets the value of the component definition element at the specified XPath.
	   * @param compDefXpath the XPath of the element relative to the component definition element.
	   * @param value the value to be assigned to the element
	   */
	  public void setComponentDefinition(String compDefXpath, String value);
	  
	  /**
	   * Sets the value of the component definition elements at the specified XPath.
	   * @param compDefXpath the XPath of the element relative to the component definition element.
	   * @param values the value to be assigned to the elements
	   */
	  public void setComponentDefinition(String compDefXpath, String[] values);
	  
	  /**
	   * Sets the attribute value of the component definition element at the specified XPath.
	   * @param compDefXpath the XPath of the element relative to the component definition element.
	   * @param attributeName the attribute name
	   * @param value the value to be assigned to the attribute
	   */
	  public void setComponentDefinitionAttribute(String compDefXpath, String attributeName, String value);
	  
	  /**
	   * Sets the value of the component definition element at the specified XPath.
	   * @param compDefXpath the XPath of the element relative to the component definition element.
	   * @param value the value to be assigned to the element
	   * @param useCData whether a CDATA node should be used to save the value
	   */
	  public void setComponentDefinition(String compDefXpath, String value, boolean useCData);
	  
	  /**
	   * Removes an input from this action definition
	   * @param inputName the name of the input to be removed.
	   */
	  public void removeInput(String privateParamName);
	  
	  /**
	   * Renames the named action input. No operation is performed if the input does not exist. Any
	   * inputs defined in the component definition section that refer to the input using the
	   * {oldName} parameter reference are also modified to refer to {newName}. 
	   * @param oldName the name of the input to be renamed.
	   * @param newName the new input name.
	   */
	  public void renameInput(String oldName, String newName);
	  
	  /**
	   * Removes a resource from this action definition
	   * @param privateResourceName the name of the resource to be removed.
	   */
	  public void removeResource(String privateResourceName);
	  
	  /**
	   * Removes all inputs from this action definition
	   */
	  public void deleteAllInputs();
	  
	  /**
	   * Removes all output from this action definition
	   */
	  public void deleteAllOutputs();
	  
	  /**
	   * Removes all resources from this action definition
	   */
	  public void deleteAllResources();
	  
	  /**
	   * Removes all component definition elements at the specified XPath
	   * 
	   * @param compDefXpath the XPath of the elements relative to the component definition element.
	   */
	  public void removeComponentDefinitions(String compDefXpath);
	  
	  /**
	   * Removes all component definition child elements from this action definition. 
	   */
	  public void removeComponentDefinitions();
	  	  
	  /**
	   * Moves the given input to the specified position in the action inputs list.
	   * @param actionInput the input to be moved.
	   * @param index the new input position
	   */
	  public void setInputIndex(ActionInput actionInput, int index);	  
	  
	  /**
	   * Returns all the private input names that are reserved for use by this action definition.
	   * Inputs with these names are used for a particular purpose by this action definition.
	   * 
	   * @return the reserved input names
	   */
	  public String[] getReservedInputNames();
	  
	  /**
	   * Returns all the private outputs names that are reserved for use by this action definition.
	   * Outputs with these names are used for a particular purpose by this action definition.
	   * 
	   * @return the reserved output names
	   */
	  public String[] getReservedOutputNames();

	  /**
	   * Returns all the private resource names that are reserved for use by this action definition.
	   * Resources with these names are used for a particular purpose by this action definition.
	   * 
	   * @return the reserved output names
	   */
	  public String[] getReservedResourceNames();
	  
	  /**
	   * This is the default implementation for validating an action definition. By default no errors are returned.
	   * Subclasses of this class should override this method to validate the necessary inputs, outputs, and resources.
	   * 
	   * @return the validation errors that were detected.
	   */
	  public IActionSequenceValidationError[] validate();
	  	  
	  public IActionParameterMgr getActionParameterMgr();

}
