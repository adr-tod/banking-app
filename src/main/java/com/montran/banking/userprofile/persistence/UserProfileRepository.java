package com.montran.banking.userprofile.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.montran.banking.userprofile.domain.entity.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

	public Optional<UserProfile> findByName(String name);
}
