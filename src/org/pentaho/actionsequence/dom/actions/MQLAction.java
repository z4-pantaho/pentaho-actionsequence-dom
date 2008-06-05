package org.pentaho.actionsequence.dom.actions;

import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInputValueProvider;
import org.pentaho.actionsequence.dom.IActionOutput;

public class MQLAction extends AbstractRelationalDbAction {
  public static final String QUERY_RESULT_OUTPUT_NAME = "query-result"; //$NON-NLS-1$
  
  public static final String COMPONENT_NAME = "MQLRelationalDataComponent"; //$NON-NLS-1$
  public static final String OUTPUT_NAME_ELEMENT = "output-name"; //$NON-NLS-1$
  public static final String MQL_ELEMENT = "mql"; //$NON-NLS-1$
  public static final String QUERY_RESULT_ELEMENT = "query-result"; //$NON-NLS-1$
  public static final String MAPPED_QUERY_OUTPUT_NAME = "query_result"; //$NON-NLS-1$
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  public static final String DISABLE_DISTINCT_ELEMENT = "DisableDistinct"; //$NON-NLS-1$
  public static final String FORCE_DB_DIALECT_ELEMENT = "ForceDBDialect"; //$NON-NLS-1$
  public static final String MQLQUERY_CLASSNAME = "MQLQueryClassName"; //$NON-NLS-1$
    
  protected static final String[] EXPECTED_INPUTS = new String[] {
    QUERY_ELEMENT
  };
  
  public MQLAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public MQLAction() {
    super(COMPONENT_NAME);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    IActionOutput actionOutput = setOutputParam(QUERY_RESULT_OUTPUT_NAME, QUERY_RESULT_OUTPUT_NAME, ActionSequenceDocument.RESULTSET_TYPE);
    actionOutput.setMapping(MAPPED_QUERY_OUTPUT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getReservedOutputNames() {
    String expectedOutput = QUERY_RESULT_ELEMENT;
    String compDefVal = getComponentDefinitionValue(OUTPUT_NAME_ELEMENT);
    if (compDefVal != null) {
      expectedOutput = compDefVal;
    } else if (getOutputParam(expectedOutput) == null) {
      IActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.RESULTSET_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }

  
  public IActionInputValueProvider getQuery() {
    IActionInputValueProvider query = super.getQuery();
    
    // The following condition covers an alternative way to store the mql
    // within the action definition. This class does not use this method when
    // writing to the dom.
    if (query == ActionInputConstant.NULL_INPUT) {
      Element element = getComponentDefElement(MQL_ELEMENT);
      if (element != null) {
        try {
          query = new ActionInputConstant(prettyPrint(DocumentHelper.parseText(element.asXML())).getRootElement().asXML(), actionParameterMgr);
        } catch (DocumentException e) {
          query = new ActionInputConstant(element.asXML(), actionParameterMgr);
        }
      }
    }
    
    return query;
  }

  public void setQuery(IActionInputValueProvider value) {
    super.setQuery(value);
    
    // The following removes an alternative way to store the mql
    // within the action definition. This class does not use this method when
    // writing to the dom.
    Element element = getComponentDefElement(MQL_ELEMENT);
    if (element != null) {
      element.detach();
    }
  }

  public void setDisableDistinct(IActionInputValueProvider value) {
    setActionInputValue(DISABLE_DISTINCT_ELEMENT, value);
  }
  
  public IActionInputValueProvider getDisableDistinct() {
    return getActionInputValue(DISABLE_DISTINCT_ELEMENT);
  }
  
  public void setForceDbDialect(IActionInputValueProvider value) {
    setActionInputValue(FORCE_DB_DIALECT_ELEMENT, value);
  }
  
  // forces the use of the metadata.xmi's dialect, even if the actual 
  // connection is of a different database.  this may be necessary if 
  // there are issues with detecting the correct database.  The value
  // defaults to false if not specified.
  public IActionInputValueProvider getForceDbDialect() {
    return getActionInputValue(FORCE_DB_DIALECT_ELEMENT);
  }
  
  public void setMqlQueryClassName(IActionInputValueProvider value) {
    setActionInputValue(MQLQUERY_CLASSNAME, value);
  }
  
  // forces the use of the metadata.xmi's dialect, even if the actual 
  // connection is of a different database.  this may be necessary if 
  // there are issues with detecting the correct database.  The value
  // defaults to false if not specified.
  public IActionInputValueProvider getMqlQueryClassName() {
    return getActionInputValue(MQLQUERY_CLASSNAME);
  }
  
  
  private Document prettyPrint( Document document ) {
    try {
      OutputFormat format = OutputFormat.createPrettyPrint();
      format.setEncoding(document.getXMLEncoding());
      StringWriter stringWriter = new StringWriter();
      XMLWriter writer = new XMLWriter( stringWriter, format );
      // XMLWriter has a bug that is avoided if we reparse the document
      // prior to calling XMLWriter.write()
      writer.write(DocumentHelper.parseText(document.asXML()));
      writer.close();
      document = DocumentHelper.parseText( stringWriter.toString() );
    }
    catch ( Exception e ){
      e.printStackTrace();
            return( null );
    }
    return( document );
  } 
}
