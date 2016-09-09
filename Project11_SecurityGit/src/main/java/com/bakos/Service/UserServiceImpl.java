package com.bakos.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakos.UserDAO.UserDAO;
import com.bakos.UserDTO.Users;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDAO;

	public void addUser(Users user) {
		userDAO.addUser(user);
		logger.info("User ID:" + user.getId() + ", Login: " + user.getLogin()
				+ " created");
	}

	@Override
	public Users getUserByLoginAndPassword(String login, String Password) {
		logger.info("Calling a method: getUserByLoginAndPassword");

		return userDAO.getUserByLoginAndPassword(login, Password);
	}

	@Override
	public Users getUserById(int id) {

		logger.info("Calling a method: getUserById");

		return userDAO.getUserById(id);
	}

	@Override
	public boolean UserByLoginExist(String login) {
		logger.info("Calling a method: UserByLoginExist");
		return userDAO.UserByLoginExist(login);
	}

	@Override
	public List<Users> getAllUsers() {

		logger.info("Calling a method: getAllUsers");
		return userDAO.getAllUsers();
	}

	@Override
	public Users findUserByUsername() {
		logger.info("Calling a method: findUserByUsername");
		return userDAO.findUserByUsername();
	}

	@Override
	public void deleteUser(int id) {
		logger.info("Calling a method: deleteUser");
		userDAO.deleteUser(id);
	}

	@Override
	public void changeRole(boolean isTrue) {
		logger.info("Calling a method: changeRole");
		userDAO.changeRole(isTrue);
	}

	@Override
	public List<String> getAllUsersLogin() {
		return userDAO.getAllUsersLogin();
	}

	@Override
	public Users getUserLogin(String login) {		
		return userDAO.getUserLogin(login);
	}

}
