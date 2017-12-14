package com.realdolmen.sportclub.events.repository;

import com.realdolmen.sportclub.common.entity.RecurringEventInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurringEventInfoRepository extends JpaRepository<RecurringEventInfo, Long> {
}
