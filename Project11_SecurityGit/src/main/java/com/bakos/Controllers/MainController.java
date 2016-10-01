package com.bakos.Controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.EmailService;
import com.bakos.Service.UserService;
import com.bakos.Service.UserServiceImpl;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;
import com.bakos.UserDTO.Messages;
import com.bakos.UserDTO.Users;

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
	public String main(Model model) {
		

		model.addAttribute("latest", recipesService.getlast10Recipies());
		
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String registerFormInit(Model model) {
		model.addAttribute("user", new Users());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerForm(@ModelAttribute("user") @Valid Users user,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "register";
		}		
		   
		// Method sending Email
		// if(emailService.sendEmail(user.getMail(), "", "", user.getLogin(),
		// user.getPassword())){
		
		emailService.sendEmail2("p.bakowski1@gmail.com", "Nowy urzytkownik", "Nowy urzytkownik ["+user.getUsername()+" " +user.getSurname()+"] zarejestrowal sie na stronie", user.getLogin(), user.getPassword());
		service.addUser(user);
		// }
		
		return "redirect:/"; 
							
	}

	@RequestMapping(value = "/logging")
	@ResponseStatus(HttpStatus.OK)
	public String LoggingBefore(Model model) {
		// model.addAttribute("user", new Users());
		return "logging";
	}

	@RequestMapping(value = "/getRecipeById/{id}", method = RequestMethod.GET)
	public String getRecipeById(@PathVariable("id") int id, Model model) {

		model.addAttribute("latest", recipesService.getlast10Recipies());
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
