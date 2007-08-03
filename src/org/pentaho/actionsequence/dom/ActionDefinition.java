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

import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.pentaho.actionsequence.dom.actions.AreaChartAction;
import org.pentaho.actionsequence.dom.actions.BarChartAction;
import org.pentaho.actionsequence.dom.actions.BirtReportAction;
import org.pentaho.actionsequence.dom.actions.ContentOutputAction;
import org.pentaho.actionsequence.dom.actions.CopyParamAction;
import org.pentaho.actionsequence.dom.actions.DeleteScheduledJobAction;
import org.pentaho.actionsequence.dom.actions.EmailAction;
import org.pentaho.actionsequence.dom.actions.FormatMsgAction;
import org.pentaho.actionsequence.dom.actions.HelloWorldAction;
import org.pentaho.actionsequence.dom.actions.JFreeReportAction;
import org.pentaho.actionsequence.dom.actions.JFreeReportGenAction;
import org.pentaho.actionsequence.dom.actions.JMSAction;
import org.pentaho.actionsequence.dom.actions.JasperReportAction;
import org.pentaho.actionsequence.dom.actions.JavascriptAction;
import org.pentaho.actionsequence.dom.actions.KettleJobAction;
import org.pentaho.actionsequence.dom.actions.KettleTransformAction;
import org.pentaho.actionsequence.dom.actions.LineChartAction;
import org.pentaho.actionsequence.dom.actions.ListSchedJobsAction;
import org.pentaho.actionsequence.dom.actions.MQLAction;
import org.pentaho.actionsequence.dom.actions.MdxConnectionAction;
import org.pentaho.actionsequence.dom.actions.MdxQueryAction;
import org.pentaho.actionsequence.dom.actions.PieChartAction;
import org.pentaho.actionsequence.dom.actions.PivotViewAction;
import org.pentaho.actionsequence.dom.actions.PrintMapValsAction;
import org.pentaho.actionsequence.dom.actions.PrintParamAction;
import org.pentaho.actionsequence.dom.actions.PrinterAction;
import org.pentaho.actionsequence.dom.actions.ReceiptAuditAction;
import org.pentaho.actionsequence.dom.actions.ResultSetCompareAction;
import org.pentaho.actionsequence.dom.actions.ResumeScheduledJobAction;
import org.pentaho.actionsequence.dom.actions.ResumeSchedulerAction;
import org.pentaho.actionsequence.dom.actions.SchedulerStatusAction;
import org.pentaho.actionsequence.dom.actions.SecureFilterAction;
import org.pentaho.actionsequence.dom.actions.SharkWorkflowAction;
import org.pentaho.actionsequence.dom.actions.SqlConnectionAction;
import org.pentaho.actionsequence.dom.actions.SqlExecuteAction;
import org.pentaho.actionsequence.dom.actions.SqlQueryAction;
import org.pentaho.actionsequence.dom.actions.StartScheduledJobAction;
import org.pentaho.actionsequence.dom.actions.SubActionAction;
import org.pentaho.actionsequence.dom.actions.SuspendScheduledJobAction;
import org.pentaho.actionsequence.dom.actions.SuspendSchedulerAction;
import org.pentaho.actionsequence.dom.actions.TemplateMsgAction;
import org.pentaho.actionsequence.dom.actions.XQueryAction;
import org.pentaho.actionsequence.dom.messages.Messages;

/**
 * A wrapper for an action definition element within an action sequence.
 * @author Angelo
 */
public class ActionDefinition implements IActionSequenceExecutableStatement {
  
