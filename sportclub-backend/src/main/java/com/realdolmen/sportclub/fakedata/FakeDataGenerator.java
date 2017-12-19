package com.realdolmen.sportclub.fakedata;

import com.realdolmen.sportclub.common.builder.*;
import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.*;
import org.apache.tomcat.jni.Local;
import org.hibernate.Hibernate;
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
    private UserRepository userRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderableRepository orderableRepository;

    @Autowired
    private EventRepository eventRepository;

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

    private Event publicEvent1;

    public void generate() {
        addRoles();
        addMembershipTypes();
        addEnrollments();
        addEvents();
        addRegisteredUsers();
        addEnrollmentOrders();
        addAttendanceOrders();
    }

    private void addEvents() {
        publicEvent1 = eventRepository.save(
                new EventBuilder().withName("Opendeurdag")
                        .build()
        );
    }

    private void addAttendanceOrders() {
        User guest1 = userRepository.save(
                new GuestBuilder().withEmail("gaston@pelican.com")
                        .withFirstName("Gaston")
                        .withLastName("Rouge")
                        .withRole(guest)
                        .build()
        );

        Order order1 = orderRepository.save(
                new OrderBuilder().orderDate(LocalDate.now().minusDays(1)).user(guest1).build()
        );

        Attendance attendance1 = orderableRepository.save(
                new AttendanceBuilder().ageCategory(AgeCategory.ADULT)
                        .description("A cool event for Gaston and his friends")
                        .event(publicEvent1).ordr(order1).price(new BigDecimal(5))

                        .build()
        );
    }

    private void addEnrollmentOrders() {
        List<RegisteredUser> registeredUsers = registeredUserRepository.findAll();
        Order order = orderRepository.save(
                new OrderBuilder().orderDate(LocalDate.now().minusDays(25)).user(registeredUsers.get(0)).build()
        );
        Orderable orderable = new UserEnrollmentBuilder().description("Description of the enrollment")
                .enrollment(enrollment)
                .ordr(order)
                .price(enrollment.getPrice())
                .build();
        orderable = orderableRepository.save(orderable);
        order.getOrderables().add(orderable);
        orderRepository.save(order);

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
        for (int i = 0; i <= REGISTEREDPARENTSAMOUNT; i++) {
            String firstName = "ParentFirstName" + i;
            String lastName = "ParentLastName" + i;
            Address address = new AddressBuilder().build();
            RegisteredUserBuilder parentBuilder = new RegisteredUserBuilder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(firstName + "." + lastName + "@email.com")
                    .address(address)
                    .gender(Gender.MAN)
                    .role(registeredUser)
                    .addEnrollment(enrollment)
                    .phoneNumber("0000000" + i)
                    .password(firstName + 123)
                    .isSelfManaged(true);

            registeredUserRepository.save(parentBuilder.build());
        }


        Address address = new AddressBuilder().build();
        RegisteredUserBuilder parentBuilder = new RegisteredUserBuilder()
                .firstName("Bert")
                .lastName("Vermeulen")
                .email("bv@gmail.com")
                .address(address)
                .gender(Gender.MAN)
                .role(registeredUser)
                .addEnrollment(enrollment)
                .phoneNumber("0494daziedevanhier")
                .password("test")
                .totalPoints(100)
                .isSelfManaged(true);

        registeredUserRepository.save(parentBuilder.build());

        // TODO: Fix proper way of adding parents to childs and vice versa
        /*for (int j = 0; j <= CHILDSPERPARENT; j++) {
            String childFirstName = "Child" + j;

            RegisteredUserBuilder childBuilder = new RegisteredUserBuilder()
                    .firstName(childFirstName)
                    .lastName(lastName)
                    .email(childFirstName + "." + lastName + "@email.com")
                    .address(address) //make this general
                    .gender(Gender.MAN)
                    .role(registeredUser)
                    //add enrollment
                    .phoneNumber("0000000" + i + j)
                    .password(childFirstName + 123);

            Hibernate.initialize(parent.getChildAccounts());
            parent.getChildAccounts().add(childBuilder.build(parent));

            parentBuilder.addChildAccount(childBuilder.build(parent));
        }*/
    }

    private void addMembershipTypes() {
        adults = membershipTypeRepository.save(new MembershipTypeBuilder().name("Achtien Plus").description("Membership voor volwassenen").build());
        membershipTypeRepository.save(new MembershipTypeBuilder().name("Kids").description("Membership voor kinderen").build());
    }

    private void addRoles() {
        guest = roleRepository.save(new RoleBuilder().name("GUEST")
                .addPrivilege(Privilege.GUEST_PRIVILEGES).build());
        registeredUser = roleRepository.save(new RoleBuilder().name("REGISTERED_USER")
                .addPrivilege(Privilege.REGISTERED_USER_PRIVILEGES)
                .addPrivilege(Privilege.GUEST_PRIVILEGES).build());
        enrolledUser = roleRepository.save(new RoleBuilder().name("ENROLLED_USER")
                .addPrivilege(Privilege.ENROLLED_USER_PRIVILEGES)
                .addPrivilege(Privilege.REGISTERED_USER_PRIVILEGES)
                .addPrivilege(Privilege.GUEST_PRIVILEGES).build());
        moderator = roleRepository.save(new RoleBuilder().name("MODERATOR")
                .addPrivilege(Privilege.MODERATOR_PRIVILEGES)
                .addPrivilege(Privilege.ENROLLED_USER_PRIVILEGES)
                .addPrivilege(Privilege.REGISTERED_USER_PRIVILEGES)
                .addPrivilege(Privilege.GUEST_PRIVILEGES).build());
        administrator = roleRepository.save(new RoleBuilder().name("ADMINISTRATOR")
                .addPrivilege(Privilege.ADMINISTRATOR_PRIVILEGES)
                .addPrivilege(Privilege.MODERATOR_PRIVILEGES)
                .addPrivilege(Privilege.ENROLLED_USER_PRIVILEGES)
                .addPrivilege(Privilege.REGISTERED_USER_PRIVILEGES)
                .addPrivilege(Privilege.GUEST_PRIVILEGES).build());
    }
}
