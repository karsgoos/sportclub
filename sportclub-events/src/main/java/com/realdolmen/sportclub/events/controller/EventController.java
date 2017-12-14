package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.repository.EventRepository;
import com.realdolmen.sportclub.events.DTO.AttendEventDTO;
import com.realdolmen.sportclub.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	
	@RequestMapping(method = RequestMethod.GET, value = "api/events/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Event getEvent(@PathVariable Long id){
		return eventRepository.findOne(id);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "api/events/attend")
	public  void attendEvent(@RequestBody AttendEventDTO dto){
		//guest
		if(dto.getUserId()==null)
			eventService.attendOpenEvent(dto.getFirstName(),dto.getLastName(),dto.getNrOfAdults(),dto.getNrOfChildren());
		//registered user closed event
		else if(dto.getNrOfAdults()==0&&dto.getNrOfChildren()==0)
			eventService.attendClosedEvent(dto.getUserId(),dto.getEventId());
		//registered user open event
		else
		eventService.attendOpenEvent(dto.getUserId(),dto.getEventId(),dto.getNrOfAdults(),dto.getNrOfChildren());
	}
	
}