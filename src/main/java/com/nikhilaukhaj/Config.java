package com.nikhilaukhaj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/todoList?serverTimezone=UTC";
    private static String username = "admin";
    private static String password = "1234";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(CONNECTION_STRING, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
