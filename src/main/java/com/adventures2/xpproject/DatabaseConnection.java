package com.adventures2.xpproject;

import java.sql.*;

public class DatabaseConnection {
    //CONSTANTS
    private final static String CLASS_NAME = "com.mysql.jdbc.Driver";
    private final static String DB_HOST = "jdbc:mysql://mysql83.unoeuro.com/serento_dk_db?useSSL=false";
    private final static String DB_USER = "serento_dk";
    private final static String DB_PASSWORD = "y2med9zt5c";

    //DYNAMICS
    private static Connection connection;
    private static Statement statement;

    private static void createConnection() {
        try {
            Class.forName(CLASS_NAME);
            connection = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeConnetion() {
        if(connection != null && statement != null) {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        createConnection();
        return connection;
    }

    public static ResultSet query(String sql) {
        createConnection();

        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int insertReturnId (PreparedStatement preparedStatement) {
        createConnection();

        try {
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
                return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }

        return 0;
    }

    public static void insert(PreparedStatement preparedStatement) {
        createConnection();

        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(PreparedStatement preparedStatement) {
        createConnection();

        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

