package com.campus.connect.Controller;

// This DTO defines the structure of the data sent from the backend to the Angular frontend
public class AdminReportDTO {
    
    private long totalEvents;
    private long totalHosts;
    private long totalCoordinators;
    private long totalStudents;

    // 1. Constructor
    public AdminReportDTO(long totalEvents, long totalHosts, long totalCoordinators, long totalStudents) {
        this.totalEvents = totalEvents;
        this.totalHosts = totalHosts;
        this.totalCoordinators = totalCoordinators;
        this.totalStudents = totalStudents;
    }
    
    // 2. Default Constructor (Good practice for Spring/Jackson)
    public AdminReportDTO() {
    }

    // 3. Getters and Setters
    public long getTotalEvents() { return totalEvents; }
    public void setTotalEvents(long totalEvents) { this.totalEvents = totalEvents; }

    public long getTotalHosts() { return totalHosts; }
    public void setTotalHosts(long totalHosts) { this.totalHosts = totalHosts; }

    public long getTotalCoordinators() { return totalCoordinators; }
    public void setTotalCoordinators(long totalCoordinators) { this.totalCoordinators = totalCoordinators; }

    public long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(long totalStudents) { this.totalStudents = totalStudents; }
}