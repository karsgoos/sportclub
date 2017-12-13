package com.realdolmen.sportclub;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Sportclub;
import com.realdolmen.sportclub.common.repository.SportclubRepository;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
@ComponentScan("com.realdolmen.sportclub")
@EnableJpaRepositories("com.realdolmen.sportclub")
@EntityScan("com.realdolmen.sportclub")

public class Application {

    @Autowired
    private EventRepository eventRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void initData() {
        Event e = new Event();
        e.setName("FOOOD");
        e.setStartDate(LocalDateTime.of(2017,12,16,12,0,0));
        e.setEndDate(LocalDateTime.of(2017,12,16,14,0,0));
        e.setDeadline(LocalDateTime.of(2017,12,15,12,0,0));
        Address address = new Address();
        address.setCountry("Belgium");
        e.setAddress(address);
        eventRepository.save(e);
        Event t = new Event();
        t.setName("FOOOD");
        t.setStartDate(LocalDateTime.of(2017,12,17,12,0,0));
        t.setEndDate(LocalDateTime.of(2017,12,17,14,0,0));
        t.setDeadline(LocalDateTime.of(2017,12,16,12,0,0));
        Address a = new Address();
        a.setCountry("Belgium");
        t.setAddress(a);
        eventRepository.save(t);
        
    }

}
