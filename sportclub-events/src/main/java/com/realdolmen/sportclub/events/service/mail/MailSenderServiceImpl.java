package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Autowired
    EmailService emailService;


    Logger logger = LoggerFactory.getLogger(this.getClass());


    public void sendMailGuestAttendPublicEvent(Guest guest, Event event, String unsubscribeLink) {

        String subject = "Bevestiging; ingeschreven voor: " + event.getName();
        String content = mailContentBuilder.buildMailGuestAttendPublicEvent(guest, event, unsubscribeLink);
        storeAndSendMail(guest, subject, content);

    }

    @Override
    public void scheduleMailEnrollmentEnding(UserEnrollment userEnrollment) {
        String subject = "Lidmaatschap eindigt in 1 maand";
        User user = userEnrollment.getOrdr().getUser();
        String content = mailContentBuilder.buildMailEnrollmentEnding(user, userEnrollment.getEnrollment().getMembershipType());
        LocalDate reminderDate = userEnrollment.getEnrollment().getEndDate().minusMonths(1);
        LocalDateTime reminderDateAndTime = reminderDate.atTime(18, 0);
        storeAndScheduleMail(user, subject, content, reminderDateAndTime);
    }

    @Override
    public void sendMailPaymentEnrollmentReceived(UserEnrollment userEnrollment) {
        String subject = "Betaling ontvangen - " + userEnrollment.getEnrollment().getMembershipType().getName();
        User user = userEnrollment.getOrdr().getUser();
        String content = mailContentBuilder.buildMailPaymentForEnrollmentReceived(user,userEnrollment.getEnrollment());
        storeAndSendMail(user,subject,content);
    }

    @Override
    public void sendMailPaymentEventReceived(User user, Event event) {

        String subject = "Betaling ontvangen - " + event.getName();
        String content = mailContentBuilder.buildMailPaymentForEventReceived(user,event);
        storeAndSendMail(user,subject,content);
    }

    private void storeAndScheduleMail(User user, String subject, String content, LocalDateTime dueDate) {

        Email email = new Email(user, subject, content, dueDate, SendStatus.SCHEDULED);
        emailService.save(email);
    }

    private void storeAndSendMail(User user, String subject, String content) {
        Email email = new Email(user, subject, content, LocalDateTime.now(), SendStatus.SCHEDULED);
        email = emailService.save(email);
        sendMail(email);
    }

    public void sendMail(Email email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {

            helper.setFrom(emailAddress);
            helper.setTo(email.getUser().getEmail());

            helper.setSubject(email.getSubject());

            helper.setText(email.getContent(), true);
        } catch (MessagingException e) {

            logger.error("There went someting wrong while generating the email ...");
        }

        try {
            javaMailSender.send(mimeMessage);
            email.setSendStatus(SendStatus.SUCCES);

        } catch (MailException e) {
            logger.error("Failed to send email to " + email.getUser().getEmail() + " with subject " + email.getSubject());
            email.setSendStatus(SendStatus.FAILED);

        } finally {
            emailService.save(email);

        }


    }


}
