package com.nodemy.client.treenode;

import javax.management.ObjectInstance;

/**
 * Add an mBean to our tree nodes.
 * 
 *
 */
public class MBeanNode extends BaseNode {

	private static final long serialVersionUID = -8336179814567167724L;
	private ObjectInstance mBean = null;

	public MBeanNode(ObjectInstance bean) {
		super(bean);
		mBean = bean;
	}

	public ObjectInstance getmBean() {
		return mBean;
	}

	public void setmBean(ObjectInstance mBean) {
		this.mBean = mBean;
	}

	public String toString() {
		String name = mBean.getObjectName().getCanonicalName();
		int index = mBean.getObjectName().getDomain().length();
		name = name.substring(index + 1, name.length());
		return name;
	}

}
