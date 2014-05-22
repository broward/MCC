package com.nodemy.client;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.nodemy.client.ui.ConnectionDialog;

/** 
 * Mission Control Client Menu Bar
 * 
 */
public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 9149258818627474649L;
	private JMenu connectionMenu, environmentMenu, helpMenu;

	public MenuBar() {
		super();

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
	
    /**
     * The new connection.
     */
    public void uponNewConnection() {
        ConnectionDialog dlg = new ConnectionDialog(null);
        if (!dlg.run()) {
            return;
        }
    }
}
