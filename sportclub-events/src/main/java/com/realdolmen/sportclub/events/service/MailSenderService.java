package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Guest;
import com.realdolmen.sportclub.common.entity.Sportclub;

/**
 * Created by FVDBF69 on 14/12/2017.
 */
public interface MailSenderService {

    void sendTestMail(String to, String subject);

    void sendMailGuestAttendPublicEvent(Guest guest, Event event, Sportclub sportclub, String unsubscribeLink);
}
