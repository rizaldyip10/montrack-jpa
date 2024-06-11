package com.purwadhika.montrackv2.repositories;

import com.purwadhika.montrackv2.entities.User;
import com.purwadhika.montrackv2.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByUserId(Long userId);
    Optional<Wallet> findByName(String name);
    Optional<Wallet> findByIsActiveTrueAndUserId(Long userId);
}
