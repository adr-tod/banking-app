package com.montran.banking.balance.business;

import com.montran.banking.balance.domain.entity.Balance;

public interface BalanceService {

	public Iterable<Balance> findAll();
}
