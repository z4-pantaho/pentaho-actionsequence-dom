package org.pentaho.actionsequence.dom.actions;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionDefinition;
import org.pentaho.actionsequence.dom.IActionVariable;

public abstract class AbstractChartAction extends ActionDefinition {

  public static final String CHART_TYPE_XPATH = "chart-attributes/chart-type"; //$NON-NLS-1$
  public static final String COMPONENT_NAME = "org.pentaho.component.ChartComponent"; //$NON-NLS-1$
  
  public static final String CHART_ATTRIBUTES_ELEMENT = "chart-attributes"; //$NON-NLS-1$
  public static final String CHART_DATA_ELEMENT = "chart-data"; //$NON-NLS-1$
  public static final String CHART_WIDTH_ELEMENT = "width"; //$NON-NLS-1$
  public static final String CHART_HEIGHT_ELEMENT = "height"; //$NON-NLS-1$
  public static final String CHART_TITLE_ELEMENT = "title"; //$NON-NLS-1$
  public static final String CHART_SUBTITLE_ELEMENT = "subtitle"; //$NON-NLS-1$
  public static final String CHART_FONT_ELEMENT = "font-family"; //$NON-NLS-1$
  public static final String CHART_FONT_SIZE_ELEMENT = "size"; //$NON-NLS-1$
  public static final String CHART_BY_ROW_ELEMENT = "by-row"; //$NON-NLS-1$
  public static final String CHART_PAINT_BORDER_ELEMENT = "border-paint";  //$NON-NLS-1$
  public static final String CHART_SUBTITLE_XPATH = CHART_ATTRIBUTES_ELEMENT + "/" + CHART_SUBTITLE_ELEMENT;  //$NON-NLS-1$
  public static final String CHART_BORDER_COLOR_XPATH = CHART_ATTRIBUTES_ELEMENT + "/" + CHART_PAINT_BORDER_ELEMENT;  //$NON-NLS-1$
  public static final String CHART_TITLE_FONT_XPATH = "chart-attributes/title-font";  //$NON-NLS-1$
  public static final String CHART_TITLE_FONT_BOLD_ELEMENT = "is-bold";  //$NON-NLS-1$
  public static final String CHART_TITLE_FONT_ITALIC_ELEMENT = "is-italic";  //$NON-NLS-1$
  public static final String CHART_TITLE_FONT_BOLD_XPATH = CHART_TITLE_FONT_XPATH +"/" + CHART_TITLE_FONT_BOLD_ELEMENT;  //$NON-NLS-1$
  public static final String CHART_TITLE_FONT_ITALIC_XPATH = CHART_TITLE_FONT_XPATH +"/" + CHART_TITLE_FONT_ITALIC_ELEMENT;  //$NON-NLS-1$
  public static final String CHART_TITLE_FONT_FAMILY_XPATH = CHART_TITLE_FONT_XPATH +"/" + CHART_FONT_ELEMENT;  //$NON-NLS-1$
  public static final String CHART_TITLE_FONT_SIZE_XPATH = CHART_TITLE_FONT_XPATH +"/" + CHART_FONT_SIZE_ELEMENT;  //$NON-NLS-1$
  public static final String CHART_BORDER_VISIBLE_ELEMENT = "border-visible";  //$NON-NLS-1$
  public static final String CHART_BORDER_VISIBLE_XPATH = "chart-attributes/" + CHART_BORDER_VISIBLE_ELEMENT;  //$NON-NLS-1$
  public static final String CHART_FONT_SIZE = "font-size"; //$NON-NLS-1$
  public static final String CHART_TITLE_ITALIC = "title-italic"; //$NON-NLS-1$
  public static final String CHART_TITLE_BOLD = "title-bold"; //$NON-NLS-1$
  
  
  public static final String[] EXPECTED_INPUTS = new String[] {
    CHART_DATA_ELEMENT, 
    CHART_WIDTH_ELEMENT, 
    CHART_HEIGHT_ELEMENT, 
    CHART_TITLE_ELEMENT, 
    CHART_SUBTITLE_ELEMENT, 
    CHART_FONT_ELEMENT, 
    CHART_FONT_SIZE_ELEMENT, 
    CHART_BY_ROW_ELEMENT, 
    CHART_PAINT_BORDER_ELEMENT  
  };
  
  public AbstractChartAction(Element actionDefElement) {
    super(actionDefElement);
  }
  
  public AbstractChartAction(String componentName) {
    super(componentName);
  }
  
  public void setChartData(String value) {
    setInputValue(CHART_DATA_ELEMENT, value);
  }
  
  public String getChartData() {
    return getComponentDefinitionValue(CHART_DATA_ELEMENT);
  }
  
  public void setChartDataVariable(IActionVariable variable) {
    setReferencedVariable(CHART_DATA_ELEMENT, variable);
  }
  
