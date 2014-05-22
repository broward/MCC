package com.nodemy.client;

import com.nodemy.client.menus.MenuBar;
import com.nodemy.client.treenode.ViewTreeFactory.TreeNodeType;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Mission Control Client - application entry point
 * 
 */
public class MissionControlClient extends JFrame {
    private static final long serialVersionUID     = 2084349520746637066L;

    // Optionally set the look and feel.
    private static boolean    useSystemLookAndFeel = false;

    public MissionControlClient() {
        super("Mission Control Client");
    }

    /**
     * * Create the GUI and show it. For thread safety, * this method should be invoked from the * event-dispatching
     * thread.
     */
    private void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        // Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(new MenuBar(this));
        
        // Display the window.
        this.pack();
        this.setSize(800, 600);
        this.setVisible(true);
    }

    public void drawMe() {
        this.setContentPane(new MBeanPanel(TreeNodeType.SYSTEM));

        // Display the window.
        this.pack();
        this.setSize(800, 600);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MissionControlClient mcc = new MissionControlClient();
                mcc.createAndShowGUI();
            }
        });
    }
}
