package com.realdolmen.sportclub.points.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import com.realdolmen.sportclub.points.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:59:12.091Z")

@RestController
public class PointsApiController {

    @RequestMapping(value = "/points/user/{userId}/event/{eventId}", method = RequestMethod.DELETE)
    public ResponseEntity<PointsDetails> pointsEventEventIdDelete(@PathVariable("userId") UUID userId, @PathVariable("eventId") UUID eventId) {
        System.out.println("event points for event: " + eventId + " for user: " + userId + " were removed");
        PointsDetails details = new PointsDetails();
        details.setTotalPoints(512);

        List<PointsDetailsLinksEvents> eventLinks = new ArrayList<>();
        eventLinks.add(new PointsDetailsLinksEvents().event(new UUID(125,16).toString()));
        eventLinks.add(new PointsDetailsLinksEvents().event(new UUID(15,32).toString()));
        eventLinks.add(new PointsDetailsLinksEvents().event(new UUID(5,3).toString()));

        PointsDetailsLinks links = new PointsDetailsLinks();
        links.setSelf("http://localhost:8080/points/user/" + userId);
        links.setUser("http://localhost:8080/user/" + userId);

        links.setEvents(eventLinks);

        details.setLinks(links);
        return new ResponseEntity<PointsDetails>(details, HttpStatus.OK);
    }

    @RequestMapping(value = "/points", method = RequestMethod.GET)
    public ResponseEntity<PointsList> pointsGet() {
        PointsList list = new PointsList();
        UUID uid = new UUID(256,15);
        PointsListInnerLinks links = new PointsListInnerLinks().user("http://localhost:8080/user/" + uid)
                .details("http://localhost:8080/points/user/" + uid);
        list.add(new PointsListInner().userId(uid).points(114).links(links));

        UUID uid2 = new UUID(4567,15);
        PointsListInnerLinks links2 = new PointsListInnerLinks().user("http://localhost:8080/user/" + uid2)
                .details("http://localhost:8080/points/user/" + uid2);
        list.add(new PointsListInner().userId(uid2).points(512).links(links2));


        return new ResponseEntity<PointsList>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/points/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> pointsUserUserIdDelete(@PathVariable("userId") UUID userId) {
        System.out.println("all the points for user: " + userId + " were removed");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/points/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<PointsDetails> pointsUserUserIdGet(@PathVariable("userId") UUID userId) {
        PointsDetails details = new PointsDetails();
        details.setTotalPoints(512);

        List<PointsDetailsLinksEvents> eventLinks = new ArrayList<>();
        eventLinks.add(new PointsDetailsLinksEvents().event(new UUID(125,16).toString()));
        eventLinks.add(new PointsDetailsLinksEvents().event(new UUID(15,32).toString()));
        eventLinks.add(new PointsDetailsLinksEvents().event(new UUID(5,3).toString()));

        PointsDetailsLinks links = new PointsDetailsLinks();
        links.setSelf("http://localhost:8080/points/user/" + userId);
        links.setUser("http://localhost:8080/user/" + userId);

        links.setEvents(eventLinks);

        details.setLinks(links);
        return new ResponseEntity<PointsDetails>(details, HttpStatus.OK);
    }

    @RequestMapping(value = "/points/user/{userId}", method = RequestMethod.POST)
    public ResponseEntity<PointsDetails> pointsUserUserIdPost(@PathVariable("userId") UUID userId, @Valid @RequestBody PointsInput pointsData) {
        System.out.println(pointsData.toString());
        PointsDetails details = new PointsDetails();
        details.setTotalPoints(512);

        List<PointsDetailsLinksEvents> eventLinks = new ArrayList<>();
        eventLinks.add(new PointsDetailsLinksEvents().event(new UUID(125,16).toString()));
        eventLinks.add(new PointsDetailsLinksEvents().event(new UUID(15,32).toString()));
        eventLinks.add(new PointsDetailsLinksEvents().event(new UUID(5,3).toString()));

        for (PointsInputEvents e : pointsData.getEvents()) {
            eventLinks.add(new PointsDetailsLinksEvents().event("http://localhost:8080/event/" + e.getEvent()));
        }

        PointsDetailsLinks links = new PointsDetailsLinks();
        links.setSelf("http://localhost:8080/points/user/" + userId);
        links.setUser("http://localhost:8080/user/" + userId);

        links.setEvents(eventLinks);

        details.setLinks(links);
        return new ResponseEntity<PointsDetails>(details, HttpStatus.OK);
    }

    @RequestMapping(value = "/points/user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<PointsDetails> pointsUserUserIdPut(@PathVariable("userId") UUID userId, @Valid @RequestBody PointsInput pointsData) {
        System.out.println(pointsData.toString());

        PointsDetails details = new PointsDetails();
        details.setTotalPoints(100);

        List<PointsDetailsLinksEvents> eventLinks = new ArrayList<>();
        for (PointsInputEvents e : pointsData.getEvents()) {
            eventLinks.add(new PointsDetailsLinksEvents().event("http://localhost:8080/event/" + e.getEvent()));
        }

        PointsDetailsLinks links = new PointsDetailsLinks();
        links.setSelf("http://localhost:8080/points/user/" + userId);
        links.setUser("http://localhost:8080/user/" + userId);

        links.setEvents(eventLinks);

        details.setLinks(links);
        return new ResponseEntity<PointsDetails>(details, HttpStatus.OK);
    }

}
