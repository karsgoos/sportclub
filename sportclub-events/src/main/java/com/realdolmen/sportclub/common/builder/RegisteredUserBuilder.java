package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisteredUserBuilder extends User {

    private Long id;
    private String email = "johnny.bravo@hotmail.com";
    private String firstName = "Johnny";
    private String lastName = "Bravo";
    private Role role = new RoleBuilder().build();
    private LocalDate dateOfBirth = LocalDate.now().minusYears(20);
    private Gender gender = Gender.MAN;
    private Address address = new AddressBuilder().build();
    private String phoneNumber1 = "027887272";
    private String phoneNumber2 = "0470893956";
    private String password = "oo-la-la";
    private List<User> childAccounts = new ArrayList<>();
    private RegisteredUser parent;
    private List<Enrollment> enrollments = new ArrayList<>();
    private int totalPoints = 5;
    private boolean isOnAutomaticMailList = false;
    private String nonEditableField = "KIDJQF";
    private boolean isSelfManaged;

    public String getEmail() {
        return this.email;
    }

    public RegisteredUserBuilder(){

    }

    public RegisteredUserBuilder email(String email){
        this.email = email;
        return this;
    }

    public RegisteredUserBuilder firstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public RegisteredUserBuilder lastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public RegisteredUserBuilder role(Role role){
        this.role = role;
        return this;
    }

    public RegisteredUserBuilder parent(RegisteredUser parent){
        this.parent = parent;
        return this;
    }

    public RegisteredUserBuilder dateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public RegisteredUserBuilder gender(Gender gender){
        this.gender = gender;
        return this;
    }

    public RegisteredUserBuilder address(Address address){
        this.address = address;
        return this;
    }

    public RegisteredUserBuilder phoneNumber1(String phoneNumber1){
        this.phoneNumber1 = phoneNumber1;
        return this;
    }

    public RegisteredUserBuilder phoneNumber2(String phoneNumber2){
        this.phoneNumber2 = phoneNumber2;
        return this;
    }

    public RegisteredUserBuilder password(String password){
        this.password = password;
        return this;
    }

    public RegisteredUserBuilder addChildAccount(RegisteredUser childAccount){
        this.childAccounts.add(childAccount);
        return this;
    }

    public RegisteredUserBuilder addEnrollment(Enrollment enrollment){
        this.enrollments.add(enrollment);
        return this;
    }

    public RegisteredUserBuilder totalPoints(int totalPoints){
        this.totalPoints = totalPoints;
        return this;
    }

    public RegisteredUserBuilder isOnAutomaticMailList(boolean isOnAutomaticMailList){
        this.isOnAutomaticMailList = isOnAutomaticMailList;
        return this;
    }

    public RegisteredUserBuilder nonEditableField(String nonEditableField){
        this.nonEditableField = nonEditableField;
        return this;
    }

    public RegisteredUserBuilder isSelfManaged(boolean isSelfManaged){
        this.isSelfManaged = isSelfManaged;
        return this;
    }

    public RegisteredUser build(){
        RegisteredUser user = new RegisteredUser();
        user.setEmail(this.email);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setRole(this.role);
        user.setDateOfBirth(this.dateOfBirth);
        user.setGender(this.gender);
        user.setAddress(this.address);
        user.setPhoneNumber1(this.phoneNumber1);
        user.setPhoneNumber2(this.phoneNumber2);
        user.setPassword(this.password);
        user.setChildAccounts(this.childAccounts);
        user.setEnrollments(this.enrollments);
        user.setTotalPoints(this.totalPoints);
        user.setOnAutomaticMailList(this.isOnAutomaticMailList);
        user.setNonEditableField(this.nonEditableField);
        user.setSelfManaged(this.isSelfManaged);
        user.setParent(null);
        return user;
    }

    public RegisteredUser build(RegisteredUser parent){
        RegisteredUser user = new RegisteredUser();
        user.setEmail(this.email);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setRole(this.role);
        user.setDateOfBirth(this.dateOfBirth);
        user.setGender(this.gender);
        user.setAddress(this.address);
        user.setPhoneNumber1(this.phoneNumber1);
        user.setPhoneNumber2(this.phoneNumber2);
        user.setPassword(this.password);
        user.setChildAccounts(this.childAccounts);
        user.setEnrollments(this.enrollments);
        user.setTotalPoints(this.totalPoints);
        user.setOnAutomaticMailList(this.isOnAutomaticMailList);
        user.setNonEditableField(this.nonEditableField);
        user.setSelfManaged(this.isSelfManaged);
        user.setParent(parent);
        return user;
    }
}
