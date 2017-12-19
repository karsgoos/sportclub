package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.*;
import com.realdolmen.sportclub.events.config.TestConfig;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class EventServiceImpTest {
	@InjectMocks
	EventService eventService = new EventServiceImpl();

	@Mock
	private EventRepository eventRepository;

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private AttendanceRepository attendanceRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private MailSenderService mailSenderService;
	
	@Mock
	private SportclubRepository sportclubRepository;
	
	@Mock
	private Guest u;
	
	@Mock
	private Event e;
	
	@Mock
	private Sportclub s;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		Mockito.when(e.getName()).thenReturn("event1");
		Mockito.when(e.getPriceAdult()).thenReturn(BigDecimal.TEN);
		Mockito.when(e.getPriceChild()).thenReturn(BigDecimal.ONE);
		Mockito.when(u.getFirstName()).thenReturn("bertje");
		Mockito.when(u.getId()).thenReturn(1L);
		Mockito.when(s.getName()).thenReturn("R-Sportclub");
		//initialize mocks
		Mockito.when(eventRepository.findOne(1L)).thenReturn(e);
		Mockito.when(userRepository.findOne(1L)).thenReturn(u);
		Mockito.when(sportclubRepository.findOne(1L)).thenReturn(s);
	}

	@Test
	public void testFindByIdWorks(){
		Mockito.when(eventRepository.findOne(1L)).thenReturn(e);
		Event result = eventService.findById(1L);
		assertEquals("event1",result.getName());
		Mockito.verify(eventRepository).findOne(1L);
	}
	
	@Test
	public void testFindAllWorks(){
		Mockito.when(eventRepository.findAll()).thenReturn(Arrays.asList(e,e,e));
		Collection result = eventService.findAll();
		assertEquals(3,result.size());
		Mockito.verify(eventRepository).findAll();
	}
	
	@Test
	public void testAttendOpenEventRegisteredUserHappyFlow(){
		//initialize argumentCaptors
		ArgumentCaptor<Order> order = ArgumentCaptor.forClass(Order.class);
		ArgumentCaptor<Attendance> attendance = ArgumentCaptor.forClass(Attendance.class);
		ArgumentCaptor<Event> event = ArgumentCaptor.forClass(Event.class);
		
		//execute method
		eventService.attendOpenEvent("1","1",2,3);
		
		//capture arguments
		Mockito.verify(orderRepository).save(order.capture());
		Mockito.verify(eventRepository).save(event.capture());
		Mockito.verify(attendanceRepository,times(5)).save(attendance.capture());
		
		//count nr of adults and children
		int nrOfChildren=0;
		int nrOfAdults=0;
		for(Attendance a : attendance.getAllValues()){
			if(a.getAgeCategory()==AgeCategory.ADULT)
				nrOfAdults++;
			else if(a.getAgeCategory()==AgeCategory.CHILD)
				nrOfChildren++;
		}
		
		//check total attendencies in event
		Mockito.verify(e,times(5)).addAttendance(any(Attendance.class));
		
		//check nr of children
		assertEquals(2,nrOfAdults);
		assertEquals(3,nrOfChildren);
		
	}
	
	@Test
	public void testAttendOpenEventGuestHappyFlow(){
		//initialize argumentCaptors
		ArgumentCaptor<Order> order = ArgumentCaptor.forClass(Order.class);
		ArgumentCaptor<Attendance> attendance = ArgumentCaptor.forClass(Attendance.class);
		ArgumentCaptor<Event> event = ArgumentCaptor.forClass(Event.class);
		Mockito.when(userRepository.save(any(Guest.class))).thenAnswer(new Answer<User>() {
			@Override
			public User answer(InvocationOnMock mock) throws Throwable {
				return u;
			}
		});
		
		//execute method
		eventService.attendOpenEvent("bert", "beton","bert@beton.be","1",2,3);
		
		//capture arguments
		Mockito.verify(orderRepository).save(order.capture());
		Mockito.verify(eventRepository).save(event.capture());
		Mockito.verify(attendanceRepository,times(5)).save(attendance.capture());
		
		//count nr of adults and children
		int nrOfChildren=0;
		int nrOfAdults=0;
		for(Attendance a : attendance.getAllValues()){
			if(a.getAgeCategory()==AgeCategory.ADULT)
				nrOfAdults++;
			else if(a.getAgeCategory()==AgeCategory.CHILD)
				nrOfChildren++;
		}
		
		//check total attendencies in event
		Mockito.verify(e,times(5)).addAttendance(any(Attendance.class));
		
		//check nr of children
		assertEquals(2,nrOfAdults);
		assertEquals(3,nrOfChildren);
		Mockito.verify(userRepository).save(any(User.class));
		//TODO: test unsubscribelink
		Mockito.verify(mailSenderService).sendMailGuestAttendPublicEvent(u,e,s,"http://www.realdolmen.com");
	}
	
	@Test
	public void testAttendClosedEventHappyFlow(){
		//initialize mocks
		Mockito.when(eventRepository.findOne(1L)).thenReturn(e);
		Mockito.when(userRepository.findOne(1L)).thenReturn(u);
		
		//initialize argumentCaptors
		ArgumentCaptor<Order> order = ArgumentCaptor.forClass(Order.class);
		ArgumentCaptor<Attendance> attendance = ArgumentCaptor.forClass(Attendance.class);
		ArgumentCaptor<Event> event = ArgumentCaptor.forClass(Event.class);
		
		//execute method
		eventService.attendClosedEvent("1","1");
		
		//capture arguments
		Mockito.verify(orderRepository).save(order.capture());
		Mockito.verify(eventRepository).save(event.capture());
		Mockito.verify(attendanceRepository).save(attendance.capture());
		
		//check ageCategory
		assertEquals(AgeCategory.DEFAULT,attendance.getValue().getAgeCategory());
		
		//check total attendencies in event
		Mockito.verify(e).addAttendance(any(Attendance.class));
	}

}