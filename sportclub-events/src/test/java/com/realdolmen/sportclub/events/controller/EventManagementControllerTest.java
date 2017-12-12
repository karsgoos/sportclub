package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.service.EventManagementService;
import com.realdolmen.sportclub.events.service.EventManagementServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {EventManagementServiceImpl.class, EventManagementController.class})
public class EventManagementControllerTest extends AbstractJUnit4SpringContextTests {

    private MockMvc mockMvc;

    @Spy
    private EventManagementService eventManagementService;

    @InjectMocks
    private EventManagementController eventManagementController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventManagementController).build();
    }

    @Test
    public void returnsErrorWhenCanNotCreateEvent() throws Exception {
        Mockito.when(eventManagementService.create(Mockito.any())).thenThrow(CouldNotCreateEventException.class);
        mockMvc.perform(post("/events")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}