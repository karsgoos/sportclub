package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.MembershipType;
import com.realdolmen.sportclub.common.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipTypeRepository extends JpaRepository<MembershipType, Long> {
}
