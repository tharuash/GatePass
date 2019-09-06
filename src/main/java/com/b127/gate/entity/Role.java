package com.b127.gate.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Collection<User> roleUsers;
	
	public Role() {
		
	}
	
	public Role(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Collection<User> roleUsers) {
		this.roleUsers = roleUsers;
	}
	
	

}
