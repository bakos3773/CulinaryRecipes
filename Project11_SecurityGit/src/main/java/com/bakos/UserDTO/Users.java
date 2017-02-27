package com.bakos.UserDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.bakos.annotation.UniqueUsername;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Users")
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Size(min=4)
	@Column(unique=true)
	@UniqueUsername(message="Such user alredy exists!")
	private String login;
	@NotBlank
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String username;
	@NotBlank
	@Column(nullable = false)
	private String surname;
	@NotBlank
	@Column(nullable = false)
	private String address;
	@NotBlank
	@Email
	@Column(nullable = false)
	private String mail;
	private String role;	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Collection<CulinaryRecipes> culinaryRecipes = new ArrayList<CulinaryRecipes>();	
	@ManyToMany
	@JoinTable(name="MESSAGES_AND_USERS",
	joinColumns=@JoinColumn(name="MESSAGES_ID"),
	inverseJoinColumns=@JoinColumn(name="USERS_ID"))
	List<Messages> messages;	
	@ManyToMany
	@JoinTable(name="USER_INVITATIONS",
	joinColumns=@JoinColumn(name="USER_ID"),
	inverseJoinColumns=@JoinColumn(name="INVITATIONS_ID"))
	List<Invitations> invitations;
	@OneToMany(mappedBy="user")
	List<Friends> friends;

	public Users() {

	}
	
	

	public Users(int id, String login, String password, String username,
			String surname, String address, String mail, String role) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.username = username;
		this.surname = surname;
		this.address = address;
		this.mail = mail;
		this.role = role;
	}



	public Users(String login, String password, String username,
			String surname, String address, String mail, String role) {
		this.login = login;
		this.password = password;
		this.username = username;
		this.surname = surname;
		this.address = address;
		this.mail = mail;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collection<CulinaryRecipes> getCulinaryRecipes() {
		return culinaryRecipes;
	}

	public void setCulinaryRecipes(Collection<CulinaryRecipes> culinaryRecipes) {
		this.culinaryRecipes = culinaryRecipes;
	}

	public List<Invitations> getInvitations() {
		return invitations;
	}

	public void setInvitations(List<Invitations> invitations) {
		this.invitations = invitations;
	}
	

}
