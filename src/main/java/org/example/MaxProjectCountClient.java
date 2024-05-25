package org.example;

import java.util.Date;

public class MaxProjectCountClient {
    private int projectCount; // New property for the number of projects
    private int id; // Unique identifier
    private String name; // Client's name
    private final Date birthday; // Date of birth
    private final String level; // Client's level
    private final double salary; // Salary
    private final int clientId; // Unique client identifier
    private int workerId; // Unique worker identifier

    // Default constructor for initializing with default values
    public MaxProjectCountClient() {
        this.id = 0;
        this.name = "";
        this.birthday = new Date();
        this.level = "";
        this.salary = 0.0;
        this.clientId = 0;
    }

    // Class constructor for initializing all properties
    public MaxProjectCountClient(int id, String name, Date birthday, String level, double salary, int clientId, int workerId, int projectCount) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
        this.clientId = clientId;
        this.workerId = workerId;
        this.projectCount = projectCount;
    }

    // Getters and setters for all properties

    // Getter and setter for project start date
    private Date startDate;
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // Getter and setter for project finish date
    private Date finishDate;
    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    // Getter and setter for project identifier
    private int projectId;
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    // Getter and setter for unique object identifier
    public int getId() {
        return id;
    }

    public void setId() {
        setId(0);
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for client's name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter for date of birth
    public Date getBirthday() {
        return birthday;
    }

    // Getter for client's level
    public String getLevel() {
        return level;
    }

    // Getter for salary
    public double getSalary() {
        return salary;
    }

    // Getter for unique client identifier
    public int getClientId() {
        return clientId;
    }

    // Getter and setter for unique worker identifier
    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    // Getter and setter for client's project count
    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    // Overridden toString() method for representing the object as a string
    @Override
    public String toString() {
        return "MaxProjectCountClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", level='" + level + '\'' +
                ", salary=" + salary +
                ", clientId=" + clientId +
                ", workerId=" + workerId +
                ", projectCount=" + projectCount +
                '}';
    }
}
