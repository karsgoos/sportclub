package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Guest;
import com.realdolmen.sportclub.common.entity.Sportclub;
import com.realdolmen.sportclub.events.service.mail.MailContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by FVDBF69 on 13/12/2017.
 */
@Component("javasampleapproachMailSender")
public class MailSenderServiceImpl implements MailSenderService {


    @Value("${spring.mail.username}")
    String emailAddress;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    MailContentBuilder mailContentBuilder;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     *
     * @param to String The delivery mail address
     * @param subject String The subject of the mail
     *
     *  Send One Mail with the body in content-type="text/html"
     */
    public void sendTestMail(String to, String subject) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {

            helper.setFrom(emailAddress);
            helper.setTo(to);
            helper.setSubject(subject);

            String content = mailContentBuilder.buildTest();
            helper.setText(content, true);
        } catch (MessagingException e){

            logger.error("There went someting wrong while generating the email ...");
        }

        logger.info("Sending mail from " + emailAddress + " to " + to + " ...");

        javaMailSender.send(mimeMessage);

        logger.info("Done!");
    }

    public void sendMailGuestAttendPublicEvent(Guest guest, Event event, Sportclub sportclub, String unsubscribeLink) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {

            helper.setFrom(emailAddress);
            helper.setTo(guest.getEmail());
            String subject = "Confirmation attending " + event.getName();
            helper.setSubject(subject);
            String content = mailContentBuilder.buildMailGuestAttendPublicEvent(guest, event, sportclub, unsubscribeLink);
            helper.setText(content, true);
        } catch (MessagingException e){

            logger.error("There went someting wrong while generating the email ...");
        }
        logger.info("mail sending");

        javaMailSender.send(mimeMessage);
        
        logger.info("done");
    }


}
