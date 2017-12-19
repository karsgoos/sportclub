package com.realdolmen.sportclub.events.repository;

import com.realdolmen.sportclub.common.entity.Attendance;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.User;

import java.util.List;

public interface EventAttendeeRepository {
    List<User> findAttendeesForEvent(Event event);
    List<User> findCancellationsForEvent(Event event);
}
