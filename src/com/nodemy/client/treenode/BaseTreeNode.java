package com.nodemy.client.treenode;

import java.util.Enumeration;

/**
 * Root tree node which helps build the tree.
 * 
  */
public class BaseTreeNode extends BaseNode {
    private static final long serialVersionUID = 5110518750967750676L;

    public BaseTreeNode() {
        super("AMP Environments");
    }

    /**
     * Look for our environment (domain). If it doesn't exist, create it.
     * 
     * @param domain
     * @return
     */
    public void addNode(MBeanNode node) {

        System.out.println("adding a node");
        // Get our domain node, create it if it doesn't exist
        BaseNode domain = this.getChild(this, node.getmBean().getObjectName().getDomain());

        // Get our category ("type") node, create it if it doesn't exist
        String type = node.getmBean().getObjectName().getKeyProperty("Type");

        // No type, then it's not our bean, toss it.
        if (type != null) {
            BaseNode category = this.getChild(domain, type);
            category.add(node);
        }
    }

    // Get a node, create if if it doesn't exist
    protected BaseNode getChild(BaseNode parent, String child) {
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
}
