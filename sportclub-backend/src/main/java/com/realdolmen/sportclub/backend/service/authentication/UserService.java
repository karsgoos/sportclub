package com.realdolmen.sportclub.backend.service.authentication;

import com.realdolmen.sportclub.common.entity.Privilege;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.common.repository.RegisteredUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDetailsService {
    private final RegisteredUserRepository registeredUserRepository;

    public UserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        RegisteredUser registeredUser = registeredUserRepository.findByEmail(email);

        if (registeredUser != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();

            for (Privilege privilege : registeredUser.getRole().getPrivileges()) {
                authorities.add(new SimpleGrantedAuthority(privilege.name()));
            }

            return new User(registeredUser.getEmail(), registeredUser.getPassword(), authorities);
        }

        throw new UsernameNotFoundException("User " + email + " not found.");
    }
}
