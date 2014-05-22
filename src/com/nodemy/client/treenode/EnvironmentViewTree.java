package com.nodemy.client.treenode;

import javax.management.ObjectInstance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Build our mBean tree filtered by environment.
 * 
 */
public class EnvironmentViewTree extends BaseTree {
    private static final long serialVersionUID = 5110518750967750676L;

    protected void addFilteredNodes(Set<ObjectInstance> mBeans) {

        // run through comparator here if we need to.
        List<ObjectInstance> mBeanList = new ArrayList<ObjectInstance>();
        Iterator<ObjectInstance> i = mBeans.iterator();

        while (i.hasNext()) {
            mBeanList.add(i.next());
        }
    }
}
