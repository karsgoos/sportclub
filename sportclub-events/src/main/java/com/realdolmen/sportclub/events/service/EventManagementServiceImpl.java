package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
import com.realdolmen.sportclub.events.exceptions.InvalidEventException;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * An event is valid if it has a start date and end date in the future,
     * and if the end date is before the start date.
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
        if (event.getResponsibles() == null || event.getResponsibles().size() == 0) {
            throw new InvalidEventException("The event should have at least one responsible.");
        }
        if (event.getEnrollments() == null) {
            throw new InvalidEventException("Enrollments cannot be null.");
        }
        if (event.getAddress() == null) {
            throw new InvalidEventException("Address cannot be null.");
        }
        if (event.getDeadline() == null) {
            throw new InvalidEventException("Deadline cannot be null.");
        }
        if (event.getPrice() == null) {
            throw new InvalidEventException("Price cannot be null.");
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
}
