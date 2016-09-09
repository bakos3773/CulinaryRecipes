package com.bakos.UserDTO;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Messages {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long recipientId;
	private long senderId;
	private String text;
	
	@ManyToMany(mappedBy="messages")
	List<Users> users;
	
	public Messages() {
		// TODO Auto-generated constructor stub
	}


	public Messages(long recipientId, long senderId, String text) {
		super();
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.text = text;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getRecipientId() {
		return recipientId;
	}


	public void setRecipientId(long recipientId) {
		this.recipientId = recipientId;
	}


	public long getSenderId() {
		return senderId;
	}


	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
}
