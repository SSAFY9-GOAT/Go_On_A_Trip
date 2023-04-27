package com.ssafy.goat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static util.ConnectionConst.*;

public class DBConnectionUtil {

    private static final DBConnectionUtil instance = new DBConnectionUtil();

    private DBConnectionUtil() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionUtil getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable ac : autoCloseables) {
            if (ac != null) {
                try {
                    ac.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
