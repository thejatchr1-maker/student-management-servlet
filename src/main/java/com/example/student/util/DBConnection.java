package com.example.student.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final Logger logger =
            LogManager.getLogger(DBConnection.class);

    private static final String URL =
            "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "!QA890423";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("Database connection established.");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            logger.error("Database connection failed", e);
        }
        return null;
    }
}