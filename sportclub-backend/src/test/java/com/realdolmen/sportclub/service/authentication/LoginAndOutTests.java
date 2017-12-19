package com.realdolmen.sportclub.service.authentication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.logging.Filter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class LoginAndOutTests {

    /*  THINGS WE MIGHT TEST
        Max length should be set for all the text boxes
        Password field should be masked with asterisks (*****)
        Left and right trimming should be done for Password field
        Login credentials in UPPER case should not be treated as invalid
        Forgot Password link should be present on the form
        Reset button should clear data from all the text boxes in the form
        Validation message should be shown when special characters are entered in the username field, or when invalid username and/or password is entered or the fields are left blank
        User should be redirected to Login page if the Login URL is bypassed
        Clicking on ‘Log out’ should take the user back to Home Page
        If the user clicks on “Remember me” option, he should be redirected to appropriate page on next login
        User should be redirected to appropriate page for Forgot password option
        Clicking on Sign Up should take the user to registration page
     */

    @Autowired
    private WebApplicationContext context;



    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    //Will test if index page is correctly accessible for every possible user/guest
/*    @Test
    public void testIndexPage() throws Exception{
        mvc.perform(get("/")).andExpect(status().isOk()).andExpect(forwardedUrl("/sportclub-frontend/src/index.html"));
    }

    //Will test if login page is correctly accessible for every possible user/guest
    @Test
    public void testLoginPage() throws Exception{
        mvc.perform(get("/login")).andExpect(status().isOk()).andExpect(forwardedUrl("/sportclub-frontend/src/login.html"));
    }

    //Will test if contact page is correctly accessible for every possible user/guest
    @Test
    public void testContactPage() throws Exception{
        mvc.perform(get("/contact")).andExpect(status().isOk()).andExpect(forwardedUrl("/sportclub-frontend/src/contact.html"));
    }

    //Will test if registration page is correctly accessible for every possible user/guest
    @Test
    public void testRegistrationPage() throws Exception{
        mvc.perform(get("/registration")).andExpect(status().isOk()).andExpect(forwardedUrl("/sportclub-frontend/src/registration.html"));
    }

    //Will test if guestcalender page is correctly accessible for every possible user/guest
    @Test
    public void testGuestCalenderPage() throws Exception{
        mvc.perform(get("/guestcalender")).andExpect(status().isOk()).andExpect(forwardedUrl("/sportclub-frontend/src/guestcalender.html"));
    }

    //Will test if registrationform page is correctly accessible for every possible user/guest
    @Test
    public void testRegistrationFormPage() throws Exception{
        mvc.perform(get("/registrationform")).andExpect(status().isOk()).andExpect(forwardedUrl("/sportclub-frontend/src/registrationform.html"));
    }*/

    @Test
    public void loginPageToDashboardPage() throws Exception{

    }



    @Test//check if matches regex pattern(valid e-mail)
    public void testFindByEmailValidParameterSucceeds(){}

    @Test//check if doesn't match regex pattern(invalid-email)
    public void testFindByEmailInvalidParameterThrowsException(){}

    @Test//check if user is valid
    public void testFindByUserValidParameterSucceeds(){}

    @Test//check if user is invalid
    public void testFindByUserInvalidParameterThrowsException(){
    }

    @Test//check if password is correct
    public void testFindByPasswordValidParameterSucceeds(){}

    @Test//check if password is incorrect
    public void testFindByPasswordInvalidParameterThrowsException(){}


}
