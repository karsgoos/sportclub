package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public Collection<Event> findAll() {
		return eventRepository.findAll();
	}
	
	@Override
	public Event findById(Long id) {
		return eventRepository.findOne(id);
	}
}
