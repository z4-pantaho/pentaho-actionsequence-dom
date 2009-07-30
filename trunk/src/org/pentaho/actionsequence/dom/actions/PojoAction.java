package org.pentaho.actionsequence.dom.actions;

import org.dom4j.Element;

public class PojoAction extends ActionDefinition {
  public static final String COMPONENT_NAME = "org.pentaho.platform.engine.services.solution.PojoComponent"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] {};

  public PojoAction(Element actionDefElement, IActionParameterMgr actionInputProvider) {
    super(actionDefElement, actionInputProvider);
  }

  public PojoAction() {
    super(COMPONENT_NAME);
  }

  public static boolean accepts(Element element) {
    return ActionDefinition.accepts(element) && hasComponentName(element, COMPONENT_NAME);
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }
}
