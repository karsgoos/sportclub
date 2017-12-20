package com.realdolmen.sportclub.backend.controller;

import com.realdolmen.sportclub.backend.dao.UserPointsDao;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.common.entity.Role;
import com.realdolmen.sportclub.common.entity.authentication.AuthenticatedUser;
import com.realdolmen.sportclub.common.repository.RegisteredUserRepository;
import com.realdolmen.sportclub.common.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    RegisteredUserRepository registeredUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/register")
    public boolean register() {
        return true;
    }

    @PostMapping("/user/{id}/role")
    // @PreAuthorize("hasAuthority('ADMINISTRATOR_PRIVILEGES')")
    public ResponseEntity<Boolean> changeRole(@PathVariable("id") Long id, @RequestParam("role_id") Long role_id) {
        RegisteredUser user;
        Role role;

        if ((user = registeredUserRepository.findOne(id)) != null) {
             if ((role = roleRepository.findOne(role_id)) != null) {
                 user.setRole(role);
                 registeredUserRepository.save(user);

                 return new ResponseEntity<>(true, HttpStatus.OK);
             }
        }

        return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('REGISTERED_USER_PRIVILEGES')")
    public AuthenticatedUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        RegisteredUser user = registeredUserRepository.findByEmail(((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername());

        AuthenticatedUser authenticatedUser = new AuthenticatedUser(user.getEmail(), user.getFirstName(), user.getLastName(), user.getTotalPoints());

        return authenticatedUser;
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
