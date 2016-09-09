package com.bakos.UserDTO;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Statistics {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	private int counter;
	
	@ManyToOne
	@JoinColumn(name="recipe_id")
	private CulinaryRecipes culinaryRecipes;
	
	public Statistics() {
	}

	
	public Statistics(Date date, int counter) {
		this.date = date;
		this.counter = counter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}


	public CulinaryRecipes getCulinaryRecipes() {
		return culinaryRecipes;
	}


	public void setCulinaryRecipes(CulinaryRecipes culinaryRecipes) {
		this.culinaryRecipes = culinaryRecipes;
	}
	
	
}
