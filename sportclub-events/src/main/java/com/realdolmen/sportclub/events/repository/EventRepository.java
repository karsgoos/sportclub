package com.realdolmen.sportclub.events.repository;

import com.realdolmen.sportclub.events.mocks.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
