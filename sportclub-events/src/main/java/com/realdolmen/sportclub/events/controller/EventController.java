package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "api/events")
	public Collection<Event> getEvents(){
		return eventRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "api/events/test")
	public Event getEvent(){
		return new Event();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "api/events/{id}")
	public Event getEvent(@PathVariable Long id){
		return eventRepository.findOne(id);
	}
}