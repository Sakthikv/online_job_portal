package com.example.onlinejob;

import java.util.List;

public class jobData {
    private List<jobDetails> items;

    // Constructors
    public jobData() {
    }

    public jobData(List<jobDetails> items) {
        this.items = items;
    }

    // Getters and Setters
    public List<jobDetails> getItems() {
        return items;
    }

    public void setItems(List<jobDetails> items) {
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
