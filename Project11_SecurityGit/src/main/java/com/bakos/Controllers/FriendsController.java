package com.bakos.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bakos.Service.ForumService;
import com.bakos.Service.FriendsService;
import com.bakos.Service.UserService;
import com.bakos.UserDTO.Invitations;
import com.bakos.UserDTO.Users;

@Controller
public class FriendsController {
	
	@Autowired
	UserService userService;
	@Autowired
	FriendsService friendsService;
	

	

	@RequestMapping(value="/user/frendsSide/friendsMainSide",  method=RequestMethod.GET)
	public String startController(Model model){
		
		if(!friendsService.checkInvitatios().isEmpty()){
			model.addAttribute("invitationsExist", true);
			model.addAttribute("listOfInvitation", friendsService.checkInvitatios());
		}else{
			model.addAttribute("invitationsExist", false);
		}		

		
		return "friendsSideIndex";
	}
	
	@RequestMapping(value="/user/frendsSide/autocomplete/getAllUsers", method=RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	@ResponseBody
	public List<String> allUsers(){
		
		return userService.getAllUsersLogin();
	}
	
	@RequestMapping(value="/user/frendsSide/autocomplete/autocompleteShowUser/{login}", method=RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public String autocompleteShowUser(@PathVariable("login")String login, Model model){
		
		model.addAttribute("searchingUser", userService.getUserLogin(login));
		
		if(!friendsService.checkInvitatios().isEmpty()){
			model.addAttribute("invitationsExist", true);
			model.addAttribute("listOfInvitation", friendsService.checkInvitatios());
		}else{
			model.addAttribute("invitationsExist", false);
		}		
		
		return "autocompleteShowUser";
	}
	

	@RequestMapping(value="/user/frendsSide/autocomplete/autocompleteShowUser/addUserToFriend/{id}", method=RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public String addUserToFriend(@PathVariable("id")int id, Model model){

		friendsService.sendInvitation(id);
		return "redirect:/user/frendsSide/friendsMainSide";
	}
	
	@RequestMapping(value="/user/frendsSide/ignoreFriend/{id}", method=RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ignoreFriend(@PathVariable("id")int id, Model model){
		
		friendsService.ignoreFriend(id);
	}
}
