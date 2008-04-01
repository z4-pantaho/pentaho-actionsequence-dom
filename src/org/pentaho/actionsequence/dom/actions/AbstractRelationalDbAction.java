package org.pentaho.actionsequence.dom.actions;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionOutput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInputValueProvider;
import org.pentaho.actionsequence.dom.IActionInputVariable;

public abstract class AbstractRelationalDbAction extends ActionDefinition {

  public class FormatInput extends ActionInput {

    FormatInput(Element element, IActionParameterMgr actionParameterMgr) {
      super(element, actionParameterMgr);
    }

    public Object getValue() {
      Format format = null;
      String formatPattern = getStringValue();
      if (formatPattern != null) {
        format = getFormat(formatPattern);
      }
      return format;
    }
  }

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
  public static final String TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT = "format-type"; //$NON-NLS-1$
  public static final String TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT = "format-string"; //$NON-NLS-1$
  public static final String TRANSFORM_SORT_FORMAT_TYPE_ELEMENT = "sort-format-type"; //$NON-NLS-1$
  public static final String TRANSFORM_SORT_FORMAT_STRING_ELEMENT = "sort-format-string"; //$NON-NLS-1$
  public static final String TRANSFORM_ORDERED_MAPS = "ordered-maps"; //$NON-NLS-1$
  public static final String DECIMAL_FORMAT_TYPE = "decimal"; //$NON-NLS-1$
  public static final String DATE_FORMAT_TYPE = "decimal"; //$NON-NLS-1$
  public static final String QUERY_RESULT_ELEMENT = "query-result"; //$NON-NLS-1$
  public static final String OUTPUT_NAME_ELEMENT = "output-name"; //$NON-NLS-1$
  public static final String MAX_ROWS_ELEMENT = "max_rows"; //$NON-NLS-1$
  public static final String LIVE_CONNECTION_ELEMENT = "live"; //$NON-NLS-1$
  public static final String DB_URL_NAME = "db-url"; //$NON-NLS-1$
  public static final String OUTPUT_RESULT_SET = "output-result-set"; //$NON-NLS-1$
  public static final String OUTPUT_PREPARED_STATEMENT = "output-prepared_statement"; //$NON-NLS-1$
  public static final String SHARED_CONNECTION = "shared-connection"; //$NON-NLS-1$
  public static final String RESULTSET_FORWARD_ONLY = "forward-only"; //$NON-NLS-1$
  //Added by Arijit Chatterjee "timeout in xaction file"
  public static final String TIMEOUT = "timeout"; //$NON-NLS-1$

  public AbstractRelationalDbAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public AbstractRelationalDbAction(String componentName) {
    super(componentName);
  }

  public IActionInputValueProvider getQuery() {
    IActionInputValueProvider actionInput = getActionInputValue(QUERY_ELEMENT);

    // This is deprecated functionality for determining the name of the query input parameter.
    if (actionInput.getValue() == null) {
      Object queryName = getActionInputValue(QUERY_NAME_ELEMENT).getValue();
      if (queryName != null) {
        actionInput = getActionInputValue(queryName.toString());
      }
    }
    // End deprecated functionality

    return actionInput;
  }

  public void setQuery(IActionInputValueProvider value) {
    setActionInputValue(QUERY_ELEMENT, value);

    // Remove deprecated method of determining query that may exist. 
    setActionInputValue(QUERY_NAME_ELEMENT, null);
  }

  //Added by Arijit Chatterjee "Sets the value of timeout provided in xaction file"
  public void setQueryTimeout(IActionInputValueProvider value) {
    setActionInputValue(TIMEOUT, value);
  }

  //Added by Arijit Chatterjee. Gets the value of TIMEOUT
  public IActionInputValueProvider getQueryTimeout() {
    return getActionInputValue(TIMEOUT);
  }

  public void setSharedConnection(IActionInputValueProvider value) {
    if (value instanceof ActionInputConstant) {
      throw new IllegalArgumentException();
    }
    setActionInputValue(PREPARED_COMPONENT_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
      setJndi(null);
    }
  }

  public IActionInputValueProvider getSharedConnection() {
    return getActionInputValue(PREPARED_COMPONENT_ELEMENT);
  }

  public void setJndi(IActionInputValueProvider value) {
    setActionInputValue(JNDI_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setDriver(null);
      setDbUrl(null);
      setUserId(null);
      setPassword(null);
      setSharedConnection(null);
    }
  }

  public IActionInputValueProvider getJndi() {
    return getActionInputValue(JNDI_ELEMENT);
  }

  public void setDriver(IActionInputValueProvider value) {
    setActionInputValue(DRIVER_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
      setSharedConnection(null);
    }
  }

  public IActionInputValueProvider getDriver() {
    return getActionInputValue(DRIVER_ELEMENT);
  }

