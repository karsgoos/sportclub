package com.realdolmen.sportclub.events.controller;

import com.realdolmen.sportclub.common.entity.Address;
import com.realdolmen.sportclub.common.entity.AgeCategory;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.events.config.TestConfig;
import com.realdolmen.sportclub.events.exceptions.CouldNotCreateEventException;
import com.realdolmen.sportclub.events.exceptions.CouldNotUpdateEventException;
import com.realdolmen.sportclub.events.service.EventManagementService;
import org.junit.Before;
import org.junit.Ignore;
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

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test @Ignore
    public void correctEventPostRequest()throws Exception{
        Address postedAddress = new Address();
        postedAddress.setCountry("Belgium");
        LocalDateTime deadLine = LocalDateTime.now().plusDays(5);
        LocalDateTime startDate = LocalDateTime.now().plusDays(100);
        LocalDateTime endDate = startDate.plusHours(5);
        Map<AgeCategory, BigDecimal> price = new HashMap<>();
        price.put(AgeCategory.ADULT, new BigDecimal(100));
        price.put(AgeCategory.CHILD, new BigDecimal(80));


        postedAddress.setHomeNumber(1);
        postedAddress.setPostalCode("1000");
        postedAddress.setStreet("Stationstraat");
        Event postedEvent = new Event();
        postedEvent.setAddress(postedAddress);
        postedEvent.setClosed(false);
        postedEvent.setDeadline(deadLine);
        postedEvent.setDescription("Test description");
        postedEvent.setEndDate(endDate);
        postedEvent.setStartDate(startDate);
        postedEvent.setMaxParticipants(100);
        postedEvent.setName("SuccessEvent");
        postedEvent.setMinParticipants(3);
        postedEvent.setPrice(price);


        Mockito.when(eventManagementService.create(postedEvent)).thenReturn(postedEvent);

        mockMvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.asJsonString(postedEvent)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

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