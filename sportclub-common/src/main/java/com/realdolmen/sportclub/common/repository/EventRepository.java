package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
