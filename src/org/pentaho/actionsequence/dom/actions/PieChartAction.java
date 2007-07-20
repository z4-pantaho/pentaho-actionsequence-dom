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

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;

public class PieChartAction extends AbstractChartAction {

  public static final String CHART_TYPE = "PieChart"; //$NON-NLS-1$

  public PieChartAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public PieChartAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition(CHART_TYPE_XPATH, CHART_TYPE);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  protected boolean accepts(Element element) {
    boolean result = false;
    if (super.accepts(element)) {
      element = (Element)element.selectSingleNode(ActionSequenceDocument.COMPONENT_DEF_NAME + "/chart-attributes/chart-type"); //$NON-NLS-1$
      result = (element != null) && element.getText().equals(CHART_TYPE);
    }
    return result;
  }
}
