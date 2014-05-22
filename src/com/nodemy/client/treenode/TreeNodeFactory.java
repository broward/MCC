package com.nodemy.client.treenode;

import com.nodemy.client.JMXConnection;

import javax.management.ObjectInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Supplies node-trees which filter on different criteria.
 * 
 */
public class TreeNodeFactory {

    private static TreeNodeFactory      instance;
    private Map<TreeNodeType, Class<?>> treeNodeTypes = new HashMap<TreeNodeType, Class<?>>();

    public enum TreeNodeType {
        BASE, SYSTEM, ENVIRONMENT
    };

    protected TreeNodeFactory() {
        super();
        treeNodeTypes.put(TreeNodeType.BASE, BaseTreeNode.class);
        treeNodeTypes.put(TreeNodeType.ENVIRONMENT, EnvironmentTreeNode.class);
        treeNodeTypes.put(TreeNodeType.SYSTEM, SystemTreeNode.class);
    }

    // Get the singleton
    public static TreeNodeFactory getInstance() {
        if (instance == null)
            instance = new TreeNodeFactory();
        return instance;
    }

    public BaseTreeNode getTreeView(TreeNodeType treeNodeType) {
        BaseTreeNode result = null;
        try {
            BaseTreeNode root = (BaseTreeNode) treeNodeTypes.get(treeNodeType).newInstance();
            result = createTree(root);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return result;
    }
    
    /**
     * Create our mBean tree
     * 
     * @param top
     */
    private BaseTreeNode createTree(BaseTreeNode top) {

        if (JMXConnection.getInstance().getmBeanServer() == null) {
            System.out.println("NO MBEAN SERVER HERE");
            return top;
        }

        // Get our beans
        Set<ObjectInstance> beans = null;
        try {
            beans = JMXConnection.getInstance().getmBeanServer().queryMBeans(null, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<ObjectInstance> sortedBeans = new ArrayList<ObjectInstance>();

        System.out.println("bean count =" + beans.size());
        for (ObjectInstance bean : beans) {
            sortedBeans.add(bean);
        }

        // Build our tree
        for (ObjectInstance bean : sortedBeans) {
            MBeanNode node = new MBeanNode(bean);
            top.addNode(node);
        }
        
        return top;
    }
}
