package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
import com.realdolmen.sportclub.events.exceptions.EventExportException;
import com.realdolmen.sportclub.events.exceptions.EventNotFoundException;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface EventManagementService {
    /**
     * Create and persist a new Event.
     * The Event must start in the future, and its end date must be after its start date.
     *
     * @param event The Event data.
     * @return The resulting Event. This is identical to the argument but contain an id.
     * @throws CouldNotCreateEventException If {@code event} was {@code null}, or some repository exception occurred.
     */
    Event create(Event event) throws CouldNotCreateEventException;

    /**
     * Update an existing Event.
     * The Event must start in the future, and its end date must be after its start date.
     *
     * @param event An Event, containing the updated data but the id of the original Event.
     * @return The updated Event. This is identical to the argument.
     * @throws CouldNotCreateEventException If {@code event} was {@code null}, or some repository exception occurred.
     */
    Event update(Event event) throws CouldNotUpdateEventException;

    /**
     * Get a single Event.
     *
     * @param id The Event id.
     * @return The Event.
     */
    Event find(Long id) throws EventNotFoundException;

    /**
     * Get all Events.
     *
     * @param page The page (0-based).
     * @param pageSize The size of a page.
     * @return A list of Events (according to the given page).
     */
    List<Event> findAll(int page, int pageSize);

    /**
     * Export the list of attendees of the Event with ID {@code id} to an Excel file.
     *
     * @param id The id of the event.
     * @return An excel workbook as byte array.
     * @throws EventNotFoundException If the Event is not found.
     * @throws EventExportException If the Event cannot be exported.
     */
    byte[] exportAttendanceList(Long id) throws EventNotFoundException, EventExportException;
}
