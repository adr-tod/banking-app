package com.montran.banking.account.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.montran.banking.accountstatus.domain.entity.AccountStatus;
import com.montran.banking.balance.domain.entity.Balance;
import com.montran.banking.base.BaseEntity;
import com.montran.banking.currency.domain.entity.Currency;
import com.montran.banking.user.domain.entity.User;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

	private String iban;
	private String name;
	private String address;
	@ManyToOne
	@JoinColumn(name = "currency_id")
	@JsonManagedReference
	private Currency currency;
	@ManyToOne
	@JoinColumn(name = "status_id")
	@JsonManagedReference
	private AccountStatus status;
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private User user;
	@OneToOne
	@JoinColumn(name = "balance_id")
	@JsonManagedReference
	private Balance balance;

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

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
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

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

}
