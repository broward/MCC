package com.nodemy.client.ui;

import com.nodemy.client.JMXConnectionSingleton;
import com.nodemy.client.MBeanPanel;
import com.nodemy.client.MissionControlClient;

import javax.swing.JFrame;

/**
 * Obtain a connection host.
 * 
 */
public class ConnectionDialog extends InputDialog {
    private JFrame myParent = null;
	/**
	 * Construct a connection dialog.
	 * 
	 * @param pParent
	 *            the parent frame.
	 */
	public ConnectionDialog(JFrame pParent) {
		super(pParent);
		myParent = pParent;
		setTitle("New Connection");
		setDescription("Enter Mission Control Server host");
		addInput(HOST, 20);
	}
    
	protected String validateInput() {
		try {
		    JMXConnectionSingleton mcc = JMXConnectionSingleton.getInstance(this.getInput(ConnectionDialog.HOST));
		    MissionControlClient client = (MissionControlClient) myParent;
		    client.drawMe();
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}

	public static final String HOST = "Host";
	private static final long serialVersionUID = 3792073930961654970L;

}