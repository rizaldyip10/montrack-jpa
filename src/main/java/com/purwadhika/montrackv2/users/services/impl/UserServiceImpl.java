package com.purwadhika.montrackv2.users.services.impl;

import com.purwadhika.montrackv2.exceptions.DataNotFoundException;
import com.purwadhika.montrackv2.users.entity.User;
import com.purwadhika.montrackv2.users.repositories.UserRepository;
import com.purwadhika.montrackv2.users.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new DataNotFoundException("User not found");
        }
        return user.get();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User createUser(String name, String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            System.out.println("User already exist");
            return null;
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User updateUser() {
        return null;
    }

    @Override
    public String deleteUser() {
        return null;
    }
}
