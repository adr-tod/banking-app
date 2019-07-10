package com.montran.banking.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montran.banking.account.business.AccountService;
import com.montran.banking.account.domain.entity.Account;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public Iterable<Account> findAll() {
		System.out.println("account controller: someone made a request to me!");
		return accountService.findAll();
	}
}
