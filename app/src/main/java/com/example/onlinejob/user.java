package com.example.onlinejob;

import com.example.onlinejob.Employee;

import java.util.List;

public class user {
    private List<Employee> items;

    // Constructors
    public user() {
    }

    public user(List<Employee> items) {
        this.items = items;
    }

    // Getters and setters
    // You can generate these using your IDE or define them manually

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Data{" +
                "items=" + items +
                '}';
    }
}
