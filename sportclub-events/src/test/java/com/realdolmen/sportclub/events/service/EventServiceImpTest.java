//package com.realdolmen.sportclub.events.service;
//
//import com.realdolmen.sportclub.common.entity.Event;
//import com.realdolmen.sportclub.common.repository.EventRepository;
//import com.realdolmen.sportclub.common.repository.UserRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class EventServiceImpTest {
//	@InjectMocks
//	EventService eventService;
//
//	@Mock
//	private EventRepository eventRepository;
//
//	@Mock
//	private UserRepository userRepository;
//
//	@Before
//	public void init(){
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testFindByIdWorks(){
////		Event e = new Event();
////		e.setName("event1");
////		Mockito.when(eventRepository.findOne(1L)).thenReturn(e);
////		Event result = eventService.findById(1L);
////		assertEquals("event1",result.getName());
//	}
//
//}
//
//
////	Collection<Event> findAll();
////	Event findById(Long id);
////	void attendEvent(Long userId, Long EventId, int nrOfAdults, int nrOfChildren);
////	void attendEvent(String firstName, String lastName, String email, Long eventId, int nrOfAdults, int nrOfChildren);