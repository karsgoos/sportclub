package com.realdolmen.sportclub.backend.controller;

import com.realdolmen.sportclub.common.entity.Sportclub;
import com.realdolmen.sportclub.backend.SportclubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sportclub")
public class SportclubController {

    private final SportclubRepository sportclubRepository;

    @Autowired
    public SportclubController(SportclubRepository sportclubRepository) {
        this.sportclubRepository = sportclubRepository;
    }

    @GetMapping
    public List<Sportclub> getAllSportClubs() {
        return sportclubRepository.findAll();
    }
}
