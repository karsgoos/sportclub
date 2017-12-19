package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
