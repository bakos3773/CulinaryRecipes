package com.bakos.Controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.UserService;
import com.bakos.UserDTO.CulinaryRecipes;

@Controller
@RequestMapping("/user/recipes")
public class RecipeController {

	@Autowired
	UserService service;

	@Autowired
	CulinaryRecipesService recipesService;
	
	String rootDirectory = System.getProperty("catalina.home");
	
	private static final Logger logger = LoggerFactory
			.getLogger(RecipeController.class);

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public @ResponseBody CulinaryRecipes read(@PathVariable(value = "id") int id) {

		CulinaryRecipes recipe = recipesService.read(id);
		return recipe;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@ModelAttribute("recipe") CulinaryRecipes recipe,
			BindingResult result, @PathVariable("id") int id) {

		recipesService.updateRecipe(recipe, id);
	}
	
	@RequestMapping(value = "/myCulinaryRecipe", method = RequestMethod.GET)
	public String myCulinaryRecipe(Model model) {

		model.addAttribute("myRecipes", recipesService.getAllMyCulinaryRecipes());
		model.addAttribute("recipe", new CulinaryRecipes());

		return "myCulinaryRecipe";
	}

	@RequestMapping(method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public @ResponseBody List<CulinaryRecipes> updateAll() {

		return recipesService.getAllMyCulinaryRecipes();
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable("id") int id) {

		recipesService.removeRecipe(id);
	}
	
	@RequestMapping(value="/articles", method=RequestMethod.GET)
	public String articles(Model model){
		
		model.addAttribute("y", recipesService.getAllArticles() );
		
		return "articles";
	}
	
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public String show( @PathVariable("id")int id, Model model ){
		
		recipesService.setStatistics(id);
		model.addAttribute("recipes", recipesService.getRecipieById(id) );
		return "getRecipeById";
	}	
	
	@RequestMapping(value="/statistics", method=RequestMethod.GET)
	public String statiscic( Model model ){

		return "statistics";
	}		
	
	@RequestMapping(value="/upload", method=RequestMethod.GET) // TEST 
	public String upload(Model model){

		return "upload";
	}

	
	@RequestMapping(value="/uploadPost", method=RequestMethod.POST) // TEST 
	public String onSubmit( HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file ) throws IllegalStateException, IOException{
		
		
		
		if (!file.isEmpty()) {

			try {
				
				File dir = new File(rootDirectory +File.separator + "resources" + File.separator + "images");
				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(dir.getAbsoluteFile() + File.separator + recipesService.getlastOneRecipies().getId() + ".jpg");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

				stream.write(file.getBytes());
				stream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
        return "redirect:/user/home";
	}
	
	@RequestMapping(value="/images/{imageId}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable("imageId")String imageId) throws IOException{
		
		System.out.println("Pobieram zdjecia!!!");
		
		File fileFromServe = new File(rootDirectory+File.separator + "resources" + File.separator + "images"
				+ File.separator + imageId + ".jpg");
		
		return Files.readAllBytes(fileFromServe.toPath());
	}
}
