package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.Order;
import com.realdolmen.sportclub.common.entity.Orderable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderableRepository extends JpaRepository<Orderable, Long> {
}
