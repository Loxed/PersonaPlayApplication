package com.example.personaplayfront.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static Connection conn;
    private static String DB_URL= "jdbc:mysql://localhost:3306/mydatabase";
    private static String DB_USER = "username";
    private static String DB_PASSWORD = "password";

    //ctor
    public ConnectionUtil() {

    }
    public ConnectionUtil(String db_url, String db_user, String db_password) {
        DB_URL = db_url;
        DB_USER = db_user;
        DB_PASSWORD = db_password;
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        return conn;
    }

}
