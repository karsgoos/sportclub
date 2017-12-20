package com.realdolmen.sportclub.backend.service;

import com.realdolmen.sportclub.common.entity.Enrollment;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.common.entity.authentication.AuthenticatedUser;
import com.realdolmen.sportclub.common.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class BackendUserService {
    @Autowired
    RegisteredUserRepository registeredUserRepository;

    public AuthenticatedUser getCurrentUser(String email) {
        RegisteredUser user = registeredUserRepository.findByEmail(email);

        List<String> enrollmentStrings = new ArrayList<>();

        for (Enrollment enrollment : user.getEnrollments()) {
            enrollmentStrings.add(enrollment.getName());
        }

        AuthenticatedUser authenticatedUser =
                new AuthenticatedUser(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(),
                        user.getTotalPoints(), user.getRole().getName(), enrollmentStrings);

        return authenticatedUser;
    }

    public List<RegisteredUser> getAllUsers() {
        return registeredUserRepository.findAll();
    }

    public RegisteredUser getUser(Long id) {
        return registeredUserRepository.findOne(id);
    }

    public RegisteredUser persistUser(RegisteredUser user) {
        return registeredUserRepository.save(user);
    }

    public Page<AuthenticatedUser> getUserPage(Pageable pageable) {
        return registeredUserRepository.findAllWithEnrollments(pageable).map(this::convertToAuthenticatedUser);
    }

    private AuthenticatedUser convertToAuthenticatedUser(final RegisteredUser registeredUser) {
        final AuthenticatedUser authenticatedUser = new AuthenticatedUser();

        authenticatedUser.setId(registeredUser.getId());
        authenticatedUser.setEmail(registeredUser.getEmail());
        authenticatedUser.setFirstName(registeredUser.getFirstName());
        authenticatedUser.setLastName(registeredUser.getLastName());
        authenticatedUser.setTotalPoints(registeredUser.getTotalPoints());
        authenticatedUser.setRole(registeredUser.getRole().getName());

        List<String> enrollmentStrings = new ArrayList<>();
        for (Enrollment enrollment : registeredUser.getEnrollments()) {
            enrollmentStrings.add(enrollment.getName());
        }

        authenticatedUser.setEnrollments(enrollmentStrings);

        return authenticatedUser;
    }
}
