package com.purwadhika.montrackv2.controllers;

import com.purwadhika.montrackv2.dto.UserRequestDto;
import com.purwadhika.montrackv2.entities.User;
import com.purwadhika.montrackv2.services.UserService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@Log
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody UserRequestDto newUser) {
        return userService.createUser(newUser.getName(), newUser.getEmail(), newUser.getPassword(), newUser.getPin());
    }
}
