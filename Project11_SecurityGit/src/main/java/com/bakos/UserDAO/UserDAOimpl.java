package com.bakos.UserDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bakos.UserDTO.Users;

@Repository
@Transactional
public class UserDAOimpl implements UserDAO {

	@PersistenceContext
	private EntityManager manager;

	public void addUser(Users user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		manager.persist(user);
	}

	@Override
	public Users getUserByLoginAndPassword(String login, String Password) {
		Query query = manager
				.createQuery("Select x From Users x WHERE x.login= :login AND password= :password");
		query.setParameter("login", login);
		query.setParameter("password", Password);
		Users user = (Users) query.getSingleResult();
		return user;
	}

	@Override
	public Users getUserById(int id) {
		Query query = manager
				.createQuery("Select x From Users x WHERE x.id= :id");
		query.setParameter("id", id);
		return (Users) query.getSingleResult();
	}

	@Override
	public boolean UserByLoginExist(String login) {

		Query query = manager.createQuery(
				"Select z from Users z where z.login= :arg1", Users.class);
		query.setParameter("arg1", login);

		List results = query.getResultList();

		if (results.isEmpty()) {
			return false;
		} else if (results.size() == 1) {
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Users> getAllUsers() {

		List<Users> query = manager.createQuery("Select e From Users e WHERE e.role= 'ROLE_USER'").getResultList();
		return query;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllUsersLogin() {

		List<Users> query = manager.createQuery("Select e From Users e WHERE e.role= 'ROLE_USER'").getResultList();
		
		List<String> lista = new ArrayList<String>();
		
		for( Users x: query){
			lista.add(x.getLogin());
		}
		return lista;
	}

	@Override
	public Users findUserByUsername() {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String actualUserLogin = auth.getName();

		Query query = manager
				.createQuery("Select DISTINCT z from Users z where z.login= :arg1");
		query.setParameter("arg1", actualUserLogin);
		Users user = (Users) query.getSingleResult();

		return user;
	}

	@Override
	public void deleteUser(int id) {

		Query query = manager
				.createQuery("Select DISTINCT z from Users z where z.id= :arg1");
		query.setParameter("arg1", id);
		Users user = (Users) query.getSingleResult();
		manager.remove(user);

	}

	@Override
	public void changeRole(boolean isTrue) {
		Users user = findUserByUsername();
		final UsernamePasswordAuthenticationToken token;
		if (isTrue) {
			user.setRole("ROLE_SHADOW");
			token = new UsernamePasswordAuthenticationToken(
					user.getLogin(),
					user.getPassword(),
					Arrays.<GrantedAuthority> asList(new SimpleGrantedAuthority(
							"ROLE_SHADOW")));
		} else {
			user.setRole("ROLE_ADMIN");
			token = new UsernamePasswordAuthenticationToken(
					user.getLogin(),
					user.getPassword(),
					Arrays.<GrantedAuthority> asList(new SimpleGrantedAuthority(
							"ROLE_ADMIN")));
		}
		manager.merge(user);

		SecurityContextHolder.getContext().setAuthentication(token);

	}

	@Override
	public Users getUserLogin(String login) {
		Query query = manager.createQuery(
				"Select z from Users z where z.login= :arg1", Users.class);
		Users user = (Users) query.setParameter("arg1", login).getSingleResult();
		
		return user;
	}

}