  public void setDbUrl(IActionInputValueProvider value) {
    setActionInputValue(CONNECTION_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
      setSharedConnection(null);
    }
  }

  public IActionInputValueProvider getDbUrl() {
    return getActionInputValue(CONNECTION_ELEMENT);
  }

  public void setUserId(IActionInputValueProvider value) {
    setActionInputValue(USER_ID_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
      setSharedConnection(null);
    }
  }

  public IActionInputValueProvider getUserId() {
    return getActionInputValue(USER_ID_ELEMENT);
  }

  public void setPassword(IActionInputValueProvider value) {
    setActionInputValue(PASSWORD_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setJndi(null);
      setSharedConnection(null);
    }
  }

  public IActionInputValueProvider getPassword() {
    return getActionInputValue(PASSWORD_ELEMENT);
  }

  public void setPerformTransform(IActionInputValueProvider value) {
    setActionInputValue(TRANSFORM_ELEMENT, value);
    if ((value instanceof IActionInputVariable) || ((value != null) && (value.getValue() != null))) {
      setTransformMeasuresColumn(null);
      setTransformPivotColumn(null);
      setTransformSortColumn(null);
      setTransformPivotDataFormat(null);
      setTransformSortDataFormat(null);
    }
  }

  public IActionInputValueProvider getPerformTransform() {
    return getActionInputValue(TRANSFORM_ELEMENT);
  }

  public void setTransformPivotColumn(IActionInputValueProvider value) {
    setActionInputValue(TRANSFORM_PIVOT_COLUMN_ELEMENT, value);
  }

  public IActionInputValueProvider getTransformPivotColumn() {
    return getActionInputValue(TRANSFORM_PIVOT_COLUMN_ELEMENT);
  }

  public void setTransformMeasuresColumn(IActionInputValueProvider value) {
    setActionInputValue(TRANSFORM_MEASURES_COLUMN_ELEMENT, value);
  }

  public IActionInputValueProvider getTransformMeasuresColumn() {
    return getActionInputValue(TRANSFORM_MEASURES_COLUMN_ELEMENT);
  }

