package com.nodemy.client.treenode;

import java.util.Comparator;

import javax.management.ObjectInstance;

/**
 * For mBeans to be sorted in order of tree hierarchy.
 * ENVIRONMENTS created before SYSTEMS
 * SYSTEMS created before SERVERS
 * so remaining elements have a pre-existing hierarchy to fall into.
 * 
 * 
 *
 * @param <T>
 */
public class SystemViewTreeComparator<T> implements Comparator<T> {

    final private static String TYPE = "Type";
    int total = 0;

    
	@Override
	public int compare(T o1, T o2) {
	    int result = 1;

		ObjectInstance mbean1 = (ObjectInstance) o1;
		ObjectInstance mbean2 = (ObjectInstance) o2;

		NodeTypeSingleton.NodeType type1 = NodeTypeSingleton.getInstance().getNodeType(mbean1.getObjectName().getKeyProperty(TYPE));
		NodeTypeSingleton.NodeType type2 = NodeTypeSingleton.getInstance().getNodeType(mbean2.getObjectName().getKeyProperty(TYPE));
		
		if ((type1 != null) && (type2 != null)) {
		    result = type1.compareTo(type2);
		} else {
		    System.out.println("BAD INPUT HERE!!!!!!!");
		}
		
		return result;
	}

}
