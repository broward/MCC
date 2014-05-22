package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Default detail editor for mBeans which have no specified detail editor.
 * 
  *
 */
public class DefaultEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = 8893627548297848080L;

	public DefaultEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);
		
		this.buildOperationsPane();
	}
}
