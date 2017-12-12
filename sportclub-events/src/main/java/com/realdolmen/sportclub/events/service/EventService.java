package com.realdolmen.sportclub.events.service;


import com.realdolmen.sportclub.common.entity.Event;

import java.util.Collection;

public interface EventService {
	Collection<Event> findAll();
	Event findById(Long id);
}
