package com.realdolmen.sportclub.events.service;

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
public class MailSenderServiceImpl implements MailSenderService{


    @Value("${spring.mail.username}")
    String emailAddress;

    @Autowired
    private JavaMailSender javaMailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     *
     * @param to String The delivery mail address
     * @param subject String The subject of the mail
     * @param body String The body of the mail in text format.
     *
     *  Send One Mail with the body in plain text format (eq. \n is a new line)
     */
    public void sendOneMailPlainText(String to, String subject, String body) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(emailAddress);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);

        logger.info("Sending mail from " + emailAddress + " to " + to + " ...");

        javaMailSender.send(mail);

        logger.info("Done!");
    }

    /**
     *
     * @param to String The delivery mail address
     * @param subject String The subject of the mail
     * @param htmlBodyMsg String The body of the mail written in content-type="text/html".
     *
     *  Send One Mail with the body in content-type="text/html"
     */
    public void sendOneMailTextHtml(String to, String subject, String htmlBodyMsg) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {

            helper.setFrom(emailAddress);
            helper.setTo(to);
            helper.setSubject(subject);
            mimeMessage.setContent(htmlBodyMsg, "text/html");
        } catch (MessagingException e){

            logger.error("There went someting wrong while generating the email ...");
        }

        logger.info("Sending mail from " + emailAddress + " to " + to + " ...");

        javaMailSender.send(mimeMessage);

        logger.info("Done!");
    }

    /**
     *
     * @param to String[] The array of delivery mail addresses
     * @param subject String The subject of the mail
     * @param body String The body of the mail in text format.
     *
     *  Send multiple Mails with the body in plain text format (eq. \n is a new line)
     */
    public void sendMultipleMailPlainText(String[] to, String subject, String body){

        for ( int i = 0; i < to.length; i++) {

            sendOneMailTextHtml(to[i], subject, body);
        }
    }

    /**
     *
     * @param to String[] The array of delivery mail addresses
     * @param subject String The subject of the mail
     * @param htmlBodyMsg String The body of the mail written in content-type="text/html".
     *
     *  Send multiple Mails with the body in content-type="text/html"
     */
    public void sendMultipleMailTextHtml(String[] to, String subject, String htmlBodyMsg) {

        for ( int i = 0; i < to.length; i++) {

            sendOneMailTextHtml(to[i], subject, htmlBodyMsg);
        }
    }


}
