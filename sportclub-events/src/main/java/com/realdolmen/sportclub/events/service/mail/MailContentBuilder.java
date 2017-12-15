package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Guest;
import com.realdolmen.sportclub.common.entity.Sportclub;
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


    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine){
        super();
        this.templateEngine = templateEngine;
    }

    public String buildTest() {
        String name = "Frederik";
        String message = "My message";
        Event event = new Event();
        event.setName("Sporty event");

        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("message", message);
        context.setVariable("event", event);

        return templateEngine.process("mailTemplate", context);
    }


    public String buildMailGuestAttendPublicEvent(Guest guest, Event event, Sportclub sportclub,  String unsubscribeLink) {

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
}
