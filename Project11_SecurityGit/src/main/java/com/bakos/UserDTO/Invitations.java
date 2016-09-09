package com.bakos.UserDTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Invitations {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private boolean isAccepted;
	private int idSender;
	private int idRecipient;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@ManyToMany(mappedBy="invitations")
	List<Users> users;
	
	
	public Invitations() {
		// TODO Auto-generated constructor stub
	}

	public Invitations(boolean isAccepted, int idSender, int idRecipient,
			Date date) {
		this.isAccepted = isAccepted;
		this.idSender = idSender;
		this.idRecipient = idRecipient;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public int getIdSender() {
		return idSender;
	}

	public void setIdSender(int idSender) {
		this.idSender = idSender;
	}

	public int getIdRecipient() {
		return idRecipient;
	}

	public void setIdRecipient(int idRecipient) {
		this.idRecipient = idRecipient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}


}
