package com.example.onlinejob;

import java.util.List;

public class Data {
    private List<Employee> items;

    // Constructors
    public Data() {
    }

    public Data(List<Employee> items) {
        this.items = items;
    }

    // Getters and Setters
    public List<Employee> getItems() {
        return items;
    }

    public void setItems(List<Employee> items) {
        this.items = items;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Data{" +
                "items=" + items +
                '}';
    }
}
