package com.bakos.UserDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bakos.Service.UserService;
import com.bakos.UserDTO.Invitations;
import com.bakos.UserDTO.Users;

@Repository
@Transactional
public class FriendsDAOimpl implements FriendsDAO {
	
	@Autowired
	UserService userService;
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void sendInvitation(int idRecipient) {		
		
		Users user = userService.findUserByUsername();		
		
		Invitations invitation = new Invitations(false, user.getId(), idRecipient, new Date());
		user.getInvitations().add(invitation);	
		
		manager.persist(invitation);
		manager.merge(user);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> checkInvitatios() {
		
		Users user = userService.findUserByUsername();
		
		Query query = manager.createNamedQuery("Friends.checkInvitatios");
		query.setParameter("id", user.getId());		
		List<Integer> listId = query.getResultList();
		
		List<Users> listUsers = new ArrayList<Users>();
		
		for( int id: listId ){
			Query query2 = manager.createNamedQuery("Friends.findUserById");
			Users dupa = (Users) query2.setParameter("id", id).getSingleResult();
			listUsers.add(dupa);
		}
		
		return listUsers;
	}

	@Override
	public void ignoreFriend(int id) {
		Query query = manager.createNamedQuery("Friends.ignoreFriend");
		query.setParameter("id", id);
		query.setParameter("idRecipient", userService.findUserByUsername().getId());
		query.executeUpdate();
	}

}
