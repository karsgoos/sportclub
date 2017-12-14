package com.realdolmen.sportclub.backend.controller;

import com.realdolmen.sportclub.backend.service.authentication.UserService;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/api/login")
    public String userLogin(String email, String password) {
        RegisteredUser registeredUser=(RegisteredUser)userService.loadUserByUsername(email);
        if(!registeredUser.getPassword().equals(password)){
            return "loginPage?faces-redirect=true" ;
        }
        return "nextPage?faces-redirect=true";
    }

}
