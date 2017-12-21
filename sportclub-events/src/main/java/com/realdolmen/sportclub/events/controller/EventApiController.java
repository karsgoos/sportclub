package com.realdolmen.sportclub.events.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.realdolmen.sportclub.common.builder.EventBuilder;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.controller.DTO.*;
import org.apache.poi.ss.formula.functions.Even;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-20T13:53:37.870Z")

@RestController
public class EventApiController {

    @Autowired
    private EventController service;

    @Autowired
    private EventManagementController managementService;



    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> eventEventIdDelete(@PathVariable("eventId") UUID eventId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
    public ResponseEntity<EventDetails> eventEventIdGet(@PathVariable("eventId") UUID eventId) {
        EventDetails details = new EventDetails();
        Event event = service.getEvent(eventId);
        return new ResponseEntity<EventDetails>(details
                    .name(event.getName())
                    .description(event.getDescription())
                    .startDate(event.getStartDate().toString())
                    .endDate(event.getEndDate().toString())
                    .deadline(event.getDeadline().toString())
                    .priceAdult(event.getPriceAdult())
                    .priceChild(event.getPriceChild())
                    .minParticipants(event.getMinParticipants())
                    .maxParticipants(event.getMaxParticipants())
                    .points(event.getPoints()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.POST)
    public ResponseEntity<Void> eventEventIdPost(@PathVariable("eventId") UUID eventId, @Valid @RequestBody EnrollData enrollData) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.PUT)
    public ResponseEntity<EventDetails> eventEventIdPut(@PathVariable("eventId") UUID eventId, @Valid @RequestBody EventData eventData) {
        // do some magic!
        return new ResponseEntity<EventDetails>(HttpStatus.OK);
    }

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public ResponseEntity<EventList> eventGet() {
        List<Event> events = new ArrayList<>();
        events.addAll(service.getEvents());

        EventList returnEvents = new EventList();

        for (Event event : events) {
            returnEvents.add(
                    new EventListInner().endDate(event.getEndDate().format(DateTimeFormatter.ISO_DATE).toString())
                    .deadline(event.getDeadline().format(DateTimeFormatter.ISO_DATE).toString())
                    .name(event.getName())
                    .startDate(event.getStartDate().format(DateTimeFormatter.ISO_DATE).toString())
                    .links(
                            new EventListInnerLinks().self("http://sportclub-events-staging.herokuapp.com/event/" + event.getId())
                    )
            );
        }
        return new ResponseEntity<EventList>(HttpStatus.OK);
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public ResponseEntity<EventDetails> eventPost(@Valid @RequestBody EventData eventData) {
        Event createEvent = EventBuilder.anEvent().withDescription(eventData.getDescription())
                .withName(eventData.getName())
//                .withEndDate(LocalDateTime.of(eventData.getEndDate(), LocalTime.now()))
//                .withStartDate(LocalDateTime.of(eventData.getStartDate(), LocalTime.now()))
                .withMaxParticipants(eventData.getMaxParticipants())
                .withMinParticipants(eventData.getMinParticipants())
                .withPriceAdult(eventData.getPriceAdult())
                .withPriceChild(eventData.getPriceChild())
                .build();
        createEvent.setPoints(eventData.getPoints());

        ResponseEntity<Event> tmp = service.getEvent(managementService.create(createEvent).getId());
        Event event = tmp.getBody();


        EventDetails details = new EventDetails().description(event.getDescription())
                .endDate(event.getEndDate().toLocalDate().toString())
                .startDate(event.getStartDate().toLocalDate().toString())
                .deadline(event.getDeadline().toString())
                .links(new EventListInnerLinks().self("http://sportclub-events-staging.herokuapp.com/event/" + event.getId()))
                .maxParticipants(event.getMaxParticipants())
                .minParticipants(event.getMinParticipants())
                .name(event.getName())
                .points(event.getPoints())
                .priceAdult(event.getPriceAdult())
                .priceChild(event.getPriceChild());

        return new ResponseEntity<EventDetails>(HttpStatus.OK);
    }

}
