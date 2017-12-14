package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.repository.EventRepository;
import com.realdolmen.sportclub.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventService eventService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "api/events")
	public Collection<Event> getEvents(){
		return eventRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "api/events/{id}")
	public Event getEvent(@PathVariable Long id){
		return eventRepository.findOne(id);
	}


	@RequestMapping(method = RequestMethod.POST, value = "api/events/attend")
	public void attendEvent(@RequestParam("userId") String userId,@RequestParam("eventId") String eventId, @RequestParam("nrOfAdults") int nrOfAdults, @RequestParam("nrOfChildren")  int nrOfChildren){
		eventService.attendEvent(userId, eventId, nrOfAdults, nrOfChildren);
	}
	
//	@RequestMapping(method = RequestMethod.POST, value = "api/events")
//	public void attendEvent(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String email, @PathVariable Long eventId, @PathVariable int nrOfAdults, @PathVariable int nrOfChildren){
//		eventService.attendEvent(firstName, lastName, email, eventId, nrOfAdults, nrOfChildren);
//	}
}