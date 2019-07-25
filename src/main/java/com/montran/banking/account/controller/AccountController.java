package com.montran.banking.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montran.banking.account.business.AccountService;
import com.montran.banking.account.domain.dto.AccountCreateDTO;
import com.montran.banking.account.domain.entity.Account;

@RestController
@RequestMapping("account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public Iterable<Account> findAll() {
		return accountService.findAll();
	}

	@GetMapping("findall/{id}")
	public Iterable<Account> findAllByUserId(@PathVariable Long id) {
		return accountService.findAllByUserId(id);
	}

	@PostMapping("create")
	public Account create(@RequestBody AccountCreateDTO accountCreateDTO) {
		return accountService.create(accountCreateDTO);
	}
}
