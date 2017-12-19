package com.realdolmen.sportclub.common.repository;

import com.realdolmen.sportclub.common.entity.Email;
import com.realdolmen.sportclub.common.entity.SendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface EmailRepository extends JpaRepository<Email,Long> {

    Set<Email> findBySendStatusAndAndDueDateBefore(SendStatus sendStatus, LocalDateTime dueDate);
}
