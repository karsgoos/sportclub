package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.Enrollment;
import com.realdolmen.sportclub.common.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
