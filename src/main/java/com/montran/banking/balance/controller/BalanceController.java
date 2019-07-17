package com.montran.banking.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montran.banking.balance.business.BalanceService;
import com.montran.banking.balance.domain.entity.Balance;

@RestController
@RequestMapping("balance")
@CrossOrigin(origins = "http://localhost:4200")
public class BalanceController {

	@Autowired
	private BalanceService balanceService;

	@GetMapping
	public Iterable<Balance> findAll() {
		System.out.println("balance controller: find all");
		return balanceService.findAll();
	}
}
