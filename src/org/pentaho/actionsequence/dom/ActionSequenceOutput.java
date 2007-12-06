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
import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

/**
 * Convenience class used to distinguish action sequence inputs from action sequence outputs.
 * @author Angelo Rodriguez
 *
 */
public class ActionSequenceOutput extends AbstractParam {

  protected ActionSequenceOutput(Element outputElement, IActionParameterMgr actionInputProvider) {
    super(outputElement, actionInputProvider);
  }
  
  public ActionSequenceOutputDestination[] getDestinations() {
    ArrayList outputDestinations = new ArrayList();
    List destinationElements = ioElement.selectNodes(ActionSequenceDocument.OUTPUTS_DESTINATIONS_NAME + "/*"); //$NON-NLS-1$
    for (Iterator iter = destinationElements.iterator(); iter.hasNext();) {
      outputDestinations.add(new ActionSequenceOutputDestination((Element)iter.next(), actionInputProvider));
    }
    return (ActionSequenceOutputDestination[])outputDestinations.toArray(new ActionSequenceOutputDestination[0]);
  }
  
  
  public ActionSequenceOutputDestination addDestination(String destination, String name) {
    Element destinationParent = DocumentHelper.makeElement(ioElement, ActionSequenceDocument.OUTPUTS_DESTINATIONS_NAME);     
    Element newDestinationElement = destinationParent.addElement(destination);
    newDestinationElement.setText(name);
    ActionSequenceOutputDestination actionSequenceOutputDestination = new ActionSequenceOutputDestination(newDestinationElement, actionInputProvider);
    ActionSequenceDocument.fireIoChanged(this);
    return actionSequenceOutputDestination;
  }
}
