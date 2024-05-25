package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseInitService {
    // Logger for logging error messages
    private static final Logger logger = Logger.getLogger(DatabaseInitService.class.getName());

    public static void main(String[] args) {
        try {
            // Connecting to the database using the Database class
            Connection connection = Database.getInstance().getConnection();

            // SQL query from init_db.sql file
            String sqlFilePath = "sql/init_db.sql";
            String sql = readSQLFromFile(sqlFilePath);

            // Table name for checking and creating
            String tableName = "CLIENT";

            // Checking if the "CLIENT" table exists
            if (!tableExists(connection, tableName)) {
                // The "CLIENT" table does not exist, it can be created
                String createTableSQL = "CREATE TABLE " + tableName + " (ID SERIAL PRIMARY KEY, NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2 AND LENGTH(NAME) <= 1000))";
                executeSQL(connection, createTableSQL);
                System.out.println("Table " + tableName + " has been created.");
            } else {
                System.out.println("Table " + tableName + " already exists.");
            }

            // Executing the SQL query to initialize the database
            executeSQL(connection, sql);

            System.out.println("Database has been successfully initialized.");
        } catch (SQLException | IOException e) {
            if (e.getMessage().contains("already exists")) {
                // Ignoring the exception if the table already exists
                System.out.println("Table already exists.");
            } else {
                logger.log(Level.SEVERE, "Error initializing the database", e);
            }
        }
    }

    // Method to read SQL queries from a file
    private static String readSQLFromFile(String filePath) throws IOException {
        StringBuilder sql = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sql.append(line).append("\n");
            }
        }
        return sql.toString();
    }

    // Method to execute SQL queries
    private static void executeSQL(Connection connection, String sql) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    // Method to check if a table exists in the database
    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        String checkTableSQL = "SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME=?";
        try (PreparedStatement statement = connection.prepareStatement(checkTableSQL)) {
            statement.setString(1, tableName);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }
}
