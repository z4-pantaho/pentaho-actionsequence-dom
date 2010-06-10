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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class ActionControlStatement implements IActionControlStatement {

  IActionControlStatement parent;
  ArrayList<IActionSequenceExecutableStatement> children = new ArrayList<IActionSequenceExecutableStatement>();
  
  
  public ActionControlStatement(IActionControlStatement parent) {
    this.parent = parent;
  }
  
  /**
   * Adds a new child action definition to the end of this control statements
   * list of children.
   * @param componentName the name of the component that processes
   * the action definition
   * @return the newly created action definition
   * @throws IllegalAccessException 
   * @throws InstantiationException 
   */
  public IActionDefinition addAction(Class actionDefinitionClass) {
    throw new UnsupportedOperationException("cannot do a newInstance()");
//    ActionDefinition action = null;   
//    try {
//      action = (ActionDefinition) actionDefinitionClass.newInstance();
//      children.add(action);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return action;
  }
  
  /**
   * Adds a new child action definition to this control statement.
   * @param componentName the name of the component that processes
   * the action definition
   * @param index the index of where to add the new action. If index
   * is greater than the number of children then the new action is added
   * at the end of the list of children.
   * @return the newly created action definition
   * @throws IllegalAccessException 
   * @throws InstantiationException 
   */
  public IActionDefinition addAction(Class actionDefClass, int index) {
    IActionDefinition actionDef = null;
    try {
      if (index >= children.size()) {
        actionDef = addAction(actionDefClass);
      } else {
        throw new UnsupportedOperationException("cannot do a newInstance()");
//        actionDef = (ActionDefinition) actionDefClass.newInstance();
//        children.add(index, actionDef);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return actionDef;
  }
  
  /**
   * @return the child actions and control statements
   */
  public List<IActionSequenceExecutableStatement> getChildren() {
    return children;
  }
  
  /**
   * @return the control statement that contains this action definition or null if there is no parent control statement.
   */
  public IActionControlStatement getParent() {
    return parent;
  }
  
  public void setParent(IActionControlStatement parent) {
    this.parent = parent;
  }
  
  /**
   * Adds an action definition to the end of this control statements list of 
   * children. This control statement becomes the new parent of the action
   * definition.
   * @param actionDef the action definition to be added.
   */
  public void add(IActionDefinition actionDef) {
    actionDef.setParent(this.parent);
    children.add(actionDef);
  }
  
  /**
   * Adds a new child action definition to this control statement. 
   * @param actionDef the action definition to be added.
   * @param index the index of where to add the new action. If index
   * is greater than the number of children then the new action is added
   * at the end of the list of children.
   */
  public void add(IActionDefinition actionDef, int index) {
    if (index >= children.size()) {
      add(actionDef);
    } else {
      actionDef.setParent(this.parent);
      children.add(index, actionDef);
    }
  }
  
  /**
   * Adds a control statement to the end of this control statments list of 
   * children. This control statement becomes the new parent of the specified control statement.
   * @param controlStatement the control statment to be added.
   */
  public void add(IActionControlStatement controlStatement) {
    controlStatement.setParent(this.parent);
    children.add(controlStatement);
  }
  
  /**
   * Adds a control statement to this conrtol statement's list of 
   * children. 
   * @param controlStatement the control statement to be added.
   * @param index the index of where to add the new control statement. If index
   * is greater than the number of children then the new control statement is added
   * at the end of the list of children.
   */
  public void add(IActionControlStatement controlStatement, int index) {
    if (index >= children.size()) {
      add(controlStatement);
    } else {
      controlStatement.setParent(this.parent);
      children.add(index, controlStatement);
    }
  }
  
  /** 
   * Creates a new child action loop to the end of this control statement's children.
   * @param loopOn the loop on variable name
   */
  public IActionLoop addLoop(String loopOn) {
    IActionLoop loop = new ActionLoop(this.parent);
    loop.setLoopOn(loopOn);
    children.add(loop);
    return loop;
  }
  
  /** 
   * Creates a new child action loop.
   * @param loopOn the loop on variable name
   * @param index the index where the new loop should be created. If index
   * is greater than the number of children then the new loop is added
   * at the end of the list of children.
   */
  public IActionLoop addLoop(String loopOn, int index) {
    IActionLoop actionLoop = null;
    if (index >= children.size()) {
      actionLoop = addLoop(loopOn);
    } else {
      actionLoop = new ActionLoop(this.parent);
      actionLoop.setLoopOn(loopOn);
      children.add(index, actionLoop);
    }
    return actionLoop;
  }
  
  /** 
   * Creates a new child if statement to the end of this control statement's children.
   * @param condition the if condition
   */
  public IActionIfStatement addIf(String condition) {
    IActionIfStatement ifStatement = new ActionIfStatement(this.parent);
    ifStatement.setCondition(condition);
    children.add(ifStatement);
    return ifStatement;
  }
  
  /** 
   * Creates a new child if statement.
   * @param condition the if condition
   * @param index the index where the new if statement should be created. If index
   * is greater than the number of children then the new if statement is added
   * at the end of the list of children.
   */
  public IActionIfStatement addIf(String condition, int index) {
    IActionIfStatement actionIf = null;
    if (index >= children.size()) {
      actionIf = addIf(condition);
    } else {
      actionIf = new ActionIfStatement(this.parent);
      actionIf.setCondition(condition);
      children.add(index, actionIf);
    }
    return actionIf;
  }
  
  public IActionSequenceDocument getActionSequence() {
    return getParent().getActionSequence();
  }
}
