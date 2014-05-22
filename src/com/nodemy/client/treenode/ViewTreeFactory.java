package com.nodemy.client.treenode;

import com.nodemy.client.JMXConnectionSingleton;

import javax.management.ObjectInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Supplies node-trees which filter/construct on different criteria.
 * 
 */
public class ViewTreeFactory {

    private static ViewTreeFactory      instance;
    private Map<TreeNodeType, Class<?>> treeNodeTypes = new HashMap<TreeNodeType, Class<?>>();
    public Map<TreeNodeType, String>  treeNodeLabels = new HashMap<TreeNodeType, String>();

    public enum TreeNodeType {
        TYPE, SYSTEM, ENVIRONMENT
    };

    protected ViewTreeFactory() {
        super();
        // kludgy to use two maps, do it for now
        treeNodeTypes.put(TreeNodeType.TYPE, TypeViewTree.class);
        treeNodeTypes.put(TreeNodeType.ENVIRONMENT, EnvironmentViewTree.class);
        treeNodeTypes.put(TreeNodeType.SYSTEM, SystemViewTree.class);
        
        treeNodeLabels.put(TreeNodeType.TYPE, "By Type");
        treeNodeLabels.put(TreeNodeType.ENVIRONMENT, "By Environment");
        treeNodeLabels.put(TreeNodeType.SYSTEM, "By System");
    }

    // Get the singleton
    public static ViewTreeFactory getInstance() {
        if (instance == null)
            instance = new ViewTreeFactory();
        return instance;
    }

    public BaseTree getTreeView(TreeNodeType treeNodeType) {
        BaseTree result = null;
        try {
            BaseTree root = (BaseTree) treeNodeTypes.get(treeNodeType).newInstance();
            root.type = treeNodeType;
            root.getLabel().setText("AMP Environments: " + treeNodeLabels.get(treeNodeType));
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
    private BaseTree createTree(BaseTree root) {

        if (JMXConnectionSingleton.getInstance().getmBeanServer() == null) {
            System.out.println("NO MBEAN SERVER HERE");
            return root;
        }

        // Get our beans
        Set<ObjectInstance> beans = null;
        try {
            beans = JMXConnectionSingleton.getInstance().getmBeanServer().queryMBeans(null, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("bean count =" + beans.size());
        root.addNodes(beans);
        System.out.println("child count = " + root.getChildCount());
        return root;
    }
}
