package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.dto.MessageDto;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.User;
import com.realdolmen.sportclub.events.DTO.AttendEventDTO;
import com.realdolmen.sportclub.events.exceptions.*;
import com.realdolmen.sportclub.events.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('MODERATOR_PRIVILEGES')")
    public @ResponseBody
    MessageDto create(@RequestBody Event event) {
        try {
            Event result = service.create(event);
            return new MessageDto("", result);
        } catch (CouldNotCreateEventException e) {
            return new MessageDto(e.getMessage(), null);
        }
    }

    @RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.PUT, value = "events/{id}")
    @PreAuthorize("hasAuthority('MODERATOR_PRIVILEGES')")
    public @ResponseBody
    MessageDto update(@PathVariable("id") Long id, @RequestBody Event event) {
        try {
            Event result = service.update(event);
            return new MessageDto("", result);
        } catch (CouldNotUpdateEventException e) {
            return new MessageDto(e.getMessage(), null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "events/{id}")
    @PreAuthorize("hasAuthority('MODERATOR_PRIVILEGES')")
    public @ResponseBody
    MessageDto delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new MessageDto("", null);
        } catch (EventNotFoundException e) {
            return new MessageDto(e.getMessage(), null);
        }
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "events/detail/{id}")
    @PreAuthorize("hasAuthority('GUEST_PRIVILEGES')")
    public @ResponseBody
    MessageDto findEvent(@PathVariable("id") Long id) {
        try {
            Event result = service.find(id);
            return new MessageDto("", result);
        } catch (EventNotFoundException e) {
            return new MessageDto("Het gevraagde evenement werd niet gevonden.", null);
        }
    }

    @RequestMapping(produces = "application/json", params = {"page", "pageSize"}, method = RequestMethod.GET, value = "events/timeline")
    @PreAuthorize("hasAuthority('MODERATOR_PRIVILEGES')")
    public @ResponseBody
    MessageDto findAll(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<Event> result = service.findAll(page, pageSize);
        return new MessageDto("", result);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "events/{id}/cancellations")
    @PreAuthorize("hasAuthority('ADMINISTRATOR_PRIVILEGES')")
    public @ResponseBody
    MessageDto findCancellations(@PathVariable("id") Long id) {
        try {
            List<User> result = service.findCancellations(id);
            return new MessageDto("", result);
        } catch (EventNotFoundException e) {
            return new MessageDto("Het gevraagde evenement werd niet gevonden.", null);
        }
    }

    // The endpoint determines the file name in most popular browsers,
    // so this has to be a Dutch endpoint
    @RequestMapping(produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", method = RequestMethod.GET, value = "events/{id}/cancellations/annulaties")
    @PreAuthorize("hasAuthority('ADMINISTRATOR_PRIVILEGES')")
    public @ResponseBody
    byte[] exportCancellations(@PathVariable("id") Long id) throws EventExportException, EventNotFoundException {
        return service.exportCancellations(id);
    }

    @PostMapping("events/{id}/attachment")
    @PreAuthorize("hasAuthority('MODERATOR_PRIVILEGES')")
    public @ResponseBody
    ResponseEntity<?> uploadAttachement(@PathVariable("id") Long id, @RequestParam("file") MultipartFile attachment) {
        if (attachment.isEmpty()) {
            return new ResponseEntity(new MessageDto("Er werd geen bijlage meegegeven.", null), HttpStatus.BAD_REQUEST);
        }

        try {
            service.saveAttachment(id, attachment);
        } catch (IOException e) {
            return new ResponseEntity<>(new MessageDto("Er liep iets fout bij het wegschrijven van de bijlage.", null), HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new MessageDto("Het opgegeven bestand moet in PDF formaat zijn.", null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new MessageDto("", null), HttpStatus.CREATED);
    }

    @PostMapping("events/{id}/image")
    @PreAuthorize("hasAuthority('MODERATOR_PRIVILEGES')")
    public @ResponseBody
    ResponseEntity<?> uploadImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile attachment) {
        if (attachment.isEmpty()) {
            return new ResponseEntity(new MessageDto("Er werd geen afbeelding meegegeven.", null), HttpStatus.BAD_REQUEST);
        }

        try {
            service.saveImage(id, attachment);
        } catch (IOException e) {
            return new ResponseEntity<>(new MessageDto("Er liep iets fout bij het wegschrijven van de afbeelding.", null), HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new MessageDto("Het opgegeven bestand moet een afbeelding zijn (GIF/JPG/PNG).", null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new MessageDto("", null), HttpStatus.CREATED);
    }

    @GetMapping(value = "events/{id}/image")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("id") Long id) throws AttachmentNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentType(service.getImageMimeTypeForEvent(id));
        } catch (EventNotFoundException e) {
            throw new AttachmentNotFoundException(e);
        }
        ResponseEntity<byte[]> entity = new ResponseEntity<>(service.findImage(id), headers, HttpStatus.OK);
        return entity;
    }

    @GetMapping(value = "events/{id}/attachment", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] downloadAttachment(@PathVariable("id") Long id) throws AttachmentNotFoundException {
        return service.findAttachment(id);
    }

    /**
     * Returns a Json list of event participants including number of adults and children
     *
     * @param id event id
     * @return json list of event participants
     * @throws EventNotFoundException
     */
    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "events/{id}/participants")
    @PreAuthorize("hasAuthority('MODERATOR_PRIVILEGES')")
    public @ResponseBody
    List<AttendEventDTO> findParticipantsOnEvent(@PathVariable("id") Long id) throws EventNotFoundException {
        return service.findParticipantsOfEvent(id);
    }

    // The endpoint determines the file name in most popular browsers,
    // so this has to be a Dutch endpoint
    @RequestMapping(produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", method = RequestMethod.GET, value = "events/{id}/deelnemers")
    @PreAuthorize("hasAuthority('MODERATOR_PRIVILEGES')")
    public @ResponseBody
    byte[] exportAttendees(@PathVariable("id") Long id) throws EventExportException, EventNotFoundException {
        return service.exportAttendanceList(id);
    }


}
