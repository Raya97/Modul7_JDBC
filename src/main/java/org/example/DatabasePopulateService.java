package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabasePopulateService {
    // Logger for logging error messages
    private static final Logger logger = Logger.getLogger(DatabasePopulateService.class.getName());

    public static void main(String[] args) {
        try {
            // Connecting to the database using the Database class
            Connection connection = Database.getInstance().getConnection();

            // SQL queries from the populate_db.sql file
            String sqlFilePath = "sql/populate_db.sql";
            String sql = readSQLFromFile(sqlFilePath);

            // Executing SQL queries to populate the database tables
            executeSQL(connection, sql);

            System.out.println("Database tables have been successfully populated.");
        } catch (SQLException | IOException e) {
            logger.log(Level.SEVERE, "Error populating database tables", e);
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
}
