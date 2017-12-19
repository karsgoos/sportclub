package com.realdolmen.sportclub.common.entity;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RegisteredUser extends User {

    @NotNull
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @Embedded
    @NotNull
    private Address address;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String mobileNumber;

    @NotNull
    private String password;

    @OneToMany
    private List<User> childAccounts = new ArrayList<>();

    @ManyToMany
    private List<Enrollment> enrollments = new ArrayList<>();

    @Column
    private int totalPoints;

    @NotNull
    private boolean isOnAutomaticMailList;

    @NotNull
    private boolean isSelfManaged;

    public boolean isOnAutomaticMailList() {
        return isOnAutomaticMailList;
    }

    public void setOnAutomaticMailList(boolean onAutomaticMailList) {
        isOnAutomaticMailList = onAutomaticMailList;
    }

    private String nonEditableField;

    public RegisteredUser(){

    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender isGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    public Gender getGender() {
        return gender;
    }

    public String getNonEditableField() {
        return nonEditableField;
    }

    public void setNonEditableField(String nonEditableField) {
        this.nonEditableField = nonEditableField;
    }
    
    public boolean isSelfManaged() {
        return isSelfManaged;
    }

    public void setSelfManaged(boolean selfManaged) {
        isSelfManaged = selfManaged;
    }
}
