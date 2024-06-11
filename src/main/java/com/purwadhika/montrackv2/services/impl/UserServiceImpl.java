package com.purwadhika.montrackv2.services.impl;

import com.purwadhika.montrackv2.entities.User;
import com.purwadhika.montrackv2.repositories.UserRepository;
import com.purwadhika.montrackv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User createUser(String name, String email, String password, String pin) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            System.out.println("User already exist");
            return null;
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setPin(pin);
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
