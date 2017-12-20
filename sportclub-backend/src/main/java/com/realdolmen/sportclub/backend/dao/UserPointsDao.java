package com.realdolmen.sportclub.backend.dao;

public class UserPointsDao {

    private String email;
    private String fullName;
    private int points;

    public UserPointsDao() {}

    public UserPointsDao(String email, String fullName, int points) {
        this.email = email;
        this.fullName = fullName;
        this.points = points;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
