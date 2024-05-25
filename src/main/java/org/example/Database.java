package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    // Logger for logging error messages
    private static final Logger logger = Logger.getLogger(Database.class.getName());
    // Singleton instance of Database
    private static Database instance;
    // Connection object to hold the database connection
    private Connection connection;

    // Private constructor to restrict instantiation from other classes
    private Database() {
        try {
            // Establishing connection to H2 database
            connection = DriverManager.getConnection("jdbc:h2:~/Modul6", "Raisa", "");
        } catch (SQLException e) {
            // Logging the error if the connection fails
            logger.log(Level.SEVERE, "Error connecting to the database", e);
        }
    }

    // Method to get the singleton instance of Database
    public static synchronized Database getInstance() {
        if (instance == null) {
            // Creating the instance if it doesn't exist
            instance = new Database();
        }
        // Returning the singleton instance
        return instance;
    }

    // Method to get the database connection
    public Connection getConnection() {
        return connection;
    }
}
