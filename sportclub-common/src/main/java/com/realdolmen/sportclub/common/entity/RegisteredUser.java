package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    private String phoneNumber1;

    @NotNull
    private String phoneNumber2;

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

    @ManyToOne
    private RegisteredUser parent;

    public boolean isOnAutomaticMailList() {
        return isOnAutomaticMailList;
    }

    public void setOnAutomaticMailList(boolean onAutomaticMailList) {
        isOnAutomaticMailList = onAutomaticMailList;
    }

    private String nonEditableField;

    public RegisteredUser() {

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

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
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

    public RegisteredUser getParent() {
        return parent;
    }

    public void setParent(RegisteredUser parent) {
        this.parent = parent;
    }

    @Override
    public String getEmail() {
        if(!isSelfManaged){
            return parent.getEmail();
        }
        return super.getEmail();
    }
}
