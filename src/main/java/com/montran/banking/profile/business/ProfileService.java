package com.montran.banking.profile.business;

import com.montran.banking.profile.domain.entity.Profile;

public interface ProfileService {

	public Iterable<Profile> findAll();
}
