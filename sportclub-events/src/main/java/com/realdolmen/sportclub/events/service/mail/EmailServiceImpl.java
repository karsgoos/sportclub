package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.Email;
import com.realdolmen.sportclub.common.entity.SendStatus;
import com.realdolmen.sportclub.common.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailRepository emailRepository;


    @Override
    public Email save(Email email) {
        return emailRepository.save(email);
    }

    @Override
    public Set<Email> getEmailsToSend() {
        return emailRepository.findBySendStatusAndAndDueDateBefore(SendStatus.SCHEDULED, LocalDateTime.now());
    }
}
