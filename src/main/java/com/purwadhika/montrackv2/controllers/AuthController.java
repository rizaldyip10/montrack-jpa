package com.purwadhika.montrackv2.controllers;

import com.purwadhika.montrackv2.dto.LoginRequestDto;
import com.purwadhika.montrackv2.dto.LoginResponseDto;
import com.purwadhika.montrackv2.entities.UserAuth;
import com.purwadhika.montrackv2.responses.Response;
import com.purwadhika.montrackv2.services.AuthService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Log
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto userLogin) {
        log.info("User login request recieved from userId: " + userLogin.getEmail());
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLogin.getEmail(),
                                userLogin.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserAuth userDetails = (UserAuth) authentication.getPrincipal();
        String token = authService.generateToken(authentication);
        LoginResponseDto response = new LoginResponseDto();
        response.setMessage("Login success");
        response.setToken(token);
        return Response.success(response.getMessage(), response);
    }
}
