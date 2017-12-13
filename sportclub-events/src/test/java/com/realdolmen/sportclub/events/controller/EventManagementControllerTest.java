package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.config.TestConfig;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
import com.realdolmen.sportclub.events.service.EventManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public class EventManagementControllerTest extends AbstractJUnit4SpringContextTests {

    private MockMvc mockMvc;

    @Mock
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
        mockMvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void returnsErrorWhenCanNotUpdateEvent() throws Exception {
        Mockito.when(eventManagementService.update(Mockito.any())).thenThrow(CouldNotUpdateEventException.class);
        mockMvc.perform(put("/events").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}