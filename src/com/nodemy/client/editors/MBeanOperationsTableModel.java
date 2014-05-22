package com.nodemy.client.editors;

import javax.management.ObjectName;
import javax.swing.table.AbstractTableModel;

import com.nodemy.client.JMXConnectionSingleton;

/**
 * Table model for mBean operations.
 * 
  */
public class MBeanOperationsTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 8031748088726780006L;
    private ObjectName        objectName;

    public MBeanOperationsTableModel(ObjectName name) {
        super();
        objectName = name;
    }

    @Override
    public int getRowCount() {
        int result = 0;
        try {
            if (JMXConnectionSingleton.getInstance().getmBeanServer().getMBeanInfo(objectName).getOperations() != null) {
                result = JMXConnectionSingleton.getInstance().getmBeanServer().getMBeanInfo(objectName).getOperations().length;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;

        try {
            switch (columnIndex) {

            // get attribute name
                case 0:
                    result = JMXConnectionSingleton.getInstance().getmBeanServer().getMBeanInfo(objectName).getOperations()[rowIndex].getName();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
