package com.realdolmen.sportclub.events.repository;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.RecurringEventInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStartDateBeforeOrderByStartDateDesc(LocalDateTime localDateTime, Pageable pageable);
    List<Event> findByStartDateAfterOrderByStartDateAsc(LocalDateTime localDateTime, Pageable pageable);
    void deleteByStartDateAfterAndRecurringEventInfoEquals(LocalDateTime localDateTime, RecurringEventInfo info);
    List<Event> findByReminderDateBetween(LocalDateTime time1, LocalDateTime time2);
}
