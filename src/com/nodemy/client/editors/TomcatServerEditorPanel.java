package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Detail editor for mBean of type "TomcatServer".
 * 
  *
 */
public class TomcatServerEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = -1590820596313799094L;

	public TomcatServerEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);

		this.buildOperationsPane();
	}
}
