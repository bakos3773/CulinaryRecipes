package com.bakos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakos.UserDAO.FriendsDAO;
import com.bakos.UserDTO.Users;
@Service
public class FriendsServiceImpl implements FriendsService {

	@Autowired
	FriendsDAO friendsDAO;
	
	@Override
	public void sendInvitation(int idRecipient){
		
		friendsDAO.sendInvitation(idRecipient);
	}

	@Override
	public List<Users> checkInvitatios() {
		
		return friendsDAO.checkInvitatios();
	}

	@Override
	public void ignoreFriend(int id) {
		
		friendsDAO.ignoreFriend(id);
	}

}
