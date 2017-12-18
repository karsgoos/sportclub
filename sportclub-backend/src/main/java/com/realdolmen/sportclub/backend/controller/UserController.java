package com.realdolmen.sportclub.backend.controller;

import com.realdolmen.sportclub.backend.dao.UserPointsDao;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.common.entity.User;
import com.realdolmen.sportclub.common.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    RegisteredUserRepository registeredUserRepository;

    @GetMapping("/register")
    public boolean register() {
        return true;
    }

    @GetMapping("/user")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        System.out.println(user.toString());

        return registeredUserRepository.findByEmail(user.getUsername());
    }

    @GetMapping("/user-points")
    public int getCurrentUserPoints(){
        return ((RegisteredUser)getCurrentUser()).getTotalPoints();
    }

    @GetMapping("/points")
    public List<UserPointsDao> getPointsOfAllUsers(){
        List<RegisteredUser> users = registeredUserRepository.findAll();
        List<UserPointsDao> points = new ArrayList<>();
        for(RegisteredUser user : users){
            UserPointsDao userPointsDao = new UserPointsDao(user.getEmail(),user.getFirstName()+" "+user.getLastName(),user.getTotalPoints());
            points.add(userPointsDao);
        }
        return points;
    }
}
