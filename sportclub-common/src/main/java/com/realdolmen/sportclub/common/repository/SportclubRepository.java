package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.Sportclub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportclubRepository extends JpaRepository<Sportclub, Long> {
}