  public static final int CUSTOM_ACTION_ID = 0;
  public static final int JFREE_REPORT_ACTION_ID = 1;
  public static final int JASPER_REPORT_ACTION_ID = 2;
  public static final int BIRT_REPORT_ACTION_ID = 3;
  public static final int BAR_CHART_ACTION_ID = 4;
  public static final int PIE_CHART_ACTION_ID = 5;
  public static final int LINE_CHART_ACTION_ID = 6;
  public static final int AREA_CHART_ACTION_ID = 7;
  public static final int SQL_QUERY_ACTION_ID = 8;
  public static final int MDX_QUERY_ACTION_ID = 9;
  public static final int XQUERY_ACTION_ID = 10;
  public static final int PIVOT_VIEW_ACTION_ID = 11;
  public static final int RESULT_SET_COMPARE_ACTION_ID = 12;
  public static final int LIST_SCHED_JOBS_ACTION_ID = 13;
  public static final int RESUME_SCHEDULER_ACTION_ID = 14;
  public static final int SCHEDULER_STATUS_ACTION_ID = 15;
  public static final int SUSPEND_SCHEDULER_ACTION_ID = 16;
  public static final int SUSPEND_SCHED_JOB_ACTION_ID = 17;
  public static final int RESUME_SCHED_JOB_ACTION_ID = 18;
  public static final int START_SCHED_JOB_ACTION_ID = 19;
  public static final int DELETE_SCHED_JOB_ACTION_ID = 20;
  public static final int KETTLE_TRANSFORM_ACTION_ID = 21;
  public static final int KETTLE_JOB_ACTION_ID = 22;
  public static final int FORMAT_MSG_ACTION_ID = 23;
  public static final int COPY_PARAM_ACTION_ID = 24;
  public static final int CONTENT_OUTPUT_ACTION_ID = 25;
  public static final int PRINT_PARAM_ACTION_ID = 26;
  public static final int PRINT_MAP_VALS_ACTION_ID = 27;
  public static final int HELLO_WORLD_ACTION_ID = 28;
  public static final int JMS_ACTION_ID = 29;
  public static final int JFREE_REPORT_GEN_ACTION_ID = 30;
  public static final int EMAIL_ACTION_ID = 31;
  public static final int JAVASCRIPT_ACTION_ID = 32;
  public static final int PRINTER_ACTION_ID = 33;
  public static final int SHARK_WORKFLOW_ACTION_ID = 34;
  public static final int RECEIPT_AUDIT_ACTION_ID = 35;
  public static final int TEMPLATE_MSG_ACTION_ID = 36;
  public static final int SECURE_FILTER_ACTION_ID = 37;
  public static final int SUB_ACTION_ACTION_ID = 38;
  public static final int SQL_EXECUTE_ACTION_ID = 39;
  public static final int MQL_ACTION_ID = 40;
  public static final int SQL_CONNECTION_ACTION_ID = 41;
  public static final int MDX_CONNECTION_ACTION_ID = 42;
  
  private static final ActionSequenceValidationError[] EMPTY_ARRAY = new ActionSequenceValidationError[0];
  
