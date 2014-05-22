package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Detail editor for mBean of type "Service".
 * 
  *
 */
public class ServiceEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = -1853871464328898394L;

	public ServiceEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);
		
		this.buildOperationsPane();
	}

}
