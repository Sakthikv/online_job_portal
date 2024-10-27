package com.example.onlinejob;

import java.util.List;

public class recruiterData {
    private List<recruiterDetails> items;

    // Constructors
    public recruiterData() {
    }

    public recruiterData(List<recruiterDetails> items) {
        this.items = items;
    }

    // Getters and Setters
    public List<recruiterDetails> getItems() {
        return items;
    }

    public void setItems(List<recruiterDetails> items) {
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
