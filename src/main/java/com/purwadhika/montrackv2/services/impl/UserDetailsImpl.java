package com.purwadhika.montrackv2.services.impl;

import com.purwadhika.montrackv2.entities.UserAuth;
import com.purwadhika.montrackv2.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userData = userRepository.findByEmail(email).orElse(null);
        return new UserAuth(userData);
    }
}
