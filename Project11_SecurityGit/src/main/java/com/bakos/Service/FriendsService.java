package com.bakos.Service;

import java.util.List;

import com.bakos.UserDTO.Users;

public interface FriendsService {
	
	public void sendInvitation(int idRecipient);
	public List<Users> checkInvitatios();
	public void ignoreFriend(int id);
	
}
