package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;

public interface EventManagementService {
    Event create(Event event) throws CouldNotCreateEventException;
}
