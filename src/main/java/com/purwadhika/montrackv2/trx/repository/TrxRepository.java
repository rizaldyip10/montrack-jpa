package com.purwadhika.montrackv2.trx.repository;

import com.purwadhika.montrackv2.trx.entity.Trx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrxRepository extends JpaRepository<Trx, Long>{
}
