package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Detail editor for mBean of type "Environment".
 * 
  *
 */
public class EnvironmentEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = 6595612846367551912L;

	public EnvironmentEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);
		
		this.buildOperationsPane();
	}
}
