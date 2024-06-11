package com.purwadhika.montrackv2.controllers;

import com.purwadhika.montrackv2.dto.WalletDto;
import com.purwadhika.montrackv2.entities.Wallet;
import com.purwadhika.montrackv2.services.WalletService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
@Log
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{userId}")
    public List<Wallet> getUserWallets(@PathVariable("userId") Long userId) {
        return walletService.getUserWallets(userId);
    }

    @GetMapping("{userId}/{walletId}")
    public Wallet getUserWalletDetail(@PathVariable("userId") Long userId, @PathVariable("walletId") Long walletId) {
        return walletService.getUserWalletDetail(userId, walletId);
    }

    @PostMapping("/{userId}")
    public Wallet createNewWaller(@PathVariable("userId") Long userId, @RequestBody WalletDto newWalletDto) {
        return walletService.createWallet(newWalletDto.getName(), newWalletDto.getBalance(), newWalletDto.getCurrencyId(), userId);
    }

    @PutMapping("/{userId}/{walletId}")
    public Wallet updateWallet(@PathVariable("userId") Long userId, @PathVariable("walletId") Long walletId, @RequestBody WalletDto updatedWallet) {
        return walletService.updateWallet(userId, walletId, updatedWallet.getName(), updatedWallet.getBalance());
    }

    @PatchMapping("/{userId}/{walletId}")
    private String setActiveWallet(@PathVariable("userId") Long userId, @PathVariable("walletId") Long walletId) {
        return walletService.selectActiveWallet(walletId, userId);
    }
}
