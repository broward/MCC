package com.nodemy.base.service.missioncontrol.treenode;

import org.junit.Test;

import com.nodemy.client.treenode.NodeTypeSingleton;

import static org.junit.Assert.*;

/**
 * Unit tests for NodeTypes enumeration.
 * 
 */
public class testNodeTypes {
    private String environment = "Environment";
    private String system      = "System";
    private String server      = "Server";
    private String failure     = "Failure";

    @Test
    public void testCompareTo() {

        NodeTypeSingleton.NodeType nodeType1 = NodeTypeSingleton.getInstance().getNodeType(environment);
        assertTrue("'Environment' string should return a nodeType", nodeType1 != null);

        NodeTypeSingleton.NodeType nodeType2 = NodeTypeSingleton.getInstance().getNodeType(system);
        assertTrue("'System' string should return a nodeType", nodeType2 != null);

        NodeTypeSingleton.NodeType nodeType3 = NodeTypeSingleton.getInstance().getNodeType(server);
        assertTrue("'Server' string should return a nodeType", nodeType3 != null);

        int result = nodeType1.compareTo(nodeType2);
        assertEquals("'Environment' should precede 'System'", result, -1);

        result = nodeType2.compareTo(nodeType3);
        assertEquals("'System' should precede 'Server'", result, -1);

        result = nodeType3.compareTo(nodeType1);
        System.out.println("result = " + result);
        assertEquals("'Server' should follow 'Environment'", result, 2);
    }

    @Test
    public void testNodeMap() {
        NodeTypeSingleton.NodeType nodeType1 = NodeTypeSingleton.getInstance().getNodeType(environment);
        assertTrue("'Environment' should return a valid nodeType", nodeType1 != null);

        nodeType1 = NodeTypeSingleton.getInstance().getNodeType(failure);
        assertTrue("'Failure' should return a null nodeType", nodeType1 == null);

        nodeType1 = NodeTypeSingleton.getInstance().getNodeType(null);
        assertTrue("NULL should return a null nodeType", nodeType1 == null);
    }
}
