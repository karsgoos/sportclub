package com.realdolmen.sportclub.events.service;


import com.realdolmen.sportclub.common.entity.Event;

import java.util.Collection;

public interface EventService {
	Collection<Event> findAll();
	Event findById(Long id);
	void attendOpenEvent(String userId, String eventId, int nrOfAdults, int nrOfChildren);
	void attendOpenEvent(String firstName, String lastName, String email, String eventId, int nrOfAdults, int nrOfChildren);
	void attendClosedEvent(String userId, String eventId);
}
