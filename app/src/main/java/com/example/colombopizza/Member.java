package com.example.colombopizza;

public class Member {
    private int id;
    private String name;
    private String email;
    private String location;
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {this.location = location; }

    public void setPass(String pass) {
        this.password = pass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() { return  location;}

    public String getPassword() {
        return password;
    }
}
