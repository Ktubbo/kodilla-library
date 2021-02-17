/*
package com.library.dbmanager;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Properties;

@Service
public class DBManager {

    private Connection conn;
    private static DBManager dbManagerInstance;

    private DBManager() throws SQLException {
        */
/*Properties connectionProps = new Properties();
        connectionProps.put("user", "kodilla_librarian");
        connectionProps.put("password", "kodilla_password");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/kodilla_library?serverTimezone=" +
                        "Europe/Warsaw&useSSL=False&allowPublicKeyRetrieval=true",
                connectionProps);*//*

        Properties connectionProps = new Properties();
        connectionProps.put("user", "sa");
        connectionProps.put("password", "password");
        conn = DriverManager.getConnection(
                "jdbc:h2:file:~/test2;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE",
                connectionProps);
    }

    public static DBManager getInstance() throws SQLException {
        if (dbManagerInstance == null) {
            dbManagerInstance = new DBManager();
        }
        return dbManagerInstance;
    }

    public Connection getConnection() {
        return conn;
    }

    public Long getNextVal() {
        try {
            String sqlQuery = "SELECT * FROM hibernate_sequence;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            rs.next();
            long result = rs.getInt(1);
            return result;
        } catch (SQLException e) {
            return 0L;
        }
    }
}*/
