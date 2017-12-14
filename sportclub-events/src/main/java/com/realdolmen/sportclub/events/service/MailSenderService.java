package com.realdolmen.sportclub.events.service;

/**
 * Created by FVDBF69 on 14/12/2017.
 */
public interface MailSenderService {

    void sendOneMailPlainText(String to, String subject, String body);
    void sendOneMailTextHtml(String to, String subject, String htmlBodyMsg);
    void sendMultipleMailPlainText(String[] to, String subject, String body);
    void sendMultipleMailTextHtml(String[] to, String subject, String htmlBodyMsg);
}
