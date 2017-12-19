package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.*;

/**
 * Created by FVDBF69 on 14/12/2017.
 */
public interface MailSenderService {


    void sendMailGuestAttendPublicEvent(Guest guest, Event event, String unsubscribeLink);

    void sendMailPaymentEventReceived(User user, Event event);

    void sendMailPaymentEnrollmentReceived(UserEnrollment userEnrollment);

    void scheduleMailEnrollmentEnding(UserEnrollment userEnrollment);

    void sendMail(Email email);
}
