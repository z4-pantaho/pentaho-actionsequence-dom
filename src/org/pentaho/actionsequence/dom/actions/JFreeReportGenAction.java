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

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInputValueProvider;

public class JFreeReportGenAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "com.pentaho.component.JFreeReportGeneratorComponent"; //$NON-NLS-1$
  public static final String RESULT_SET = "result-set"; //$NON-NLS-1$
  public static final String COMPONENT_SETTINGS = "component-settings"; //$NON-NLS-1$
  public static final String TEMPLATE_PATH_PROP = "template-path"; //$NON-NLS-1$
  public static final String ORIENTATION_PROP = "orientation"; //$NON-NLS-1$
  public static final String NULL_STRING_PROP = "null-string"; //$NON-NLS-1$
  public static final String HORIZONTAL_OFFSET_PROP = "horizontal-offset"; //$NON-NLS-1$
  public static final String REPORTNAME_PROP = "report-name"; //$NON-NLS-1$
  public static final String CREATE_SUBTOTALS_PROP = "create-sub-totals"; //$NON-NLS-1$
  public static final String CREATE_GRANDTOTALS_PROP = "create-grand-totals"; //$NON-NLS-1$
  public static final String CREATE_ROWBANDING_PROP = "create-row-banding"; //$NON-NLS-1$
  public static final String TOTALCOLUMN_NAME_PROP = "total-column-name"; //$NON-NLS-1$
  public static final String TOTALCOLUMN_WIDTH_PROP = "total-column-width"; //$NON-NLS-1$
  public static final String TOTALCOLUMN_FORMAT_PROP = "total-column-format"; //$NON-NLS-1$
  public static final String ROWBANDING_COLOR_PROP = "row-banding-color"; //$NON-NLS-1$
  public static final String CREATE_TOTALCOLUMN_PROP = "create-total-column"; //$NON-NLS-1$
  public static final String COLUMN_HEADER_BACKGROUND_COLOR_PROP = "column-header-background-color"; //$NON-NLS-1$
  public static final String COLUMN_HEADER_FOREGROUND_COLOR_PROP = "column-header-foreground-color"; //$NON-NLS-1$
  public static final String COLUMN_HEADER_FONT_FACE_PROP = "column-header-font-face"; //$NON-NLS-1$
  public static final String COLUMN_HEADER_FONT_SIZE_PROP = "column-header-font-size"; //$NON-NLS-1$
  public static final String COLUMN_HEADER_GAP_PROP = "column-header-gap"; //$NON-NLS-1$
  public static final String SPACER_WIDTH_PROP = "spacer-width"; //$NON-NLS-1$
  public static final String REPORT_DEFINITION = "report-definition"; //$NON-NLS-1$
  
  protected static final String[] EXPECTED_INPUTS = new String[] {
    RESULT_SET,
    COMPONENT_SETTINGS
  };
  
  public JFreeReportGenAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public JFreeReportGenAction() {
    super(COMPONENT_NAME);
  }
  
  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }
  
  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
  
  /*
   * Get the element from the COMPONENT_SETTINGS or ComponentDef section XML part. 
   */
  private IActionInputValueProvider getComponentValue(String elementName) {
	  IActionInputValueProvider value = getActionInputValue(elementName);
	  try {
		  if (value == IActionInputValueProvider.NULL_INPUT) {
			value = getActionInputValue(COMPONENT_SETTINGS);
			Document doc = DocumentHelper.parseText(value.getStringValue());
		    Node componentNode = doc.getRootElement();	      
		    value = new ActionInputConstant(componentNode.selectSingleNode(elementName).getText());
		  }
	  } catch (Exception e) {
		  value = ActionInputConstant.NULL_INPUT;
	  } 
	  return value;
  }

  public void setResultSet(IActionInputValueProvider value) {
    setActionInputValue(RESULT_SET, value);
  }
  
  public IActionInputValueProvider getResultSet() {
    return getComponentValue(RESULT_SET);
  }
  
  public IActionInputValueProvider getComponentSettings() {
    return getActionInputValue(COMPONENT_SETTINGS);
  }

  public void setTemplatePath(IActionInputValueProvider value) {
    setActionInputValue(TEMPLATE_PATH_PROP, value);
  }
  
  public IActionInputValueProvider getTemplatePath() {
    return getComponentValue(TEMPLATE_PATH_PROP);
  }

  public void setOrientation(IActionInputValueProvider value) {
    setActionInputValue(ORIENTATION_PROP, value);
  }
  
  public IActionInputValueProvider getOrientation() {
    return getComponentValue(ORIENTATION_PROP);
  }

  public void setNullString(IActionInputValueProvider value) {
    setActionInputValue(NULL_STRING_PROP, value);
  }
  
  public IActionInputValueProvider getNullStrin() {
    return getComponentValue(NULL_STRING_PROP);
  }

  public void setHorizontalOffset(IActionInputValueProvider value) {
    setActionInputValue(HORIZONTAL_OFFSET_PROP, value);
  }
  
  public IActionInputValueProvider getHorizontalOffset() {
    return getComponentValue(HORIZONTAL_OFFSET_PROP);
  }

  public void setReportName(IActionInputValueProvider value) {
    setActionInputValue(REPORTNAME_PROP, value);
  }
  
  public IActionInputValueProvider getReportName() {
    return getComponentValue(REPORTNAME_PROP);
  }

  public void setCreateSubTotals(IActionInputValueProvider value) {
    setActionInputValue(CREATE_SUBTOTALS_PROP, value);
  }
  
  public IActionInputValueProvider getCreateSubTotals() {
    return getComponentValue(CREATE_SUBTOTALS_PROP);
  } 
  
  public void setCreateGrandTotals(IActionInputValueProvider value) {
    setActionInputValue(CREATE_GRANDTOTALS_PROP, value);
  }
  
  public IActionInputValueProvider getCreateGrandTotals() {
    return getComponentValue(CREATE_GRANDTOTALS_PROP);
  } 
  
  public void setCreateRowBanding(IActionInputValueProvider value) {
    setActionInputValue(CREATE_ROWBANDING_PROP, value);
  }
  
  public IActionInputValueProvider getCreateRowBanding() {
    return getComponentValue(CREATE_ROWBANDING_PROP);
  }   
  
  public void setTotalColumnName(IActionInputValueProvider value) {
    setActionInputValue(TOTALCOLUMN_NAME_PROP, value);
  }
  
  public IActionInputValueProvider getTotalColumnName() {
    return getComponentValue(CREATE_ROWBANDING_PROP);
  }   
  
  public void setTotalColumnWidth(IActionInputValueProvider value) {
    setActionInputValue(TOTALCOLUMN_WIDTH_PROP, value);
  }
  
  public IActionInputValueProvider getTotalColumnWidth() {
    return getComponentValue(TOTALCOLUMN_WIDTH_PROP);
  }   
  
  public void setTotalColumnFormat(IActionInputValueProvider value) {
    setActionInputValue(TOTALCOLUMN_FORMAT_PROP, value);
  }
  
  public IActionInputValueProvider getTotalColumnFormat() {
    return getComponentValue(TOTALCOLUMN_FORMAT_PROP);
  }     
  
  public void setRowBandingColor(IActionInputValueProvider value) {
    setActionInputValue(ROWBANDING_COLOR_PROP, value);
  }
  
  public IActionInputValueProvider getRowBandingColor() {
    return getComponentValue(ROWBANDING_COLOR_PROP);
  }     

  public void setCreateTotalColumn(IActionInputValueProvider value) {
    setActionInputValue(CREATE_TOTALCOLUMN_PROP, value);
  }
  
  public IActionInputValueProvider getCreateTotalColumn() {
    return getComponentValue(CREATE_TOTALCOLUMN_PROP);
  }     

  public void setColumnHeaderBackgroundColor(IActionInputValueProvider value) {
    setActionInputValue(COLUMN_HEADER_BACKGROUND_COLOR_PROP, value);
  }
  
  public IActionInputValueProvider getColumnHeaderBackgroundColor() {
    return getComponentValue(COLUMN_HEADER_BACKGROUND_COLOR_PROP);
  }     

  public void setColumnHeaderForegroundColor(IActionInputValueProvider value) {
    setActionInputValue(COLUMN_HEADER_FOREGROUND_COLOR_PROP, value);
  }
  
  public IActionInputValueProvider getColumnHeaderForegroundColor() {
    return getComponentValue(COLUMN_HEADER_FOREGROUND_COLOR_PROP);
  }     

  public void setColumnHeaderFontFace(IActionInputValueProvider value) {
    setActionInputValue(COLUMN_HEADER_FONT_FACE_PROP, value);
  }
  
  public IActionInputValueProvider getColumnHeaderFontFace() {
    return getComponentValue(COLUMN_HEADER_FONT_FACE_PROP);
  }     

  public void setColumnHeaderFontSize(IActionInputValueProvider value) {
    setActionInputValue(COLUMN_HEADER_FONT_SIZE_PROP, value);
  }
  
  public IActionInputValueProvider getColumnHeaderFontSize() {
    return getComponentValue(COLUMN_HEADER_FONT_SIZE_PROP);
  }     

  public void setColumnHeaderGap(IActionInputValueProvider value) {
    setActionInputValue(COLUMN_HEADER_GAP_PROP, value);
  }
  
  public IActionInputValueProvider getColumnHeaderGap() {
    return getComponentValue(COLUMN_HEADER_GAP_PROP);
  }     

  public void setSpacerWidth(IActionInputValueProvider value) {
    setActionInputValue(SPACER_WIDTH_PROP, value);
  }
  
  public IActionInputValueProvider getSpacerWidth() {
    return getComponentValue(SPACER_WIDTH_PROP);
  }
  
  public void setOutputReportDefinition(String publicOutputName) {
    setOutputParam(REPORT_DEFINITION, publicOutputName, ActionSequenceDocument.CONTENT_TYPE);
  }
  
  public ActionOutput getOutputReportDefinition() {
	  return getOutputParam(REPORT_DEFINITION);
  }
}
