package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private MailSenderService mailSenderService;

    @Scheduled(fixedDelay = 1800000)
    public void sendScheduledMails(){

        Set<Email> emails = emailService.getEmailsToSend();

        for(Email email : emails){
            mailSenderService.sendMail(email);
        }
    }
}
