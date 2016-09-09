package com.bakos.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.UserService;
import com.bakos.UserDTO.Articles;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService service;
	
	@Autowired
	CulinaryRecipesService recipesService;

	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public String allUsers(Model model) {

		model.addAttribute("users", service.getAllUsers());

		return "allUsers";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id, Model model) {

		service.deleteUser(id);

		return "allUsers";
	}

	@RequestMapping(value = "/changeRole/{isTrue}", method = RequestMethod.GET)
	public String checkedTypes(@PathVariable("isTrue") boolean isTrue,
			Model model) {

		service.changeRole(isTrue);
		model.addAttribute("user", service.findUserByUsername());
		model.addAttribute("article", new Articles());

		return "adminSettings";
	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String adminSettings(Model model) {

		model.addAttribute("user", service.findUserByUsername());
		model.addAttribute("article", new Articles());
		return "adminSettings";
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.POST)
	public String adminSettingsPost( @ModelAttribute("article")Articles article ) {

		article.setDate(new Date());
		
		recipesService.addArticle(article);
		return "adminSettings";
	}	
	
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public String articles( Model model) {

		model.addAttribute("y", recipesService.getAllArticles() );
		return "setArticle";
	}
	
	@RequestMapping(value = "/allArticles", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public @ResponseBody List<Articles> articlesAll( Model model) {
		
		List<Articles> to = recipesService.getAllArticles();
		return to;
	}
	
	@RequestMapping(value="/articles/remove/{id}", method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable("id") int id) {

		recipesService.removeArticle(id);
	}
}
