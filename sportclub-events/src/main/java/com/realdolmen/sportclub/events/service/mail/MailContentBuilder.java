package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.*;
import com.realdolmen.sportclub.common.repository.SportclubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.VariablesMap;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring4.context.SpringWebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * Created by FVDBF69 on 14/12/2017.
 */
@Service
public class MailContentBuilder {
    private TemplateEngine templateEngine;

    private Sportclub sportclub;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine, SportclubRepository sportclubRepository) {
        super();
        this.templateEngine = templateEngine;
        this.sportclub = sportclubRepository.findOne(1L);
    }


    public String buildMailGuestAttendPublicEvent(Guest guest, Event event, String unsubscribeLink) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

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
        Context context = new Context();

        context.setVariable("user", user);
        context.setVariable("membershipType", membershipType);
        context.setVariable("sportclub", sportclub);
        return templateEngine.process("enrollmentEnding", context);
    }

    public String buildMailPaymentForEventReceived(User user, Event event) {
        Context context = new Context();

        context.setVariable("user", user);
        context.setVariable("event", event);
        context.setVariable("sportclub", sportclub);
        if (event.getRecurringEventInfo() == null) {
            return templateEngine.process("paymentForSingleEventReceived", context);
        }
        return templateEngine.process("paymentForRecurringEventReceived", context);
    }

    public String buildMailPaymentForEnrollmentReceived(User user, Enrollment enrollment) {
        Context context = new Context();

        context.setVariable("user", user);
        context.setVariable("enrollment", enrollment);
        context.setVariable("sportclub", sportclub);
                return templateEngine.process("paymentForEnrollmentReceived", context);
    }
}
