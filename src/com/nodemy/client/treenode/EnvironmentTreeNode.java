package com.nodemy.client.treenode;


import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Root tree node which helps build the tree.
 * 
 *
 */
public class EnvironmentTreeNode extends BaseTreeNode {
    
	public EnvironmentTreeNode() {
	    super();
    }

    private static final long serialVersionUID = 5110518750967750676L;

	/**
	 * Look for our environment (domain). If it doesn't exist, create it.
	 * 
	 * @param domain
	 * @return
	 */
	public void addNode(MBeanNode node) {
		
		// Get our domain node, create it if it doesn't exist
		DefaultMutableTreeNode domain = this.getChild(this, node.getmBean()
				.getObjectName().getDomain());
		
		// Get our category ("type") node, create it if it doesn't exist
		String type = node.getmBean().getObjectName().getKeyProperty("Server");
		
		// No type, then it's not our bean, toss it.
		if (type != null) {
		    DefaultMutableTreeNode category = this.getChild(domain, type);
		    category.add(node);
		}
	}

	// Get a node, create if if it doesn't exist
	protected DefaultMutableTreeNode getChild(DefaultMutableTreeNode parent, String child) {
		DefaultMutableTreeNode result = null;

		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> e = parent.children();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode childNode = e
					.nextElement();
			if (childNode.getUserObject().equals(child)) {
				return childNode;
			}
		}

		result = new DefaultMutableTreeNode(child);
		parent.add(result);
		return result;
	}
}
