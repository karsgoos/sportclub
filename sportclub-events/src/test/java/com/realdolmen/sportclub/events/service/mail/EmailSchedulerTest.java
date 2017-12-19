package com.realdolmen.sportclub.events.service.mail;

import com.realdolmen.sportclub.common.entity.Email;
import com.realdolmen.sportclub.events.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class EmailSchedulerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private MailSenderService mailSenderService;

    @InjectMocks
    EmailScheduler emailScheduler = new EmailScheduler();

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Set<Email> emails = new HashSet<>();
        emails.add(new Email());
        emails.add(new Email());
        emails.add(new Email());
        Mockito.when(emailService.getEmailsToSend()).thenReturn(emails);
    }

    @Test
    public void testSendScheduledMailsCallsSendMailsThreeTimes() {
        emailScheduler.sendScheduledMails();
        Mockito.verify(mailSenderService,times(3)).sendMail(any(Email.class));
    }

   /* @Test
    public void testScheduler(){
        // to test if a cron expression runs only from Monday to Friday
        org.springframework.scheduling.support.CronTrigger trigger =
                new CronTrigger("0 0 1 * * MON-FRI");
        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss EEEE");
        final Date yesterday = today.getTime();
        log.info("Yesterday was : " + df.format(yesterday));
        Date nextExecutionTime = trigger.nextExecutionTime(
                new TriggerContext() {

                    @Override
                    public Date lastScheduledExecutionTime() {
                        return yesterday;
                    }

                    @Override
                    public Date lastActualExecutionTime() {
                        return yesterday;
                    }

                    @Override
                    public Date lastCompletionTime() {
                        return yesterday;
                    }
                });

        String message = "Next Execution date: " + df.format(nextExecutionTime);
        log.info(message);
        Assert.assertTrue(true);

    }*/

}
