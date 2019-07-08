package com.montran.banking.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montran.banking.profile.business.ProfileService;
import com.montran.banking.profile.domain.entity.Profile;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping
	public Iterable<Profile> findAll() {
		return profileService.findAll();
	}
}
