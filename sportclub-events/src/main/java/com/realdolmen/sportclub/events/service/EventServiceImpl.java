package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.AttendanceRepository;
import com.realdolmen.sportclub.common.repository.OrderRepository;
import com.realdolmen.sportclub.common.repository.UserRepository;
import com.realdolmen.sportclub.events.repository.EventRepository;
import com.realdolmen.sportclub.common.repository.*;
import com.realdolmen.sportclub.events.service.mail.MailSenderService;
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
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MailSenderService mailSenderService;
	
	@Autowired
	private SportclubRepository sportclubRepository;
	
	@Override
	public Collection<Event> findAll() {
		return eventRepository.findAll();
	}
	
	@Override
	public Event findById(Long id) {
		return eventRepository.findOne(id);
	}
	
	//attend open event as a registerd user
	@Override
	public void attendOpenEvent(String userId, String eventId, int nrOfAdults, int nrOfChildren) {
		
		Order order = new Order();
		order.setUser(userRepository.findOne(Long.parseLong(userId)));
		
		Event event= eventRepository.findOne(Long.parseLong(eventId));

		for(int i=0;i<nrOfAdults;i++){
			singleAttendanceToEvent(event,order,AgeCategory.ADULT);
		}
		for(int i=0;i<nrOfChildren;i++){
			singleAttendanceToEvent(event,order,AgeCategory.CHILD);
		}
		
		orderRepository.save(order);
		eventRepository.save(event);
		//TODO: send email
	}
	
	//attend open event as a guest
	@Override
	public void attendOpenEvent(String firstName, String lastName, String email, String eventId, int nrOfAdults, int nrOfChildren) {
		Guest guest = new Guest();
		guest.setEmail(email);
		guest.setFirstName(firstName);
		guest.setLastName(lastName);
		
		//TODO: role for guest
		Role role = new Role();
		role.setName("GUEST");
		roleRepository.save(role);
		guest.setRole(role);
		
		guest=userRepository.save(guest);
		attendOpenEvent(guest.getId().toString(),eventId,nrOfAdults,nrOfChildren);
		
		//TODO: unsubscribe link
		mailSenderService.sendMailGuestAttendPublicEvent(guest,eventRepository.findOne(Long.parseLong(eventId)),"http://www.realdolmen.com");
	}
	
	@Override
	public void attendClosedEvent(String userId, String eventId) {
		
		Order order = new Order();
		order.setUser(userRepository.getOne(Long.parseLong(userId)));
		Event event= eventRepository.findOne(Long.parseLong(eventId));
		
		singleAttendanceToEvent(event,order,AgeCategory.DEFAULT);
		
		orderRepository.save(order);
		eventRepository.save(event);
		//TODO: send email
	}
	
	private void singleAttendanceToEvent(Event event, Order order, AgeCategory ageCategory){
		Attendance attendance = new Attendance();
		attendance.setEvent(event);
		if(ageCategory == AgeCategory.CHILD){
			attendance.setPrice(event.getPriceChild());
			attendance.setAgeCategory(AgeCategory.CHILD);
			attendance.setDescription(event.getName()+" kind");
		}
		else if(ageCategory == AgeCategory.ADULT){
			attendance.setPrice(event.getPriceAdult());
			attendance.setAgeCategory(AgeCategory.ADULT);
			attendance.setDescription(event.getName()+" volwassene");
		}
		else{
			attendance.setPrice(event.getPriceAdult());
			attendance.setAgeCategory(AgeCategory.DEFAULT);
			attendance.setDescription(event.getName());
		}
		
		attendanceRepository.save(attendance);
		order.addOrderable(attendance);
		event.addAttendance(attendance);
		attendance.setOrdr(order);
	}
	
}
