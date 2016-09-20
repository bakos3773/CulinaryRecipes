package com.bakos.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.UserService;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;
import com.bakos.UserDTO.Messages;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;

	@Autowired
	CulinaryRecipesService recipeService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {

		model.addAttribute("recipes", recipeService.getAllRecipies());
		model.addAttribute("filterPattern", new FilterPattern());
		return "home";
	}
	@Secured(value = { "ROLE_USER" })
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(Model model) {

		model.addAttribute("user", service.findUserByUsername());
		return "settings";
	}	
	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String addRecipeBefore(Model model) {

		model.addAttribute("culinaryRecipes", new CulinaryRecipes());

		return "addRecipe";
	}
	
//	@PreAuthorize -  Jeœli u¿ytkownik posiada rolê ROLE_USER oraz dlugosc pola name klasy culinaryRecipes bedzie
//	wieksze(=)5, to wywo³anie metody bêdzie mo¿liwe, oraz jesli u¿ytkownik posiada rolê ROLE_ADMIN. W przeciwnym 
//	wypadku wyrzucony zostanie wyj¹tek zabezpieczeñ i metoda nie zostanie wywo³ana.
	@PreAuthorize("(hasRole('ROLE_USER') and #culinaryRecipes.name.length()>=5) or hasRole('ROLE_ADMIN')" )
	@RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
	public String addRecipeAfter(
			@Valid @ModelAttribute("culinaryRecipes") CulinaryRecipes culinaryRecipes, BindingResult result, HttpServletRequest request,
			Model model) {		

		if(result.hasErrors()){
			return "addRecipe";
		}
		System.out.println("Controller dodawanie przepisu - opis!");
		recipeService.addCulinaryRecipe(culinaryRecipes);
		model.addAttribute("filterPattern", new FilterPattern());

		return "redirect:/user/recipes/upload";
	}

	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/checkedTypes", method = RequestMethod.POST)
	public String checkedTypes(
			@ModelAttribute("filterPattern") FilterPattern filterPattern, RedirectAttributes model) {
//		 RedirectAttributes  - Mo¿emy zatem umieœciæ obiekt Spitter w sesji przed wykonaniem przekierowania,
//		a nastêpnie pobraæ z sesji po jego wykonaniu
//		model.addAttribute("recipes", recipeService.checkedTypes(filterPattern));
		model.addFlashAttribute("recipes", recipeService.checkedTypes(filterPattern));

		return "redirect:/home";
	}
	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(@ModelAttribute("message") Messages message,
			Principal principal, Model model) {

		return "redirect:/";
	}
	


}
