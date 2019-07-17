package com.montran.banking.balance.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montran.banking.balance.domain.entity.Balance;
import com.montran.banking.balance.persistence.BalanceRepository;

@Service
public class BalanceServiceImpl implements BalanceService {

	@Autowired
	private BalanceRepository balanceRepository;

	@Override
	public Iterable<Balance> findAll() {
		return balanceRepository.findAll();
	}

}
