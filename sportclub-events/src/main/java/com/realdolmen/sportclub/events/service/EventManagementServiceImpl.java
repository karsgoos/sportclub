package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EventManagementServiceImpl implements EventManagementService {
    @Autowired
    private EventRepository repository;

    @Override
    public Event create(Event event) throws CouldNotCreateEventException {
        if (event == null) {
            throw new CouldNotCreateEventException(new IllegalArgumentException("Event cannot be null."));
        }

        if (!isValid(event)) {
            throw new CouldNotCreateEventException("Invalid event data.");
        }

        return repository.save(event);
    }

    @Override
    public Event update(Event event) throws CouldNotUpdateEventException {
        if (event == null) {
            throw new CouldNotUpdateEventException(new IllegalArgumentException("Event cannot be null."));
        }

        if (!isValid(event)) {
            throw new CouldNotUpdateEventException("Invalid event data.");
        }

        return repository.save(event);
    }

    /**
     * An event is valid if it has a start date and end date in the future,
     * and if the end date is before the start date.
     *
     * @param event
     * @return
     */
    private boolean isValid(Event event) {
        boolean valid = true;

        if (event.getStartDate() == null || event.getEndDate() == null) {
            valid = false;
        } else {
            if (event.getStartDate().isBefore(LocalDateTime.now())) {
                valid = false;
            } else if (event.getEndDate().isBefore(event.getStartDate())) {
                valid = false;
            }
        }

        return valid;
    }
}
