package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.Email;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EventManagementService eventService;

    @Autowired
    private MailSenderService mailSenderService;

    @Scheduled(initialDelay = 60000, fixedDelay = 1800000)
    public void sendScheduledMails() {

        Set<Email> emails = emailService.getEmailsToSend();

        for (Email email : emails) {
            mailSenderService.sendMail(email);
        }
    }

    @Scheduled(fixedDelay = 3600000)
    public void sendReminderMails(){
        List<Event> eventsToSendReminders = eventService.getAllEventsWithReminderDateInLastHour();

        for(Event event: eventsToSendReminders){
            mailSenderService.sendReminderMails(event);
        }
    }
}
