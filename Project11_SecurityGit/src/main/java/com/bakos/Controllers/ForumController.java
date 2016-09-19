package com.bakos.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bakos.Service.ForumService;
import com.bakos.UserDTO.Forum;
import com.bakos.UserDTO.Forum_Messages;
import com.bakos.UserDTO.Forum_Themes;

@Controller
public class ForumController {
	
	@Autowired
	ForumService forumService;
	
	@RequestMapping(value="/user/forum", method=RequestMethod.GET)
	public String forum(Model model){
		
		model.addAttribute("forum", new Forum());
		
		return "forumSections";
	}

	@RequestMapping(value="/user/forum/setSection", method=RequestMethod.GET)
	public String setSectionBefore( Model model ){
		
		model.addAttribute("forum", new Forum());
		
		return "forumSections";
	}
	
	@RequestMapping(value="/user/forum/setSection", method=RequestMethod.POST)
	public String setSectionPost(@ModelAttribute("forum")Forum forum){
		
		forumService.setSections(forum);
		
		return "redirect:/user/forum";
	}
	
	@RequestMapping(value="user/forum/getAllSections", method=RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	@ResponseBody
	public List<Forum> getAllSections(){
		
		return forumService.getAllSections();
	}	
	
	@RequestMapping(value="user/forum/section/{id_section}", method=RequestMethod.GET)
	public String showTopicBefore(Model model, @PathVariable("id_section")int id){
		
		model.addAttribute("forum", new Forum());
		model.addAttribute("forumThemes", new Forum_Themes());
		model.addAttribute("allTopics", forumService.getAllForum_Themes(id));
		model.addAttribute("id_section", id);
		
		
		
		return "forumAllTopics";
	}
	
	@RequestMapping(value="user/forum/section/{id_section}", method=RequestMethod.POST)
	public String setTopicPost(@ModelAttribute("forumThemes")Forum_Themes forumThemes, BindingResult result, Model model, @PathVariable("id_section")int id){
		
			
		forumService.setForumTopics(forumThemes, id);
		model.addAttribute("forum", new Forum());
		model.addAttribute("allTopics", forumService.getAllForum_Themes(id));
		model.addAttribute("id_section", id);
		
		return "redirect:/user/forum/section/"+id;
	}

	@RequestMapping(value="user/forum/section/{id_section}/theme/{id}", method=RequestMethod.GET)
	public String responseFromTheTopic(@PathVariable("id_section")int id_section, @PathVariable("id")int id_theme, Model model){
		
		

		model.addAttribute("forum", new Forum());
		model.addAttribute("formThemesAnswer", new Forum_Messages());
		model.addAttribute("id_section", id_section);
		model.addAttribute("forumTheme", forumService.getForumThemeById(id_theme));
		
		return "responseTopic";
	}
	
	@RequestMapping(value="user/forum/section/{id_section}/theme/{id}/setMessage", method=RequestMethod.POST)
	public String responseFromTheTopicPost(@ModelAttribute("formThemesAnswer")Forum_Messages forumMessages, BindingResult result, @PathVariable("id_section")int id_section, @PathVariable("id")int id, Model model){
				
		model.addAttribute("forum", new Forum());
		forumService.setTopicAnswer(forumMessages, id);
		
		return "redirect:/user/forum/section/"+id_section+"/theme/"+id;
	}
	//"user/forum/section/{id_section}/theme/{id}/allTopicAnswer"
	@RequestMapping(value="user/forum/section/{id_section}/theme/{id}", method=RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	@ResponseBody
	public List<Forum_Messages> getAllAnswerOfTheme(@PathVariable("id_section")int idSection, @PathVariable("id")int idTopic){
	
		return forumService.getAllAnswerOfTheme(idSection, idTopic);
	}		
}
