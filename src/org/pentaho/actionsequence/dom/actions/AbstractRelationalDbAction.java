package org.pentaho.actionsequence.dom.actions;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionVariable;

public abstract class AbstractRelationalDbAction extends ActionDefinition {

  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String QUERY_NAME_ELEMENT = "query-name"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String JNDI_ELEMENT = "jndi"; //$NON-NLS-1$
  public static final String DRIVER_ELEMENT = "driver"; //$NON-NLS-1$
  public static final String CONNECTION_ELEMENT = "connection"; //$NON-NLS-1$
  public static final String USER_ID_ELEMENT = "user-id"; //$NON-NLS-1$
  public static final String PASSWORD_ELEMENT = "password"; //$NON-NLS-1$
  public static final String TRANSFORM_ELEMENT = "transform"; //$NON-NLS-1$
  public static final String TRANSFORM_SORT_COLUMN_ELEMENT = "sort-by-col"; //$NON-NLS-1$
  public static final String TRANSFORM_PIVOT_COLUMN_ELEMENT = "pivot-column"; //$NON-NLS-1$
  public static final String TRANSFORM_MEASURES_COLUMN_ELEMENT = "measures-column"; //$NON-NLS-1$
  public static final String TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT = "format-type";
  public static final String TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT = "format-string";
  public static final String TRANSFORM_SORT_FORMAT_TYPE_ELEMENT = "sort-format-type";
  public static final String TRANSFORM_SORT_FORMAT_STRING_ELEMENT = "sort-format-string";
  public static final String TRANSFORM_ORDERED_MAPS = "ordered-maps"; //$NON-NLS-1$
  public static final String DECIMAL_FORMAT_TYPE = "decimal";
  public static final String DATE_FORMAT_TYPE = "decimal";
  public static final String QUERY_RESULT_ELEMENT = "query-result"; //$NON-NLS-1$
  public static final String OUTPUT_NAME_ELEMENT = "output-name"; //$NON-NLS-1$
  public static final String MAX_ROWS_ELEMENT = "max_rows"; //$NON-NLS-1$
  public static final String LIVE_CONNECTION_ELEMENT = "live"; //$NON-NLS-1$
  public static final String DB_URL_NAME = "db-url"; //$NON-NLS-1$
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  public static final String OUTPUT_PREPARED_STATEMENT = "output-prepared_statement"; //$NON-NLS-1$
  public static final String SHARED_CONNECTION = "shared-connection"; //$NON-NLS-1$
  public static final String RESULTSET_FORWARD_ONLY = "forward-only"; //$NON-NLS-1$
  
  public AbstractRelationalDbAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public AbstractRelationalDbAction(String componentName) {
    super(componentName);
  }

  public String getQuery() {
    Object query = getInputValue(QUERY_ELEMENT);
    
    // This is deprecated functionality for determining the name of the query input parameter.
    if (query == null) {
      Object queryName = getInputValue(QUERY_NAME_ELEMENT);
      if (queryName != null) {
        query = getInputValue(queryName.toString());
      }
    }
    // End deprecated functionality
    
    if ((query != null) && (actionParameterMgr != null)) {
      query = actionParameterMgr.replaceParameterReferences(query.toString());
    }
    return query != null ? query.toString() : (String)query;
  }
  
  public void setQueryParam(IActionVariable variable) {
    setInputParam(QUERY_ELEMENT, variable);
    
    // Remove deprecated method of determining query that may exist.
    setInputValue(QUERY_NAME_ELEMENT, null);
  }
  
  public ActionInput getQueryParam() {
    ActionInput actionInput = getInputParam(QUERY_ELEMENT);
    
    // This is deprecated functionality for determining the name of the query input parameter.
    if (actionInput == null) {
      Object queryName = getInputValue(QUERY_NAME_ELEMENT);
      if (queryName != null) {
        actionInput = getInputParam(queryName.toString());
      }
    }
    // End deprecated functionality
    
    return actionInput;
  }
  
  public void setQuery(String value) {
    setInputValue(QUERY_ELEMENT, value);
    
    // Remove deprecated method of determining query that may exist. 
    setInputValue(QUERY_NAME_ELEMENT, null);
  }
  
