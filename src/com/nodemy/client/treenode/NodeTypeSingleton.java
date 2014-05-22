package com.nodemy.client.treenode;

import javax.swing.ImageIcon;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Enumeration of node types for our tree.
 * 
 */
public class NodeTypeSingleton {
    private static NodeTypeSingleton instance;
    private String             resourcePath  = "C:\\Users\\browardh\\Workspaces\\amp\\MissionControlClient\\src\\com\\nodemy\\base\\service\\missioncontrol\\resources\\";
    
    public enum NodeType {
        SYSTEM, SERVER, NODE, ENVIRONMENT, AGENT, ALERT, ALERTMANAGER, DATASOURCESWITCH, EXTERNALSERVICE, LOGGINGSERVER, MONITORING, MONITORINGAGGREGATION, MYSQLSERVER, REGISTRY, SERVICE, SWITCHABLEDATASOURCE, TOMCATSERVER, WARDEPLOYER, DEFAULT
    };

    private LinkedHashMap<String, NodeType> nodeMap = new LinkedHashMap<String, NodeType>();

    public NodeTypeSingleton() {
        nodeMap.put("Agent", NodeType.AGENT);
        nodeMap.put("Alert", NodeType.ALERT);
        nodeMap.put("AlertManager", NodeType.ALERTMANAGER);
        nodeMap.put("DataSourceSwitch", NodeType.DATASOURCESWITCH);
        nodeMap.put("Default", NodeType.DEFAULT);
        nodeMap.put("Environment", NodeType.ENVIRONMENT);
        nodeMap.put("ExternalService", NodeType.EXTERNALSERVICE);
        nodeMap.put("LoggingServer", NodeType.LOGGINGSERVER);
        nodeMap.put("Monitoring", NodeType.MONITORING);
        nodeMap.put("MonitoringAggregation", NodeType.MONITORINGAGGREGATION);
        nodeMap.put("MySqlServer", NodeType.MYSQLSERVER);
        nodeMap.put("Node", NodeType.NODE);
        nodeMap.put("Registry", NodeType.REGISTRY);
        nodeMap.put("Server", NodeType.SERVER);
        nodeMap.put("Service", NodeType.SERVICE);
        nodeMap.put("SwitchableDataSource", NodeType.SWITCHABLEDATASOURCE);
        nodeMap.put("System", NodeType.SYSTEM);
        nodeMap.put("TomcatServer", NodeType.TOMCATSERVER);
        nodeMap.put("WarDeployer", NodeType.WARDEPLOYER);
    }

    // Get the singleton
    public static NodeTypeSingleton getInstance() {
        if (instance == null)
            instance = new NodeTypeSingleton();
        return instance;
    }

    // Get nodetype of a string representation of a type
    public NodeType getNodeType(String type) {
        NodeType nodeType = nodeMap.get(type);
        if (nodeType == null) {
            nodeType = NodeTypeSingleton.NodeType.DEFAULT;
        }
        return nodeType;
    }

    // get string representation of a nodetype
    public String getType(NodeType nodeType) {
        Iterator<String> i = nodeMap.keySet().iterator();
        while (i.hasNext()) {
            String key = (String) i.next();
            NodeType type = (NodeType) nodeMap.get(key);
            if (type.equals(nodeType)) {
                return key;
            }
        }
        return null;
    }
    
    /**
     * get icon which corresponds to current mbean class
     * 
     * @return
     */
    public ImageIcon getIcon(NodeType nodeType) {
        return new ImageIcon(resourcePath + nodeType.toString() + ".gif");
    }
}
