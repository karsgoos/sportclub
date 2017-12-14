package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
}
