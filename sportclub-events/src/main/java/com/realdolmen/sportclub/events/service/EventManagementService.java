package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.User;
import com.realdolmen.sportclub.events.exceptions.AttachmentNotFoundException;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
import com.realdolmen.sportclub.events.exceptions.EventExportException;
import com.realdolmen.sportclub.events.exceptions.EventNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * Get all Events. Passing a negative page will return events in the past,
     * while passing a page >= 0 will return events in the future.
     *
     * @param page The page.
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

    /**
     * Obtain a list of cancelled attendances for a given event.
     *
     * @param id The id of the Event to query.
     * @return A list of attendances which have been cancelled.
     * @throws EventNotFoundException If the Event does not exist.
     */
    List<User> findCancellations(Long id) throws EventNotFoundException;

    /**
     * Obtain a list of cancelled attendances for a given event as an Excel file.
     *
     * @param id The id of the Event to query.
     * @return A list of attendances which have been cancelled (Excel workbook as byte array).
     * @throws EventNotFoundException If the Event does not exist.
     */
    byte[] exportCancellations(Long id) throws EventNotFoundException, EventExportException;

    public void saveAttachment(Long id, MultipartFile attachement) throws IOException;

    byte[] findAttachment(Long id) throws AttachmentNotFoundException;
}
