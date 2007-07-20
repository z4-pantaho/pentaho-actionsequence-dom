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
import org.pentaho.actionsequence.dom.ActionDefinition;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionVariable;

public class XQueryAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.XQueryLookupRule"; //$NON-NLS-1$
  public static final String DOCUMENT_ELEMENT = "document" ; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query" ; //$NON-NLS-1$
  public static final String QUERY_RESULT_ELEMENT = "query-result" ; //$NON-NLS-1$
  public static final String SOURCE_XML = "source-xml"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  public static final String OUTPUT_PREPARED_STATEMENT = "output-prepared_statement"; //$NON-NLS-1$

  protected static final String[] EXPECTED_RESOURCES = new String[] {
    DOCUMENT_ELEMENT
  };
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    DOCUMENT_ELEMENT,
    QUERY_ELEMENT
  };

  public XQueryAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public XQueryAction() {
    super(COMPONENT_NAME);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getExpectedOutputs() {
    String expectedOutput = QUERY_RESULT_ELEMENT;
    if (getOutputParam(expectedOutput) ==  null) { 
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.RESULTSET_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }
  
  public String[] getExpectedResources() {
    return EXPECTED_RESOURCES;
  }
  
  public void setSourceXml(String value) {
    setInputValue(DOCUMENT_ELEMENT, value);
  }
  
  public String getSourceXml() {
    return getComponentDefinitionValue(DOCUMENT_ELEMENT);
  }
  
  public void setSourceXmlVariable(IActionVariable variable) {
    setReferencedVariable(DOCUMENT_ELEMENT, variable);
  }
  
  public IActionVariable getSourceXmlVariable() {
    return getReferencedVariable(DOCUMENT_ELEMENT);
  }

  public void setQuery(String value) {
    setInputValue(QUERY_ELEMENT, value);
  }
  
  public String getQuery() {
    return getComponentDefinitionValue(QUERY_ELEMENT);
  }
  
  public void setQueryVariable(IActionVariable variable) {
    setReferencedVariable(QUERY_ELEMENT, variable);
  }
  
  public IActionVariable getQueryVariable() {
    return getReferencedVariable(QUERY_ELEMENT);
  }
  
  public void setOutputResultSetName(String name) {
    setOutputName(QUERY_RESULT_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputPreparedStatementName(null);
    }
  }
  
  public String getOutputResultSetName() {
    return getOutputPublicName(QUERY_RESULT_ELEMENT);
  }
  
  public ActionOutput getOutputResultSetVariable() {
    return getOutputParam(QUERY_RESULT_ELEMENT);
  }
  
  public void setOutputPreparedStatementName(String name) {
    setOutputName(PREPARED_COMPONENT_ELEMENT, name, ActionSequenceDocument.XQUERY_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputResultSetName(null);
      ActionOutput[] actionOutputs = getOutputParams();
      for (int i = 0; i < actionOutputs.length; i++) {
        if (!actionOutputs[i].getType().equals(ActionSequenceDocument.XQUERY_TYPE)) {
          actionOutputs[i].delete();
        }
      }
    }
  }
  
  public String getOutputPreparedStatementName() {
    return getOutputPublicName(PREPARED_COMPONENT_ELEMENT);
  }  
  
  public ActionOutput getOutputPreparedStatementVariable() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }
}
