package com.realdolmen.sportclub.backend.config;

import com.realdolmen.sportclub.backend.service.authentication.UserService;
import com.realdolmen.sportclub.common.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    RegisteredUserRepository registeredUserRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(new UserService(registeredUserRepository))
               // .passwordEncoder(new BCryptPasswordEncoder());
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
