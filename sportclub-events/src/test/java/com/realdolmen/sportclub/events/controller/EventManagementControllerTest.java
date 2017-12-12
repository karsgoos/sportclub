package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.service.EventManagementService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.context.WebApplicationContext;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class EventManagementControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EventManagementService eventManagementService;
    private EventManagementController eventManagementController;

    @Before
    public void setUp() {
        eventManagementController = new EventManagementController();
        eventManagementController.setService(eventManagementService);
    }

    @Before
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void returnsErrorWhenCanNotCreateEvent() throws Exception {
        Mockito.when(eventManagementService.create(Mockito.any())).thenThrow(CouldNotCreateEventException.class);
        mockMvc.perform(post("/events")
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}