package com.bakos.UserDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@XmlRootElement
public class CulinaryRecipes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String type;
	@NotBlank(message="{xxx.validation}")
	private String name;
	@Lob
	@NotBlank(message="{xxx.validation}")
	private String components;
	@Lob
	@NotBlank(message="{xxx.validation}")
	private String howToPerform;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="HH:mm:ss dd/MM/yyyy")
	private Date date;		
	@JsonIgnore	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private Users user;
	private String isPrivateRecipe;
	
	@OneToMany(mappedBy="culinaryRecipes",fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JsonIgnore
	private List<Statistics> statistics = new ArrayList<Statistics>();
	
	@OneToMany(mappedBy="culinaryRecipes", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<RecipesComments> recipeComments = new ArrayList<RecipesComments>();


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
	@XmlTransient
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	@XmlTransient
	public List<Statistics> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistics> statistics) {
		this.statistics = statistics;
	}

	public List<RecipesComments> getRecipeComments() {
		return recipeComments;
	}

	public void setRecipeComments(List<RecipesComments> recipeComments) {
		this.recipeComments = recipeComments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
