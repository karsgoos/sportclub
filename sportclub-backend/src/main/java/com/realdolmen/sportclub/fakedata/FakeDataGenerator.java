package com.realdolmen.sportclub.fakedata;

import com.realdolmen.sportclub.common.builder.*;
import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.EnrollmentRepository;
import com.realdolmen.sportclub.common.repository.MembershipTypeRepository;
import com.realdolmen.sportclub.common.repository.RegisteredUserRepository;
import com.realdolmen.sportclub.common.repository.RoleRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FakeDataGenerator {

    static final int REGISTEREDPARENTSAMOUNT = 5;
    static final int CHILDSPERPARENT = 2;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    /*
    *
    * Reusable attributes
    *
    */

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Role administrator;
    private Role registeredUser;
    private Role enrolledUser;
    private Role moderator;
    private Role guest;

    private List<RegisteredUser> registeredUsers;
    private User parentAccount;

    private MembershipType adults;

    private Enrollment enrollment;

    public void generate() {
        addRoles();
        addMembershipTypes();
        addEnrollments();
        addRegisteredUsers();
    }

    private void addEnrollments() {
        Date now = new Date();
        enrollment = enrollmentRepository.save(new EnrollmentBuilder()
                .name("Lazy bikers of 2017")
                .startDate(LocalDate.parse("01/01/2017", formatter))
                .endDate(LocalDate.parse("31/12/2017", formatter))
                .membershipType(adults)
                .price(new BigDecimal(20))
                .build());
    }

    private void addRegisteredUsers() {

        for(int i=0; i<=REGISTEREDPARENTSAMOUNT; i++ ){
            String firstName = "ParentFirstName " + i;
            String lastName = "ParentLastName " + i;
            Address address = new AddressBuilder().build();
            RegisteredUserBuilder parentBuilder = new RegisteredUserBuilder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(firstName+"."+lastName+"@email.com")
                    .address(address)
                    .gender(Gender.MAN)
                    .role(registeredUser)
                    .addEnrollment(enrollment)
                    .phoneNumber("0000000" + i)
                    .password(firstName + 123);
            for(int j=0; j<=CHILDSPERPARENT; j++){
                String childFirstName = "Child " + j;

                RegisteredUserBuilder childBuilder = new RegisteredUserBuilder()
                        .firstName(childFirstName)
                        .lastName(lastName)
                        .email(childFirstName+"."+lastName+"@email.com")
                        .address(address) //make this general
                        .gender(Gender.MAN)
                        .role(registeredUser)
                        //add enrollment
                        .phoneNumber("0000000" + i + j)
                        .password(childFirstName + 123);
                parentBuilder.addChildAccount(registeredUserRepository.save(childBuilder.build()));
            }

            registeredUserRepository.save(parentBuilder.build());

        }



    }

    private void addMembershipTypes() {
        adults = membershipTypeRepository.save(new MembershipTypeBuilder().name("Achtien Plus").description("Membership voor volwassenen").build());
        membershipTypeRepository.save(new MembershipTypeBuilder().name("Kids").description("Membership voor kinderen").build());

    }

    private void addRoles() {
        administrator = roleRepository.save( new RoleBuilder().name("ADMINISTRATOR").addPrivilege(Privilege.CAN_CHANGE_PRIVILEGES).build());
        registeredUser = roleRepository.save( new RoleBuilder().name("REGISTERED_USER").addPrivilege(Privilege.CAN_CHANGE_PRIVILEGES).build());
        enrolledUser = roleRepository.save( new RoleBuilder().name("ENROLLED_USER").addPrivilege(Privilege.CAN_CHANGE_PRIVILEGES).build());
        moderator = roleRepository.save( new RoleBuilder().name("MODERATOR").addPrivilege(Privilege.CAN_CHANGE_PRIVILEGES).build());
        guest = roleRepository.save( new RoleBuilder().name("GUEST").addPrivilege(Privilege.CAN_CHANGE_PRIVILEGES).build());
    }
}
