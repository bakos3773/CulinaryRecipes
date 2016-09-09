package com.bakos.UserDTO;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Forum_Themes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String themes;
	private int numberOfAnswers;
	private String author;
	private int numberOfViews;
	private String lastPost;
	@OneToMany(mappedBy="forumTopics")
	@JsonIgnore
	private List<Forum_Messages> forumMessages;
	@ManyToOne
	@JoinColumn(name="forum_id")
	private Forum forum;
	
	public Forum_Themes(){
		
	}

	public Forum_Themes(String themes, int answers, String author,
			int numberOfViews, String lastPost) {
		super();
		this.themes = themes;
		this.numberOfAnswers = answers;
		this.author = author;
		this.numberOfViews = numberOfViews;
		this.lastPost = lastPost;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getThemes() {
		return themes;
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getNumberOfAnswers() {
		return numberOfAnswers;
	}

	public void setNumberOfAnswers(int numberOfAnswers) {
		this.numberOfAnswers = numberOfAnswers;
	}

	public int getNumberOfViews() {
		return numberOfViews;
	}

	public void setNumberOfViews(int numberOfViews) {
		this.numberOfViews = numberOfViews;
	}

	public String getLastPost() {
		return lastPost;
	}

	public void setLastPost(String lastPost) {
		this.lastPost = lastPost;
	}

	public List<Forum_Messages> getForumMessages() {
		return forumMessages;
	}

	public void setForumMessages(List<Forum_Messages> forumMessages) {
		this.forumMessages = forumMessages;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	
	
}
