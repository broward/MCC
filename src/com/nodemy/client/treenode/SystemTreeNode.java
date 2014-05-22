package com.nodemy.client.treenode;


public class SystemTreeNode extends BaseTreeNode {
    private static final long serialVersionUID = -550662808683420898L;

    public SystemTreeNode() {
        super();
    }

    /**
     * Sort our mbeans by Domain / Server / System
     * 
     * @param domain
     * @return
     */
    public void addNode(MBeanNode node) {

        // Get our domain node, create it if it doesn't exist
        BaseNode domainNode = this.getChild(this, node.getmBean().getObjectName().getDomain());

        // Get our category ("type") node, create it if it doesn't exist
        String system = node.getmBean().getObjectName().getKeyProperty("System");

        // No type, then add it at domain level
        if (system == null) {
            domainNode.add(node);
            return;
        }

        BaseNode systemNode = this.getChild(domainNode, system);
        String server = node.getmBean().getObjectName().getKeyProperty("Server");

        // no server, add it at system level
        if (server == null) {
            systemNode.add(node);
            return;
        }

        BaseNode serverNode = this.getChild(systemNode, server);
        serverNode.add(node);

    }
}
