package com.realdolmen.sportclub.common.entity.authentication;

public class AuthenticatedUser {
    private String email;
    private String firstName, lastName;
    private int totalPoints;
    private String role;

    public AuthenticatedUser(String email, String firstName, String lastName, int totalPoints) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPoints = totalPoints;
    }

    public AuthenticatedUser(String email, String firstName, String lastName, int totalPoints, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPoints = totalPoints;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
