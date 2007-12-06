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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import org.dom4j.Element;
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
  
  public EmailAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public EmailAction() {
    super(COMPONENT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  public void setTo(String to) {
    setInputValue(TO_ELEMENT, to);
  }
  
  public String getTo() {
    // The message could come from a hash map that is named after the "to" input. I believe
    // this is deprecated functionality.
    Object toString = getInputValue(TO_ELEMENT);
    if (toString instanceof HashMap) {
      toString = ((HashMap)toString).get(EmailAction.TO_ELEMENT);
    }
    // End deprecated functionality
    
    if ((toString != null) && (actionParameterMgr != null)) {
      toString = actionParameterMgr.replaceParameterReferences(toString.toString());
    }
    return toString != null ? toString.toString() : (String)toString;
  }
  
  public void setToParam(IActionVariable variable) {
    setInputParam(TO_ELEMENT, variable);
  }
  
  public ActionInput getToParam() {
    return getInputParam(TO_ELEMENT);
  }
  
  public void setFrom(String from) {
    setInputValue(FROM_ELEMENT, from);
  }
  
  public String getFrom() {
    Object from = getInputValue(FROM_ELEMENT);
    if ((from != null) && (actionParameterMgr != null)) {
      from = actionParameterMgr.replaceParameterReferences(from.toString());
    }
    return from != null ? from.toString() : (String)from;
  }
  
  public void setFromParam(IActionVariable variable) {
    setInputParam(FROM_ELEMENT, variable);
  }
  
  public ActionInput getFromParam() {
    return getInputParam(FROM_ELEMENT);

  }
  
  public void setCc(String cc) {
    setInputValue(CC_ELEMENT, cc);
  }
  
  public String getCc() {
    Object cc = getInputValue(CC_ELEMENT);
    if ((cc != null) && (actionParameterMgr != null)) {
      cc = actionParameterMgr.replaceParameterReferences(cc.toString());
    }
    return cc != null ? cc.toString() : (String)cc;
  }
  
  public void setCcParam(IActionVariable variable) {
    setInputParam(CC_ELEMENT, variable);
  }
  
  public ActionInput getCcParam() {
    return getInputParam(CC_ELEMENT);
  }
  
  public void setBcc(String bcc) {
    setInputValue(BCC_ELEMENT, bcc);
  }
  
  public String getBcc() {
    Object bcc = getInputValue(BCC_ELEMENT);
    if ((bcc != null) && (actionParameterMgr != null)) {
      bcc = actionParameterMgr.replaceParameterReferences(bcc.toString());
    }
    return bcc != null ? bcc.toString() : (String)bcc;
  }
  
  public void setBccParam(IActionVariable variable) {
    setInputParam(BCC_ELEMENT, variable);
  }
  
  public ActionInput getBccParam() {
    return getInputParam(BCC_ELEMENT);
  }
  
  public void setMessageHtml(String htmlMsg) {
    setInputValue(HTML_MSG_ELEMENT, htmlMsg);
  }
  
  public String getMessageHtml() {
    Object msg = getInputValue(HTML_MSG_ELEMENT);
    if (msg instanceof InputStream) {
      InputStream is = (InputStream)msg;
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte bytes[] = new byte[1024];
      int numRead = 0;
      try {
        while ((numRead = is.read(bytes)) != -1) {
          if (numRead > 0) {
            baos.write(bytes, 0, numRead);
          }
        }
        msg = baos.toString();
      } catch (Exception e) {
        msg = "";
      }
    }
    if ((msg != null) && (actionParameterMgr != null)) {
      msg = actionParameterMgr.replaceParameterReferences(msg.toString());
    }
    return msg != null ? msg.toString() : (String)msg;
  }
  
  public void setMessageHtmlParam(IActionVariable variable) {
    setInputParam(HTML_MSG_ELEMENT, variable);
  }
  
  public ActionInput getMessageHtmlParam() {
    return getInputParam(HTML_MSG_ELEMENT);
  }
  
  public void setMessagePlain(String msg) {
    setInputValue(PLAIN_MSG_ELEMENT, msg);
  }
  
  public String getMessagePlain() {
    
    // The message could come from a hash map that is named after the "to" input. I believe
    // this is deprecated functionality.
    Object msg = getInputValue(TO_ELEMENT);
    if (msg instanceof HashMap) {
      msg = ((HashMap)msg).get(EmailAction.PLAIN_MSG_ELEMENT);
    } else {
      msg = null;
    }
    // End deprecated functionality
    
    if (msg == null) {
      msg = getInputValue(PLAIN_MSG_ELEMENT);
    }
    
    if ((msg != null) && (actionParameterMgr != null)) {
      msg = actionParameterMgr.replaceParameterReferences(msg.toString());
    }
    return msg != null ? msg.toString() : (String)msg;
  }
  
  public void setMessagePlainParam(IActionVariable variable) {
    setInputParam(PLAIN_MSG_ELEMENT, variable);
  }
  
  public ActionInput getMessagePlainParam() {
    return getInputParam(PLAIN_MSG_ELEMENT);
  }
  
  public void setSubject(String subject) {
    setInputValue(SUBJECT_ELEMENT, subject);
  }
  
  public String getSubject() {
    // The message could come from a hash map that is named after the "to" input. I believe
    // this is deprecated functionality.
    Object subject = getInputValue(TO_ELEMENT);
    if (subject instanceof HashMap) {
      subject = ((HashMap)subject).get(EmailAction.SUBJECT_ELEMENT);
    } else {
      subject = null;
    }
    // End deprecated functionality
    
    if (subject == null) {
      subject = getInputValue(SUBJECT_ELEMENT);
    }
    
    if ((subject != null) && (actionParameterMgr != null)) {
      subject = actionParameterMgr.replaceParameterReferences(subject.toString());
    }
    return subject != null ? subject.toString() : (String)subject;
  }
  
  public void setSubjectParam(IActionVariable variable) {
    setInputParam(SUBJECT_ELEMENT, variable);
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
  
  public EmailAttachment addAttachment(IActionVariable variable) {
    EmailAttachment[] emailAttachments = getAttachments();
    for (int i = 0; i < emailAttachments.length; i++) {
      if (emailAttachments[i].isDeprecatedAttachmentStyle()) {
        emailAttachments[i].convertToNewAttachmentStyle();
      }
    }
    return new EmailAttachment(this, variable);
  }
  
  public EmailAttachment addAttachment(String name, URI uri, String mimeType) {
    EmailAttachment[] emailAttachments = getAttachments();
    for (int i = 0; i < emailAttachments.length; i++) {
      if (emailAttachments[i].isDeprecatedAttachmentStyle()) {
        emailAttachments[i].convertToNewAttachmentStyle();
      }
    }
    return new EmailAttachment(this, name, uri, mimeType);
  }
  
  public EmailAttachment[] getAttachments() {
    Element[] elements = getComponentDefElements(EmailAttachment.ELEMENT_NAME);
    EmailAttachment[] emailAttachments = new EmailAttachment[elements.length];
    if (emailAttachments.length != 0) {
      for (int i = 0; i < elements.length; i++) {
        emailAttachments[i] = new EmailAttachment(elements[i], actionParameterMgr);
      }
    } else {
      // This else statement handles deprecated functionality. It is here to ensure that old
      // style email actions still work.
      if ((getInputValue(EmailAttachment.OLD_ATTACHMENT_ELEMENT) != null) ||
          (getComponentDefElement(EmailAttachment.OLD_ATTACHMENT_ELEMENT) != null)){
        emailAttachments = new EmailAttachment[1];
        emailAttachments[0] = new EmailAttachment(this);
      }
    }
    return emailAttachments;
  }
}
