package com.purwadhika.montrackv2.users.service;

import com.purwadhika.montrackv2.auth.dto.RegisterRequestDto;
import com.purwadhika.montrackv2.users.entity.User;

import java.util.List;

public interface UserService {
    User register(RegisterRequestDto user);

    User findByEmail(String email);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    User profile();
}