package com.purwadhika.montrackv2.services;

import com.purwadhika.montrackv2.entities.Currency;

import java.util.List;

public interface CurrencyService {
    public List<Currency> getCurrencies();
    public Currency getCurrencyDetail();
}
