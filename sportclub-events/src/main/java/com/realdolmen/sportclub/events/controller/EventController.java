package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "events/all")
	public Collection<Event> getEvents(){
		List a = new ArrayList<Event>();
		a.add(new Event());
		return a;
//		return eventRepository.findAll();
	}
}
