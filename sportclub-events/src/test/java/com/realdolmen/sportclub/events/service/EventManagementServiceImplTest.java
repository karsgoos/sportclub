package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
import com.realdolmen.sportclub.events.exceptions.EventNotFoundException;
import com.realdolmen.sportclub.events.repository.EventRepository;
import com.realdolmen.sportclub.events.repository.RecurringEventInfoRepository;
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

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@RunWith(MockitoJUnitRunner.class)
public class EventManagementServiceImplTest {
    @InjectMocks
    private EventManagementServiceImpl service = new EventManagementServiceImpl();

    @Mock
    private EventRepository repository;

    @Mock
    private RecurringEventInfoRepository recurringEventInfoRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Find one returns an empty event
        Mockito.when(repository.findOne(Mockito.anyLong())).thenReturn(new Event());

        // Repository.save returns its argument
        Mockito.when(recurringEventInfoRepository.save((RecurringEventInfo) Mockito.any())).thenAnswer(new Answer<RecurringEventInfo>() {
            @Override
            public RecurringEventInfo answer(InvocationOnMock invocation) throws Throwable {
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

    @Test
    public void canCreateRecurringEventOnSingleWeekday() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        RecurringEventInfo recurringEventInfo = new RecurringEventInfo();
        recurringEventInfo.setStartDate(event.getStartDate());
        recurringEventInfo.setEndDate(event.getStartDate().plusMonths(1));
        recurringEventInfo.setWeekdays(new TreeSet<>());
        recurringEventInfo.getWeekdays().add(DayOfWeek.MONDAY);
        event.setRecurringEventInfo(recurringEventInfo);

        service.create(event);
        Mockito.verify(repository, Mockito.times(1)).save((Iterable<Event>) Mockito.any());
        Mockito.verify(recurringEventInfoRepository, Mockito.times(1)).save((RecurringEventInfo) Mockito.any());
    }

    @Test
    public void canCreateRecurringEventOnMultipleWeekdays() throws CouldNotCreateEventException {
        Event event = createValidEvent();
        RecurringEventInfo recurringEventInfo = new RecurringEventInfo();
        recurringEventInfo.setStartDate(event.getStartDate());
        recurringEventInfo.setEndDate(event.getStartDate().plusMonths(1));
        recurringEventInfo.setWeekdays(new TreeSet<>());
        recurringEventInfo.getWeekdays().add(DayOfWeek.MONDAY);
        recurringEventInfo.getWeekdays().add(DayOfWeek.WEDNESDAY);
        event.setRecurringEventInfo(recurringEventInfo);

        service.create(event);
        Mockito.verify(repository, Mockito.times(1)).save((Iterable<Event>) Mockito.any());
        Mockito.verify(recurringEventInfoRepository, Mockito.times(1)).save((RecurringEventInfo) Mockito.any());
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
        event.getAddress().setHomeNumber(null);
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
    public void pagingWorksCorrectlyWithPositivePageAndFutureEvents() {
        ArrayList<Event> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Event event = createValidEvent();
            event.setStartDate(LocalDateTime.now().plusMonths(1));
            results.add(event);
        }
        Mockito.when(repository.findByStartDateAfterOrderByStartDateAsc(Mockito.any(), (Pageable) Mockito.any())).thenReturn(results);
        List<Event> result = service.findAll(0, 10);
        Assert.assertNotNull(result);
        Assert.assertEquals(10, result.size());
        Mockito.verify(repository, Mockito.times(1)).findByStartDateAfterOrderByStartDateAsc(Mockito.any(), (Pageable) Mockito.any());
        Mockito.verify(repository, Mockito.never()).findByStartDateBeforeOrderByStartDateDesc(Mockito.any(), (Pageable) Mockito.any());
    }

    @Test
    public void pagingReturnsNothingWithPositivePageAndPastEvents() {
        Mockito.when(repository.findByStartDateAfterOrderByStartDateAsc(Mockito.any(), (Pageable) Mockito.any())).thenReturn(new ArrayList<>());
        List<Event> result = service.findAll(0, 10);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
        Mockito.verify(repository, Mockito.times(1)).findByStartDateAfterOrderByStartDateAsc(Mockito.any(), (Pageable) Mockito.any());
        Mockito.verify(repository, Mockito.never()).findByStartDateBeforeOrderByStartDateDesc(Mockito.any(), (Pageable) Mockito.any());
    }

    @Test
    public void pagingWorksCorrectlyWithNegativePageAndPastEvents() {
        ArrayList<Event> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Event event = createValidEvent();
            event.setStartDate(LocalDateTime.now().minusMonths(1));
            results.add(event);
        }
        Mockito.when(repository.findByStartDateBeforeOrderByStartDateDesc(Mockito.any(), (Pageable) Mockito.any())).thenReturn(results);
        List<Event> result = service.findAll(-1, 10);
        Assert.assertNotNull(result);
        Assert.assertEquals(10, result.size());
        Mockito.verify(repository, Mockito.never()).findByStartDateAfterOrderByStartDateAsc(Mockito.any(), (Pageable) Mockito.any());
        Mockito.verify(repository, Mockito.times(1)).findByStartDateBeforeOrderByStartDateDesc(Mockito.any(), (Pageable) Mockito.any());
    }

    @Test
    public void pagingReturnsNothingWithNegativePageAndFutureEvents() {
        Mockito.when(repository.findByStartDateBeforeOrderByStartDateDesc(Mockito.any(), (Pageable) Mockito.any())).thenReturn(new ArrayList<>());
        List<Event> result = service.findAll(-1, 10);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
        Mockito.verify(repository, Mockito.never()).findByStartDateAfterOrderByStartDateAsc(Mockito.any(), (Pageable) Mockito.any());
        Mockito.verify(repository, Mockito.times(1)).findByStartDateBeforeOrderByStartDateDesc(Mockito.any(), (Pageable) Mockito.any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void pagingFailsWithNegativePageSize() {
        service.findAll(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pagingFailsWithZeroPageSize() {
        service.findAll(0, 0);
    }

    @Test
    public void canObtainCancellationsForEvent() throws EventNotFoundException {
        List<User> users = new ArrayList<>();
        Event event = createValidEvent();
        for (int i = 0; i < 10; i++) {
            Attendance attendance = new Attendance();
            User user = new RegisteredUser();
            Order order = new Order();

            order.setUser(user);
            attendance.setOrdr(order);
            attendance.setCancelled(true);
            order.addOrderable(attendance);
            event.addAttendance(attendance);

            users.add(user);
        }
        for (int i = 0; i < 5; i++) {
            Attendance attendance = new Attendance();
            User user = new Guest();
            Order order = new Order();

            order.setUser(user);
            attendance.setOrdr(order);
            attendance.setCancelled(true);
            order.addOrderable(attendance);
            event.addAttendance(attendance);

            users.add(user);
        }
        Mockito.when(repository.findOne(1L)).thenReturn(event);
        List<User> result = service.findCancellations(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(users, result);
    }

    @Test
    public void canObtainCancellationsForEventAndReturnsCorrectCount() throws EventNotFoundException {
        List<User> users = new ArrayList<>();
        Event event = createValidEvent();
        // Cancellations
        for (int i = 0; i < 10; i++) {
            Attendance attendance = new Attendance();
            User user = new RegisteredUser();
            Order order = new Order();

            order.setUser(user);
            attendance.setOrdr(order);
            attendance.setCancelled(true);
            order.addOrderable(attendance);
            event.addAttendance(attendance);

            users.add(user);
        }
        // Non cancellations
        for (int i = 0; i < 5; i++) {
            Attendance attendance = new Attendance();
            User user = new Guest();
            Order order = new Order();

            order.setUser(user);
            attendance.setOrdr(order);
            attendance.setCancelled(false);
            order.addOrderable(attendance);
            event.addAttendance(attendance);
        }
        Mockito.when(repository.findOne(1L)).thenReturn(event);
        List<User> result = service.findCancellations(1L);
        Assert.assertNotNull(result);
        // Only the cancellations were added to users, so we should only get those
        Assert.assertEquals(users, result);
    }

    private Event createValidEvent() {
        Event event = new Event();
        event.setStartDate(LocalDateTime.now());
        event.setEndDate(LocalDateTime.now().plusDays(5));
        event.setEnrollments(new ArrayList<>());
        Address address = new Address();
        address.setCountry("BE");
        address.setPostalCode("9000");
        address.setStreet("Sportstraat");
        address.setCity("Gent");
        address.setHomeNumber("1");
        event.setAddress(address);
        event.setName("Test Event");
        event.setMaxParticipants(10);
        event.setMinParticipants(1);
        List<RegisteredUser> responsibles = new ArrayList<>();
        responsibles.add(new RegisteredUser());
        event.setResponsibles(responsibles);
        event.setDeadline(LocalDateTime.now().plusDays(10));
        event.setPriceAdult(new BigDecimal(10.0));
        event.setPriceChild(new BigDecimal(7.0));
        return event;
    }

}