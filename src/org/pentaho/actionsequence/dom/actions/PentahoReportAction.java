package org.pentaho.actionsequence.dom.actions;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputSource;
import org.pentaho.actionsequence.dom.IActionInputVariable;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionResource;

public class PentahoReportAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.reporting.platform.plugin.SimpleReportingComponent"; //$NON-NLS-1$
  public static final String REPORT_DEFINITION_ELEMENT = "report-definition"; //$NON-NLS-1$
  public static final String REPORT_DEFINITION_INPUT_STREAM_ELEMENT = "reportDefinitionInputStream"; //$NON-NLS-1$
  public static final String REPORT_DEFINITION_PATH_ELEMENT = "reportDefinitionPath"; //$NON-NLS-1$
  public static final String USE_CONTENT_REPOSITORY_ELEMENT = "useContentRepository"; //$NON-NLS-1$
  public static final String PAGINATE_OUTPUT_ELEMENT = "paginate"; //$NON-NLS-1$
  public static final String OUTPUT_TYPE_ELEMENT = "outputType"; //$NON-NLS-1$
  public static final String REPORTHTML_CONTENTHANDLER_PATTERN_ELEMENT = "content-handler-pattern"; //$NON-NLS-1$
  public static final String REPORTGENERATE_YIELDRATE_ELEMENT = "yield-rate"; //$NON-NLS-1$
  public static final String ACCEPTED_PAGE_ELEMENT = "accepted-page"; //$NON-NLS-1$
  public static final String REPORT_OUTPUT_ELEMENT = "outputstream";
  
  protected static final String[] EXPECTED_RESOURCES = new String[] {
    REPORT_DEFINITION_ELEMENT
  };
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    REPORT_DEFINITION_PATH_ELEMENT,
    REPORT_DEFINITION_ELEMENT,
    USE_CONTENT_REPOSITORY_ELEMENT,
    PAGINATE_OUTPUT_ELEMENT,
    OUTPUT_TYPE_ELEMENT,
    REPORTGENERATE_YIELDRATE_ELEMENT,
    ACCEPTED_PAGE_ELEMENT
  };
  
  public PentahoReportAction(Element actionDefElement, IActionParameterMgr actionParameterMgr) {
    super(actionDefElement, actionParameterMgr);
  }

  public PentahoReportAction() {
    super(COMPONENT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  public IActionResource getReportDefinitionResource() {
    return getResource(REPORT_DEFINITION_ELEMENT);
  }
  
  public IActionResource setReportDefinitionResource(URI uri, String mimeType) {
    IActionResource actionResource = null;
    if (uri == null) {
      setResourceUri(REPORT_DEFINITION_ELEMENT, null, mimeType);
    } else {
      actionResource = setResourceUri(REPORT_DEFINITION_ELEMENT, uri, mimeType);         
      setReportDefinition(null);
    }
    return actionResource;
  }
  
  public void setReportDefinition(IActionInputSource value) {
    if (value instanceof IActionInputVariable) {
      IActionInputVariable variable = (IActionInputVariable)value;
      if (variable.getType().equals(ActionSequenceDocument.INPUT_STREAM_TYPE)) {
        setActionInputValue(REPORT_DEFINITION_INPUT_STREAM_ELEMENT, value);
      } else {
        setActionInputValue(REPORT_DEFINITION_PATH_ELEMENT, value);
      }
      setReportDefinitionResource(null, null);
    } else if ((value != null) && (((ActionInputConstant)value).getValue() != null)) {
      setActionInputValue(REPORT_DEFINITION_PATH_ELEMENT, value);
      setReportDefinitionResource(null, null);
    } else {
      setActionInputValue(REPORT_DEFINITION_INPUT_STREAM_ELEMENT, (IActionInputSource)null);
      setActionInputValue(REPORT_DEFINITION_PATH_ELEMENT, (IActionInputSource)null);
    }
  }
  
  public IActionInput getReportDefinition() {
    return getInput(REPORT_DEFINITION_PATH_ELEMENT);
  }
  
  public void setUseContentRepository(IActionInputSource value)
  {
    setActionInputValue(USE_CONTENT_REPOSITORY_ELEMENT, value);
  }
  
  public IActionInput getUseContentRepository()
  {
    return getInput(USE_CONTENT_REPOSITORY_ELEMENT);
  }
  
  public void setPaginate(IActionInputSource value)
  {
    setActionInputValue(PAGINATE_OUTPUT_ELEMENT, value);
  }
  
  public IActionInput getPaginate()
  {
    return getInput(PAGINATE_OUTPUT_ELEMENT);
  }
  
  public void setOutputType(IActionInputSource value) {
    setActionInputValue(OUTPUT_TYPE_ELEMENT, value);
  }
  
  public IActionInput getOutputType() {
    return getInput(OUTPUT_TYPE_ELEMENT);
  }
  
  public void setAcceptedPage(IActionInputSource value) {
    setActionInputValue(ACCEPTED_PAGE_ELEMENT, value);
  }
  
  public IActionInput getAcceptedPage() {
    return getInput(ACCEPTED_PAGE_ELEMENT);
  }
  
  public void setYieldRate(IActionInputSource value) {
    setActionInputValue(REPORTGENERATE_YIELDRATE_ELEMENT, value);
  }
  
  public IActionInput getYieldRate() {
    return getInput(REPORTGENERATE_YIELDRATE_ELEMENT);    
  }
  
  public void setReportHtmlContentHandlerPattern(IActionInputSource value) {
    setActionInputValue(REPORTHTML_CONTENTHANDLER_PATTERN_ELEMENT, value);
  }
  
  public IActionInput getReportHtmlContentHandlerPattern() {
    return getInput(REPORTHTML_CONTENTHANDLER_PATTERN_ELEMENT);    
  }
  
  public List<IActionInput> getReportParameters() {
    ArrayList<String> knownInputs = new ArrayList<String>(Arrays.asList(EXPECTED_INPUTS));
    ArrayList<IActionInput> reportParameters = new ArrayList<IActionInput>();
    for (IActionInput input : getInputs()) {
      if ((input instanceof ActionInputConstant)
          && !knownInputs.contains(((ActionInputConstant)input).getName())) {
        reportParameters.add(input);
      } else if ((input instanceof IActionInputVariable)
          && !knownInputs.contains(((IActionInputVariable)input).getVariableName())) {
        reportParameters.add(input);
      }
    }
    return reportParameters;
  }
  
  public void setReportParameters(List<IActionInputSource> reportParameters) {
    List<IActionInput> existingReportParameters = getReportParameters();
    for (IActionInput existingReportParameter : existingReportParameters) {
      if (existingReportParameter instanceof ActionInputConstant) {
        setActionInputValue(((ActionInputConstant)existingReportParameter).getName(), (IActionInputSource)null);
      } else if (existingReportParameter instanceof IActionInputVariable) {
        setActionInputValue(((IActionInputVariable)existingReportParameter).getVariableName(), (IActionInputSource)null);
      }
    }
    for (IActionInputSource reportParameter : reportParameters) {
      if (reportParameter instanceof ActionInputConstant) {
        setActionInputValue(((ActionInputConstant)reportParameter).getName(), (IActionInputSource)reportParameter);
      } else if (reportParameter instanceof IActionInputVariable) {
        setActionInputValue(((IActionInputVariable)reportParameter).getVariableName(), (IActionInputSource)reportParameter);
      }
    }
  }
  
  public IActionOutput setOutputReport(String publicOutputName) {
    IActionOutput actionOutput = setOutput(REPORT_OUTPUT_ELEMENT, publicOutputName, ActionSequenceDocument.CONTENT_TYPE);
    if ((publicOutputName != null) && (publicOutputName.trim().length() > 0)) {
      IActionOutput[] actionOutputs = getOutputs();
      for (int i = 0; i < actionOutputs.length; i++) {
        if (actionOutputs[i].getType().equals(ActionSequenceDocument.CONTENT_TYPE)
            && !actionOutputs[i].getName().equals(REPORT_OUTPUT_ELEMENT)) {
          actionOutputs[i].delete();
        }
      }
    }
    return actionOutput;
  }
  
  public IActionOutput getOutputReport() {
    String privateOutputName = REPORT_OUTPUT_ELEMENT;
    if (getOutput(privateOutputName) ==  null) { 
      IActionOutput[] actionOutputs = getOutputs(ActionSequenceDocument.CONTENT_TYPE);
      if (actionOutputs.length > 0) {
        privateOutputName = actionOutputs[0].getName();
      }
    }
    return getOutput(REPORT_OUTPUT_ELEMENT);
  }
}
