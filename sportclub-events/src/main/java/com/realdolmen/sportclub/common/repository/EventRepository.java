package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Order;
import com.realdolmen.sportclub.common.entity.RecurringEventInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStartDateBeforeOrderByStartDateDesc(LocalDateTime localDateTime, Pageable pageable);
    List<Event> findByStartDateAfterOrderByStartDateAsc(LocalDateTime localDateTime, Pageable pageable);
    void deleteByStartDateAfterAndRecurringEventInfoEquals(LocalDateTime localDateTime, RecurringEventInfo info);
    List<Event> findByReminderDateBetween(LocalDateTime time1, LocalDateTime time2);
    Event findOneByUuid(String uuid);
}
