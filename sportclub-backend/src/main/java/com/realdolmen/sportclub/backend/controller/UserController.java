package com.realdolmen.sportclub.backend.controller;

import com.realdolmen.sportclub.common.entity.User;
import com.realdolmen.sportclub.common.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    RegisteredUserRepository registeredUserRepository;

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('CAN_DO_SOMETHING')")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        System.out.println(user.getUsername());

        return registeredUserRepository.findByEmail(user.getUsername());
    }
}
