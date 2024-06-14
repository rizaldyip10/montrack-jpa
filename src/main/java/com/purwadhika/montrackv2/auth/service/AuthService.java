package com.purwadhika.montrackv2.auth.service;

import org.springframework.security.core.Authentication;

public interface AuthService {
    String generateToken(Authentication authentication);
    String encodePassword(String password);
    String encodePin(int pin);
}
