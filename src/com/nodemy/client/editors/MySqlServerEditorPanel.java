package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Detail editor for mBean of type "MySqlServer".
 * 
 *
 */
public class MySqlServerEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = -1233922499849036174L;

	public MySqlServerEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);
		
		this.buildOperationsPane();
	}

}
