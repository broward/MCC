package com.nodemy.client.treenode;

import com.nodemy.client.TypeFactory;

import javax.management.ObjectInstance;
import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Our base class for tree nodes, add a display label and icon.
 * 
 *
 */
public class BaseNode extends DefaultMutableTreeNode {
    private static final long serialVersionUID = 172534109662792376L;
    private JLabel label = new JLabel();
    
    public BaseNode() {
        super();
        this.setUserObject("empty");
    }
    
    public BaseNode(Object object) {
        super(object);
        if (object instanceof ObjectInstance) {
            label.setText(((ObjectInstance) object).getObjectName().getCanonicalName());
            label.setIcon(TypeFactory.getInstance().getIcon(((ObjectInstance) object).getClassName()));
        } else {
            label.setText(object.toString());
            label.setIcon(TypeFactory.getInstance().getDirectIcon(object.toString()));
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
      
    
}