  public void setSharedConnectionParam(IActionVariable variable) {
    setInputParam(PREPARED_COMPONENT_ELEMENT, variable);
    if (variable != null) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
    }
  }
  
  public Object getSharedConnection() {
    Object connection = getInputValue(PREPARED_COMPONENT_ELEMENT);
    return connection;
  }
  
  public ActionInput getSharedConnectionParam() {
    return getInputParam(PREPARED_COMPONENT_ELEMENT);
  }
  
  public void setJndi(String value) {
    setInputValue(JNDI_ELEMENT, value);
    if (value != null) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
      setSharedConnectionParam(null);
    }
  }
  
  public String getJndi() {
    Object jndiName = getInputValue(JNDI_ELEMENT);
    if ((jndiName != null) && (actionParameterMgr != null)) {
      jndiName = actionParameterMgr.replaceParameterReferences(jndiName.toString());
    }
    return jndiName != null ? jndiName.toString() : (String)jndiName;
  }
  
  public void setJndiParam(IActionVariable variable) {
    setInputParam(JNDI_ELEMENT, variable);
    if (variable != null) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
      setSharedConnectionParam(null);
    }
  }
  
  public ActionInput getJndiParam() {
    return getInputParam(JNDI_ELEMENT);
  }
  
  public void setDriver(String value) {
    setInputValue(DRIVER_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setSharedConnectionParam(null);
    }
  }
  
  public String getDriver() {
    Object driver = getInputValue(DRIVER_ELEMENT);
    if ((driver != null) && (actionParameterMgr != null)) {
      driver = actionParameterMgr.replaceParameterReferences(driver.toString());
    }
    return driver != null ? driver.toString() : (String)driver;
  }
  
  public void setDriverParam(IActionVariable variable) {
    setInputParam(DRIVER_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setSharedConnectionParam(null);
    }
  }
  
  public ActionInput getDriverParam() {
    return getInputParam(DRIVER_ELEMENT);
  }
  
  public void setDbUrl(String value) {
    setInputValue(CONNECTION_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setSharedConnectionParam(null);
    }
  }
  
  public String getDbUrl() {
    Object url = getInputValue(CONNECTION_ELEMENT);
    if ((url != null) && (actionParameterMgr != null)) {
      url = actionParameterMgr.replaceParameterReferences(url.toString());
    }
    return url != null ? url.toString() : (String)url;
  }
  
  public void setDbUrlParam(IActionVariable variable) {
    setInputParam(CONNECTION_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setSharedConnectionParam(null);
    }
  }
  
  public ActionInput getDbUrlParam() {
    return getInputParam(CONNECTION_ELEMENT);
  }
  
  public void setUserId(String value) {
    setInputValue(USER_ID_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setSharedConnectionParam(null);
    }
  }
  
  public String getUserId() {
    Object userId = getInputValue(USER_ID_ELEMENT);
    if ((userId != null) && (actionParameterMgr != null)) {
      userId = actionParameterMgr.replaceParameterReferences(userId.toString());
    }
    return userId != null ? userId.toString() : (String)userId;
  }
  
  public void setUserIdParam(IActionVariable variable) {
    setInputParam(USER_ID_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setSharedConnectionParam(null);
    }
  }
  
  public ActionInput getUserIdParam() {
    return getInputParam(USER_ID_ELEMENT);
  }
  
  public void setPassword(String value) {
    setInputValue(PASSWORD_ELEMENT, value);
    if (value != null) {
      setJndi(null);
      setSharedConnectionParam(null);
    }
  }
  
  public String getPassword() {
    Object password = getInputValue(PASSWORD_ELEMENT);
    if ((password != null) && (actionParameterMgr != null)) {
      password = actionParameterMgr.replaceParameterReferences(password.toString());
    }
    return password != null ? password.toString() : (String)password;
  }
  
  public void setPasswordParam(IActionVariable variable) {
    setInputParam(PASSWORD_ELEMENT, variable);
    if (variable != null) {
      setJndi(null);
      setSharedConnectionParam(null);
    }
  }
  
  public ActionInput getPasswordParam() {
    return getInputParam(PASSWORD_ELEMENT);
  }
  
  public void setPerformTransform(boolean value) {
    setInputValue(TRANSFORM_ELEMENT, Boolean.toString(value));
    if (!value) {
      setTransformMeasuresColumn(-1);
      setTransformPivotColumn(-1);
      setTransformSortColumn(-1);
      setTransformPivotDataFormat(null);
      setTransformSortDataFormat(null);
    }
  }
  
  public boolean getPerformTransform() {
    Object performTransform = getInputValue(TRANSFORM_ELEMENT);
    if ((performTransform != null) && (actionParameterMgr != null)) {
      performTransform = actionParameterMgr.replaceParameterReferences(performTransform.toString());
    }
    return performTransform != null ? Boolean.parseBoolean(performTransform.toString()) : false;
  }
  
  public void setPerformTransformParam(IActionVariable variable) {
    setInputParam(TRANSFORM_ELEMENT, variable);
  }
  
  public ActionInput getPerformTransformParam() {
    return getInputParam(TRANSFORM_ELEMENT);
  }
  
  public void setTransformPivotColumn(int columnIdx) {
    if (columnIdx < 0) {
      setInputValue(TRANSFORM_PIVOT_COLUMN_ELEMENT, null); 
    } else {
      setInputValue(TRANSFORM_PIVOT_COLUMN_ELEMENT, Integer.toString(columnIdx));
    }
  }
  
  public int getTransformPivotColumn() {
    int columnIdx = -1;
    Object value = getInputValue(TRANSFORM_PIVOT_COLUMN_ELEMENT);
    if ((value != null) && (actionParameterMgr != null)) {
      value = actionParameterMgr.replaceParameterReferences(value.toString());
    }
    if (value != null) {
      try {
        columnIdx = Integer.parseInt(value.toString());
      } catch (Exception ex) {
        //Do nothing.
      }
    }
    return columnIdx;
  }
  
  public void setTransformPivotColumnParam(IActionVariable variable) {
    setInputParam(TRANSFORM_PIVOT_COLUMN_ELEMENT, variable);
  }
  
  public ActionInput getTransformPivotColumnParam() {
    return getInputParam(TRANSFORM_PIVOT_COLUMN_ELEMENT);
  }
  
  public void setTransformMeasuresColumn(int columnIdx) {
    if (columnIdx < 0) {
      setInputValue(TRANSFORM_MEASURES_COLUMN_ELEMENT, null); 
    } else {
      setInputValue(TRANSFORM_MEASURES_COLUMN_ELEMENT, Integer.toString(columnIdx));
    }
  }
  
  public int getTransformMeasuresColumn() {
    int columnIdx = -1;
    Object value = getInputValue(TRANSFORM_MEASURES_COLUMN_ELEMENT);
    if ((value != null) && (actionParameterMgr != null)) {
      value = actionParameterMgr.replaceParameterReferences(value.toString());
    }
    if (value != null) {
      try {
        columnIdx = Integer.parseInt(value.toString());
      } catch (Exception ex) {
        //Do nothing.
      }
    }
    return columnIdx;
  }
  
  public void setTransformMeasuresColumnParam(IActionVariable variable) {
    setInputParam(TRANSFORM_MEASURES_COLUMN_ELEMENT, variable);
  }
  
  public ActionInput getTransformMeasuresColumnParam() {
    return getInputParam(TRANSFORM_MEASURES_COLUMN_ELEMENT);
  }
  
  public void setTransformPivotDataFormat(Format value) {
    if (value == null) {
      setInputValue(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT, null);
      setInputValue(TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT, null);
    } else if (value instanceof DecimalFormat) {
      setInputValue(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT, ((DecimalFormat)value).toPattern());
      setInputValue(TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT, DECIMAL_FORMAT_TYPE);
    } else if (value instanceof SimpleDateFormat) {
      setInputValue(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT, ((SimpleDateFormat)value).toPattern());
      setInputValue(TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT, DATE_FORMAT_TYPE);
    }
  }
  
  public Format getTransformPivotDataFormat() {
    return getFormat(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT);
  }
  
  public void setTransformPivotDataFormatParam(IActionVariable variable) {
    setInputParam(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT, variable);
  }
  
  public ActionInput getTransformPivotDataFormatParam() {
    return getInputParam(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT);
  }
  
  public void setTransformSortDataFormat(Format value) {
    if (value == null) {
      setInputValue(TRANSFORM_SORT_FORMAT_STRING_ELEMENT, null);
      setInputValue(TRANSFORM_SORT_FORMAT_TYPE_ELEMENT, null);
    } else if (value instanceof DecimalFormat) {
      setInputValue(TRANSFORM_SORT_FORMAT_STRING_ELEMENT, ((DecimalFormat)value).toPattern());
      setInputValue(TRANSFORM_SORT_FORMAT_TYPE_ELEMENT, DECIMAL_FORMAT_TYPE);
    } else if (value instanceof SimpleDateFormat) {
      setInputValue(TRANSFORM_SORT_FORMAT_STRING_ELEMENT, ((SimpleDateFormat)value).toPattern());
      setInputValue(TRANSFORM_SORT_FORMAT_TYPE_ELEMENT, DATE_FORMAT_TYPE);
    }
  }
  
  public Format getTransformSortDataFormat() {
    return getFormat(TRANSFORM_SORT_FORMAT_STRING_ELEMENT);
  }
  
  public void setTransformSortDataFormatParam(IActionVariable variable) {
    setInputParam(TRANSFORM_SORT_FORMAT_STRING_ELEMENT, variable);
  }
  
  public ActionInput getTransformSortDataFormatParam() {
    return getInputParam(TRANSFORM_SORT_FORMAT_STRING_ELEMENT);
  }
  
  public void setTransformSortColumn(int columnIdx) {
    if (columnIdx < 0) {
      setInputValue(TRANSFORM_SORT_COLUMN_ELEMENT, null); 
    } else {
      setInputValue(TRANSFORM_SORT_COLUMN_ELEMENT, Integer.toString(columnIdx));
    }
  }
  
  public int getTransformSortColumn() {
    int columnIdx = -1;
    Object value = getInputValue(TRANSFORM_SORT_COLUMN_ELEMENT);
    if ((value != null) && (actionParameterMgr != null)) {
      value = actionParameterMgr.replaceParameterReferences(value.toString());
    }
    if (value != null) {
      try {
        columnIdx = Integer.parseInt(value.toString());
      } catch (Exception ex) {
        //Do nothing.
      }
    }
    return columnIdx;
  }
  
  public void setTransformSortColumnParam(IActionVariable variable) {
    setInputParam(TRANSFORM_SORT_COLUMN_ELEMENT, variable);
  }
  
  public ActionInput getTransformSortColumnParam() {
    return getInputParam(TRANSFORM_SORT_COLUMN_ELEMENT);
  }
  
  public void setTransformOrderOutputColumns(boolean value) {
    setInputValue(TRANSFORM_ORDERED_MAPS, Boolean.toString(value));
  }
  
  public boolean getTransformOrderOutputColumns() {
    Object performTransform = getInputValue(TRANSFORM_ORDERED_MAPS);
    if ((performTransform != null) && (actionParameterMgr != null)) {
      performTransform = actionParameterMgr.replaceParameterReferences(performTransform.toString());
    }
    return performTransform != null ? Boolean.parseBoolean(performTransform.toString()) : false;
  }
  
  public void setTransformOrderOutputColumnsParam(IActionVariable variable) {
    setInputParam(TRANSFORM_ORDERED_MAPS, variable);
  }
  
  public ActionInput getTransformOrderOutputColumnsParam() {
    return getInputParam(TRANSFORM_ORDERED_MAPS);
  }
  
  private Format getFormat(String elementName) {
    Format format = null;
    Object formatPattern = getInputValue(elementName);
    if ((formatPattern != null) && (actionParameterMgr != null)) {
      formatPattern = actionParameterMgr.replaceParameterReferences(formatPattern.toString());
    }
    if (formatPattern != null) {
      try {
        format = new DecimalFormat(formatPattern.toString());
      } catch (Exception ex) {
        try {
          format = new SimpleDateFormat(formatPattern.toString());
        } catch (Exception ex2) {
        }
      }
    }
    return format;
  }
  
  public void setOutputResultSetName(String name) {
    // This removes deprecated functionality.
    setInputValue(OUTPUT_NAME_ELEMENT, null);
    // End deprecated functionality.
    
    setOutputParam(QUERY_RESULT_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputPreparedStatementName(null);
    }
  }
  
  public String getOutputResultSetName() {
    String publicName = null;
    ActionOutput actionOutput = getOutputResultSetParam();
    if (actionOutput != null) {
      publicName = actionOutput.getPublicName();
    }
    return publicName;
  }
  
  public ActionOutput getOutputResultSetParam() {
    // This is deprecated functionality.
    Object outputName = getInputValue(OUTPUT_NAME_ELEMENT);
    if (outputName == null) {
      outputName = QUERY_RESULT_ELEMENT;
    }
    // End deprecated functionality.
    
    ActionOutput actionOutput = getOutputParam(outputName.toString());
    
    // More deprecated functionality.
    if (actionOutput == null) {
      ActionOutput[] actionOutputs = getAllOutputParams();
      if (actionOutputs.length > 0) {
        actionOutput = actionOutputs[0];
      }
    }
    // End deprecated functionality.
    
    return actionOutput;
  }
  
  public void setOutputResultSet(Object object) {
    ActionOutput actionOutput = getOutputResultSetParam();
    if (actionOutput != null) {
      actionOutput.setValue(object);
    }
  }
  
  public void setOutputPreparedStatement(Object object) {
    ActionOutput actionOutput = getOutputPreparedStatementParam();
    if (actionOutput != null) {
      actionOutput.setValue(object);
    }
  }
  
  public void setMaxRows(int rows) {
    if (rows < 0) {
      setInputValue(MAX_ROWS_ELEMENT, null); 
    } else {
      setInputValue(MAX_ROWS_ELEMENT, Integer.toString(rows));
    }
  }
  
  public int getMaxRows() {
    int maxRows = -1;
    Object value = getInputValue(MAX_ROWS_ELEMENT);
    if ((value != null) && (actionParameterMgr != null)) {
      value = actionParameterMgr.replaceParameterReferences(value.toString());
    }
    if (value != null) {
      try {
        maxRows = Integer.parseInt(value.toString());
      } catch (Exception ex) {
        //Do nothing.
      }
    }
    return maxRows;
  }
  
  public ActionInput getMaxRowsParam() {
    return getInputParam(MAX_ROWS_ELEMENT);
  }
  
  public void setMaxRowsParams(IActionVariable variable) {
    setInputParam(MAX_ROWS_ELEMENT, variable);
  }
  
  public void setOutputPreparedStatementName(String name) {
    setOutputParam(PREPARED_COMPONENT_ELEMENT, name, ActionSequenceDocument.SQL_QUERY_TYPE);
    if ((name != null) && (name.trim().length() > 0)) {
      setOutputResultSetName(null);
      ActionOutput[] actionOutputs = getAllOutputParams();
      for (int i = 0; i < actionOutputs.length; i++) {
        if (!actionOutputs[i].getType().equals(ActionSequenceDocument.SQL_QUERY_TYPE)) {
          actionOutputs[i].delete();
        }
      }
    }
  }
  
  public String getOutputPreparedStatementName() {
    return getPublicOutputName(PREPARED_COMPONENT_ELEMENT);
  }
  
  public ActionOutput getOutputPreparedStatementParam() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }
  
  public void setLive(boolean value) {
    setInputValue(LIVE_CONNECTION_ELEMENT, Boolean.toString(value)); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getLive() {
    Object live = getInputValue(LIVE_CONNECTION_ELEMENT);
    if ((live != null) && (actionParameterMgr != null)) {
      live = actionParameterMgr.replaceParameterReferences(live.toString());
    }
    return live != null ? Boolean.parseBoolean(live.toString()) : false;
  }
  
  public void setLiveParam(IActionVariable variable) {
    setInputParam(LIVE_CONNECTION_ELEMENT, variable);
  }
  
  public ActionInput getLiveParam() {
    return getInputParam(LIVE_CONNECTION_ELEMENT);
  }
  
  public void setUseForwardOnlyResultSet(boolean value) {
    setInputValue(RESULTSET_FORWARD_ONLY, Boolean.toString(value)); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean getUseForwardOnlyResultSet() {
    Object useForwardOnly = getInputValue(RESULTSET_FORWARD_ONLY);
    if ((useForwardOnly != null) && (actionParameterMgr != null)) {
      useForwardOnly = actionParameterMgr.replaceParameterReferences(useForwardOnly.toString());
    }
    return useForwardOnly != null ? Boolean.parseBoolean(useForwardOnly.toString()) : false;
  }
  
  public void setUseForwardOnlyResultSetParam(IActionVariable variable) {
    setInputParam(RESULTSET_FORWARD_ONLY, variable);
  }
  
  public ActionInput getUseForwardOnlyResultSetParam() {
    return getInputParam(RESULTSET_FORWARD_ONLY);
  }
}
