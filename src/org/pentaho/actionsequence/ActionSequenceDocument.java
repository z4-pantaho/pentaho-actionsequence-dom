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
package org.pentaho.actionsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.pentaho.actionsequence.action.HelloWorldAction;


/**
 * A wrapper class for an action definition resource element.
 * @author Angelo Rodriguez
 *
 */
public class ActionSequenceDocument implements IActionSequenceDocument {
    

  List<IActionSequenceInput> inputs = new ArrayList<IActionSequenceInput>();
  List<IActionSequenceOutput> outputs = new ArrayList<IActionSequenceOutput>();
  List<IActionSequenceResource> resources = new ArrayList<IActionSequenceResource>();
  IActionLoop rootLoop;
  String author;
  String description;
  String help;
  String resultType;
  String icon;
  String version;
  String loggingLevel;
  String title;
  
  Metadata metadata = new Metadata();
  
  /**
   * @deprecated
   * This class is created to workaround architectual issues in the BI server. As soon as the issues
   * are addressed this class will no longer exist.
   */
  public class Metadata {
    String solutionName;
    String solutionPath;
    String documentName;
    public String getSolutionName() {
      return solutionName;
    }
    public void setSolutionName(String solutionName) {
      this.solutionName = solutionName;
    }
    public String getSolutionPath() {
      return solutionPath;
    }
    public void setSolutionPath(String solutionPath) {
      this.solutionPath = solutionPath;
    }
    public String getDocumentName() {
      return documentName;
    }
    public void setDocumentName(String documentName) {
      this.documentName = documentName;
    }
  }
  
  public ActionSequenceDocument() {
    rootLoop = new ActionLoop(this);
  }
  
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHelp() {
    return help;
  }

  public void setHelp(String help) {
    this.help = help;
  }

  public String getResultType() {
    return resultType;
  }

