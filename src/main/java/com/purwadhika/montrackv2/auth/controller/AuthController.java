package com.purwadhika.montrackv2.auth.controller;

import com.purwadhika.montrackv2.auth.dto.LoginRequestDto;
import com.purwadhika.montrackv2.auth.dto.LoginResponseDto;
import com.purwadhika.montrackv2.auth.dto.RegisterRequestDto;
import com.purwadhika.montrackv2.auth.dto.RegisterResponseDto;
import com.purwadhika.montrackv2.auth.entity.UserAuth;
import com.purwadhika.montrackv2.auth.service.AuthService;
import com.purwadhika.montrackv2.users.repositories.UserRepository;
import com.purwadhika.montrackv2.users.services.UserService;
import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, UserRepository userRepository, UserService userService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequestDto userRegister) {
        String hashedPassword = authService.encodePassword(userRegister.getPassword());
        userService.createUser(userRegister.getName(), userRegister.getEmail(), hashedPassword);

        RegisterResponseDto response = new RegisterResponseDto();
        response.setName(userRegister.getName());
        response.setMessage("Register success");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequestDto userLogin) throws IllegalAccessException {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                userLogin.getEmail(),
                                userLogin.getPassword()));

        var context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);

        UserAuth userDetails = (UserAuth) authentication.getPrincipal();
        String token = authService.generateToken(authentication);

        LoginResponseDto response = new LoginResponseDto();
        response.setMessage("Login success");
        response.setToken(token);

        Cookie cookie = new Cookie("sid", token);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", cookie.getName() + "=" + cookie.getValue() + "; Path=/; HttpOnly");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}
