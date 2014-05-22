package com.nodemy.client.editors;

import javax.management.ObjectInstance;

/**
 * Detail editor for mBean of type "Node".
 * 
  *
 */
public class NodeEditorPanel extends AbstractEditorPanel {
	private static final long serialVersionUID = 5926380047838192810L;

	public NodeEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);
		
		this.buildOperationsPane();
	}

}
