package com.nodemy.client.menus;

import com.nodemy.client.MBeanPanel;
import com.nodemy.client.treenode.MBeanFilterSingleton;
import com.nodemy.client.treenode.NodeTypeSingleton;
import com.nodemy.client.treenode.ViewTreeFactory;
import com.nodemy.client.treenode.ViewTreeFactory.TreeNodeType;
import com.nodemy.client.ui.ConnectionDialog;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.EnumSet;

/**
 * Mission Control Client Menu Bar
 * 
 */
public class MenuBar extends JMenuBar {
    private static final long serialVersionUID = 9149258818627474649L;
    protected JFrame          parent           = null;
    private JMenu             connectionMenu, environmentMenu, viewMenu, helpMenu;

    public MenuBar(JFrame parent) {
        super();
        this.parent = parent;
        buildMenu();
    }

    private void buildMenu() {

        // Build connection menu
        connectionMenu = new JMenu("Connection");
        connectionMenu.setMnemonic(KeyEvent.VK_C);
        buildConnectionMenu(connectionMenu);
        this.add(connectionMenu);

        // Build environment menu
        environmentMenu = new JMenu("Environment");
        environmentMenu.setMnemonic(KeyEvent.VK_E);
        buildEnvironmentMenu(environmentMenu);
        this.add(environmentMenu);

        viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        buildViewMenu(viewMenu);
        this.add(viewMenu);

        viewMenu = new JMenu("Preferences");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        buildPreferencesMenu(viewMenu);
        this.add(viewMenu);

        // Build help menu
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        buildHelpMenu(helpMenu);
        this.add(helpMenu);
    }

    /**
     * Build our connection menu.
     * 
     * @param menu
     */
    private void buildConnectionMenu(JMenu menu) {
        // a group of JMenuItems
        JMenuItem menuItem = new JMenuItem("Connect", KeyEvent.VK_C);
        menuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(menuItem);
        menuItem.addActionListener(new ConnectActionListener());
    }

    /**
     * Build our environment menu.
     * 
     * @param menu
     */
    private void buildEnvironmentMenu(JMenu menu) {
        // a group of JMenuItems
        JMenuItem menuItem = new JMenuItem("Define Environment", KeyEvent.VK_D);
        menuItem.setMnemonic(KeyEvent.VK_D);
        menu.add(menuItem);

        menuItem = new JMenuItem("Add System", KeyEvent.VK_A);
        menuItem.setMnemonic(KeyEvent.VK_A);
        menu.add(menuItem);

        menuItem = new JMenuItem("Remove System", KeyEvent.VK_R);
        menuItem.setMnemonic(KeyEvent.VK_R);
        menu.add(menuItem);

        menuItem = new JMenuItem("Deploy WebApp", KeyEvent.VK_W);
        menuItem.setMnemonic(KeyEvent.VK_W);
        menu.add(menuItem);
    }

    /**
     * Build alternate views menu.
     * 
     * @param menu
     */
    private void buildViewMenu(JMenu menu) {
        JMenuItem menuItem;

        for (TreeNodeType t : EnumSet.allOf(TreeNodeType.class)) {
            menuItem = new JMenuItem();
            menuItem.setText(ViewTreeFactory.getInstance().treeNodeLabels.get(t));
            menuItem.setName(t.name());
            menuItem.addActionListener(new ViewMenuListener());
            menu.add(menuItem);
        }

    }

    /**
     * Build preferences menu
     * 
     * @param menu
     */
    private void buildPreferencesMenu(JMenu menu) {
        JCheckBoxMenuItem menuItem = null;

        for (NodeTypeSingleton.NodeType s : EnumSet.allOf(NodeTypeSingleton.NodeType.class)) {
            menuItem = new JCheckBoxMenuItem();
            menuItem.setText(NodeTypeSingleton.getInstance().getType(s));
            menuItem.setIcon(NodeTypeSingleton.getInstance().getIcon(s));
            menuItem.setSelected(true);
            menuItem.addActionListener(new PreferenceMenuListener());
            menu.add(menuItem);
        }
    }

    /**
     * Build our help menu.
     * 
     * @param menu
     */
    private void buildHelpMenu(JMenu menu) {
        // a group of JMenuItems
        JMenuItem menuItem = new JMenuItem("About", KeyEvent.VK_A);
        menuItem.setMnemonic(KeyEvent.VK_A);
        menu.add(menuItem);
    }

    class ConnectActionListener implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            ConnectionDialog dlg = new ConnectionDialog(parent);

            if (!dlg.run()) {
                return;
            } else {
                System.out.println("here i am baby");
            }
        }
    }

    class ViewMenuListener implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            // Clear view menu subitems
            Component[] children = viewMenu.getComponents();
            for (int i = 0; i < children.length; i++) {
                if (children[i] instanceof JMenuItem) {
                    JMenuItem item = (JMenuItem) children[i];
                    item.setSelected(false);
                }
            }

            // kludgy workaround by placing type.name in menuitem.name.
            JMenuItem self = (JMenuItem) actionEvent.getSource();
            parent.getContentPane().removeAll();
            TreeNodeType type = ViewTreeFactory.TreeNodeType.TYPE;
            if (self.getName().equals(ViewTreeFactory.TreeNodeType.SYSTEM.name())) {
                type = ViewTreeFactory.TreeNodeType.SYSTEM;
            } else if (self.getName().equals(ViewTreeFactory.TreeNodeType.ENVIRONMENT.name())) {
                type = ViewTreeFactory.TreeNodeType.ENVIRONMENT;
            }
            parent.setContentPane(new MBeanPanel(type));
            parent.pack();
            parent.setSize(800, 600);
            parent.setVisible(true);
            parent.repaint();
        }
    }

    // Add or remove types from our display tree.
    class PreferenceMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBoxMenuItem item = (JCheckBoxMenuItem) e.getSource();
            NodeTypeSingleton.NodeType nodeType = NodeTypeSingleton.getInstance().getNodeType(item.getText());

            // remove this entry from exclusion list
            if (item.isSelected()) {
                System.out.println("removing an item");
                MBeanFilterSingleton.getInstance().excludedTypes.remove(nodeType);

                // add this entry to exclusion list
            } else {
                System.out.println("adding an exlusion");
                MBeanFilterSingleton.getInstance().excludedTypes.add(nodeType);
            }

            // kludgy redraw for now
            parent.getContentPane().removeAll();
            parent.setContentPane(new MBeanPanel(TreeNodeType.SYSTEM));
            parent.pack();
            parent.setSize(800, 600);
            parent.setVisible(true);
            parent.repaint();
        }

    }
}
