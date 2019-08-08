package com.montran.banking.accountstatus.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.base.BaseEntity;

@Entity
@Table(name = "accountstatuses")
public class AccountStatus extends BaseEntity {

	private String name;
	@OneToMany(mappedBy = "status")
	@JsonBackReference
	private List<Account> accounts = new ArrayList<>();

	public AccountStatus() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
