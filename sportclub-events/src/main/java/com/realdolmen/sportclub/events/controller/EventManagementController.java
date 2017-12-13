package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.AgeCategory;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
import com.realdolmen.sportclub.events.exceptions.EventExportException;
import com.realdolmen.sportclub.events.exceptions.EventNotFoundException;
import com.realdolmen.sportclub.events.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EventManagementController {
    @Autowired
    private EventManagementService service;

    public EventManagementController() {

    }

    @RequestMapping(consumes = "application/json", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, value = "/events")
    public @ResponseBody
    Event create(@RequestBody Event event) throws CouldNotCreateEventException {
        return service.create(event);
    }

    @RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.PUT, value = "/events")
    public @ResponseBody
    Event update(@RequestBody Event event) throws CouldNotUpdateEventException {
        return service.update(event);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "/events/{id}")
    public @ResponseBody
    Event findEvent(@PathVariable("id") Long id) throws EventNotFoundException {
        return service.find(id);
    }

    @RequestMapping(produces = "application/json", params = {"page", "pageSize"}, method = RequestMethod.GET, value = "/events")
    public @ResponseBody
    List<Event> findAll(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return service.findAll(page, pageSize);
    }

    @RequestMapping(produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", method = RequestMethod.GET, value = "/events/{id}/attendees")
    public @ResponseBody
    byte[] exportAttendees(@PathVariable("id") Long id) throws EventExportException, EventNotFoundException {
        return service.exportAttendanceList(id);
    }

    @ExceptionHandler(CouldNotCreateEventException.class)
    public ResponseEntity handleCouldNotCreateEventException() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handleEventNotFoundException() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventExportException.class)
    public ResponseEntity handleEventExportException() {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
