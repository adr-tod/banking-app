package com.montran.banking.balance.domain.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.base.BaseEntity;

@Entity
@Table(name = "balances")
public class Balance extends BaseEntity {

	@OneToOne(mappedBy = "balance")
	@JsonBackReference
	private Account account;
	private Double available;

	public Balance() {
		super();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Double getAvailable() {
		return available;
	}

	public void setAvailable(Double available) {
		this.available = available;
	}
}
