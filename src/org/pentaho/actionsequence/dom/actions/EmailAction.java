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

import java.util.ArrayList;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionDefinition;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
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
  
  public void setToParam(IActionVariable variable) {
    setReferencedVariable(TO_ELEMENT, variable);
  }
  
  public ActionInput getToParam() {
    return getInputParam(TO_ELEMENT);
  }
  
  public void setFrom(String from) {
    setInputValue(FROM_ELEMENT, from);
  }
  
  public String getFrom() {
    return getComponentDefinitionValue(FROM_ELEMENT);
  }
  
  public void setFromParam(IActionVariable variable) {
    setReferencedVariable(FROM_ELEMENT, variable);
  }
  
  public ActionInput getFromParam() {
    return getInputParam(FROM_ELEMENT);

  }
  
  public void setCc(String cc) {
    setInputValue(CC_ELEMENT, cc);
  }
  
  public String getCc() {
    return getComponentDefinitionValue(CC_ELEMENT);
  }
  
  public void setCcParam(IActionVariable variable) {
    setReferencedVariable(CC_ELEMENT, variable);
  }
  
  public ActionInput getCcParam() {
    return getInputParam(CC_ELEMENT);
  }
  
  public void setBcc(String bcc) {
    setInputValue(BCC_ELEMENT, bcc);
  }
  
  public String getBcc() {
    return getComponentDefinitionValue(BCC_ELEMENT);
  }
  
  public void setBccParam(IActionVariable variable) {
    setReferencedVariable(BCC_ELEMENT, variable);
  }
  
  public ActionInput getBccParam() {
    return getInputParam(BCC_ELEMENT);
  }
  
  public void setMessageHtml(String htmlMsg) {
    setInputValue(HTML_MSG_ELEMENT, htmlMsg);
  }
  
  public String getMessageHtml() {
    return getComponentDefinitionValue(PLAIN_MSG_ELEMENT);
  }
  
  public void setMessageHtmlParam(IActionVariable variable) {
    setReferencedVariable(HTML_MSG_ELEMENT, variable);
  }
  
  public ActionInput getMessageHtmlParam() {
    return getInputParam(HTML_MSG_ELEMENT);
  }
  
  public void setMessagePlain(String msg) {
    setInputValue(PLAIN_MSG_ELEMENT, msg);
  }
  
  public String getMessagePlain() {
    return getComponentDefinitionValue(HTML_MSG_ELEMENT);
  }
  
  public void setMessagePlainParam(IActionVariable variable) {
    setReferencedVariable(PLAIN_MSG_ELEMENT, variable);
  }
  
  public ActionInput getMessagePlainParam() {
    return getInputParam(PLAIN_MSG_ELEMENT);
  }
  
  public void setSubject(String subject) {
    setInputValue(SUBJECT_ELEMENT, subject);
  }
  
  public String getSubject() {
    return getComponentDefinitionValue(SUBJECT_ELEMENT);
  }
  
  public void setSubjectParam(IActionVariable variable) {
    setReferencedVariable(SUBJECT_ELEMENT, variable);
  }
  
  public ActionInput getSubjectParam() {
    return getInputParam(SUBJECT_ELEMENT);
  }
  
  public ActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInputParam(TO_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing input parameter for destination address.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Destination address is unavailable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Destination address is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    validationError = validateInputParam(SUBJECT_ELEMENT);
    if (validationError != null) {
      switch (validationError.errorCode) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing input parameter for subject.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Subject input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Subject input parameter is uninitialized.";
          break;
      }
      errors.add(validationError);
    }
    
    ActionSequenceValidationError htmlError = validateInputParam(HTML_MSG_ELEMENT);
    ActionSequenceValidationError plainError = validateInputParam(PLAIN_MSG_ELEMENT);
    if ((htmlError != null) && (plainError != null)) {
      if (plainError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED) {
        plainError.errorMsg = "Email message input parameter is uninitialized.";
        errors.add(plainError);
      } else if (htmlError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED) {
        htmlError.errorMsg = "Email message input parameter is uninitialized.";
        errors.add(htmlError);
      } else if (plainError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR) {
        plainError.errorMsg = "Email message input parameter references unknown variable.";
        errors.add(plainError);
      } else if (htmlError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR) {
        htmlError.errorMsg = "Email message input parameter references unknown variable.";
        errors.add(htmlError);
      } else if (plainError.errorCode == ActionSequenceValidationError.INPUT_MISSING) {
        plainError.errorMsg = "Missing input parameter for email message.";
        errors.add(plainError);
      } else if (htmlError.errorCode == ActionSequenceValidationError.INPUT_MISSING) {
        htmlError.errorMsg = "Missing input parameter for email message.";
        errors.add(htmlError);
      } else {
        errors.add(plainError);
      }
    }
    
    return (ActionSequenceValidationError[])errors.toArray(new ActionSequenceValidationError[0]);
  }
}
