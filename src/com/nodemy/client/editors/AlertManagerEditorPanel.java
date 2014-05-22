package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Detail editor for mBean of type "Alert Manager".
 * 
  *
 */
public class AlertManagerEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = 1140320810207823665L;

	public AlertManagerEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);
		
		this.buildOperationsPane();
	}

}
