package com.realdolmen.sportclub;

import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.RoleRepository;
import com.realdolmen.sportclub.common.repository.*;
import com.realdolmen.sportclub.common.repository.UserRepository;
import com.realdolmen.sportclub.events.repository.EventRepository;
import com.realdolmen.sportclub.common.entity.Sportclub;
import com.realdolmen.sportclub.common.repository.SportclubRepository;
import com.realdolmen.sportclub.fakedata.FakeDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.realdolmen.sportclub"})
@EnableJpaRepositories("com.realdolmen.sportclub")
@EntityScan("com.realdolmen.sportclub")
@EnableScheduling
public class Application {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private SportclubRepository sportclubRepository;

    @Autowired
    private FakeDataGenerator fakeDataGenerator;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void initData() {
        sportclubRepository.save(new Sportclub("Sportclub A"));
        fakeDataGenerator.generate();
    }

}