  public void setResultType(String resultType) {
    this.resultType = resultType;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getLoggingLevel() {
    return loggingLevel;
  }

  public void setLoggingLevel(String loggingLevel) {
    this.loggingLevel = loggingLevel;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Removes the named action sequence input from the action sequence
   * @param inputName the input name
   */
  public void deleteInput(String inputName) {
    IActionSequenceInput input = getInput(inputName);
    if (input != null) {
      inputs.remove(input);
    }
  }
  
  /**
   * @return the action sequence inputs of the specified type
   */
  public List<IActionSequenceInput> getInputs(String[] types) {
    List<IActionSequenceInput> matchingInputs = new ArrayList<IActionSequenceInput>();
    if (types == null) {
      matchingInputs.addAll(inputs);
    } else {
      ArrayList<String> typesList = new ArrayList<String>(Arrays.asList(types));
      for (IActionSequenceInput input : inputs) {
        if (typesList.contains(input.getType())) {
          matchingInputs.add(input);
        }
     }
    }
    return matchingInputs;
  }
  
  /**
   * @return all the inputs to the action sequence
   */
  public List<IActionSequenceInput> getInputs() {
    return inputs;
  }
  
  public void setInputs(IActionSequenceInput[] inputs) {
    this.inputs.clear();
    this.inputs.addAll(Arrays.asList(inputs));
  }
  
  /**
   * @param inputName the input name.
   * @return the input with the given name or null if it does not exist
   */
  public IActionSequenceInput getInput(String inputName) {
    IActionSequenceInput input = null;
    for (IActionSequenceInput actionSequenceInput : inputs) {
      if (actionSequenceInput.getName().equals(inputName)) {
        input = actionSequenceInput;
        break;
      }
    }
    return input;
  }
  
  /**
   * Adds a new input this action sequence. If the input already 
   * exists the type of the input is set to the specified type.
   * 
   * @param inputName the input name
   * @param inputType the input type
   * @return the action sequence input
   */
  public IActionSequenceInput createInput(String inputName, String inputType) {
    IActionSequenceInput input = getInput(inputName);
    if (input == null) {  
      input = new ActionSequenceInput(this);
      input.setName(inputName);
      input.setType(inputType);
      inputs.add(input);
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
    IActionSequenceOutput output = getOutput(outputName);
    if (output != null) {
      outputs.remove(output);
    }
  }
  
  /**
   * @return an array of all the outputs from this action sequence.
   */
  public List<IActionSequenceOutput> getOutputs() {
    return outputs;
  }
  
  public void setOutputs(IActionSequenceOutput[] outputs) {
    this.outputs.clear();
    this.outputs.addAll(Arrays.asList(outputs));
  }
  
  /**
   * @param outputName
   * @return the action sequence output with the given name or null if 
   * it does not exist.
   */
  public IActionSequenceOutput getOutput(String outputName) {
    IActionSequenceOutput output = null;
    for (IActionSequenceOutput actionSequenceOutput : outputs) {
      if (actionSequenceOutput.getName().equals(outputName)) {
        output = actionSequenceOutput;
        break;
      }
    }
    return output;
  }
  
  /**
   * Adds a new output to this action sequence. If the output already 
   * exists the type of the output is set to the specified type.
   * 
   * @param outputName the input name
   * @param outputType the input type
   * @return the action sequence output
   */
  public IActionSequenceOutput createOutput(String outputName, String outputType) {
    IActionSequenceOutput output = getOutput(outputName);
    if (output == null) {  
      output = new ActionSequenceOutput(this);
      output.setName(outputName);
      output.setType(outputType);
      outputs.add(output);
    } else {
      output.setType(outputType);
    }
    return output;
  }
  
  public IActionSequenceOutput createOutput(IActionOutput actionOutput, String outputType) {
    return createOutput(actionOutput.getName(), outputType);
  }
  
  /**
   * Removes the named action sequence resource from the action sequence
   * @param resourceName the resource name
   */
  public void deleteResource(String resourceName) {
    IActionSequenceResource resource = getResource(resourceName);
    if (resource != null) {
      resources.remove(resource);
    }
  }
  
  /**
   * @return all the action sequence resources
   */
  public List<IActionSequenceResource> getResources() {
    return resources;
  }
  
  public void setResources(IActionSequenceResource[] resources) {
    this.resources.clear();
    this.resources.addAll(Arrays.asList(resources));
  }
  
  /**
   * @param resourceName the resource name.
   * @return the resource with the given name or null if it does not exist
   */
  public IActionSequenceResource getResource(String resourceName) {
    IActionSequenceResource resource = null;
    for (IActionSequenceResource actionSequenceResource : resources) {
      if (actionSequenceResource.getName().equals(resourceName)) {
        resource = actionSequenceResource;
        break;
      }
    }
    return resource;
  }
  
  /**
   * Adds a new resource to this action sequence. 
   * @param resourceName the resource name
   * @param resourceFileType the resource file type
   * @param filePath the resource file path
   * @param mimeType the resource mime type
   * @return the action sequence resource
   */
  public IActionSequenceResource setResourceUri(String resourceName, String uri, String mimeType) {
    IActionSequenceResource docResource = getResource(resourceName);
    if (uri == null) {
      resources.remove(docResource);
    } else {
      if (docResource == null) {
        docResource = new ActionSequenceResource(this);
        docResource.setName(resourceName);
        resources.add(docResource);
      }
      docResource.setUri(uri);
      docResource.setMimeType(mimeType);
    }
    return docResource;
  }
  
  public IActionLoop getRootLoop() {
    return rootLoop;
  }
  
  public void setRootLoop(IActionLoop rootLoop) {
    this.rootLoop = rootLoop;
  }
  
  /**
   * Create a new loop at the top level of the action sequence
   * @param loopOn the name of the parameter to loop on
   * @param index the index of where to insert the loop
   * @return the create action loop
   */
  public IActionLoop addLoop(String loopOn, int index) {
    return getRootLoop().addLoop(loopOn, index);
  }
  
  /**
   * Adds an action loop to the end of this documents list of 
   * children. 
   * @param loopOn the name of the paremater to loop on
   */
  public IActionLoop addLoop(String loopOn) {
    return getRootLoop().addLoop(loopOn);
  }
  
  /**
   * Adds an if statement to the end of this documents list of 
   * children. 
   * @param condition the if condition
   */
  public IActionIfStatement addIf(String condition) {
    return getRootLoop().addIf(condition);
  }
  
  /**
   * Create a new if statment at the top level of the action sequence
   * @param condition the if condition
   * @param index the index of where to insert the if statment
   * @return the createe if statment
   */
  public IActionIfStatement addIf(String condition, int index) {
    return getRootLoop().addIf(condition, index);
  }
  
  /**
   * @return all the top level action definitions and control statments in
   * the action sequence.
   */
  public List<IActionSequenceExecutableStatement> getExecutableChildren() {
    return getRootLoop().getChildren();
  }
  
  /**
   * Adds a new child action definition to the end of this documents
   * list of children.
   * @param componentName the name of the component that processes
   * the action definition
   * @return the newly created action definition
   * @throws IllegalAccessException 
   * @throws InstantiationException 
   */
  public IActionDefinition addAction(Class actionDefinitionClass) {
    return getRootLoop().addAction(actionDefinitionClass);
  }
  
  /** 
   * Creates a new action definition which conforms to the specifications of this template.
   * @param parent the parent of the new detail
   * @param index the index where the new element should be created
   * @throws IllegalAccessException 
   * @throws InstantiationException 
   */
  public IActionDefinition addAction(Class actionDefinitionClass, int index) {
    return getRootLoop().addAction(actionDefinitionClass, index);
  }
  
  /**
   * @deprecated
   * No replacement. This method was created to workaround architectual issues in the BI server. As soon as the issues
   * are addressed this method will no longer exist.
   */
  public Metadata getMetadata() {
    return metadata;
  }
  
  private List<IActionResource> getReferencesTo(IActionSequenceExecutableStatement executableStatement, IActionSequenceResource actionSequenceResource) {
    List<IActionResource> referers = new ArrayList<IActionResource>();
    if (executableStatement instanceof IActionControlStatement) {
      IActionControlStatement controlStatement = (IActionControlStatement)executableStatement;
      for (IActionSequenceExecutableStatement child : controlStatement.getChildren()) {
        referers.addAll(getReferencesTo(child, actionSequenceResource));
      }
    } else {
      ActionDefinition actionDefinition = (ActionDefinition)executableStatement;
      for (IActionResource resource : actionDefinition.getResources()) {
        if (resource.getPublicName().equals(actionSequenceResource.getName())) {
          referers.add(resource);
        }
      }
    }
    return referers;
  }
  public List<IActionResource> getReferencesTo(IActionSequenceResource actionSequenceResource) {
    return getReferencesTo(actionSequenceResource);
  }
  
  public static void main(String[] args) throws Exception
  {
    ActionSequenceDocument helloWorldActionSequence = createClientSideActionSequence();
    String xml = generateActionSequenceXml(helloWorldActionSequence);
  }
  
  static ActionSequenceDocument createClientSideActionSequence() {
    ActionSequenceDocument actionSequenceDocument = new ActionSequenceDocument();    
    HelloWorldAction helloWorldAction= new HelloWorldAction();
    helloWorldAction.setQuote(new ActionInputConstant("This is a test"));
    actionSequenceDocument.getRootLoop().add(helloWorldAction);
    return actionSequenceDocument;
  }
  
  static String generateActionSequenceXml(ActionSequenceDocument doc) {
    org.pentaho.actionsequence.dom.ActionSequenceDocument xmlDoc = new org.pentaho.actionsequence.dom.ActionSequenceDocument();
    org.pentaho.actionsequence.dom.actions.HelloWorldAction xmlHelloWorld = new org.pentaho.actionsequence.dom.actions.HelloWorldAction();
    xmlDoc.getRootLoop().add(xmlHelloWorld);
    
    HelloWorldAction helloWorld = (HelloWorldAction)doc.getExecutableChildren().get(0);
    String message = ((ActionInputConstant)helloWorld.getQuote()).getStringValue();
    xmlHelloWorld.setQuote(new org.pentaho.actionsequence.dom.ActionInputConstant(message, null));
    return xmlDoc.toString();
  }
}
