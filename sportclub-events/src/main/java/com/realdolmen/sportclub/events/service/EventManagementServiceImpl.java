package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import org.springframework.stereotype.Service;

@Service
public class EventManagementServiceImpl implements EventManagementService {
    @Override
    public Event create(Event event) throws CouldNotCreateEventException {
        throw new CouldNotCreateEventException(new UnsupportedOperationException("not yet implemented"));
    }
}
