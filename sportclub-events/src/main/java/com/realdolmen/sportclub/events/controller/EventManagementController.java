package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.*;
import com.realdolmen.sportclub.events.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventManagementController {
    @Autowired
    private EventManagementService service;

    public EventManagementController() {

    }

    @RequestMapping(consumes = "application/json", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, value = "events")
    public @ResponseBody
    Event create(@RequestBody Event event) throws CouldNotCreateEventException {
        return service.create(event);
    }

    @RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.PUT, value = "events")
    public @ResponseBody
    Event update(@RequestBody Event event) throws CouldNotUpdateEventException {
        return service.update(event);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "events/{id}")
    public @ResponseBody
    Event findEvent(@PathVariable("id") Long id) throws EventNotFoundException {
        return service.find(id);
    }

    @RequestMapping(produces = "application/json", params = {"page", "pageSize"}, method = RequestMethod.GET, value = "events")
    public @ResponseBody
    List<Event> findAll(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return service.findAll(page, pageSize);
    }

    @PostMapping("events/{id}/attachment")
    public @ResponseBody ResponseEntity<?> uploadAttachement(@PathVariable("id") Long id, @RequestParam("file")MultipartFile attachment){
        if (attachment.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            service.saveAttachment(id, attachment);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                attachment.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "events/{id}/attachment", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] downloadAttachment(@PathVariable("id") Long id) throws AttachmentNotFoundException {
        return service.findAttachment(id);
    }


    @RequestMapping(produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", method = RequestMethod.GET, value = "events/{id}/attendees")
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
