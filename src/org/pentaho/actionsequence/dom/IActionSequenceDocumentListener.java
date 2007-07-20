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

/**
 * Classes that implement this interface can be notified of changes to an action sequence.
 * 
 * @author Angelo Rodriguez
 *
 */
public interface IActionSequenceDocumentListener {
  
  public void ioAdded(AbstractParam io);
  public void ioRemoved(Object parent, AbstractParam io);
  public void ioRenamed(AbstractParam io);
  public void ioChanged(AbstractParam io);
  
  public void resourceAdded(Object resource);
  public void resourceRemoved(Object parent, Object resource);
  public void resourceRenamed(Object resource);
  public void resourceChanged(Object resource);
  
  public void actionAdded(ActionDefinition action);
  public void actionRemoved(Object parent, ActionDefinition action);
  public void actionRenamed(ActionDefinition action);
  public void actionChanged(ActionDefinition action);
  
  public void controlStatementAdded(ActionControlStatement controlStatement);
  public void controlStatementRemoved(Object parent, ActionControlStatement controlStatement);
  public void controlStatementChanged(ActionControlStatement controlStatement);
  
  public void headerChanged(ActionSequenceDocument actionSequenceDocument);
}
