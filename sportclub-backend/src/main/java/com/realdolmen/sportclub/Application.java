package com.realdolmen.sportclub;

import com.realdolmen.sportclub.common.entity.Sportclub;
import com.realdolmen.sportclub.common.repository.SportclubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "com.realdolmen.sportclub")
public class Application {

    @Autowired
    private SportclubRepository sportclubRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void initData() {
        sportclubRepository.save(new Sportclub("Sportclub A"));
        sportclubRepository.save(new Sportclub("Sportclub B"));
        sportclubRepository.save(new Sportclub("Sportclub C"));
        sportclubRepository.save(new Sportclub("Sportclub D"));
        sportclubRepository.save(new Sportclub("Sportclub E"));
    }

}
