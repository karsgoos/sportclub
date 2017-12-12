package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.mocks.Event;
import com.realdolmen.sportclub.events.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventManagementController {
    @Autowired
    private EventManagementService service;

    @RequestMapping(method = RequestMethod.POST, value = "event")
    public @ResponseBody Event create(@RequestBody Event event) throws CouldNotCreateEventException {
        return service.create(event);
    }

    public EventManagementService getService() {
        return service;
    }

    public void setService(EventManagementService service) {
        this.service = service;
    }

    @ExceptionHandler(CouldNotCreateEventException.class)
    public ResponseEntity handleException() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