  public static final HashMap ACTION_ID_MAP = new HashMap();
  
  
  static {
    ACTION_ID_MAP.put(new Integer(JFREE_REPORT_ACTION_ID), new JFreeReportAction());
    ACTION_ID_MAP.put(new Integer(JASPER_REPORT_ACTION_ID), new JasperReportAction());
    ACTION_ID_MAP.put(new Integer(BIRT_REPORT_ACTION_ID), new BirtReportAction());
    ACTION_ID_MAP.put(new Integer(BAR_CHART_ACTION_ID), new BarChartAction());
    ACTION_ID_MAP.put(new Integer(PIE_CHART_ACTION_ID), new PieChartAction());
    ACTION_ID_MAP.put(new Integer(LINE_CHART_ACTION_ID), new LineChartAction());
    ACTION_ID_MAP.put(new Integer(AREA_CHART_ACTION_ID), new AreaChartAction());
    ACTION_ID_MAP.put(new Integer(SQL_QUERY_ACTION_ID), new SqlQueryAction());
    ACTION_ID_MAP.put(new Integer(SQL_EXECUTE_ACTION_ID), new SqlExecuteAction());
    ACTION_ID_MAP.put(new Integer(MDX_QUERY_ACTION_ID), new MdxQueryAction());
    ACTION_ID_MAP.put(new Integer(XQUERY_ACTION_ID), new XQueryAction());
    ACTION_ID_MAP.put(new Integer(PIVOT_VIEW_ACTION_ID), new PivotViewAction());
    ACTION_ID_MAP.put(new Integer(RESULT_SET_COMPARE_ACTION_ID), new ResultSetCompareAction());
    ACTION_ID_MAP.put(new Integer(LIST_SCHED_JOBS_ACTION_ID), new ListSchedJobsAction());
    ACTION_ID_MAP.put(new Integer(RESUME_SCHEDULER_ACTION_ID), new ResumeSchedulerAction());
    ACTION_ID_MAP.put(new Integer(SCHEDULER_STATUS_ACTION_ID), new SchedulerStatusAction());
    ACTION_ID_MAP.put(new Integer(SUSPEND_SCHEDULER_ACTION_ID), new SuspendSchedulerAction());
    ACTION_ID_MAP.put(new Integer(SUSPEND_SCHED_JOB_ACTION_ID), new SuspendScheduledJobAction());
    ACTION_ID_MAP.put(new Integer(RESUME_SCHED_JOB_ACTION_ID), new ResumeScheduledJobAction());
    ACTION_ID_MAP.put(new Integer(START_SCHED_JOB_ACTION_ID), new StartScheduledJobAction());
    ACTION_ID_MAP.put(new Integer(DELETE_SCHED_JOB_ACTION_ID), new DeleteScheduledJobAction());
    ACTION_ID_MAP.put(new Integer(KETTLE_TRANSFORM_ACTION_ID), new KettleTransformAction());
    ACTION_ID_MAP.put(new Integer(KETTLE_JOB_ACTION_ID), new KettleJobAction());
    ACTION_ID_MAP.put(new Integer(FORMAT_MSG_ACTION_ID), new FormatMsgAction());
    ACTION_ID_MAP.put(new Integer(COPY_PARAM_ACTION_ID), new CopyParamAction());
    ACTION_ID_MAP.put(new Integer(CONTENT_OUTPUT_ACTION_ID), new ContentOutputAction());
    ACTION_ID_MAP.put(new Integer(PRINT_PARAM_ACTION_ID), new PrintParamAction());
    ACTION_ID_MAP.put(new Integer(PRINT_MAP_VALS_ACTION_ID), new PrintMapValsAction());
    ACTION_ID_MAP.put(new Integer(HELLO_WORLD_ACTION_ID), new HelloWorldAction());
    ACTION_ID_MAP.put(new Integer(MQL_ACTION_ID), new MQLAction());
    ACTION_ID_MAP.put(new Integer(JMS_ACTION_ID), new JMSAction());
    ACTION_ID_MAP.put(new Integer(JFREE_REPORT_GEN_ACTION_ID), new JFreeReportGenAction());
    ACTION_ID_MAP.put(new Integer(EMAIL_ACTION_ID), new EmailAction());
    ACTION_ID_MAP.put(new Integer(JAVASCRIPT_ACTION_ID), new JavascriptAction());
    ACTION_ID_MAP.put(new Integer(PRINTER_ACTION_ID), new PrinterAction());
    ACTION_ID_MAP.put(new Integer(SHARK_WORKFLOW_ACTION_ID), new SharkWorkflowAction());
    ACTION_ID_MAP.put(new Integer(RECEIPT_AUDIT_ACTION_ID), new ReceiptAuditAction());
    ACTION_ID_MAP.put(new Integer(TEMPLATE_MSG_ACTION_ID), new TemplateMsgAction());
    ACTION_ID_MAP.put(new Integer(SECURE_FILTER_ACTION_ID), new SecureFilterAction());
    ACTION_ID_MAP.put(new Integer(SUB_ACTION_ACTION_ID), new SubActionAction());
    ACTION_ID_MAP.put(new Integer(SQL_CONNECTION_ACTION_ID), new SqlConnectionAction());
    ACTION_ID_MAP.put(new Integer(MDX_CONNECTION_ACTION_ID), new MdxConnectionAction());
  };
  
  Element actionDefElement;
  private static final String[] EXPECTED_INPUTS = new String[0];
  private static final String[] EXPECTED_OUTPUTS = new String[0];
  private static final String[] EXPECTED_RESOURCES= new String[0];
  
