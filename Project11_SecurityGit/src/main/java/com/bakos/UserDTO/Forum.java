package com.bakos.UserDTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Forum implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String sections;
	private int howManyThemes;
	private int howManyPosts;
	private String lastPost;
	@OneToMany(mappedBy="forum")
	@JsonIgnore
	private List<Forum_Themes> forumThemes;

	public Forum(){
		
	}

	public Forum(String sections, int howManyThemes, int howManyPosts,
			String lastPost, List<Forum_Themes> forumThemes) {
		this.sections = sections;
		this.howManyThemes = howManyThemes;
		this.howManyPosts = howManyPosts;
		this.lastPost = lastPost;
		this.forumThemes = forumThemes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSections() {
		return sections;
	}

	public void setSections(String sections) {
		this.sections = sections;
	}

	public int getHowManyThemes() {
		return howManyThemes;
	}

	public void setHowManyThemes(int howManyThemes) {
		this.howManyThemes = howManyThemes;
	}

	public int getHowManyPosts() {
		return howManyPosts;
	}

	public void setHowManyPosts(int howManyPosts) {
		this.howManyPosts = howManyPosts;
	}

	public List<Forum_Themes> getForumThemes() {
		return forumThemes;
	}

	public void setForumThemes(List<Forum_Themes> forumThemes) {
		this.forumThemes = forumThemes;
	}

	public String getLastPost() {
		return lastPost;
	}

	public void setLastPost(String lastPost) {
		this.lastPost = lastPost;
	}
	
	
}
