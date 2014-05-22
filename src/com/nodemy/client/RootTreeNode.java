package com.nodemy.client;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Root tree node which helps build the tree.
 * 
 *
 */
public class RootTreeNode extends DefaultMutableTreeNode {
	private static final long serialVersionUID = 5110518750967750676L;

	public RootTreeNode(Object userObject) {
		super(userObject);
	}

	/**
	 * Look for our environment (domain). If it doesn't exist, create it.
	 * 
	 * @param domain
	 * @return
	 */
	public void addNode(MBeanTreeNode node) {
		
		// Get our domain node, create it if it doesn't exist
		DefaultMutableTreeNode domain = this.getChild(this, node.getmBean()
				.getObjectName().getDomain());
		
		// Get our category node, create it if it doesn't exist
		String type = node.getmBean().getObjectName().getKeyProperty("Type");
		DefaultMutableTreeNode category = this.getChild(domain, type);
		category.add(node);
	}

	// Get a node, create if if it doesn't exist
	private DefaultMutableTreeNode getChild(DefaultMutableTreeNode parent, String child) {
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
