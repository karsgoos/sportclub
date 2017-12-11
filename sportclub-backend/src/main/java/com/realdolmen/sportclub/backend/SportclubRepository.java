package com.realdolmen.sportclub.backend;

import com.realdolmen.sportclub.common.entity.Sportclub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportclubRepository extends JpaRepository<Sportclub, Long> {
}
