package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Detail editor for mBean of type "ExternalService".
 * 
 *
 */
public class ExternalServiceEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = -3850811943395906414L;

	public ExternalServiceEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);
		
		this.buildOperationsPane();
	}
}
