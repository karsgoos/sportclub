package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;

public interface EventManagementService {
    /**
     * Create and persist a new Event.
     *
     * @param event The Event data.
     * @return The resulting Event. This is identical to the argument but contain an id.
     * @throws CouldNotCreateEventException If {@code event} was {@code null}, or some repository exception occurred.
     */
    Event create(Event event) throws CouldNotCreateEventException;

    /**
     * Update an existing Event.
     *
     * @param event An Event, containing the updated data but the id of the original Event.
     * @return The updated Event. This is identical to the argument.
     * @throws CouldNotCreateEventException If {@code event} was {@code null}, or some repository exception occurred.
     */
    Event update(Event event) throws CouldNotUpdateEventException;
}
