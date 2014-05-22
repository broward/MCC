package com.nodemy.client.treenode;

import javax.management.ObjectInstance;
import javax.management.ObjectName;

import java.util.Enumeration;
import java.util.Set;

/**
 * Build our mBean tree grouped by "Type",
 * 
  */
public class TypeViewTree extends BaseTree {
    private static final long serialVersionUID = -4012229007874658178L;

    @Override
    protected void addFilteredNodes(Set<ObjectInstance> mBeans) {

        // run through comparator here if we need to.

        for (ObjectInstance bean : mBeans) {
            MBeanNode node = new MBeanNode(bean);
            this.addNode(node);
        }
    }

    // add a node to ourself
    private void addNode(MBeanNode node) {

        // Get our domain node, create it if it doesn't exist
        BaseNode domain = this.getChild(this, node.getmBean().getObjectName().getDomain());

        // Get our category ("type") node, create it if it doesn't exist
        String type = node.getmBean().getObjectName().getKeyProperty("Type");

        // No type, then it's not our bean, toss it.
        if (type != null) {
            BaseNode category = this.getChild(domain, type);
            node.getLabel().setText(simplifyObjectName(node.getmBean().getObjectName()));
            category.add(node);
        } else {
            // this.add(node);
        }
    }

    // Get a node, create if if it doesn't exist
    private BaseNode getChild(BaseNode parent, String child) {
        BaseNode result = null;

        @SuppressWarnings("unchecked")
        Enumeration<BaseNode> e = parent.children();
        while (e.hasMoreElements()) {
            BaseNode childNode = e.nextElement();
            if (childNode.getUserObject().equals(child)) {
                return childNode;
            }
        }

        result = new BaseNode(child);
        parent.add(result);
        return result;
    }

    /**
     * Format a node's name for its context in this tree.
     */
    public static String simplifyObjectName(ObjectName objectName) {
        NodeTypeSingleton.NodeType nodeType = NodeTypeSingleton.getInstance().getNodeType(objectName.getKeyProperty("Type"));
        String system = objectName.getKeyProperty("System");
        String name = objectName.getKeyProperty("Name");
        String defaultName = "";
        if (name != null) {
                defaultName = name;
         }
        if (system != null) {
            defaultName = defaultName + "  (" + system + ")";
        } 

        switch (nodeType) {
            case AGENT: {
                return "Agent";
            }
            case ALERTMANAGER: {
                return "AlertManager";
            }
            case SYSTEM: {
                return objectName.getKeyProperty("Name");
            }
            case TOMCATSERVER: {
                return objectName.getKeyProperty("Name");
            }
            case NODE: {
                return objectName.getKeyProperty("Node");
            }
            case REGISTRY: {
                return "Registry";
            }
            // Should only have one Environment bean
            case ENVIRONMENT: {
                return "Environment";
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
