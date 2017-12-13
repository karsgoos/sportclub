package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
import com.realdolmen.sportclub.events.exceptions.EventNotFoundException;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EventManagementServiceImplTest {
    @InjectMocks
    private EventManagementServiceImpl service = new EventManagementServiceImpl();

    @Mock
    private EventRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Repository.save returns its argument
        Mockito.when(repository.save((Event) Mockito.any())).thenAnswer(new Answer<Event>() {
            @Override
            public Event answer(InvocationOnMock invocation) throws Throwable {
                return invocation.getArgument(0);
            }
        });

        // Find one returns an empty event
        Mockito.when(repository.findOne(Mockito.anyLong())).thenReturn(new Event());
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void throwsExceptionWhenCreateArgumentIsNull() throws CouldNotCreateEventException {
        service.create(null);
    }

    @Test
    public void canCreateEvent() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        Event result = service.create(event);

        Assert.assertEquals(event, result);
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void canNotCreateEventWithNegativeDuration() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.setStartDate(LocalDateTime.now().plusDays(10));
        event.setEndDate(LocalDateTime.now().plusDays(5));
        service.create(event);
    }

    private static final String UPDATE_DESCRIPTION = "New description";

    @Test
    public void canUpdateEventAfterCreation() throws CouldNotCreateEventException, CouldNotUpdateEventException {
        Event event = createValidEvent();
        event = service.create(event);

        event.setDescription(UPDATE_DESCRIPTION);
        Event result = service.update(event);

        Assert.assertEquals(event, result);
        Assert.assertEquals(UPDATE_DESCRIPTION, result.getDescription());
    }

    @Test(expected = CouldNotUpdateEventException.class)
    public void canNotUpdateEventWithNegativeDuration() throws CouldNotUpdateEventException, CouldNotCreateEventException {
        Event event = createValidEvent();
        event = service.create(event);
        event.setStartDate(LocalDateTime.now().plusDays(10));
        event.setEndDate(LocalDateTime.now().plusDays(5));
        service.update(event);
    }

    @Test(expected = CouldNotUpdateEventException.class)
    public void throwsExceptionWhenUpdateArgumentIsNull() throws CouldNotUpdateEventException {
        service.update(null);
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void canNotCreateEventWithNegativeMaxParticipants() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.setMaxParticipants(-1);
        service.create(event);
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void canNotCreateEventWithNegativeMinParticipants() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.setMinParticipants(-1);
        service.create(event);
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void canNotCreateEventWithMaxParticipantsLessThanMinParticipants() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.setMinParticipants(10);
        event.setMaxParticipants(9);
        service.create(event);
    }

    @Test
    public void canCreateEventWithMinParticipantsEqualToMaxParticipants() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.setMinParticipants(1);
        event.setMaxParticipants(1);
        service.create(event);
    }

    @Test
    public void canCreateEventWithMinParticipantsLessThanMaxParticipants() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.setMinParticipants(1);
        event.setMaxParticipants(2);
        service.create(event);
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void canNotCreateEventWithInvalidAddressCountry() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.getAddress().setCountry(null);
        service.create(event);
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void canNotCreateEventWithInvalidAddressStreet() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.getAddress().setStreet(null);
        service.create(event);
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void canNotCreateEventWithInvalidAddressHomeNumber() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.getAddress().setHomeNumber(0);
        service.create(event);
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void canNotCreateEventWithInvalidAddressPostalCode() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        event.getAddress().setPostalCode(null);
        service.create(event);
    }

    @Test
    public void canGetSingleEvent() throws EventNotFoundException {
        Assert.assertNotNull(service.find(1L));
    }

    @Test(expected = EventNotFoundException.class)
    public void canNotGetNonExistingEvent() throws EventNotFoundException {
        Mockito.when(repository.findOne(Mockito.anyLong())).thenReturn(null);
        service.find(1L);
    }

    @Test
    public void canGetPageOfEvents() {
        Mockito.when(repository.findAll((Pageable) Mockito.any())).thenReturn(new PageImpl(new ArrayList<>()));
        List<Event> result = service.findAll(0, 10);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void pagingWorksCorrectly() {
        ArrayList<Event> results = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            results.add(new Event());
        }
        Mockito.when(repository.findAll((Pageable) Mockito.any())).thenReturn(new PageImpl(results));
        List<Event> result = service.findAll(0, 10);
        Assert.assertNotNull(result);
        Assert.assertEquals(10, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void pagingFailsWithNegativePage() {
        service.findAll(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pagingFailsWithNegativePageSize() {
        service.findAll(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pagingFailsWithZeroPageSize() {
        service.findAll(0, 0);
    }

    private Event createValidEvent() {
        Event event = new Event();
        event.setStartDate(LocalDateTime.now());
        event.setEndDate(LocalDateTime.now().plusDays(5));
        event.setEnrollments(new ArrayList<>());
        event.setPrice(new HashMap<>());
        Address address = new Address();
        address.setCountry("BE");
        address.setPostalCode("9000");
        address.setStreet("Sportstraat");
        address.setHomeNumber(1);
        event.setAddress(address);
        event.setName("Test Event");
        event.setMaxParticipants(10);
        event.setMinParticipants(1);
        List<RegisteredUser> responsibles = new ArrayList<>();
        responsibles.add(new RegisteredUser());
        event.setResponsibles(responsibles);
        event.setDeadline(LocalDateTime.now().plusDays(10));
        return event;
    }

}