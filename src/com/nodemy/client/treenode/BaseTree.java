package com.nodemy.client.treenode;

import com.nodemy.client.treenode.ViewTreeFactory.TreeNodeType;

import javax.management.ObjectInstance;

import java.util.Set;

/**
 * Base class for building our mBean tree.
 * 
 */
public abstract class BaseTree extends BaseNode {
    private static final long serialVersionUID = -3090432302503707713L;
    private MBeanFilterSingleton       mBeanFilter      = null;
    protected TreeNodeType    type             = null;

    public BaseTree() {
        super("AMP Environments");
        
        // add our filter at base class to enforce in all subclasses.
        mBeanFilter = MBeanFilterSingleton.getInstance();
    }

    // Need to know our type in many areas, assigned by factory on creation.
    public TreeNodeType getType() {
        return type;
    }

    // Method exposed to other classes to ensure filtering
    public void addNodes(Set<ObjectInstance> mBeans) {
        Set<ObjectInstance> filteredList = mBeanFilter.applyFilter(mBeans);
        addFilteredNodes(filteredList);
    }

    // Locally implemented node manipulation for different views
    abstract protected void addFilteredNodes(Set<ObjectInstance> mBeans);

    // maybe let other people insert filters someday
    public MBeanFilterSingleton getmBeanFilter() {
        return mBeanFilter;
    }
}