package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Guest;
import com.realdolmen.sportclub.common.entity.Sportclub;
import com.realdolmen.sportclub.events.service.MailSenderService;
import com.realdolmen.sportclub.events.service.MailSenderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by FVDBF69 on 13/12/2017.
 */
@RestController
@RequestMapping(method = RequestMethod.GET, value="api/mailsender")
public class MailSenderController {

    @Autowired
    MailSenderServiceImpl mailSenderService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * This RestController was written for testing purposes only.
     * It is not acceptable to make a email-sending RestController available in production
     *
     */
//    @RequestMapping("/")
//    public void sendMail() {
//
//
//        String to = "fvdbf69.realdolmen@gmail.com";
//        String subject = "JavaMailSender";
//        String body = "Just-Testing!";
//
//        String htmlBodyMsg = "<h3>This is e test email</h3><div> ... for testing content type text/html</div>";
//
//        String[] emails = {"fvdbf69.realdolmen@gmail.com"};
//
//        //mailSenderService.sendOneMailPlainText( to, subject, body);
//        //mailSenderService.sendTestMail(to, subject);
//       // mailSenderService.sendMultipleMailPlainText(emails, subject, body);
//        //mailSenderService.sendMultipleMailTextHtml(emails, subject, htmlBodyMsg);
//
//    }
//
//    @RequestMapping("/guestpublic")
//    public void sendGuestPublicMail(){
//        Event event = new Event();
//        event.setName("testEvent");
//        event.setDescription("This is de description of a testEvent");
//
//        event.setStartDate(LocalDateTime.now().minusDays(2));
//        event.setEndDate(LocalDateTime.now());
//
//        event.setPriceAdult(new BigDecimal("0"));
//        event.setPriceChild(new BigDecimal("8"));
//
//        Address address = new Address();
//        address.setStreet("testStreet");
//        address.setHomeNumber(10);
//        address.setPostalCode("1000");
//        address.setCity("Brussel");
//        address.setCountry("Belgie");
//
//        event.setAddress(address);
//
//        Guest guest = new Guest();
//        guest.setFirstName("John");
//        guest.setLastName("Doe");
//        guest.setEmail("fvdbf69.realdolmen@gmail.com");
//
//        String unsubscribeLink = "http://www.realdolmen.com/nl/strategische-ict";
//
//        Sportclub sportclub = new Sportclub();
//        sportclub.setName("R-sportclub");
//
//        mailSenderService.sendMailGuestAttendPublicEvent(guest, event, sportclub, unsubscribeLink);
//    }
}
