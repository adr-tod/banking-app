package com.montran.banking.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montran.banking.user.business.UserService;
import com.montran.banking.user.domain.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public Iterable<User> getAll() {
		return userService.findAll();
	}
}
