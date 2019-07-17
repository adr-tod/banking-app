package com.montran.banking.profile.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.montran.banking.base.BaseEntity;
import com.montran.banking.user.domain.entity.User;

@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

	private String name;
	@OneToMany(mappedBy = "profile")
	@JsonBackReference
	private List<User> users = new ArrayList<User>();

	public Profile() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
