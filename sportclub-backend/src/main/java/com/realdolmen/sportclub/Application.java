package com.realdolmen.sportclub;

import com.realdolmen.sportclub.common.entity.Sportclub;
import com.realdolmen.sportclub.common.repository.SportclubRepository;
import com.realdolmen.sportclub.fakedata.FakeDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

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