  /**
   * @param actionDefElement the wrapped action definition element
   */
  protected ActionDefinition(Element actionDefElement) {
    super();
    this.actionDefElement = actionDefElement;
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
  
  public static ActionDefinition createAction(int actionId) {
    ActionDefinition newActionDef = null;
    ActionDefinition actionDefinition = (ActionDefinition)ACTION_ID_MAP.get(new Integer(actionId));
    if (actionDefinition != null) {
      try {
        newActionDef = (ActionDefinition)actionDefinition.getClass().newInstance();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (actionId == CUSTOM_ACTION_ID){
      newActionDef = new ActionDefinition(Messages.getString("ActionDefinition.ENTER_CLASS_NAME")); //$NON-NLS-1$
      newActionDef.setDescription(Messages.getString("ActionDefinition.CUSTOM_ACTION_TITLE")); //$NON-NLS-1$
    }
    return newActionDef;
  }
  
  public static ActionDefinition instance(Element actionDefinitionElement) {
    ActionDefinition actionDefinition = null;
    for (Iterator iter = ACTION_ID_MAP.entrySet().iterator(); iter.hasNext() && (actionDefinition == null); ) {
      Map.Entry entry = (Map.Entry)iter.next();
      ActionDefinition tempActionDef = (ActionDefinition)entry.getValue();
      if (tempActionDef.accepts(actionDefinitionElement)) {
        try {
          Constructor constructor = tempActionDef.getClass().getDeclaredConstructor(new Class[]{Element.class});
          actionDefinition = (ActionDefinition)constructor.newInstance(new Object[]{actionDefinitionElement});
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    if (actionDefinition == null) {
      actionDefinition = new ActionDefinition(actionDefinitionElement);
    }
    return actionDefinition;
  }
  
  protected void initNewActionDefinition(){
    
  };

  protected boolean accepts(Element element) {
    boolean accepts = false;
    
    if (element.getName().equals(ActionSequenceDocument.ACTION_DEFINITION_NAME)) {
      String elementComponentName = getComponentName(element);
      int index = elementComponentName.lastIndexOf("."); //$NON-NLS-1$
      if ((index >= 0) && (index < elementComponentName.length() - 1)) {
        elementComponentName = elementComponentName.substring(index + 1);
      }
      
      String thisComponentName = getComponentName();
      index = thisComponentName.lastIndexOf("."); //$NON-NLS-1$
      if ((index >= 0) && (index < thisComponentName.length() - 1)) {
        thisComponentName = thisComponentName.substring(index + 1);
      }
      
      accepts = thisComponentName.equals(elementComponentName);
      
    }
    return accepts;
  }
  
  protected String getComponentName(Element element) {
    Element componentClassElement = element.element(ActionSequenceDocument.COMPONENT_NAME);
    return componentClassElement == null ? null : componentClassElement.getText();
  }
  /**
   * @return return the name of the component that processes the 
   * action definition
   */
  public String getComponentName() {
    return getComponentName(actionDefElement);
  }
  
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
   * Adds a new input this action definition. If the input already 
   * exists the type of the input is set to the specified type.
   * 
   * @param inputName the input name
   * @param inputType the input type
   * @return the action input
   */
  public ActionInput addInputParam(String inputName, String inputType) {
    ActionInput input = getInputParam(inputName);
    Element inputElement;
    if (input == null) {
      Element[] componentDefs = getComponentDefElements(inputName);
      for (int i = 0; i < componentDefs.length; i++) {
        componentDefs[i].detach();
      }
      inputElement = DocumentHelper.makeElement(actionDefElement, ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + inputName); //$NON-NLS-1$
      inputElement.addAttribute(ActionSequenceResource.TYPE_NAME, inputType);
      input = new ActionInput(inputElement);
      ActionSequenceDocument.fireIoAdded(input);
    } else {
      input.setType(inputType);
    }
    return input;
  }
  
  public void setInputValue(String inputName, String value) {
    setInputValue(inputName, value, true);
  }
  
  public ActionInput setInputParam(String inputName, String paramName) {
    ActionInput actionInput = null;
    if (paramName == null) {
      actionInput = getInputParam(inputName);
      if (actionInput != null) {
        actionInput.delete();
      }     
    } else {
      IActionVariable mappedElement = null;
      IActionVariable[] availInputs = getAvailInputVariables();
      
      for (int i = 0; i < availInputs.length; i++) {
        if (availInputs[i].getVariableName().equals(paramName)) {
          mappedElement = availInputs[i];
          break;
        }
      }
      String type = (mappedElement != null ? mappedElement.getType() : ActionSequenceDocument.STRING_TYPE);
      actionInput = addInputParam(inputName, type);
      actionInput.setMapping(paramName);
    }
    return actionInput;
  }
  
  public void setInputValue(String inputName, String value, boolean useCData) {
    removeInputParam(inputName);
    if (value == null) {
      removeComponentDefinitions(inputName);
    } else {
      setComponentDefinition(inputName, value, useCData);
    }
  }
  
  public String getInputValue(String inputName) {
    return getComponentDefinitionValue(inputName);
  }
  
  public ActionResource addResourceParam(String resourceName) {
    return addResourceParam(resourceName, null);
  }
  
  /**
   * Adds a new resource to this action definition. If a resource of
   * the given name already exists, no operation is performed.
   * 
   * @param resourceName the resource name
   * @return the action resource
   */
  public ActionResource addResourceParam(String resourceName, String mapping) {
    ActionResource resource = getResourceParam(resourceName);
    Element resourceElement;
    if (resource == null) {
      resourceElement = DocumentHelper.makeElement(actionDefElement, ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + resourceName); //$NON-NLS-1$
      resourceElement.addAttribute(ActionSequenceResource.TYPE_NAME, ActionSequenceDocument.RESOURCE_TYPE);
      resource = new ActionResource(resourceElement);
      if ((mapping != null) && (mapping.trim().length() > 0)) {
        resource.setMapping(mapping);
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
      resources[index++] = new ActionResource((Element)iter.next());
    }
    return resources;
  }
  
  /**
   * @param resourceName the resource name
   * @return the action definition resource with the given name or null if none exists.
   */
  public ActionResource getResourceParam(String resourceName) {
    Element inputElement = (Element)actionDefElement.selectSingleNode(ActionSequenceDocument.ACTION_RESOURCES_NAME + "/" + resourceName); //$NON-NLS-1$
    return inputElement == null ? null : new ActionResource(inputElement);
  }
  
  public ActionResource[] getResourceParams() {
    List resourcesList =  actionDefElement.selectNodes(ActionSequenceDocument.ACTION_RESOURCES_NAME + "/*"); //$NON-NLS-1$
    ActionResource[] resources = new ActionResource[resourcesList.size()];
    int index = 0;
    for (Iterator iter = resourcesList.iterator(); iter.hasNext();) {
      resources[index++] = new ActionResource((Element)iter.next());
    }
    return resources;
  }
  
  /**
   * @return the inputs referenced by this action definition
   */
  public ActionInput[] getAllInputParams() {
    List inputsList =  actionDefElement.selectNodes(ActionSequenceDocument.ACTION_INPUTS_NAME + "/*"); //$NON-NLS-1$
    ActionInput[] inputs = new ActionInput[inputsList.size()];
    int index = 0;
    for (Iterator iter = inputsList.iterator(); iter.hasNext();) {
      inputs[index++] = new ActionInput((Element)iter.next());
    }
    return inputs;
  }
  
  /**
   * @param resourceName the resource name
   * @return the action definition resource with the given name or null if none exists.
   */
  public ActionInput getInputParam(String inputName) {
    Element inputElement = (Element)actionDefElement.selectSingleNode(ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + inputName); //$NON-NLS-1$
    return inputElement == null ? null : new ActionInput(inputElement);
  }
  
  /**
   * @return the referenced inputs of the specified types
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
   * @return the referenced inputs of the specified type
   */
  public ActionInput[] getInputParams(String type) {
    return getInputParams(new String[]{type});
  }
  
  public ActionOutput addOutputParam(String outputName, String outputType) {
    ActionOutput output = getOutputParam(outputName);
    Element outputElement;
    if (output == null) {
      outputElement = DocumentHelper.makeElement(actionDefElement, ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/" + outputName); //$NON-NLS-1$
      outputElement.addAttribute(ActionSequenceResource.TYPE_NAME, outputType);
      output = new ActionOutput(outputElement);
      ActionSequenceDocument.fireIoAdded(output);
    } else {
      output.setType(outputType);
    }
    return output;
  }
  
  /**
   * @return the outputs referenced by this action definition
   */
  public ActionOutput[] getAllOutputParams() {
    List outputsList =  actionDefElement.selectNodes(ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/*"); //$NON-NLS-1$
    ActionOutput[] outputs = new ActionOutput[outputsList.size()];
    int index = 0;
    for (Iterator iter = outputsList.iterator(); iter.hasNext();) {
      outputs[index++] = new ActionOutput((Element)iter.next());
    }
    return outputs;
  }
  
  /**
   * @param resourceName the output name
   * @return the action definition output with the given name or null if none exists.
   */
  public ActionOutput getOutputParam(String outputName) {
    Element outputElement = (Element)actionDefElement.selectSingleNode(ActionSequenceDocument.ACTION_OUTPUTS_NAME + "/" + outputName); //$NON-NLS-1$
    return outputElement == null ? null : new ActionOutput(outputElement);
  }
  
  public ActionOutput[] getOutputParams() {
    return getOutputParams((String[])null);
  }
  
  /**
   * @return the referenced outputs of the specified types
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
   * @return the referenced outputs of the specified type
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
          controlStatement = new ActionIfStatement(ancestorElement);
        } else {
          controlStatement = new ActionLoop(ancestorElement);
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
      ActionSequenceDocument.fireActionRemoved(new ActionSequenceDocument(doc), this);
    }
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
   */
  public ActionSequenceDocument getDocument() {
    ActionSequenceDocument doc = null;
    if ((actionDefElement != null) && (actionDefElement.getDocument() != null)) {
      doc = new ActionSequenceDocument(actionDefElement.getDocument());
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
  public IActionVariable[] getAvailInputVariables() {
    return getDocument().getAvailInputVariables(this, (String[])null);
  }
  
  /**
   * Returns the list of ActionSequenceInputs and ActionOutputs that are available as inputs to 
   * this action definition.
   * @param types the desired input types
   * @return
   */
  public IActionVariable[] getAvailInputVariables(String[] types) {
    return getDocument().getAvailInputVariables(this, types);
  }
  
  /**
   * Returns the list of ActionSequenceInputs and ActionOutputs that are available as inputs to 
   * this action definition.
   * @param types the desired input type
   * @return
   */
  public IActionVariable[] getAvailInputVariables(String type) {
    return getDocument().getAvailInputVariables(this, new String[]{type});
  }
  
  /**
   * Removes an output from this action definition
   * @param outputName the name of the output to be removed.
   */
  public void removeOutputParam(String outputName) {
    ActionOutput actionOutput = getOutputParam(outputName);
    if (actionOutput != null) {
      actionOutput.delete();
    }
  }
  
  /**
   * Removes an input from this action definition
   * @param inputName the name of the input to be removed.
   */
  public void removeInputParam(String inputName) {
    ActionInput actionInput = getInputParam(inputName);
    if (actionInput != null) {
      actionInput.delete();
    }
  }
  
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
   * @param resourceName the name of the resource to be removed.
   */
  public void removeResourceParam(String resourceName) {
    ActionResource actionResource = getResourceParam(resourceName);
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
  
  public void clearOutputParams() {
    ActionOutput[] actionOutputs = getAllOutputParams();
    for (int i = 0; i < actionOutputs.length; i++) {
      actionOutputs[i].delete();
    }
  }
  
  public void clearResourceParams() {
    ActionResource[] actionResources = getAllResourceParams();
    for (int i = 0; i < actionResources.length; i++) {
      actionResources[i].delete();
    }
  }
  
  public void removeComponentDefinitions(String compDefXpath) {
    Element[] componentDefs = getComponentDefElements(compDefXpath);
    for (int i = 0; i < componentDefs.length; i++) {
      componentDefs[i].detach();
    }
    if (componentDefs.length > 0) {
      ActionSequenceDocument.fireActionChanged(this);
    }
  }
  
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
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getExpectedOutputs() {
    return EXPECTED_OUTPUTS;
  }

  public String[] getExpectedResources() {
    return EXPECTED_RESOURCES;
  }
  
  public ActionDefinition[] getPrecedingActionDefinitions() {
    return getDocument().getPrecedingActionDefinitions(this);
  }

  public IActionSequenceExecutableStatement[] getPrecedingExecutableStatements() {
    return getDocument().getPrecedingExecutables(this);
  }
  
  protected ActionInput setReferencedVariable(String paramName, IActionVariable variable) {
    ActionInput actionInput = null;
    if (variable != null) {
      actionInput = setInputParam(paramName, variable.getVariableName());
      if (actionInput != null) {
        actionInput.setType(variable.getType());
      }
    } else {
      removeInputParam(paramName);
    }
    return actionInput;
  }
  
  protected IActionVariable getReferencedVariable(String inputParamName) {
    SimpleActionInputVariable variable = null;
    ActionInput actionInput = getInputParam(inputParamName);
    if (actionInput != null) {
      variable = new SimpleActionInputVariable();
      variable.setVariableName(actionInput.getReferencedVariableName());
      variable.setType(actionInput.getType());
    }
    return variable; 
  }
  
  protected ActionOutput setOutputName(String privateName, String publicName, String outputType) {
    ActionOutput actionOutput = null;
    if ((publicName == null) || (publicName.trim().length() == 0)) {
      removeOutputParam(privateName);
    } else {
      actionOutput = addOutputParam(privateName, outputType); 
      actionOutput.setMapping(publicName);
    }
    return actionOutput;
  }
  
  protected String getOutputPublicName(String privateName) {
    String publicName = null;
    ActionOutput actionOutput = getOutputParam(privateName);
    if (actionOutput != null) {
      publicName = actionOutput.getPublicName();
    }
    return publicName;
  }
  
  protected ActionSequenceValidationError validateInputParam(String paramName) {
    int errorCode = ActionSequenceValidationError.INPUT_OK;
    IActionVariable variable = getReferencedVariable(paramName);
    if (variable == null) {
      if (getComponentDefElement(paramName) == null) {
        errorCode = ActionSequenceValidationError.INPUT_MISSING;
      }
    } else {
      IActionVariable[] availableInputVariables = getDocument().getAvailInputVariables(this, variable.getType());
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
      validationError.parameterName = paramName;
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
  
  protected ActionSequenceValidationError validateResourceParam(String paramName) {
    ActionSequenceValidationError validationError = validateInputParam(paramName);
    if (validationError != null) {
      validationError = null;
      int errorCode = ActionSequenceValidationError.INPUT_OK;
      ActionResource actionResource = getResourceParam(paramName);
      if (actionResource == null) {
        errorCode = ActionSequenceValidationError.INPUT_MISSING;
      } else {
        errorCode = ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR;
      }
      if (errorCode != ActionSequenceValidationError.INPUT_OK) {
        validationError = new ActionSequenceValidationError();
        validationError.actionDefinition = this;
        validationError.errorCode = errorCode;
        validationError.parameterName = paramName;
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
  
  public ActionSequenceValidationError[] validate() {
    return EMPTY_ARRAY;
  }
  
  protected ActionSequenceValidationError validateOutputParam(String paramName) {
    ActionSequenceValidationError validationError = null;
    if (getOutputParam(paramName) == null) {
      validationError = new ActionSequenceValidationError();
      validationError.actionDefinition = this;
      validationError.errorCode = ActionSequenceValidationError.OUTPUT_MISSING;
      validationError.parameterName = paramName;
      validationError.errorMsg = "Missing output.";
    }
    return validationError;
  }
}
