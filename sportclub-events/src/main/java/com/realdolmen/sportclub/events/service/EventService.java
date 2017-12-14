package com.realdolmen.sportclub.events.service;


import com.realdolmen.sportclub.common.entity.Event;

import java.util.Collection;

public interface EventService {
	Collection<Event> findAll();
	Event findById(Long id);
	void attendEvent(String userId, String EventId, int nrOfAdults, int nrOfChildren);
	void attendEvent(String firstName, String lastName, String email, Long eventId, int nrOfAdults, int nrOfChildren);
}
