package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    public RegisteredUser findByEmail(String email);
}
