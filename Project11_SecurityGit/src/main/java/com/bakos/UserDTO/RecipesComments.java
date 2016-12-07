package com.bakos.UserDTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RecipesComments implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Lob
	private String comment;
	
	private String nameCommentator;
	
	private int idCommentator;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipe_id")
	@JsonIgnore
	private CulinaryRecipes culinaryRecipes;	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CulinaryRecipes getCulinaryRecipes() {
		return culinaryRecipes;
	}

	public void setCulinaryRecipes(CulinaryRecipes culinaryRecipes) {
		this.culinaryRecipes = culinaryRecipes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNameCommentator() {
		return nameCommentator;
	}

	public void setNameCommentator(String nameCommentator) {
		this.nameCommentator = nameCommentator;
	}

	public int getIdCommentator() {
		return idCommentator;
	}

	public void setIdCommentator(int idCommentator) {
		this.idCommentator = idCommentator;
	}


}
