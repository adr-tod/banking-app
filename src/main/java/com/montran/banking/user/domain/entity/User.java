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
import com.montran.banking.payment.domain.entity.Payment;
import com.montran.banking.userprofile.domain.entity.UserProfile;

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
	private UserProfile profile;
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Account> accounts = new ArrayList<Account>();
	@OneToMany(mappedBy = "createdBy")
	@JsonBackReference
	private List<Payment> paymentsCreated = new ArrayList<Payment>();
	@OneToMany(mappedBy = "verifiedBy")
	@JsonBackReference
	private List<Payment> paymentsVerified = new ArrayList<Payment>();
	@OneToMany(mappedBy = "approvedBy")
	@JsonBackReference
	private List<Payment> paymentsApproved = new ArrayList<Payment>();
	@OneToMany(mappedBy = "authorizedBy")
	@JsonBackReference
	private List<Payment> paymentsAuthorized = new ArrayList<Payment>();
	@OneToMany(mappedBy = "cancelledBy")
	@JsonBackReference
	private List<Payment> paymentsCancelled = new ArrayList<Payment>();

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

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Payment> getPaymentsCreated() {
		return paymentsCreated;
	}

	public void setPaymentsCreated(List<Payment> paymentsCreated) {
		this.paymentsCreated = paymentsCreated;
	}

	public List<Payment> getPaymentsVerified() {
		return paymentsVerified;
	}

	public void setPaymentsVerified(List<Payment> paymentsVerified) {
		this.paymentsVerified = paymentsVerified;
	}

	public List<Payment> getPaymentsApproved() {
		return paymentsApproved;
	}

	public void setPaymentsApproved(List<Payment> paymentsApproved) {
		this.paymentsApproved = paymentsApproved;
	}

	public List<Payment> getPaymentsAuthorized() {
		return paymentsAuthorized;
	}

	public void setPaymentsAuthorized(List<Payment> paymentsAuthorized) {
		this.paymentsAuthorized = paymentsAuthorized;
	}

	public List<Payment> getPaymentsCancelled() {
		return paymentsCancelled;
	}

	public void setPaymentsCancelled(List<Payment> paymentsCancelled) {
		this.paymentsCancelled = paymentsCancelled;
	}
}
