package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.*;

/**
 * Created by FVDBF69 on 14/12/2017.
 */
public interface MailSenderService {


    void sendMailGuestAttendPublicEvent(Guest guest, Event event, String unsubscribeLink);

    void sendMailEnrollmentEnding(UserEnrollment userEnrollment);

    void sendMail(Email email);

    void sendReminderMails(Event event);
}
