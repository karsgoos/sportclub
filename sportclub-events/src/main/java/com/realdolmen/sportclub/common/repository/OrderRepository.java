package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}