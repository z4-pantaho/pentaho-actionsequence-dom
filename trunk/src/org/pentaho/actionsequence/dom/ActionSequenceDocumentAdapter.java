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


public class ActionSequenceDocumentAdapter implements IActionSequenceDocumentListener {

  public ActionSequenceDocumentAdapter() {
    super();
  }

  public void ioAdded(IAbstractIOElement io) {
  }

  public void ioRemoved(Object parent, IAbstractIOElement io) {
  }

  public void ioRenamed(IAbstractIOElement io) {
  }

  public void ioChanged(IAbstractIOElement io) {
  }

  public void resourceAdded(Object resource) {
  }

  public void resourceRemoved(Object parent, Object resource) {
  }

  public void resourceRenamed(Object resource) {
  }

  public void resourceChanged(Object resource) {
  }

  public void actionAdded(IActionDefinition action) {
  }

  public void actionRemoved(Object parent, IActionDefinition action) {
  }

  public void actionRenamed(IActionDefinition action) {
  }

  public void actionChanged(IActionDefinition action) {
  }

  public void controlStatementAdded(IActionControlStatement controlStatement) {
  }

  public void controlStatementRemoved(Object parent, IActionControlStatement controlStatement) {
  }

  public void controlStatementChanged(IActionControlStatement controlStatement) {
  }

  public void headerChanged(IActionSequenceDocument actionSequenceDocument) {
  }

}
