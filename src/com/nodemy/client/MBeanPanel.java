package com.nodemy.client;

import com.nodemy.client.treenode.BaseTree;
import com.nodemy.client.treenode.MBeanNode;
import com.nodemy.client.treenode.ViewTreeFactory;
import com.nodemy.client.treenode.ViewTreeFactory.TreeNodeType;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

import java.awt.Dimension;

/**
 * Shows an MBean tree & detail panels.
 * 
 */
public class MBeanPanel extends JPanel implements TreeSelectionListener {
    private static final long serialVersionUID = -6047724564351671535L;
    private JEditorPane       detailPane;
    JScrollPane               detailScrollPane;
    private JTree             tree;

    public MBeanPanel(TreeNodeType treeNodeType) {
        super();

        // Create the nodes.
        BaseTree top = ViewTreeFactory.getInstance().getTreeView(treeNodeType);

        // Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        // set icon renderer
        tree.setCellRenderer(new IconRenderer());

        // Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        // Create the HTML viewing pane.
        detailPane = new JEditorPane();
        detailPane.setEditable(false);
        detailScrollPane = new JScrollPane(detailPane);

        // Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(detailScrollPane);

        Dimension minimumSize = new Dimension(250, 300);
        detailScrollPane.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(300);
        splitPane.setPreferredSize(new Dimension(780, 580));

        // Add the split pane to this panel.
        add(splitPane);
    }

    /**
     * Display detail pane when an mBean is clicked
     */
    public void valueChanged(TreeSelectionEvent e) {

        // Display detail for mbeans
        if (tree.getLastSelectedPathComponent() instanceof MBeanNode) {
            MBeanNode node = (MBeanNode) tree.getLastSelectedPathComponent();

                detailScrollPane.removeAll();

                JSplitPane detailPanel = TypeFactory.getInstance().getDetailPanel(node.getmBean());
                detailPanel.setSize(new Dimension(500, 600));

                detailScrollPane.add(detailPanel);
                detailScrollPane.repaint();

            // Otherwise, display popup menu for categories
        } else {
            // ServicePopupMenu menu = new ServicePopupMenu();
            // menu.setVisible(true);
        }
    }
}
