package com.montran.banking.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montran.banking.user.business.UserService;
import com.montran.banking.user.domain.dto.UserUpdateDTO;
import com.montran.banking.user.domain.entity.User;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public Iterable<User> findAll() {
		System.out.println("user controller: find all");
		return userService.findAll();
	}

	@PostMapping("update")
	public void update(@RequestBody UserUpdateDTO userUpdateDTO) {
		userService.update(userUpdateDTO);
	}
}
