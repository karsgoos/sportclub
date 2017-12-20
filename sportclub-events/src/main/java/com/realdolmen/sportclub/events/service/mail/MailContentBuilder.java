package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.SportclubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.persistence.PrePersist;
import java.time.format.DateTimeFormatter;


/**
 * Created by FVDBF69 on 14/12/2017.
 */
@Service
public class MailContentBuilder {
    private TemplateEngine templateEngine;

    private Sportclub sportclub;

    @Autowired
    SportclubRepository sportclubRepository;


    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        super();
        this.templateEngine = templateEngine;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");



    public String buildMailGuestAttendPublicEvent(Guest guest, Event event, String unsubscribeLink) {
        sportclub = sportclubRepository.findOne(1L);

        Context context = new Context();

        context.setVariable("guest", guest);
        context.setVariable("event", event);
        context.setVariable("startDatum", event.getStartDate().format(formatter));
        context.setVariable("startUur", event.getStartDate().format(timeFormatter));
        context.setVariable("eindDatum", event.getEndDate().format(formatter));
        context.setVariable("eindUur", event.getEndDate().format(timeFormatter));
        context.setVariable("sportclub", sportclub);
        context.setVariable("unsubscribeLink", unsubscribeLink);

        return templateEngine.process("guestAttendPublicEventTemplate", context);
    }

    public String buildMailEnrollmentEnding(User user, MembershipType membershipType) {
        sportclub = sportclubRepository.findOne(1L);
        Context context = new Context();

        context.setVariable("user", user);
        context.setVariable("membershipType", membershipType);
        context.setVariable("sportclub", sportclub);
        return templateEngine.process("enrollmentEnding", context);
    }

    public String buildMailPaymentForEventReceived(User user, Event event) {
        sportclub = sportclubRepository.findOne(1L);
        Context context = new Context();

        context.setVariable("user", user);
        context.setVariable("eventName", event.getName());
        context.setVariable("sportclub", sportclub);
        if (event.getRecurringEventInfo() == null) {
            context.setVariable("date", event.getStartDate().format(formatter));
            context.setVariable("time", event.getStartDate().format(timeFormatter));
            return templateEngine.process("paymentForSingleEventReceived", context);
        }
        context.setVariable("startDate", event.getStartDate().format(formatter));
        context.setVariable("endDate", event.getEndDate().format(formatter));
        return templateEngine.process("paymentForRecurringEventReceived", context);
    }

    public String buildMailPaymentForEnrollmentReceived(User user, Enrollment enrollment) {
        sportclub = sportclubRepository.findOne(1L);
        Context context = new Context();

        context.setVariable("user", user);
        context.setVariable("enrollment", enrollment);
        context.setVariable("sportclub", sportclub);
        context.setVariable("startDate", enrollment.getStartDate().format(formatter));
        context.setVariable("endDate", enrollment.getEndDate().format(formatter));
        return templateEngine.process("paymentForEnrollmentReceived", context);
    }

    public String buildGuestMailEventChanged(Guest guest, Event event, Sportclub sportclub, String unsubscribeLink) {

        Context context = new Context();

        context.setVariable("guest", guest);
        context.setVariable("event", event);
        context.setVariable("startDatum", event.getStartDate().format(formatter));
        context.setVariable("startUur", event.getStartDate().format(timeFormatter));
        context.setVariable("eindDatum", event.getEndDate().format(formatter));
        context.setVariable("eindUur", event.getEndDate().format(timeFormatter));
        context.setVariable("sportclub", sportclub);
        context.setVariable("unsubscribeLink", unsubscribeLink);

        return templateEngine.process("guestAttendPublicEventTemplate", context);
    }


    public String buildReminderOfEventMail(User user, Event event){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Context context = new Context();

        context.setVariable("user", user);
        context.setVariable("sportclub", sportclub);
        context.setVariable("event", event);
        context.setVariable("startDatum", event.getStartDate().format(formatter));
        context.setVariable("startUur", event.getStartDate().format(timeFormatter));

        return templateEngine.process("eventReminder", context);
    }

    public String buildNrParticipantsReachedMail(User user, Event event){
        Context context = new Context();

        context.setVariable("user", user);
        context.setVariable("event", event);

        return templateEngine.process("participantsReached", context);
    }

    public String buildEventDeletion(User user, Event event) {
        sportclub = sportclubRepository.findOne(1L);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Context context = new Context();

        context.setVariable("sportclubName", sportclub.getName());
        context.setVariable("event", event);
        context.setVariable("user", user);
        context.setVariable("startDatum", event.getStartDate().format(formatter));
        context.setVariable("startUur", event.getStartDate().format(timeFormatter));

        return templateEngine.process("eventDeletion", context);
    }

    public String buildEventUpdate(User user, Event event) {
        sportclub = sportclubRepository.findOne(1L);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Context context = new Context();

        context.setVariable("sportclub", sportclub);
        context.setVariable("event", event);
        context.setVariable("user", user);
        context.setVariable("startDatum", event.getStartDate().format(formatter));
        context.setVariable("startUur", event.getStartDate().format(timeFormatter));

        return templateEngine.process("eventUpdate", context);
    }
}
