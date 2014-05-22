package com.nodemy.client.editors;

import com.nodemy.client.JMXConnectionSingleton;

import javax.management.ObjectName;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for mBean attributes.
 * 
  */
public class MBeanAttributesTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 8031748088726780006L;
    private ObjectName        objectName;

    public MBeanAttributesTableModel(ObjectName name) {
        super();
        objectName = name;
    }

    @Override
    public int getRowCount() {
        int result = 0;
        try {
            if (JMXConnectionSingleton.getInstance().getmBeanServer().getMBeanInfo(objectName).getAttributes() != null) {
                result = JMXConnectionSingleton.getInstance().getmBeanServer().getMBeanInfo(objectName).getAttributes().length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;

        try {
            switch (columnIndex) {

            // get attribute name
                case 0:
                    result = JMXConnectionSingleton.getInstance().getmBeanServer().getMBeanInfo(objectName).getAttributes()[rowIndex].getName();
                    break;

                // get attribute value
                case 1:
                    result = JMXConnectionSingleton
                            .getInstance()
                            .getmBeanServer()
                            .getAttribute(
                                    objectName,
                                    JMXConnectionSingleton.getInstance().getmBeanServer().getMBeanInfo(objectName).getAttributes()[rowIndex]
                                            .getName());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
