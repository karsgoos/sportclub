package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.AttendanceRepository;
import com.realdolmen.sportclub.common.repository.OrderRepository;
import com.realdolmen.sportclub.common.repository.UserRepository;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Collection<Event> findAll() {
		return eventRepository.findAll();
	}
	
	@Override
	public Event findById(Long id) {
		return eventRepository.findOne(id);
	}
	
	@Override
	public void attendEvent(String userId, String eventId, int nrOfAdults, int nrOfChildren) {
		
		Order order = new Order();
		order.setUser(userRepository.getOne(Long.parseLong(userId)));

		Event event= eventRepository.findOne(Long.parseLong(eventId));

		for(int i=0;i<nrOfAdults;i++){
			Attendance attendance = new Attendance();
			attendance.setPrice(event.getPriceAdult());
			attendance.setAgeCategory(AgeCategory.ADULT);
			attendance.setDescription(event.getName()+" volwassene");
			attendanceRepository.save(attendance);
			order.addOrderable(attendance);
			event.addAttendance(attendance);
			order.setPrice(order.getPrice().add(attendance.getPrice()));
		}
		for(int i=0;i<nrOfChildren;i++){
			Attendance attendance = new Attendance();
			attendance.setPrice(event.getPriceChild());
			attendance.setAgeCategory(AgeCategory.CHILD);
			attendance.setDescription(event.getName()+" kind");
			attendanceRepository.save(attendance);
			order.addOrderable(attendance);
			event.addAttendance(attendance);
			order.setPrice(order.getPrice().add(attendance.getPrice()));
		}
		orderRepository.save(order);
		eventRepository.save(event);
	}
	
	@Override
	public void attendEvent(String firstName, String lastName, String email, Long eventId, int nrOfAdults, int nrOfChildren) {
		Guest guest = new Guest();
		guest.setEmail(email);
		guest.setFirstName(firstName);
		guest.setLastName(lastName);
		
		//TODO: role for guest
		Role role = new Role();
		role.setName("GUEST");
		guest.setRole(role);
		
		userRepository.save(guest);
		//attendEvent(guest.getId(),eventId,nrOfAdults,nrOfChildren);
	}
}