  public void setTransformPivotDataFormat(IActionInputValueProvider value) {
    if ((value == null) || ((value instanceof ActionInputConstant) && (value.getValue() == null))) {
      setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT, null);
      setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT, null);
    } else if (value instanceof ActionInputConstant) {
      Object object = value.getValue();
      if (object instanceof DecimalFormat) {
        setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT, new ActionInputConstant(((DecimalFormat) object).toPattern()));
        setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT, new ActionInputConstant(DECIMAL_FORMAT_TYPE));
      } else if (object instanceof SimpleDateFormat) {
        setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT, new ActionInputConstant(((SimpleDateFormat) object).toPattern()));
        setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT, new ActionInputConstant(DATE_FORMAT_TYPE));
      } else {
        setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT, new ActionInputConstant(object));
        setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT, null);
      }
    } else {
      setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT, value);
      setActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_TYPE_ELEMENT, null);
    }
  }

  public IActionInputValueProvider getTransformPivotDataFormat() {
    IActionInputValueProvider actionInput = getActionInputValue(TRANSFORM_PIVOT_DATA_FORMAT_STRING_ELEMENT);
    if (actionInput instanceof ActionInput) {
      actionInput = new FormatInput(((ActionInput) actionInput).getElement(), ((ActionInput) actionInput).getParameterMgr());
    } else if (actionInput instanceof ActionInputConstant) {
      Format format = null;
      String formatPattern = actionInput.getStringValue();
      if (formatPattern != null) {
        format = getFormat(formatPattern);
      }
      if (format != null) {
        actionInput = new ActionInputConstant(format, actionParameterMgr);
      }
    }
    return actionInput;
  }

  public void setTransformSortDataFormat(IActionInputValueProvider value) {
    if ((value == null) || ((value instanceof ActionInputConstant) && (value.getValue() == null))) {
      setInputValue(TRANSFORM_SORT_FORMAT_STRING_ELEMENT, null);
      setInputValue(TRANSFORM_SORT_FORMAT_TYPE_ELEMENT, null);
    } else if (value instanceof ActionInputConstant) {
      Object object = value.getValue();
      if (object instanceof DecimalFormat) {
        setActionInputValue(TRANSFORM_SORT_FORMAT_STRING_ELEMENT, new ActionInputConstant(((DecimalFormat) object).toPattern()));
        setActionInputValue(TRANSFORM_SORT_FORMAT_TYPE_ELEMENT, new ActionInputConstant(DECIMAL_FORMAT_TYPE));
      } else if (object instanceof SimpleDateFormat) {
        setActionInputValue(TRANSFORM_SORT_FORMAT_STRING_ELEMENT, new ActionInputConstant(((SimpleDateFormat) object).toPattern()));
        setActionInputValue(TRANSFORM_SORT_FORMAT_TYPE_ELEMENT, new ActionInputConstant(DATE_FORMAT_TYPE));
      } else {
        setActionInputValue(TRANSFORM_SORT_FORMAT_STRING_ELEMENT, new ActionInputConstant(object));
        setActionInputValue(TRANSFORM_SORT_FORMAT_TYPE_ELEMENT, null);
      }
    } else {
      setActionInputValue(TRANSFORM_SORT_FORMAT_STRING_ELEMENT, value);
      setActionInputValue(TRANSFORM_SORT_FORMAT_TYPE_ELEMENT, null);
    }
  }

  public IActionInputValueProvider getTransformSortDataFormat() {
    IActionInputValueProvider actionInput = getActionInputValue(TRANSFORM_SORT_FORMAT_STRING_ELEMENT);
    if (actionInput instanceof ActionInput) {
      actionInput = new FormatInput(((ActionInput) actionInput).getElement(), ((ActionInput) actionInput).getParameterMgr());
    } else if (actionInput instanceof ActionInputConstant) {
      Format format = null;
      String formatPattern = actionInput.getStringValue();
      if (formatPattern != null) {
        format = getFormat(formatPattern);
      }
      if (format != null) {
        actionInput = new ActionInputConstant(format, actionParameterMgr);
      }
    }
    return actionInput;
  }

  public void setTransformSortColumn(IActionInputValueProvider value) {
    setActionInputValue(TRANSFORM_SORT_COLUMN_ELEMENT, value);
  }

  public IActionInputValueProvider getTransformSortColumn() {
    return getActionInputValue(TRANSFORM_SORT_COLUMN_ELEMENT);
  }

  public void setTransformOrderOutputColumns(IActionInputValueProvider value) {
    setActionInputValue(TRANSFORM_ORDERED_MAPS, value);
  }

  public IActionInputValueProvider getTransformOrderOutputColumns() {
    return getActionInputValue(TRANSFORM_ORDERED_MAPS);
  }

  public void setOutputResultSet(String publicOutputName) {
    // This removes deprecated functionality.
    setInputValue(OUTPUT_NAME_ELEMENT, null);
    // End deprecated functionality.

    setOutputParam(QUERY_RESULT_ELEMENT, publicOutputName, ActionSequenceDocument.RESULTSET_TYPE);
    if ((publicOutputName != null) && (publicOutputName.trim().length() > 0)) {
      setOutputPreparedStatement(null);
    }
  }

  public ActionOutput getOutputResultSet() {
    // This is deprecated functionality.
    Object outputName = getActionInputValue(OUTPUT_NAME_ELEMENT).getValue();
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

  public void setMaxRows(IActionInputValueProvider value) {
    setActionInputValue(MAX_ROWS_ELEMENT, value);
  }

  public IActionInputValueProvider getMaxRows() {
    return getActionInputValue(MAX_ROWS_ELEMENT);
  }

  public void setOutputPreparedStatement(String publicOutputName) {
    setOutputParam(PREPARED_COMPONENT_ELEMENT, publicOutputName, ActionSequenceDocument.SQL_QUERY_TYPE);
    if ((publicOutputName != null) && (publicOutputName.trim().length() > 0)) {
      setOutputResultSet(null);
      ActionOutput[] actionOutputs = getAllOutputParams();
      for (int i = 0; i < actionOutputs.length; i++) {
        if (!actionOutputs[i].getType().equals(ActionSequenceDocument.SQL_QUERY_TYPE)) {
          actionOutputs[i].delete();
        }
      }
    }
  }

  public ActionOutput getOutputPreparedStatement() {
    return getOutputParam(PREPARED_COMPONENT_ELEMENT);
  }

  public void setLive(IActionInputValueProvider value) {
    setActionInputValue(LIVE_CONNECTION_ELEMENT, value);
  }

  public IActionInputValueProvider getLive() {
    return getActionInputValue(LIVE_CONNECTION_ELEMENT);
  }

  public void setUseForwardOnlyResultSet(IActionInputValueProvider value) {
    setActionInputValue(RESULTSET_FORWARD_ONLY, value);
  }

  public IActionInputValueProvider getUseForwardOnlyResultSet() {
    return getActionInputValue(RESULTSET_FORWARD_ONLY);
  }

  private Format getFormat(String formatPattern) {
    Format format = null;
    try {
      format = new DecimalFormat(formatPattern.toString());
    } catch (Exception ex) {
      try {
        format = new SimpleDateFormat(formatPattern.toString());
      } catch (Exception ex2) {
      }
    }
    return format;
  }
}
