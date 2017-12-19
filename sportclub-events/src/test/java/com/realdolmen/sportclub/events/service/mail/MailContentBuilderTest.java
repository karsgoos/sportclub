package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Guest;
import com.realdolmen.sportclub.common.entity.Sportclub;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.thymeleaf.TemplateEngine;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by FVDBF69 on 15/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MailContentBuilderTest {

//    @Autowired
//    private MailContentBuilder mailContentBuilder;
//
//    @Autowired
//    private JavaMailSender javaMailSender;

    private Event event;
    private Guest guest;
    private String unsubscribeLink;
    private Sportclub sportclub;

    @Before
    public void init() {
        //mailContentBuilder = new MailContentBuilder(new TemplateEngine());
        event = new Event();
        event.setName("testEvent");
        event.setDescription("This is de description of a testEvent");

        event.setStartDate(LocalDateTime.now().minusDays(2));
        event.setEndDate(LocalDateTime.now());

        event.setPriceAdult(new BigDecimal("10"));
        event.setPriceChild(new BigDecimal("8"));

        Address address = new Address();
        address.setStreet("testStreet");
        address.setHomeNumber("10");
        address.setPostalCode("1000");
        address.setCity("Brussel");
        address.setCountry("Belgie");

        event.setAddress(address);

        guest = new Guest();
        guest.setFirstName("John");
        guest.setLastName("Doe");
        guest.setEmail("fvdbf69.realdolmen@gmail.com");

        unsubscribeLink = "http://www.realdolmen.com/nl/strategische-ict";

        sportclub = new Sportclub();
        sportclub.setName("R-sportclub");

    }

    @Test
    @Ignore
    public void buildMailGuestAttendPublicEvent() {
        //TODO cannot initialize mailcontextbuilder.

        //mailContentBuilder.buildMailGuestAttendPublicEvent(guest, event, sportclub, unsubscribeLink);

        Assert.assertTrue(true);
    }
}
