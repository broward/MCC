package com.nodemy.client;

import com.nodemy.client.editors.AbstractEditorPanel;

import javax.management.ObjectInstance;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;

import java.lang.reflect.Constructor;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *  Legacy class, trying to phase this out in favor of NodeTypeSingleton.
 * 
*/
public class TypeFactory {

    private static TypeFactory instance;
    private String             resourcePath  = "C:\\Users\\browardh\\Workspaces\\amp\\MissionControlClient\\src\\com\\qpass\\base\\service\\missioncontrol\\resources\\";
    private String             editorPackage = "com.nodemy.base.service.missioncontrol.editors.";

    public enum NodeTypes {
        Default("Default"), Alert("com.nodemy.base.service.Alert"), Server("com.nodemy.base.service.agent.Server"), System(
                "com.nodemy.base.service.registry.System"), Environment("com.nodemy.base.service.env.Environment"), AlertManager(
                "com.nodemy.base.service.agent.alerting.AlertManager"), ExternalService("com.nodemy.base.service.env.ExternalService"), MySqlServer(
                "com.nodemy.base.service.env.MySqlServer"), Service("com.nodemy.base.service.registry.Service"), LoggingServer(
                "com.nodemy.base.service.env.LoggingServer"), Node("com.nodemy.base.service.env.Node"), Agent(
                "com.nodemy.base.service.agent.Agent"), TomcatServer("com.nodemy.base.service.env.TomcatServer"), DataSourceSwitch(
                "com.nodemy.base.service.registry.DataSourceSwitch"), SwitchableDataSource(
                "com.nodemy.base.service.datasource.SwitchableDataSource");

        private static final Map<String, NodeTypes> lookup = new HashMap<String, NodeTypes>();

        static {
            for (NodeTypes s : EnumSet.allOf(NodeTypes.class)) {
                lookup.put(s.getCode(), s);
            }
        }

        private String                              code;

        private NodeTypes(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static NodeTypes get(String code) {
            NodeTypes result = lookup.get(code);
            if (result == null) {
                result = lookup.get("Default");
            }
            return result;
        }
    }

    // Hide constructor
    protected TypeFactory() {

    }

    // Get the singleton
    public static TypeFactory getInstance() {
        if (instance == null)
            instance = new TypeFactory();
        return instance;

    }

    /**
     * Get edit panel which corresponds to current mbean class
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public JSplitPane getDetailPanel(ObjectInstance objectInstance) {
        Class[] classParms = new Class[1];
        Object[] objectParms = new Object[1];
        Constructor<AbstractEditorPanel> constructor = null;
        AbstractEditorPanel panel = null;

        try {
            classParms[0] = ObjectInstance.class;
            objectParms[0] = objectInstance;
            Class<AbstractEditorPanel> panelClass = (Class<AbstractEditorPanel>) Class.forName(editorPackage
                    + NodeTypes.get(objectInstance.getClassName()) + "EditorPanel");

            constructor = panelClass.getConstructor(classParms);
            panel = constructor.newInstance(objectParms);
            System.out.println("my panel class is " + panel.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return panel;
    }

    /**
     * get icon which corresponds to current mbean class
     * 
     * @return
     */
    public ImageIcon getIcon(String classType) {
        return new ImageIcon(resourcePath + NodeTypes.get(classType) + ".gif");
    }

    /**
     * 
     */
    public ImageIcon getDirectIcon(String type) {
        return new ImageIcon(resourcePath + type + ".gif");
    }
}
