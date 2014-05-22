package com.nodemy.client.treenode;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration of node types for our tree.
 * 
 *
 */
public class NodeTypes {

    public static NodeTypes instance;

    public enum NodeType {
        AGENT, ALERT, ALERTMANAGER, DATASOURCESWITCH, DEFAULT, ENVIRONMENT, EXTERNALSERVICE, LOGGINGSERVER, MONITORING, MONITORINGAGGREGATION, MYSQLSERVER, NODE, REGISTRY, SERVICE, SERVER, SWITCHABLEDATASOURCE, SYSTEM, TOMCATSERVER, WARDEPLOYER
    };

    private Map<NodeType, Class<?>> nodeTypes = new HashMap<NodeType, Class<?>>();

    public NodeTypes() {
        nodeTypes.put(NodeType.AGENT, null);
        nodeTypes.put(NodeType.ALERT, null);
        nodeTypes.put(NodeType.ALERTMANAGER, null);
        nodeTypes.put(NodeType.DATASOURCESWITCH, null);
        nodeTypes.put(NodeType.DEFAULT, null);
        nodeTypes.put(NodeType.ENVIRONMENT, null);
        nodeTypes.put(NodeType.EXTERNALSERVICE, null);
        nodeTypes.put(NodeType.LOGGINGSERVER, null);
        nodeTypes.put(NodeType.MONITORING, null);
        nodeTypes.put(NodeType.MONITORINGAGGREGATION, null);
        nodeTypes.put(NodeType.MYSQLSERVER, null);
        nodeTypes.put(NodeType.NODE, null);
        nodeTypes.put(NodeType.REGISTRY, null);
        nodeTypes.put(NodeType.SERVER, null);
        nodeTypes.put(NodeType.SERVICE, null);
        nodeTypes.put(NodeType.SWITCHABLEDATASOURCE, null);
        nodeTypes.put(NodeType.SYSTEM, null);
        nodeTypes.put(NodeType.TOMCATSERVER, null);
        nodeTypes.put(NodeType.WARDEPLOYER, null);
    }

    // Get the singleton
    public static NodeTypes getInstance() {
        if (instance == null)
            instance = new NodeTypes();
        return instance;
    }
}
