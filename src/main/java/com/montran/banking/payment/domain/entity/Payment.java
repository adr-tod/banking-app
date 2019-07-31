package com.montran.banking.payment.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.base.BaseEntity;
import com.montran.banking.currency.domain.entity.Currency;
import com.montran.banking.paymentstatus.domain.entity.PaymentStatus;
import com.montran.banking.user.domain.entity.User;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "debit_account_id")
	@JsonManagedReference
	private Account debitAccount;
	@ManyToOne
	@JoinColumn(name = "credit_account_id")
	@JsonManagedReference
	private Account creditAccount;
	private LocalDateTime dateTime;
	private Double amount;
	@ManyToOne
	@JoinColumn(name = "currency_id")
	@JsonManagedReference
	private Currency currency;
	@ManyToOne
	@JoinColumn(name = "payment_status_id")
	@JsonManagedReference
	private PaymentStatus status;
	@ManyToOne
	@JoinColumn(name = "created_by")
	@JsonManagedReference
	private User createdBy;
	@ManyToOne
	@JoinColumn(name = "verified_by")
	@JsonManagedReference
	private User verifiedBy;
	@ManyToOne
	@JoinColumn(name = "approved_by")
	@JsonManagedReference
	private User approvedBy;
	@ManyToOne
	@JoinColumn(name = "authorized_by")
	@JsonManagedReference
	private User authorizedBy;
	@ManyToOne
	@JoinColumn(name = "cancelled_by")
	@JsonManagedReference
	private User cancelledBy;

	public Payment() {
		super();
	}

	public Account getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(Account debitAccount) {
		this.debitAccount = debitAccount;
	}

	public Account getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(Account creditAccount) {
		this.creditAccount = creditAccount;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(User verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public User getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(User authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public User getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(User cancelledBy) {
		this.cancelledBy = cancelledBy;
	}
}
