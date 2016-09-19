package com.bakos.UserDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Friends.checkInvitatios", query = "SELECT x.idSender FROM Invitations x WHERE x.idRecipient= :id"),
	@NamedQuery(name="Friends.findUserById", query="SELECT x FROM Users x WHERE x.id= :id"),
	@NamedQuery(name="Friends.ignoreFriend", query="DELETE FROM Invitations x WHERE x.idSender= :id AND x.idRecipient= :idRecipient")
})
public class Friends {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String login;
	private String mail;
	@ManyToOne
	@JoinColumn(name="user_id")
	Users user;
	
	
	public Friends() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Friends(String login, String mail) {
		super();
		this.login = login;
		this.mail = mail;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
