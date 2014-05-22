package com.nodemy.client.treenode;

import javax.management.ObjectInstance;
import javax.management.ObjectName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Build our mBean tree in hierarchical order - DOMAIN, SYSTEM, SERVER, etc.
 * 
  */
public class SystemViewTree extends BaseTree {
    private static final long serialVersionUID = -550662808683420898L;

    protected void addFilteredNodes(Set<ObjectInstance> mBeans) {

        // run through comparator here if we need to.
        List<ObjectInstance> mBeanList = new ArrayList<ObjectInstance>();
        Iterator<ObjectInstance> i = mBeans.iterator();

        while (i.hasNext()) {
            mBeanList.add(i.next());
        }

        // sort in order of hierarchy precedence
        Collections.sort(mBeanList, new SystemViewTreeComparator<ObjectInstance>());

        for (ObjectInstance bean : mBeanList) {
            MBeanNode mBeanNode = new MBeanNode(bean);
            addNode(mBeanNode);
        }
    }

    // add a node to ourself
    private void addNode(MBeanNode node) {

        // Get our domain node, create it if it doesn't exist
        BaseNode domainNode = this.getDomain(node.getmBean().getObjectName().getDomain());
        BaseNode systemNode = null;
        BaseNode envNode = null;
        BaseNode serverNode = null;

        // Look for our hierarchy attributes
        String type = node.getmBean().getObjectName().getKeyProperty("Type");
        String system = node.getmBean().getObjectName().getKeyProperty("System");
        String server = node.getmBean().getObjectName().getKeyProperty("Server");
        String nodeName = node.getmBean().getObjectName().getKeyProperty("Node");

        if (system != null) {
            systemNode = this.getChild(domainNode, system);

            if ((systemNode != null) && (server != null)) {
                serverNode = this.getChild(systemNode, server);
            }

            if ((systemNode != null) && (nodeName != null)) {
                envNode = this.getChild(systemNode, nodeName);
            }
        }

        // Add our node to the tree, depending on its type
        NodeTypeSingleton.NodeType nodeType = NodeTypeSingleton.getInstance().getNodeType(type);
        switch (nodeType) {

        // Add SYSTEM nodes to the domain
            case SYSTEM: {
                node.getLabel().setText(simplifyObjectName(node.getmBean()));
                domainNode.add(node);
                break;
            }

            // Add SERVER nodes to SYSTEMS
            case SERVER: {
                node.getLabel().setText(simplifyObjectName(node.getmBean()));

                if (systemNode != null) {
                    systemNode.add(node);
                }
                break;
            }

            // Add environment NODES to SYSTEMS
            case NODE: {
                node.getLabel().setText(simplifyObjectName(node.getmBean()));

                if (systemNode != null) {
                    systemNode.add(node);
                }
                break;
            }

            case ENVIRONMENT: {
                node.getLabel().setText(simplifyObjectName(node.getmBean()));
            }

            // Dump everything else as far down the hierarchy as their properties will allow.
            // Goddamn this.
            default: {
                node.getLabel().setText(simplifyObjectName(node.getmBean()));

                if (serverNode != null) {
                    serverNode.add(node);
                } else if (envNode != null) {
                    envNode.add(node);
                } else if (systemNode != null) {
                    systemNode.add(node);
                } else {
                    domainNode.add(node);
                }
            }
        }
    }

    // Domain should live one level under root
    private BaseNode getDomain(String child) {
        BaseNode result = null;

        @SuppressWarnings("unchecked")
        Enumeration<BaseNode> e = this.children();
        while (e.hasMoreElements()) {
            BaseNode childNode = e.nextElement();
            if (childNode.getUserObject().equals(child)) {
                return childNode;
            }
        }

        result = new BaseNode(child);
        this.add(result);
        return result;
    }

    // Get a child node by its name
    private BaseNode getChild(BaseNode parent, String nodeName) {
        BaseNode result = null;
        if (parent == null) {
            return null;
        }
        if (nodeName == null) {
            return null;
        }

        @SuppressWarnings("unchecked")
        Enumeration<BaseNode> e = parent.children();
        while (e.hasMoreElements()) {
            BaseNode childNode = e.nextElement();
            if (childNode instanceof MBeanNode) {
                String childName = ((MBeanNode) childNode).getmBean().getObjectName().getKeyProperty("Name");
                if (childName != null) {
                    if (nodeName.toUpperCase().equals(childName.toUpperCase())) {
                        result = childNode;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Format a node's name for its context in this tree.
     */
    public static String simplifyObjectName(ObjectInstance objectInstance) {
        ObjectName objectName = objectInstance.getObjectName();
        NodeTypeSingleton.NodeType nodeType = NodeTypeSingleton.getInstance().getNodeType(objectName.getKeyProperty("Type"));
        String defaultName = objectName.getKeyProperty("Name");

        switch (nodeType) {
            case AGENT: {
                // no name attribute, hardcode it for now.
                return "Agent";
            }
            case ALERTMANAGER: {
                // no name attribute, hardcode it for now.
                return "AlertManager";
            }
            case NODE: {
                return objectName.getKeyProperty("Node");
            }
            // Should only have one Environment bean
            case ENVIRONMENT: {
                return "Environment";
            }
            case MONITORINGAGGREGATION: {
                return objectName.getKeyProperty("System");
            }
            case REGISTRY: {
                return "Registry";
            }
            case DEFAULT: {
                return objectName.getCanonicalName();
            }
            default: {
                return defaultName;
            }
        }
    }
}
