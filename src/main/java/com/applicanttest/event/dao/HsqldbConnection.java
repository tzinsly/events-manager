package com.applicanttest.event.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

public class HsqldbConnection {

    final static Logger logger = Logger.getLogger(HsqldbConnection.class);

    public static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            logger.warn("First Connection stablished");
            doConnection();
        }
        return conn;
    }

    public static void doConnection() {

        try {
            //Registering the HSQLDB JDBC driver
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            //Creating the connection with HSQLDB
            conn = DriverManager.getConnection("jdbc:hsqldb:file:eventsdb;", "SA", "");
            if (conn != null) {
                logger.info("Connection created successfully");
            } else {
                logger.error("Problem with creating connection");
            }

        } catch (Exception e) {
            logger.error("Exception during connection create");
            e.printStackTrace(System.out);
        }
    }


}
