package com.purwadhika.montrackv2.trx.service;

import com.purwadhika.montrackv2.trx.dto.TransactionDetailResponse;

public interface TrxService {
    TransactionDetailResponse getTransactionDetail(Long id);
}
