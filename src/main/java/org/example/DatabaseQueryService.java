package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseQueryService {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(DatabaseQueryService.class.getName());

    // Constructor that initializes the connection to the database using the Database class
    public DatabaseQueryService() {
        this.connection = Database.getInstance().getConnection();
    }

    // Method to find clients with the maximum number of projects based on a condition value
    public List<MaxProjectCountClient> findMaxProjectsClient(int yourConditionValue) {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sqlForClient = "SELECT NAME, ID FROM client WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlForClient)) {
            preparedStatement.setInt(1, yourConditionValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MaxProjectCountClient client = new MaxProjectCountClient();
                client.setName(resultSet.getString("name"));
                client.setProjectCount(resultSet.getInt("project_count"));
                client.setStartDate(resultSet.getDate("start_date"));
                client.setFinishDate(resultSet.getDate("finish_date"));
                client.setProjectId(resultSet.getInt("project_id"));
                result.add(client);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching data from client table", e);
        }

        return result;
    }

    // Method to find projects based on a condition value
    public List<MaxProjectCountClient> findMaxProjectsProject(int yourConditionValue) {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sqlForProject = "SELECT ID, CLIENT_ID, START_DATE, FINISH_DATE FROM project WHERE CLIENT_ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlForProject)) {
            preparedStatement.setInt(1, yourConditionValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MaxProjectCountClient client = new MaxProjectCountClient();
                client.setName(resultSet.getString("name"));
                client.setProjectCount(resultSet.getInt("project_count"));
                result.add(client);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching data from project table", e);
        }

        return result;
    }

    // Method to find project workers based on a condition value
    public List<MaxProjectCountClient> findMaxProjectsProjectWorker(int yourConditionValue) {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sqlForProjectWorker = "SELECT PROJECT_ID, WORKER_ID FROM project_worker WHERE WORKER_ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlForProjectWorker)) {
            preparedStatement.setInt(1, yourConditionValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MaxProjectCountClient client = new MaxProjectCountClient();
                client.setName(resultSet.getString("name"));
                client.setProjectCount(resultSet.getInt("project_count"));
                result.add(client);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching data from project_worker table", e);
        }

        return result;
    }

    // Method to find workers based on a condition value
    public List<MaxProjectCountClient> findMaxProjectsWorker(int yourConditionValue) {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sqlForWorker = "SELECT ID, NAME, BIRTHDAY, LEVEL, SALARY FROM worker WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlForWorker)) {
            preparedStatement.setInt(1, yourConditionValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MaxProjectCountClient client = new MaxProjectCountClient();
                client.setName(resultSet.getString("name"));
                client.setProjectCount(resultSet.getInt("project_count"));
                result.add(client);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching data from worker table", e);
        }

        return result;
    }

    // Main method for testing the database query service
    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();

        // Calling methods to search for clients, projects, project_workers, and workers
        List<MaxProjectCountClient> clients = queryService.findMaxProjectsClient(42);
        List<MaxProjectCountClient> projects = queryService.findMaxProjectsProject(42);
        List<MaxProjectCountClient> projectWorkers = queryService.findMaxProjectsProjectWorker(42);
        List<MaxProjectCountClient> workers = queryService.findMaxProjectsWorker(42);

        // Displaying the results
        for (MaxProjectCountClient client : clients) {
            System.out.println("Client Name: " + client.getName());
            System.out.println("Project Count: " + client.getProjectCount());
            System.out.println("-----------------------------");
        }

        for (MaxProjectCountClient project : projects) {
            System.out.println("Project ID: " + project.getId());
            System.out.println("Client ID: " + project.getClientId());
            System.out.println("Start Date: " + project.getStartDate());
            System.out.println("End Date: " + project.getFinishDate());
            System.out.println("-----------------------------");
        }

        for (MaxProjectCountClient projectWorker : projectWorkers) {
            System.out.println("Project ID: " + projectWorker.getProjectId());
            System.out.println("Worker ID: " + projectWorker.getWorkerId());
            System.out.println("-----------------------------");
        }

        for (MaxProjectCountClient worker : workers) {
            System.out.println("Worker ID: " + worker.getId());
            System.out.println("Worker Name: " + worker.getName());
            System.out.println("Birthday: " + worker.getBirthday());
            System.out.println("Level: " + worker.getLevel());
            System.out.println("Salary: " + worker.getSalary());
            System.out.println("-----------------------------");
        }
    }
}
