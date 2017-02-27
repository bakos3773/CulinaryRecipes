package com.bakos.Controllers;

import java.io.FileNotFoundException;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.EmailService;
import com.bakos.Service.UserService;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.Messages;
import com.bakos.UserDTO.Users;
import com.bakos.pdf.SaveSelectedRecesice;
import com.itextpdf.text.DocumentException;

@Controller
public class MainController {

	@Autowired
	UserService service;

	@Autowired
	EmailService emailService;
	

	@Autowired
	CulinaryRecipesService recipesService;
	
	

	private static final Logger logger = LoggerFactory
			.getLogger(MainController.class);


	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String main(Model model) throws FileNotFoundException, DocumentException {
		
		model.addAttribute("latest", recipesService.getlast10Recipies());			
		model.addAttribute("popular", recipesService.getMostPopularRecipies());
		
//		SaveSelectedRecesice savePdf = new SaveSelectedRecesice();
		
		return "index";
	}
	
	@GetMapping(value="/contact")
	@ResponseStatus(HttpStatus.OK)
	public String contact() {
		return "contact";
	}	

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String registerFormInit(Model model) {
		model.addAttribute("user", new Users());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerForm(@Valid Users user,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "register";
		}		
		   
		// Method sending Email
		// if(emailService.sendEmail(user.getMail(), "", "", user.getLogin(),
		// user.getPassword())){
		
		emailService.sendEmail2("${mailserver.username}", "Nowy urzytkownik", "Nowy urzytkownik ["+user.getUsername()+" " +user.getSurname()+"] zarejestrowal sie na stronie", user.getLogin(), user.getPassword());
		service.addUser(user);
		// }
		
		return "redirect:/"; 
							
	}

	@RequestMapping(value = "/logging")
	@ResponseStatus(HttpStatus.OK)
	public String LoggingBefore(Model model) {
		
		return "logging";
	}

	@RequestMapping(value = "/getRecipeById/{id}", method = RequestMethod.GET)
	public String getRecipeById(@PathVariable("id") int id, Model model) {

		model.addAttribute("latest", recipesService.getlast10Recipies());
		model.addAttribute("popular", recipesService.getMostPopularRecipies());
		model.addAttribute("recipe", recipesService.getRecipieById(id));

		return "mainPageShowRecipe";
	}

	@ModelAttribute("message")
	public Messages constructMessage() {
		return new Messages();
	}
	
	
	
//**************************************************************************************************	
	@RequestMapping(value="/getlast10Recipies", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)	
	public @ResponseBody List<CulinaryRecipes> getCulinaryRecipesToHtml(){
		
		logger.info("WYWOLANIE METODY: getlast10Recipies()");
		
		return recipesService.getlast10Recipies();
	}	

}
