package com.nodemy.client;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Get a connection to a host / mBeanServer
 * 
 */
public class JMXConnectionSingleton {

    private static JMXConnectionSingleton instance;
    private String               SERVER_URL       = "localhost";
    private static final String  PRE_URL          = "service:jmx:rmi:///jndi/rmi://";
    private static final String  POST_URL         = "/jmxrmi";
    private MBeanServerConnection mBeanServerConnection      = null;

    protected JMXConnectionSingleton(String host) {
        super();
        this.SERVER_URL = host;
        try {
            connect();
        } catch (Exception e) {
            //
        }
    }

    public static JMXConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new JMXConnectionSingleton("localhost");
        }
        return instance;
    }

    // Get the singleton
    public static JMXConnectionSingleton getInstance(String host) {
        instance = new JMXConnectionSingleton(host);
        return instance;
    }

    public MBeanServerConnection getmBeanServer() {
        return mBeanServerConnection;
    }

    public void connect() {
        try {
            JMXServiceURL url = new JMXServiceURL(PRE_URL + SERVER_URL + POST_URL);
            JMXConnector jmxc = JMXConnectorFactory.connect(url);
            mBeanServerConnection = jmxc.getMBeanServerConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}