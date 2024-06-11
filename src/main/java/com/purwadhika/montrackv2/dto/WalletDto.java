package com.purwadhika.montrackv2.dto;

import lombok.Data;

@Data
public class WalletDto {
    private String name;
    private Double balance;
    private Long currencyId;
    private Long userId;
}
