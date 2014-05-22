package com.nodemy.client;

import com.nodemy.client.treenode.BaseNode;

import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

import java.awt.Component;
import java.awt.Label;

/**
 * Render the respective tree icon for a AMP "Type" class
 * 
 */
public class IconRenderer implements TreeCellRenderer {

    IconRenderer() {
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        if (value instanceof BaseNode) {
            return ((BaseNode) value).getLabel();
            
        // This should never happen.
        } else {
            return new Label("THIS SHOULD NOT BE HERE");
        }
    }
}
