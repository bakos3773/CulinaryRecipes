package com.bakos.UserDAO;

import java.util.List;

import com.bakos.UserDTO.Users;

public interface FriendsDAO {
	
	public void sendInvitation(int idRecipient);
	public List<Users> checkInvitatios();
	public void ignoreFriend(int id);
}
