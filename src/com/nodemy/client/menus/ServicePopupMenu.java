package com.nodemy.client.menus;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 
 *
 */
public class ServicePopupMenu extends JPopupMenu {
	private static final long serialVersionUID = 2662901729243056388L;

	public ServicePopupMenu() {
		super();

		JMenuItem menuItem = new JMenuItem("Create Tomcat Server");
		this.add(menuItem);
		
		menuItem = new JMenuItem("Create MySql Server");
		this.add(menuItem);
		
		menuItem = new JMenuItem("Create Logging Server");
		this.add(menuItem);
	}
}
