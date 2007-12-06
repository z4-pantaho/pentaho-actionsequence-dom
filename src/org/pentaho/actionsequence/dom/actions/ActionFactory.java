package org.pentaho.actionsequence.dom.actions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.dom4j.Element;

public class ActionFactory {
  public static final Class[] AVAILABLE_ACTIONS = new Class[] {
    JFreeReportAction.class,
    JasperReportAction.class,
    BirtReportAction.class,
    BarChartAction.class,
    PieChartAction.class,
    LineChartAction.class,
    AreaChartAction.class,
    SqlQueryAction.class,
    SqlDataAction.class,
    SqlExecuteAction.class,
    MdxQueryAction.class,
    XQueryAction.class,
    PivotViewAction.class,
    ResultSetCompareAction.class,
    ListSchedJobsAction.class,
    ResumeSchedulerAction.class,
    SchedulerStatusAction.class,
    SuspendSchedulerAction.class,
    SuspendScheduledJobAction.class,
    ResumeScheduledJobAction.class,
    StartScheduledJobAction.class,
    DeleteScheduledJobAction.class,
    KettleTransformAction.class,
    KettleJobAction.class,
    FormatMsgAction.class,
    CopyParamAction.class,
    ContentOutputAction.class,
    PrintParamAction.class,
    PrintMapValsAction.class,
    HelloWorldAction.class,
    MQLAction.class,
    JMSAction.class,
    JFreeReportGenAction.class,
    EmailAction.class,
    JavascriptAction.class,
    PrinterAction.class,
    SharkWorkflowAction.class,
    ReceiptAuditAction.class,
    TemplateMsgAction.class,
    SecureFilterAction.class,
    SubActionAction.class,
    SqlConnectionAction.class,
    MdxConnectionAction.class
  };
  
  public static ActionDefinition getActionDefinition(Element actionDefDomElement, IActionParameterMgr actionInputProvider) {
    ActionDefinition actionDefinition = null;
    for (int i = 0; (i < AVAILABLE_ACTIONS.length) && (actionDefinition == null); i++) {
      try {
        Method acceptElementMethod = AVAILABLE_ACTIONS[i].getMethod("accepts", new Class[]{Element.class});
        if (Boolean.TRUE.equals(acceptElementMethod.invoke(null, new Object[]{actionDefDomElement}))) {
          Constructor constructor = AVAILABLE_ACTIONS[i].getConstructor(new Class[]{Element.class, IActionParameterMgr.class});
          actionDefinition = (ActionDefinition)constructor.newInstance(new Object[]{actionDefDomElement, actionInputProvider});
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (actionDefinition == null) {
      actionDefinition = new ActionDefinition(actionDefDomElement, actionInputProvider);
    }
    return actionDefinition;
  }

}
