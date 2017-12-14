package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.events.service.MailSenderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
//        //mailSenderService.sendOneMailTextHtml(to, subject, htmlBodyMsg);
//
//       // mailSenderService.sendMultipleMailPlainText(emails, subject, body);
//        //mailSenderService.sendMultipleMailTextHtml(emails, subject, htmlBodyMsg);
//
//    }
}
