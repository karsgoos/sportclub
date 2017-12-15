package com.realdolmen.sportclub;

import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.EventRepository;
import com.realdolmen.sportclub.common.repository.RoleRepository;
import com.realdolmen.sportclub.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
@ComponentScan("com.realdolmen.sportclub")
@EnableJpaRepositories("com.realdolmen.sportclub")
@EntityScan("com.realdolmen.sportclub")
public class Application {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void initData() {
        Event e = new Event();
        e.setName("Coding Marathon");
        e.setStartDate(LocalDateTime.of(2017,12,16,12,0,0));
        e.setEndDate(LocalDateTime.of(2017,12,16,14,0,0));
        e.setDeadline(LocalDateTime.of(2017,12,15,12,0,0));
        e.setImageUrl("http://www.adpoly.ac.ae/En/Academics/AbuDhabiMainCampus/ISET/CODATHON/Documents/CODE%20%20%20DS-01.png");
        Address address = new Address();
        address.setCountry("Belgium");
        e.setAddress(address);
        e.setPriceAdult(BigDecimal.TEN);
        e.setPriceChild(BigDecimal.ONE);
        eventRepository.save(e);
        Event t = new Event();
        t.setName("Closed FOOOD");
        t.setStartDate(LocalDateTime.of(2017,12,17,12,0,0));
        t.setEndDate(LocalDateTime.of(2017,12,17,14,0,0));
        t.setDeadline(LocalDateTime.of(2017,12,16,12,0,0));
        Address a = new Address();
        a.setCountry("Belgium");
        t.setAddress(a);
        t.setClosed(true);
        t.setDescription("Im a closed event");
        t.setPriceAdult(BigDecimal.ONE);
        t.setPriceChild(BigDecimal.ONE);
        eventRepository.save(t);

        RegisteredUser u = new RegisteredUser();
        u.setFirstName("bert");
        u.setLastName("beton");
        u.setEmail("bert@beton.be");
        u.setMobileNumber("0");
        u.setPhoneNumber("1");
        u.setDateOfBirth(LocalDate.of(1991,8,07));
        u.setAddress(new Address());
        u.setGender(Gender.MAN);
        u.setPassword("abc");
        Role r = new Role();
        r.setName("guest");
        r.setPrivileges(new ArrayList<>());
        roleRepository.save(r);
        u.setRole(r);
        userRepository.save(u);
    }

}
