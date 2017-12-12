package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.mocks.Event;

public interface EventManagementService {
    Event create(Event event) throws CouldNotCreateEventException;
}
