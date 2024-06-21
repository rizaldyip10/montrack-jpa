package com.purwadhika.montrackv2.currencies.repository;

import com.purwadhika.montrackv2.currencies.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer>  {
}
