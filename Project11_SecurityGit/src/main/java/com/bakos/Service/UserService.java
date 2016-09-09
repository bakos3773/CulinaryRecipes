package com.bakos.Service;

import java.util.List;

import com.bakos.UserDTO.Users;

public interface UserService {

	public void addUser(Users user);

	public Users getUserByLoginAndPassword(String login, String Password);

	public Users getUserById(int id);

	public boolean UserByLoginExist(String login);
	
	public Users getUserLogin(String login);

	public List<Users> getAllUsers();
	
	public List<String> getAllUsersLogin();

	public Users findUserByUsername();

	public void deleteUser(int id);

	public void changeRole(boolean isTrue);
}
