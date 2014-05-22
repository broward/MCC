package com.nodemy.client.editors;

import javax.management.ObjectInstance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Detail editor for mBean of type "Agent".
 * 
 *
 */
public class AgentEditorPanel extends AbstractEditorPanel implements
		ActionListener {
	private static final long serialVersionUID = 1140320810207823665L;

	public AgentEditorPanel(ObjectInstance objectInstance) {
		super(objectInstance);

        this.buildOperationsPane();
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
