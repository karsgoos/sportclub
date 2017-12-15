package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.AttendanceRepository;
import com.realdolmen.sportclub.common.repository.EventRepository;
import com.realdolmen.sportclub.common.repository.OrderRepository;
import com.realdolmen.sportclub.common.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
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
	
	Event e;
	User u;
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		e = new Event();
		e.setName("event1");
		e.setPriceAdult(BigDecimal.TEN);
		e.setPriceChild(BigDecimal.ONE);
		u = new Guest();
		u.setFirstName("bertje");
		
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
	public void testAttendOpenEventHappyFlow(){
		//initialize mocks
		Mockito.when(eventRepository.findOne(1L)).thenReturn(e);
		Mockito.when(userRepository.findOne(1L)).thenReturn(u);
		
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
		//check total price
		assertEquals(BigDecimal.valueOf(23),order.getValue().getPrice());
		
		//check total attendencies in event
		assertEquals(5,e.getAttendancies().size());
		
		//check nr of children
		assertEquals(2,nrOfAdults);
		assertEquals(3,nrOfChildren);
		
	}

}