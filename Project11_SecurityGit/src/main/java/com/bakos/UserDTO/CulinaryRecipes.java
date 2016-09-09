package com.bakos.UserDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CulinaryRecipes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String type;
	private String name;
	@Lob
	private String components;
	@Lob
	private String howToPerform;
	@JsonIgnore	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private Users user;
	private String isPrivateRecipe;
	
	@OneToMany(mappedBy="culinaryRecipes")
	@JsonIgnore
	private List<Statistics> statistics = new ArrayList<Statistics>();


	public CulinaryRecipes() {

	}

	public CulinaryRecipes(String type, String name, String components,
			String howToPerform, String isPrivateRecipe) {
		this.type = type;
		this.name = name;
		this.components = components;
		this.howToPerform = howToPerform;
		this.isPrivateRecipe = isPrivateRecipe;
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

	public String getComponents() {
		return components;
	}

	public void setComponents(String components) {
		this.components = components;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHowToPerform() {
		return howToPerform;
	}

	public void setHowToPerform(String howToPerform) {
		this.howToPerform = howToPerform;
	}

	public String getIsPrivateRecipe() {
		return isPrivateRecipe;
	}

	public void setIsPrivateRecipe(String isPrivateRecipe) {
		this.isPrivateRecipe = isPrivateRecipe;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Statistics> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistics> statistics) {
		this.statistics = statistics;
	}


}
