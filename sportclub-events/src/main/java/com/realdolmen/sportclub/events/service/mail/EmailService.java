package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.Email;
import com.realdolmen.sportclub.common.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface EmailService {


    Email save(Email email);

    Set<Email> getEmailsToSend();
}

