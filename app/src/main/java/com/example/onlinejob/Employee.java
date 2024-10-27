package com.example.onlinejob;

import java.util.List;

public class Employee {
    private String id;
    private String name;
    private String password;
    private String dob;
    private String phone;
    private String qualification;
    private String skills;
    private String domain;
    private String experience;

    // Constructors
    public Employee() {
    }

    public Employee(String id, String name, String password, String dob, String phone, String qualification, String skills, String domain, String experience) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.phone = phone;
        this.qualification = qualification;
        this.skills = skills;
        this.domain = domain;
        this.experience = experience;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", phone=" + phone +
                ", qualification='" + qualification + '\'' +
                ", skills='" + skills + '\'' +
                ", domain='" + domain + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
