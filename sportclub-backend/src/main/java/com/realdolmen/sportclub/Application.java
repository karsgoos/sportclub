package com.realdolmen.sportclub;

import com.realdolmen.sportclub.common.entity.Sportclub;
import com.realdolmen.sportclub.common.repository.EventRepository;
import com.realdolmen.sportclub.common.repository.RoleRepository;
import com.realdolmen.sportclub.common.repository.SportclubRepository;
import com.realdolmen.sportclub.common.repository.UserRepository;
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

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.realdolmen.sportclub"})
@EnableJpaRepositories("com.realdolmen.sportclub")
@EntityScan("com.realdolmen.sportclub")
@EnableScheduling   //necessary for scheduling of mails and points
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
