package com.purwadhika.montrackv2.services;

import com.purwadhika.montrackv2.entities.User;

import java.util.List;

public interface UserService {
    public List<User> getUserList();
    public User getUserById(Long id);
    public User createUser(String name,
                           String email,
                           String password,
                           String pin);
    public User updateUser();
    public String deleteUser();
}
