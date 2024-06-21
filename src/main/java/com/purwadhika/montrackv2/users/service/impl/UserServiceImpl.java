package com.purwadhika.montrackv2.users.service.impl;

import com.purwadhika.montrackv2.auth.dto.RegisterRequestDto;
import com.purwadhika.montrackv2.auth.helpers.Claims;
import com.purwadhika.montrackv2.currencies.entity.Currency;
import com.purwadhika.montrackv2.currencies.repository.CurrencyRepository;
import com.purwadhika.montrackv2.exceptions.ApplicationException;
import com.purwadhika.montrackv2.exceptions.DataNotFoundException;
import com.purwadhika.montrackv2.users.entity.User;
import com.purwadhika.montrackv2.users.repositories.UserRepository;
import com.purwadhika.montrackv2.users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrencyRepository currencyRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currencyRepo = currencyRepo;
    }

    @Override
    public User register(RegisterRequestDto user) {
        User newUser = user.toEntity();
        Currency currency = currencyRepo.findById(user.getActiveCurrency()).orElseThrow(() -> new DataNotFoundException("Currency not found"));
        currency.setId(user.getActiveCurrency());
        var password = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(password);
        newUser.setCurrency(currency);
        return userRepository.save(newUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ApplicationException("User not found"));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ApplicationException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User profile() {
        var claims = Claims.getClaimsFromJwt();
        var email = (String) claims.get("sub");
        return findByEmail(email);
    }
}