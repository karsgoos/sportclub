package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    RegisteredUser findByEmail(String email);
}
