package com.montran.banking.accountstatus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montran.banking.accountstatus.business.AccountStatusService;
import com.montran.banking.accountstatus.domain.entity.AccountStatus;

@RestController
@RequestMapping("/accountstatus")
public class AccountStatusController {

	@Autowired
	private AccountStatusService accountStatusService;

	@GetMapping
	public Iterable<AccountStatus> findAll() {
		return accountStatusService.findAll();
	}
}
