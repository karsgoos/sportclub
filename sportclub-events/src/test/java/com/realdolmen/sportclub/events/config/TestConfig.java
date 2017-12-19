package com.realdolmen.sportclub.events.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

/**
 * Created by FVDBF69 on 12/12/2017.
 */
@Configuration
@Profile("test")
@ContextConfiguration
public class TestConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/eventsSchema.sql")
                .build();
    }

    @Bean
    public Database database() {
        return Database.MYSQL;
    }
}

