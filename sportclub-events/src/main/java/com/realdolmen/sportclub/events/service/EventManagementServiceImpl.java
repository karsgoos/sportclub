package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.*;
import com.realdolmen.sportclub.common.entity.User;
import com.realdolmen.sportclub.events.repository.EventRepository;
import com.realdolmen.sportclub.events.service.export.EventExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EventManagementServiceImpl implements EventManagementService {
    @Autowired
    private EventRepository repository;

    @Override
    public Event create(Event event) throws CouldNotCreateEventException {
        if (event == null) {
            throw new CouldNotCreateEventException(new IllegalArgumentException("Event cannot be null."));
        }

        try {
            validate(event);
        } catch (InvalidEventException e) {
            throw new CouldNotCreateEventException(e);
        }

        return repository.save(event);
    }

    @Override
    public Event update(Event event) throws CouldNotUpdateEventException {
        if (event == null) {
            throw new CouldNotUpdateEventException(new IllegalArgumentException("Event cannot be null."));
        }

        try {
            validate(event);
        } catch (InvalidEventException e) {
            throw new CouldNotUpdateEventException(e);
        }

        return repository.save(event);
    }

    @Override
    public Event find(Long id) throws EventNotFoundException {
        if (id == null) {
            throw new EventNotFoundException(new IllegalArgumentException("Id cannot be null."));
        }

        Event result = repository.findOne(id);
        if (result == null) {
            throw new EventNotFoundException();
        }
        return result;
    }

    @Override
    public List<Event> findAll(int page, int pageSize) {
        if (page < 0 || pageSize < 1) {
            throw new IllegalArgumentException("Invalid page and pageSize arguments.");
        }

        PageRequest pageRequest = new PageRequest(page, pageSize);
        return repository.findAll(pageRequest).getContent();
    }

    @Override
    public void saveAttachment(Long id, MultipartFile mpf) throws IOException {
        if(!mpf.getContentType().toLowerCase().equals("application/pdf")){
            throw new IllegalArgumentException("Invalid file type");
        }
        Event event = repository.findOne(id);
        event.setAttachement(mpf.getBytes());
        repository.save(event);
    }

    @Override
    public byte[] findAttachment(Long id) throws AttachmentNotFoundException {
        Event event = repository.findOne(id);
        byte[] attachment = event.getAttachement();
        if(attachment==null){
            throw new AttachmentNotFoundException();
        }
        return event.getAttachement();
    }

    @Override
    public byte[] exportAttendanceList(Long id) throws EventNotFoundException, EventExportException {
        Event event = find(id);
        List<User> attendees = repository.findAttendeesForEvent(event);

        try {
            return EventExcelExporter.export(attendees);
        } catch (IOException e) {
            throw new EventExportException(e);
        }
    }

    @Override
    public List<User> findCancellations(Long id) throws EventNotFoundException {
        Event event = find(id);
        return repository.findCancellationsForEvent(event);
    }

    @Override
    public byte[] exportCancellations(Long id) throws EventNotFoundException, EventExportException {
        List<User> cancellations = findCancellations(id);

        try {
            return EventExcelExporter.export(cancellations);
        } catch (IOException e) {
            throw new EventExportException(e);
        }
    }

    /**
     * Validate an event successfully or throw an exception.
     *
     * @throws InvalidEventException If the event is not valid. The message of the
     *                               exception will contain the reason validation failed.
     */
    private void validate(Event event) throws InvalidEventException {
        if (event.getStartDate() == null || event.getEndDate() == null) {
            throw new InvalidEventException("Start and end date cannot be null.");
        }
        if (event.getEndDate().isBefore(event.getStartDate())) {
            throw new InvalidEventException("End date can not be before the start date.");
        }
        //uncomment this part when we can add the current moderator in the frontend
        if (event.getResponsibles() == null /*|| event.getResponsibles().size() == 0*/) {
            throw new InvalidEventException("The event should have at least one responsible.");
        }
        if (event.getEnrollments() == null) {
            throw new InvalidEventException("Enrollments cannot be null.");
        }
        if (event.getAddress() == null) {
            throw new InvalidEventException("Address cannot be null.");
        }
        validate(event.getAddress());
        if (event.getDeadline() == null) {
            throw new InvalidEventException("Deadline cannot be null.");
        }
        if (event.getPriceAdult() == null) {
            throw new InvalidEventException("Adult price cannot be null.");
        }
        if (event.getPriceChild() == null) {
            throw new InvalidEventException("Child price cannot be null.");
        }
        if (event.getName() == null) {
            throw new InvalidEventException("Name cannot be null.");
        }
        if (event.getMaxParticipants() < 0) {
            throw new InvalidEventException("Max participants must be greater than zero.");
        }
        if (event.getMinParticipants() < 1) {
            throw new InvalidEventException("Min participants must be greater than one.");
        }
        if (event.getMinParticipants() > event.getMaxParticipants()) {
            throw new InvalidEventException("Min participants must be smaller than or equal to max participants.");
        }
    }

    /**
     * Validate an address or throw an exception
     */
    private void validate(Address address) throws InvalidEventException {
        if (address.getCountry() == null) {
            throw new InvalidEventException("Address country cannot be null.");
        }
        if (address.getPostalCode() == null) {
            throw new InvalidEventException("Address postal code cannot be null.");
        }
        if (address.getStreet() == null) {
            throw new InvalidEventException("Address street cannot be null.");
        }
        if (address.getHomeNumber() < 1) {
            throw new InvalidEventException("Address home number cannot be less than 1.");
        }
    }
}
