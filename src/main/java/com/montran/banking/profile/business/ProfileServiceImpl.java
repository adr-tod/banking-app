package com.montran.banking.profile.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montran.banking.profile.domain.entity.Profile;
import com.montran.banking.profile.persistence.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public Iterable<Profile> findAll() {
		return profileRepository.findAll();
	}
}
