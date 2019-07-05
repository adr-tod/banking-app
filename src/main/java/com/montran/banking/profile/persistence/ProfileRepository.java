package com.montran.banking.profile.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.montran.banking.profile.domain.entity.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
