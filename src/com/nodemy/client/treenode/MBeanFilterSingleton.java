package com.nodemy.client.treenode;

import javax.management.ObjectInstance;

import java.util.HashSet;
import java.util.Set;

/**
 * Apply filtering to our list of mBeans at a single point.
 * 
 */
public class MBeanFilterSingleton {
    private static MBeanFilterSingleton instance;
    public Set<NodeTypeSingleton.NodeType> excludedTypes = new HashSet<NodeTypeSingleton.NodeType>();
    
    // Get the singleton
    public static MBeanFilterSingleton getInstance() {
        if (instance == null)
            instance = new MBeanFilterSingleton();
        return instance;
    }

    
    private MBeanFilterSingleton() {
        super();
    }

    public Set<ObjectInstance> applyFilter(Set<ObjectInstance> beanList) {
        beanList = applyGlobalFilter(beanList);
        beanList = applyExclusedTypeFilter(beanList);
        return beanList;
    }

    // Remove all "Type=Environment" beans except class of EnvironmentBean.
    private Set<ObjectInstance> applyGlobalFilter(Set<ObjectInstance> beanList) {
        Set<ObjectInstance> newList = new HashSet<ObjectInstance>();
        
        for (ObjectInstance bean : beanList) {
            String type = bean.getObjectName().getKeyProperty("Type");
            NodeTypeSingleton.NodeType nodeType = NodeTypeSingleton.getInstance().getNodeType(type);
            if (NodeTypeSingleton.NodeType.ENVIRONMENT.equals(nodeType)) {
                String className = bean.getClassName();
                if (className != null) {
                    if (className.contains("Environment")) {
                        newList.add(bean);
                    } 
                } 
            } else {
                newList.add(bean);
            }
        }
        
        return newList;
    }

    // Apply a filter on specific types.
    private Set<ObjectInstance> applyExclusedTypeFilter(Set<ObjectInstance> beanList) {
        Set<ObjectInstance> newList = new HashSet<ObjectInstance>();
        
        System.out.println("list size = " + excludedTypes.size());
        
        for (ObjectInstance bean : beanList) {
            String type = bean.getObjectName().getKeyProperty("Type");
            NodeTypeSingleton.NodeType nodeType = NodeTypeSingleton.getInstance().getNodeType(type);
            if (!excludedTypes.contains(nodeType)) {
                newList.add(bean);
            } else {
                System.out.println(bean.toString() + " was exluded!");
                System.out.println("list size = " + excludedTypes.size());
            }
        }
        
        return newList;
    }
    
    
}
