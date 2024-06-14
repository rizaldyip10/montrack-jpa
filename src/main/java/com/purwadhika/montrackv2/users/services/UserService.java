package com.purwadhika.montrackv2.users.services;

import com.purwadhika.montrackv2.users.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getUserById(Long id);
    User createUser(String name,
                           String email,
                           String password);
    User updateUser();
    String deleteUser();
}
