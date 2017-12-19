package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.repository.EventRepository;
import com.realdolmen.sportclub.events.DTO.AttendEventDTO;
import com.realdolmen.sportclub.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

@RestController
@RequestMapping(value = "api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Event> getEvents() {
        return eventRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Event event = eventRepository.findOne(id);
        if (event == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(event);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST, value = "/attend")
    public ResponseEntity attendEvent(@RequestBody AttendEventDTO dto) {
        Event e = eventRepository.findOne(Long.parseLong(dto.getEventId()));
        if (e == null)
            return ResponseEntity.badRequest().body("evenement werd niet gevonden");

        //closed event
        if (e.isClosed())
            eventService.attendClosedEvent(dto.getUserId(), dto.getEventId());
            //open event guest
        else if (dto.getUserId() == null) {
            try {
                eventService.attendOpenEvent(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getEventId(), dto.getNrOfAdults(), dto.getNrOfChildren());
            } catch (ConstraintViolationException c) {
                return ResponseEntity.badRequest().body("een of meerdere parameters voldoen niet aan de voorwaarden");
            }
        }
        //open event registered user
        else {
            try {
                eventService.attendOpenEvent(dto.getUserId(), dto.getEventId(), dto.getNrOfAdults(), dto.getNrOfChildren());
            } catch (ConstraintViolationException c) {
                return ResponseEntity.badRequest().body("een of meerdere parameters voldoen niet aan de voorwaarden");
            }
        }

        return ResponseEntity.ok().build();
    }

}