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

import java.util.List;


/**
 * A wrapper class for an action definition resource element.
 * @author Angelo Rodriguez
 *
 */
public interface IActionSequenceDocument {


  public static final String REQUEST_INPUT_SOURCE = "request"; //$NON-NLS-1$
  public static final String STRING_TYPE = "string"; //$NON-NLS-1$
  
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
  public List<IActionSequenceInput> getInputs(String[] types);
  
  /**
   * @return all the inputs to the action sequence
   */
  public List<IActionSequenceInput> getInputs();
  
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
  public List<IActionSequenceOutput> getOutputs();
  
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
  public List<IActionSequenceResource> getResources();
  
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
  public IActionSequenceResource setResourceUri(String resourceName, String uri, String mimeType);
  
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
  public List<IActionSequenceExecutableStatement> getExecutableChildren();
  
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
  
  public String toString();
  
  public List<IActionResource> getReferencesTo(IActionSequenceResource actionSequenceResource);
}
