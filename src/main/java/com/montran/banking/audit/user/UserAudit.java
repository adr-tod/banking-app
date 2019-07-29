package com.montran.banking.audit.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.montran.banking.base.BaseAudit;

@Entity
@Table(name = "users_audit")
public class UserAudit extends BaseAudit {

	public UserAudit(String operation, String userBy, String message) {
		this.timestamp = LocalDateTime.now();
		this.operation = operation;
		this.userBy = userBy;
		this.message = message;
	}

}
