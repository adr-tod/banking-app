package com.montran.banking.user.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.base.BaseEntity;
import com.montran.banking.profile.domain.entity.Profile;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	private String username;
	private String password;
	private String fullname;
	private String address;
	private String email;
	@ManyToOne
	@JoinColumn(name = "profile_id", nullable = false)
	@JsonManagedReference
	private Profile profile;
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Account> accounts = new ArrayList<Account>();

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
