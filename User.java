package com.example.sp24bse126;


public class User {
    private String username;
    private String password;
    private String role; // "Admin" or "Passenger"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + role;
    }
}
