package com.realdolmen.sportclub.common.entity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class RegisteredUser extends User {

    private LocalDate dateOfBirth;

    private boolean gender;

    private Address address;

    private String phoneNumber;

    private String mobileNumber;

    private String password;

    private List<User> childAccounts;

    private List<Enrollment> enrollments;

    private int totalPoints;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getChildAccounts() {
        return childAccounts;
    }

    public void setChildAccounts(List<User> childAccounts) {
        this.childAccounts = childAccounts;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
