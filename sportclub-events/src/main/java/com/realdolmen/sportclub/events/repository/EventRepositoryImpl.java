package com.realdolmen.sportclub.events.repository;

import com.realdolmen.sportclub.common.entity.Attendance;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventRepositoryImpl implements EventAttendeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAttendeesForEvent(Event event) {
        String queryString = "SELECT DISTINCT u FROM Event e INNER JOIN e.attendancies a " +
                "INNER JOIN a.ordr o INNER JOIN o.user u " +
                "WHERE e.id = :eventId";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class).setParameter("eventId", event.getId());
        return query.getResultList();
    }

    @Override
    public List<Attendance> findCancellationsForEvent(Event event) {
        String queryString = "SELECT DISTINCT a FROM Event e INNER JOIN e.attendancies a " +
                "WHERE a.cancelled = TRUE AND e.id = :eventId";
        TypedQuery<Attendance> query = entityManager.createQuery(queryString, Attendance.class).setParameter("eventId", event.getId());
        return query.getResultList();
    }
}
