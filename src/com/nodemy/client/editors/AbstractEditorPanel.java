package com.nodemy.client.editors;

import com.nodemy.client.treenode.SystemViewTree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.management.ObjectInstance;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.Border;

/**
 * Base class for detail editors of mBeans.
 * 
 *
 */
public class AbstractEditorPanel extends JSplitPane {
	private static final long serialVersionUID = 5401627226217280150L;
	protected ObjectInstance objectInstance;
	protected JEditorPane attributesPane;
	protected JScrollPane attributesScrollPane;
	protected JEditorPane operationsPane;
	protected JScrollPane operationsScrollPane;
	protected static Dimension minimumSize = new Dimension(490, 280);

	public AbstractEditorPanel(ObjectInstance objectInstance) {
		super();

		this.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.objectInstance = objectInstance;
		this.setSize(new Dimension(500, 600));
		this.setMinimumSize(new Dimension(500, 600));

		this.buildAttributesPane();
		this.setTopComponent(attributesPane);
		this.setBottomComponent(operationsPane = new JEditorPane());
		this.repaint();
	}

	// Used as a refresh for when MBeanInfo changes.
	protected void buildAttributesPane() {
	    BorderLayout layout = new BorderLayout();
	    
		// Add attributes panel
		attributesPane = new JEditorPane();
		JLabel label = new JLabel();
		label.setText(SystemViewTree.simplifyObjectName(objectInstance));
		label.setSize(new Dimension(attributesPane.getWidth(), 20));
		attributesPane.setLayout(layout);
		attributesPane.add(label, BorderLayout.NORTH);
		attributesPane.setSize(minimumSize);
		attributesPane.setMinimumSize(minimumSize);

		MBeanAttributesTableModel model = new MBeanAttributesTableModel(objectInstance.getObjectName());
		JTable table = new JTable(model);
		table.setBorder(BorderFactory.createLineBorder(Color.black));
		table.setSize(attributesPane.getWidth(), attributesPane.getHeight() - 40);
		table.setMinimumSize(minimumSize);
		attributesPane.add(table, BorderLayout.CENTER);
		attributesPane.repaint();
	}
	
	protected void buildOperationsPane() {
		operationsPane = new JEditorPane();
		operationsPane.setSize(minimumSize);
		operationsPane.setMinimumSize(minimumSize);
		
		MBeanOperationsTableModel model = new MBeanOperationsTableModel(objectInstance.getObjectName());
		JTable table = new JTable(model);
		table.setSize(operationsPane.getWidth(), operationsPane.getHeight());
		table.setMinimumSize(minimumSize);
		operationsPane.add(table);
		operationsPane.repaint();
		this.setBottomComponent(operationsPane);
	}
}
