package com.purwadhika.montrackv2.wallets.repository;

import com.purwadhika.montrackv2.wallets.entitiy.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
