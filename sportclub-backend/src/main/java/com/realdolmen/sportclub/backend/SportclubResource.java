package com.realdolmen.sportclub.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sportclub")
public class SportclubResource {

    private final SportclubRepository sportclubRepository;

    @Autowired
    public SportclubResource(SportclubRepository sportclubRepository) {
        this.sportclubRepository = sportclubRepository;
    }

    @GetMapping
    public List<Sportclub> getAlleSportClubs() {
        return sportclubRepository.findAll();
    }
}
