package com.purwadhika.montrackv2.services;

import org.springframework.security.core.Authentication;

public interface AuthService {
    String generateToken(Authentication authentication);
}
