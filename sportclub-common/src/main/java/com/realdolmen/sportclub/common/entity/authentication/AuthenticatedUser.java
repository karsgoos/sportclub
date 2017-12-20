package com.realdolmen.sportclub.common.entity.authentication;

import com.realdolmen.sportclub.common.entity.Enrollment;

import java.util.List;

public class AuthenticatedUser {
    private Long id;
    private String email;
    private String firstName, lastName;
    private int totalPoints;
    private String role;
    private List<String> enrollments;

    public AuthenticatedUser() {
    }

    public AuthenticatedUser(String email, String firstName, String lastName, int totalPoints) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPoints = totalPoints;
    }

    public AuthenticatedUser(Long id, String email, String firstName, String lastName, int totalPoints, String role, List<String> enrollments) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPoints = totalPoints;
        this.role = role;
        this.enrollments = enrollments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<String> enrollments) {
        this.enrollments = enrollments;
    }
}
