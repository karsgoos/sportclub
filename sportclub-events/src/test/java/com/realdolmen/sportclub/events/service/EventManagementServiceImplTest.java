package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
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

@RunWith(MockitoJUnitRunner.class)
public class EventManagementServiceImplTest {
    @InjectMocks
    private EventManagementServiceImpl service = new EventManagementServiceImpl();

    @Mock
    private EventRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = CouldNotCreateEventException.class)
    public void throwsExceptionWhenCreateArgumentIsNull() throws CouldNotCreateEventException {
        service.create(null);
    }

    @Test
    public void canCreateEvent() throws CouldNotCreateEventException {
        // Repository.save returns its argument
        Mockito.when(repository.save((Event) Mockito.any())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return invocation.getArgument(0);
            }
        });

        Event event = new Event();
        event.setDescription("Test event");
        Event result = service.create(event);

        Assert.assertEquals(result, event);
    }

}