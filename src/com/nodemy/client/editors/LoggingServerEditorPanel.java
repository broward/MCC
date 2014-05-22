package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Detail editor for mBean of type "LoggingServer".
 * 
 *
 */
public class LoggingServerEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = 3006983662099106937L;

	public LoggingServerEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);
		
		this.buildOperationsPane();
	}
}
