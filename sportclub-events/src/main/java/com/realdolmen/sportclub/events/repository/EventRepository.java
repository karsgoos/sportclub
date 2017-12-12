package com.realdolmen.sportclub.events.repository;

import com.realdolmen.sportclub.events.mocks.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
