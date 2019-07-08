package com.montran.banking.account.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.montran.banking.accountstatus.domain.entity.AccountStatus;
import com.montran.banking.base.BaseEntity;
import com.montran.banking.user.domain.entity.User;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

	private String iban;
	private String name;
	private String address;
	@Transient
	private final String currency = "EUR";
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private AccountStatus status;
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Account() {
		super();
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCurrency() {
		return currency;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}