  public IActionVariable getChartDataVariable() {
    return getReferencedVariable(CHART_DATA_ELEMENT);
  }

  public void setWidth(String value) {
    setInputValue(CHART_WIDTH_ELEMENT, value);
  }
  
  public String getWidth() {
    return getComponentDefinitionValue(CHART_WIDTH_ELEMENT);
  }
  
  public void setWidthVariable(IActionVariable variable) {
    setReferencedVariable(CHART_WIDTH_ELEMENT, variable);
  }
  
  public IActionVariable getWidthVariable() {
    return getReferencedVariable(CHART_WIDTH_ELEMENT);
  }

  public void setHeight(String value) {
    setInputValue(CHART_HEIGHT_ELEMENT, value);
  }
  
  public String getHeight() {
    return getComponentDefinitionValue(CHART_HEIGHT_ELEMENT);
  }
  
  public void setHeightVariable(IActionVariable variable) {
    setReferencedVariable(CHART_HEIGHT_ELEMENT, variable);
  }
  
  public IActionVariable getHeightVariable() {
    return getReferencedVariable(CHART_HEIGHT_ELEMENT);
  }

  public void setTitle(String value) {
    setInputValue(CHART_TITLE_ELEMENT, value);
  }
  
  public String getTitle() {
    return getComponentDefinitionValue(CHART_TITLE_ELEMENT);
  }
  
  public void setTitleVariable(IActionVariable variable) {
    setReferencedVariable(CHART_TITLE_ELEMENT, variable);
  }
  
  public IActionVariable getTitleVariable() {
    return getReferencedVariable(CHART_TITLE_ELEMENT);
  }
  
  public void setTitleBold(boolean value) {
    setComponentDefinition(CHART_TITLE_FONT_BOLD_XPATH, value ? "true" : "false"); //$NON-NLS-1$//$NON-NLS-2$
  }
  
  public boolean getTitleBold() {
    String value = getComponentDefinitionValue(CHART_TITLE_FONT_BOLD_XPATH);
    return (value != null) && value.trim().toLowerCase().equals("true"); //$NON-NLS-1$
  }
  
  public void setTitleItalic(boolean value) {
    setComponentDefinition(CHART_TITLE_FONT_ITALIC_XPATH, value ? "true" : "false"); //$NON-NLS-1$//$NON-NLS-2$
  }
  
  public boolean getTitleItalic() {
    String value = getComponentDefinitionValue(CHART_TITLE_FONT_ITALIC_XPATH);
    return (value != null) && value.trim().toLowerCase().equals("true"); //$NON-NLS-1$
  }
  
  public void setByRow(boolean value) {
    setComponentDefinition(CHART_BY_ROW_ELEMENT, value ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getByRow() {
    String value =  getComponentDefinitionValue(CHART_BY_ROW_ELEMENT);
    return (value != null) && value.trim().toLowerCase().equals("true"); //$NON-NLS-1$
  }
  
  public void setByRowVariable(IActionVariable variable) {
    setReferencedVariable(CHART_BY_ROW_ELEMENT, variable);
  }
  
  public IActionVariable getByRowVariable() {
    return getReferencedVariable(CHART_BY_ROW_ELEMENT);
  }
  
  public void setBorderVisible(boolean value) {
    setComponentDefinition(CHART_BORDER_VISIBLE_XPATH, value ? "true" : "false"); //$NON-NLS-1$//$NON-NLS-2$
  }
  
  public boolean getBorderVisible() {
    String value = getComponentDefinitionValue(CHART_BORDER_VISIBLE_XPATH);
    return (value == null) || !value.trim().toLowerCase().equals("false"); //$NON-NLS-1$
  }
  
  public void setFontFamily(String value) {
    setComponentDefinition(CHART_TITLE_FONT_FAMILY_XPATH, value);
  }
  
  public String getFontFamily() {
    return getComponentDefinitionValue(CHART_TITLE_FONT_FAMILY_XPATH);
  }

  public void setSubtitle(String value) {
    setComponentDefinition(CHART_SUBTITLE_XPATH, value);
  }
  
  public String getSubtitle() {
    return getComponentDefinitionValue(CHART_SUBTITLE_XPATH);
  }

  public void setFontSize(String value) {
    setComponentDefinition(CHART_TITLE_FONT_SIZE_XPATH, value);
  }
  
  public String getFontSize() {
    return getComponentDefinitionValue(CHART_TITLE_FONT_SIZE_XPATH);
  }

  public void setBorderPaint(String value) {
    setComponentDefinition(CHART_BORDER_COLOR_XPATH, value);
  }
  
  public String getBorderPaint() {
    return getComponentDefinitionValue(CHART_BORDER_COLOR_XPATH);
  }
  
  public void setChartType(String value) {
    setComponentDefinition(CHART_TYPE_XPATH, value);
  }
  
  public String getChartType() {
    return getComponentDefinitionValue(CHART_TITLE_ELEMENT);
  }
}
