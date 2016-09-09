package com.bakos.UserDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Forum_Messages {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String message;
	private String date;
	private String author;
	@ManyToOne
	@JoinColumn(name="forumTopics_id")
	private Forum_Themes forumTopics;
	
	public Forum_Messages(){
		
	}

	public Forum_Messages(String message, String date, String author) {
		this.message = message;
		this.date = date;
		this.author = author;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Forum_Themes getForumTopics() {
		return forumTopics;
	}

	public void setForumTopics(Forum_Themes forumTopics) {
		this.forumTopics = forumTopics;
	}
	
}
