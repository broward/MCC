package com.nodemy.client;

import javax.management.ObjectInstance;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Tree node wrapper around an mBean.
 * 
 */
public class MBeanTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = -8336179814567167724L;
	private ObjectInstance mBean = null;
	private String icon = null;

	public MBeanTreeNode(ObjectInstance bean) {
		super();
		this.setUserObject(bean);
		mBean = bean;
	}

	public ObjectInstance getmBean() {
		return mBean;
	}

	public void setmBean(ObjectInstance mBean) {
		this.mBean = mBean;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String toString() {
		String name = mBean.getObjectName().getCanonicalName();
		int index = mBean.getObjectName().getDomain().length();
		name = name.substring(index + 1, name.length());
		return name;
	}

}
