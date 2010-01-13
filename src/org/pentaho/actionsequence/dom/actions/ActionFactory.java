package org.pentaho.actionsequence.dom.actions;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ActionFactory {

  public static LinkedHashMap<String, Class> pluginActions = new LinkedHashMap<String, Class>();
  
  public static String PLUGIN_XML_FILENAME = "pentaho_platform_plugin.xml";
  public static String PLUGIN_ROOT_NODE = "pentaho-plugin";
  public static String PLUGIN_ACTION_DEFINITION_NODE = "action-definition";
  
  protected static boolean pluginsLoaded = false;
  
  protected static synchronized void loadPlugins() {
    if (!pluginsLoaded) {
      try {
        // see if we have any pentaho_action_plugin.xml files in the root of the classpath
        ActionFactory factory = new ActionFactory();
        Enumeration<URL> enumer = factory.getClass().getClassLoader().getResources( PLUGIN_XML_FILENAME );
        
        // we might have multiple documents
        while( enumer.hasMoreElements() ) {
          URL url = enumer.nextElement();
          // make sure a failure for one resource does not affect any other ones
          try {
            Object obj = url.getContent();
            if( obj instanceof InputStream ) {
              SAXReader reader = new SAXReader();
              Document doc = reader.read((InputStream)obj);
              if( doc != null ) {
                // look for nodes 
                List nodes = doc.selectNodes( PLUGIN_ROOT_NODE+"/"+PLUGIN_ACTION_DEFINITION_NODE );
                Iterator it = nodes.iterator();
                while( it.hasNext() ) {
                  // make sure that one failed class will not affect any others
                  try {
                    // pull the class name from each node
                    Element node = (Element) it.next();
                    String className = node.getText();
                    String id = node.attributeValue("id");
                    // load the class
                      Class componentClass = Class.forName(className.trim());
                      // add the class to the plugin list
                      pluginActions.put(id, componentClass);
                  } catch (Exception e) {
                    // TODO log this
                    e.printStackTrace();
                  }
                }
              }
            }
          } catch (Exception e) {
            // TODO log this
            e.printStackTrace();
          }
        }
      } catch (Exception e) {
        // TODO log this
        e.printStackTrace();
      }
      pluginsLoaded = true;
    }
  }
  
  public static ActionDefinition getActionDefinition(Element actionDefDomElement, IActionParameterMgr actionInputProvider) {
    ActionDefinition actionDefinition = null;

    if( !pluginsLoaded ) {
        loadPlugins();
    }

    // TODO a map would improve performance here
    for (Class actionClass : pluginActions.values()) {
      try {
        Method acceptElementMethod = actionClass.getMethod("accepts", new Class[]{Element.class});
        if (Boolean.TRUE.equals(acceptElementMethod.invoke(null, new Object[]{actionDefDomElement}))) {
          Constructor constructor = actionClass.getConstructor(new Class[]{Element.class, IActionParameterMgr.class});
          actionDefinition = (ActionDefinition)constructor.newInstance(new Object[]{actionDefDomElement, actionInputProvider});
          break;
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
  
  public static Class getActionDefinition(String actionId) {
    if( !pluginsLoaded ) {
        loadPlugins();
    }

    return pluginActions.get(actionId);
  }

}
