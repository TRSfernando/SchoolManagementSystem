package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHandler {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/SchoolManagementSystemDB";
            String username = "root";
            String password = "Discrimination@4";
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}

