package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.AgeCategory;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EventManagementController {
    private EventManagementService service;

    @Autowired
    public EventManagementController(EventManagementService service) {
        this.service = service;
    }

    @RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.POST, value = "/events")
    public @ResponseBody
    Event create(@RequestBody Event event) throws CouldNotCreateEventException {


        return service.create(event);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public Event findEvent() {
        return new Event();
    }

    @ExceptionHandler(CouldNotCreateEventException.class)
    public ResponseEntity handleException() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
