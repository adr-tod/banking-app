package com.montran.banking.profile.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.montran.banking.base.BaseEntity;

@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

	private String name;

	public Profile() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
