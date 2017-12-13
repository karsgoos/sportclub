package com.realdolmen.sportclub.events.repository;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.User;

import java.util.List;

public interface EventAttendeeRepository {
    List<User> findAttendeesForEvent(Event event);
}
