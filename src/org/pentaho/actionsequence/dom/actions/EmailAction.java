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
import org.pentaho.actionsequence.dom.IActionVariable;

public class EmailAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.EmailComponent"; //$NON-NLS-1$
  public static final String TO_ELEMENT = "to"; //$NON-NLS-1$
  public static final String FROM_ELEMENT = "from"; //$NON-NLS-1$
  public static final String CC_ELEMENT = "cc"; //$NON-NLS-1$
  public static final String BCC_ELEMENT = "bcc"; //$NON-NLS-1$
  public static final String SUBJECT_ELEMENT = "subject"; //$NON-NLS-1$
  public static final String PLAIN_MSG_ELEMENT = "message-plain"; //$NON-NLS-1$
  public static final String HTML_MSG_ELEMENT = "message-html"; //$NON-NLS-1$
    
  protected static final String[] EXPECTED_INPUTS = new String[] {
    TO_ELEMENT,
    FROM_ELEMENT,
    CC_ELEMENT,
    BCC_ELEMENT, 
    SUBJECT_ELEMENT,
    PLAIN_MSG_ELEMENT,
    HTML_MSG_ELEMENT
  };
  
  public EmailAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public EmailAction() {
    super(COMPONENT_NAME);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }

  protected boolean accepts(Element element) {
    // TODO Auto-generated method stub
    return super.accepts(element);
  }
  
  public void setTo(String to) {
    setInputValue(TO_ELEMENT, to);
  }
  
  public String getTo() {
    return getComponentDefinitionValue(TO_ELEMENT);
  }
  
  public void setToVariable(IActionVariable variable) {
    setReferencedVariable(TO_ELEMENT, variable);
  }
  
  public IActionVariable getToVariable() {
    return getReferencedVariable(TO_ELEMENT);
  }
  
  public void setFrom(String from) {
    setInputValue(FROM_ELEMENT, from);
  }
  
  public String getFrom() {
    return getComponentDefinitionValue(FROM_ELEMENT);
  }
  
  public void setFromVariable(IActionVariable variable) {
    setReferencedVariable(FROM_ELEMENT, variable);
  }
  
  public IActionVariable getFromVariable() {
    return getReferencedVariable(FROM_ELEMENT);

  }
  
  public void setCc(String cc) {
    setInputValue(CC_ELEMENT, cc);
  }
  
  public String getCc() {
    return getComponentDefinitionValue(CC_ELEMENT);
  }
  
  public void setCcVariable(IActionVariable variable) {
    setReferencedVariable(CC_ELEMENT, variable);
  }
  
  public IActionVariable getCcVariable() {
    return getReferencedVariable(CC_ELEMENT);
  }
  
  public void setBcc(String bcc) {
    setInputValue(BCC_ELEMENT, bcc);
  }
  
  public String getBcc() {
    return getComponentDefinitionValue(BCC_ELEMENT);
  }
  
  public void setBccVariable(IActionVariable variable) {
    setReferencedVariable(BCC_ELEMENT, variable);
  }
  
  public IActionVariable getBccVariable() {
    return getReferencedVariable(BCC_ELEMENT);
  }
  
  public void setMessageHtml(String htmlMsg) {
    setInputValue(HTML_MSG_ELEMENT, htmlMsg);
  }
  
  public String getMessageHtml() {
    return getComponentDefinitionValue(PLAIN_MSG_ELEMENT);
  }
  
  public void setMessageHtmlVariable(IActionVariable variable) {
    setReferencedVariable(HTML_MSG_ELEMENT, variable);
  }
  
  public IActionVariable getMessageHtmlVariable() {
    return getReferencedVariable(HTML_MSG_ELEMENT);
  }
  
  public void setMessagePlain(String msg) {
    setInputValue(PLAIN_MSG_ELEMENT, msg);
  }
  
  public String getMessagePlain() {
    return getComponentDefinitionValue(HTML_MSG_ELEMENT);
  }
  
  public void setMessagePlainVariable(IActionVariable variable) {
    setReferencedVariable(PLAIN_MSG_ELEMENT, variable);
  }
  
  public IActionVariable getMessagePlainVariable() {
    return getReferencedVariable(PLAIN_MSG_ELEMENT);
  }
  
  public void setSubject(String subject) {
    setInputValue(SUBJECT_ELEMENT, subject);
  }
  
  public String getSubject() {
    return getComponentDefinitionValue(SUBJECT_ELEMENT);
  }
  
  public void setSubjectVariable(IActionVariable variable) {
    setReferencedVariable(SUBJECT_ELEMENT, variable);
  }
  
  public IActionVariable getSubjectVariable() {
    return getReferencedVariable(SUBJECT_ELEMENT);
  }
  
}
