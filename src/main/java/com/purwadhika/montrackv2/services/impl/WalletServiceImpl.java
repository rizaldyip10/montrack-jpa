package com.purwadhika.montrackv2.services.impl;

import com.purwadhika.montrackv2.entities.Currency;
import com.purwadhika.montrackv2.entities.User;
import com.purwadhika.montrackv2.entities.Wallet;
import com.purwadhika.montrackv2.repositories.CurrencyRepository;
import com.purwadhika.montrackv2.repositories.UserRepository;
import com.purwadhika.montrackv2.repositories.WalletRepository;
import com.purwadhika.montrackv2.services.WalletService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Wallet> getUserWallets(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            System.out.println("User not found");
            return null;
        }
        return walletRepository.findByUserId(user.get().getId());
    }

    @Override
    public Wallet getUserWalletDetail(Long userId, Long id) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            System.out.println("User not found");
            return null;
        }
        Optional<Wallet> currWallet = walletRepository.findById(id);
        if (currWallet.isEmpty()) {
            System.out.println("Wallet not found");
            return null;
        }
        if (!Objects.equals(currWallet.get().getUser().getId(), userId)) {
            System.out.println("Error 403: This is not your wallet");
        }

        return currWallet.get();
    }

    @Override
    public Wallet createWallet(String name, Double balance, Long currencyId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Currency> currency = currencyRepository.findById(currencyId);
        if (currency.isEmpty()) {
            System.out.println("Currency not found");
            return null;
        }
        if (user.isEmpty()) {
            System.out.println("User not found");
            return null;
        }
        List<Wallet> wallets = walletRepository.findByUserId(user.get().getId());
        boolean hasActiveWallet = wallets.stream().anyMatch(Wallet::getIsActive);

        Wallet newWallet = new Wallet();
        newWallet.setName(name);
        newWallet.setBalance(balance);
        newWallet.setCurrency(currency.get());
        newWallet.setUser(user.get());
        newWallet.setIsActive(!hasActiveWallet);
        walletRepository.save(newWallet);
        return newWallet;
    }

    @Override
    public Wallet updateWallet(Long userId, Long id, String name, Double balance) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            System.out.println("User not found");
            return null;
        }
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (wallet.isEmpty()) {
            System.out.println("Wallet not found");
            return null;
        }
        if (!Objects.equals(wallet.get().getId(), user.get().getId())) {
            System.out.println("Error 403: This is not your wallet");
        }
        if (name != null) {
            wallet.get().setName(name);
        }
        if (balance != null) {
            wallet.get().setBalance(balance);
        }
        walletRepository.save(wallet.get());
        return wallet.get();
    }

    @Override
    public String deleteWallet() {
        return null;
    }

    @Override
    public String selectActiveWallet(Long id, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            System.out.println("User not found");
            return "User not found";
        }
        Optional<Wallet> wallet = walletRepository.findById(id);
        Optional<Wallet> activeWallet = walletRepository.findByIsActiveTrueAndUserId(userId);
        if (wallet.isEmpty()) {
            System.out.println("Wallet not found");
            return "Wallet not found";
        }
        if (!Objects.equals(wallet.get().getId(), user.get().getId())) {
            System.out.println("Error 403: This is not your wallet");
        }
        activeWallet.ifPresent(value -> value.setIsActive(false));
        wallet.get().setIsActive(true);
        walletRepository.save(activeWallet.get());
        walletRepository.save(wallet.get());
        return "Successfully set wallet " + wallet.get().getId() + " as active wallet";
    }

    @Override
    public Wallet incomeTrx() {
        return null;
    }

    @Override
    public Wallet expenseTrx() {
        return null;
    }


}
