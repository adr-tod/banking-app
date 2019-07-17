package com.montran.banking.paymentstatus.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.montran.banking.base.BaseEntity;
import com.montran.banking.payment.domain.entity.Payment;

@Entity
@Table(name = "paymentstatuses")
public class PaymentStatus extends BaseEntity {

	private String name;
	@OneToMany(mappedBy = "status")
	@JsonBackReference
	private List<Payment> payments = new ArrayList<Payment>();

	public PaymentStatus() {
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
