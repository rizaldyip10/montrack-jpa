package com.purwadhika.montrackv2.services;

import com.purwadhika.montrackv2.entities.Wallet;

import java.util.List;

public interface WalletService {
    public List<Wallet> getUserWallets(Long userId);
    public Wallet getUserWalletDetail(Long userId, Long id);
    public Wallet createWallet(String walletName, Double amount, Long currency_id, Long userId);
    public Wallet updateWallet(Long userId, Long id, String name, Double amount);
    public String deleteWallet();

    public String selectActiveWallet(Long id, Long userId);

    public Wallet incomeTrx();
    public Wallet expenseTrx();
}
