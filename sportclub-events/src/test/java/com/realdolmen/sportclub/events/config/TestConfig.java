package com.realdolmen.sportclub.events.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class TestConfig {
    @Autowired
    private ApplicationContext applicationContext;
}
