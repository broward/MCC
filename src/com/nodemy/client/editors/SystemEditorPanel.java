package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Detail editor for mBean of type "System".
 * 
 *
 */
public class SystemEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = 5752116650926320026L;

	public SystemEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);
		
		this.buildOperationsPane();
	}

}
