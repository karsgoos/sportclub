package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.RecurringEventInfo;
import com.realdolmen.sportclub.events.exceptions.*;
import com.realdolmen.sportclub.common.entity.User;
import com.realdolmen.sportclub.events.repository.EventRepository;
import com.realdolmen.sportclub.events.repository.RecurringEventInfoRepository;
import com.realdolmen.sportclub.events.service.export.EventExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class EventManagementServiceImpl implements EventManagementService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private RecurringEventInfoRepository recurringEventInfoRepository;

    @Override
    @Transactional
    public Event create(Event event) throws CouldNotCreateEventException {
        if (event == null) {
            throw new CouldNotCreateEventException(new IllegalArgumentException("Event cannot be null."));
        }

        try {
            validate(event);
        } catch (InvalidEventException e) {
            throw new CouldNotCreateEventException(e);
        }

        List<Event> eventsToCreate = new ArrayList<>();
        if (event.getRecurringEventInfo() != null) {
            event.setRecurringEventInfo(recurringEventInfoRepository.save(event.getRecurringEventInfo()));

            // This is a recurring event, so we have to create an event instance per occurrence
            LocalDateTime startDateTime = event.getRecurringEventInfo().getStartDate();
            LocalDateTime endDateTime = event.getRecurringEventInfo().getEndDate();

            // To do this, we loop over all the days and add events when a recurring weekday occurs
            Collection<DayOfWeek> weekDays = event.getRecurringEventInfo().getWeekdays();
            LocalDate currentDate = startDateTime.plusDays(1).toLocalDate();
            while(currentDate.isBefore(endDateTime.toLocalDate())) {
                if (weekDays.contains(currentDate.getDayOfWeek())) {
                    // Create a new event
                    // Each new event should have the same start time as the event passed to this method
                    LocalTime eventStartTime = event.getStartDate().toLocalTime();
                    LocalTime eventEndTime = event.getEndDate().toLocalTime();
                    LocalDateTime newEventStartDateTime = LocalDateTime.of(currentDate, eventStartTime);
                    LocalDateTime newEventEndDateTime = LocalDateTime.of(currentDate, eventEndTime);

                    // Create a deep copy of the event
                    Event newEvent = new Event();
                    newEvent.setPriceAdult(event.getPriceAdult());
                    newEvent.setPriceChild(event.getPriceChild());
                    newEvent.setMaxParticipants(event.getMaxParticipants());
                    newEvent.setMinParticipants(event.getMinParticipants());
                    newEvent.setDeadline(event.getDeadline());
                    newEvent.setResponsibles(event.getResponsibles());
                    newEvent.setAddress(event.getAddress());
                    newEvent.setClosed(event.isClosed());
                    newEvent.setDescription(event.getDescription());
                    newEvent.setName(event.getName());
                    newEvent.setAttachement(event.getAttachement());
                    newEvent.setImageUrl(event.getImageUrl());

                    newEvent.setStartDate(newEventStartDateTime);
                    newEvent.setEndDate(newEventEndDateTime);

                    newEvent.setRecurringEventInfo(event.getRecurringEventInfo());

                    eventsToCreate.add(newEvent);
                }

                currentDate = currentDate.plusDays(1);
            }
        }

        event = repository.save(event);
        repository.save(eventsToCreate);

        return event;
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public List<Event> findAll(int page, int pageSize) {
        if (page < 0 || pageSize < 1) {
            throw new IllegalArgumentException("Invalid page and pageSize arguments.");
        }

        PageRequest pageRequest = new PageRequest(page, pageSize);
        return repository.findAll(pageRequest).getContent();
    }

    @Override
    @Transactional
    public void saveAttachment(Long id, MultipartFile mpf) throws IOException {
        if(!mpf.getContentType().toLowerCase().equals("application/pdf")){
            throw new IllegalArgumentException("Invalid file type");
        }
        Event event = repository.findOne(id);
        event.setAttachement(mpf.getBytes());
        repository.save(event);
    }

    @Override
    @Transactional
    public byte[] findAttachment(Long id) throws AttachmentNotFoundException {
        Event event = repository.findOne(id);
        byte[] attachment = event.getAttachement();
        if(attachment==null){
            throw new AttachmentNotFoundException();
        }
        return event.getAttachement();
    }

    @Override
    @Transactional
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
    @Transactional
    public List<User> findCancellations(Long id) throws EventNotFoundException {
        Event event = find(id);
        return repository.findCancellationsForEvent(event);
    }

    @Override
    @Transactional
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
