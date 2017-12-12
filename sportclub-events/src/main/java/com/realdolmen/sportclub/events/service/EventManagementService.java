package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.mocks.Event;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface EventManagementService {
    Event create(Event event) throws CouldNotCreateEventException;
}
