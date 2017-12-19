package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.RecurringEventInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RecurringEventInfoRepository extends JpaRepository<RecurringEventInfo, Long> {
}
