package com.example.onlinejob;

public class recruiterDetails {
    private String id;
    private String name;
    private String password;
    private String dob;
    private String phone;
    private String emaild;


    // Constructors
    public recruiterDetails() {
    }

    public recruiterDetails(String id, String name, String password, String dob, String phone, String emaild) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.phone = phone;
        this.emaild = emaild;

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

    public String getemaild() {
        return emaild;
    }

    public void setQualification(String emaild) {
        this.emaild = emaild;
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
                ", emailid=" + emaild + '\'' +

                '}';
    }
}
