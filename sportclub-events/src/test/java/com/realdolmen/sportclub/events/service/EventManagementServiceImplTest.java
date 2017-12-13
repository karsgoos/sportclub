package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
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
        Mockito.when(repository.save((Event) Mockito.any())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return invocation.getArgument(0);
            }
        });
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

    private Event createValidEvent() {
        Event event = new Event();
        event.setStartDate(LocalDateTime.now());
        event.setEndDate(LocalDateTime.now().plusDays(5));
        event.setEnrollments(new ArrayList<>());
        event.setPrice(new HashMap<>());
        event.setAddress(new Address());
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