package com.montran.banking.currency.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.base.BaseEntity;
import com.montran.banking.payment.domain.entity.Payment;

@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {

	private String name;
	@OneToMany(mappedBy = "currency")
	@JsonBackReference
	private List<Account> accounts = new ArrayList<Account>();
	@OneToMany(mappedBy = "currency")
	@JsonBackReference
	private List<Payment> payments = new ArrayList<Payment>();

	public Currency() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
}
