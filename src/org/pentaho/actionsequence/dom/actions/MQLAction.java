package org.pentaho.actionsequence.dom.actions;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionDefinition;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionVariable;

public class MQLAction extends ActionDefinition {
  public static final String QUERY_RESULT_OUTPUT_NAME = "query-result"; //$NON-NLS-1$
  
  public static final String COMPONENT_NAME = "MQLRelationalDataComponent"; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String OUTPUT_NAME_ELEMENT = "output-name"; //$NON-NLS-1$
  public static final String QUERY_RESULT_ELEMENT = "query-result"; //$NON-NLS-1$
  public static final String MAPPED_QUERY_OUTPUT_NAME = "query_result"; //$NON-NLS-1$
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    QUERY_ELEMENT
  };
  
  public MQLAction(Element actionDefElement) {
    super(actionDefElement);
  }

  public MQLAction() {
    super(COMPONENT_NAME);
  }
  
  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    ActionOutput actionOutput = addOutputParam(QUERY_RESULT_OUTPUT_NAME, ActionSequenceDocument.RESULTSET_TYPE);
    actionOutput.setMapping(MAPPED_QUERY_OUTPUT_NAME);
  }
  
  public String[] getExpectedInputs() {
    return EXPECTED_INPUTS;
  }
  
  public String[] getExpectedOutputs() {
    String expectedOutput = QUERY_RESULT_ELEMENT;
    String compDefVal = getComponentDefinitionValue(OUTPUT_NAME_ELEMENT);
    if (compDefVal != null) {
      expectedOutput = compDefVal;
    } else if (getOutputParam(expectedOutput) == null) {
      ActionOutput[] actionOutputs = getOutputParams(ActionSequenceDocument.RESULTSET_TYPE);
      if (actionOutputs.length > 0) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[]{expectedOutput};
  }

  public void setQuery(String value) {
    setInputValue(QUERY_ELEMENT, value);
  }
  
  public String getQuery() {
    return getComponentDefinitionValue(QUERY_ELEMENT);
  }
  
  public void setQueryParam(IActionVariable variable) {
    setReferencedVariable(QUERY_ELEMENT, variable);
  }
  
  public ActionInput getQueryParam() {
    return getInputParam(QUERY_ELEMENT);
  }
  
  public void setOutputResultSetName(String name) {
    setOutputName(QUERY_RESULT_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE);
  }
  
  public String getOutputResultSetName() {
    return getOutputPublicName(QUERY_RESULT_ELEMENT);
  }
  
  public ActionOutput getOutputResultSetParam() {
    return getOutputParam(QUERY_RESULT_ELEMENT);
  }
}
