package com.b127.gate.entity;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passes")
public class Pass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "issued_time")
	private Timestamp issuedTime;

	@Column(name = "is_accepted")
	private boolean isAccepted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Timestamp issuedTime) {
		this.issuedTime = issuedTime;
	}

	public boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

}
