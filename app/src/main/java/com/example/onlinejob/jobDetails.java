package com.example.onlinejob;

import java.util.List;

public class jobDetails {
    private String id;
    private String companyName;
    private String jobName;
    private String salary;


    // Constructors
    public jobDetails() {
    }

    public jobDetails(String id, String companyname, String jobname, String Salary) {
        this.id = id;
        this.companyName = companyname;
        this.jobName = jobname;
        this.salary= Salary;

    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String name) {
        this.companyName = name;
    }

    public String getJobName() {
        return jobName;
    }

    public void setPassword(String jobName) {
        this.jobName= jobName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }



    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", jobName='" + jobName+ '\'' +
                ", salary='" + salary + '\''  +
                '}';
    }
}
