package com.nodemy.client.editors;

import com.nodemy.client.JMXConnectionSingleton;

import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ObjectInstance;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Default detail editor for mBeans which have no specified detail editor.
 * 
 */
public class SwitchableDataSourceEditorPanel extends AbstractEditorPanel implements ActionListener {
    private static final long serialVersionUID = 8893627548297848080L;

    public SwitchableDataSourceEditorPanel(ObjectInstance objectInstance) {
        super(objectInstance);
        this.operationsPane.removeAll();
        this.operationsPane.setLayout(new FlowLayout(25, 25, 25));

        try {
            MBeanInfo info = JMXConnectionSingleton.getInstance().getmBeanServer().getMBeanInfo(objectInstance.getObjectName());
            MBeanOperationInfo[] ops = info.getOperations();

            for (int i = 0; i < ops.length; i++) {
                JButton button = new JButton(ops[i].getName());
                button.setSize(new Dimension(80, 25));
                button.setName(ops[i].getName());
                this.operationsPane.add(button);
                button.addActionListener(this);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Object result = null;
        try {
            // Execute button action
            result = JMXConnectionSingleton.getInstance().getmBeanServer()
                    .invoke(objectInstance.getObjectName(), button.getName(), null, null);

            // Retrieve mbean and refresh screen to show changes.
            objectInstance = JMXConnectionSingleton.getInstance().getmBeanServer().getObjectInstance(objectInstance.getObjectName());
            this.buildAttributesPane();
            this.repaint();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
