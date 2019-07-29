package com.montran.banking.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montran.banking.user.business.UserService;
import com.montran.banking.user.domain.dto.UserSaveDTO;
import com.montran.banking.user.domain.dto.UserUpdateDTO;
import com.montran.banking.user.domain.entity.User;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public Iterable<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("find/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PostMapping("add")
	public void add(@RequestBody UserSaveDTO userSaveDTO) {
		userService.save(userSaveDTO);
	}

	@PostMapping("update")
	public void update(@RequestBody UserUpdateDTO userUpdateDTO) {
		userService.update(userUpdateDTO);
	}

	@PostMapping("delete/{id}")
	public void remove(@PathVariable Long id) {
		userService.deleteById(id);
	}
}
