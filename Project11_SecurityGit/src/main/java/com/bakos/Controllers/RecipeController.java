package com.bakos.Controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	public String onSubmit( HttpServletRequest request ) throws IllegalStateException, IOException{
		
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");		
		DiskFileItemFactory d = new DiskFileItemFactory();
		ServletFileUpload uploader = new ServletFileUpload(d);
	
		
		try {
			List<FileItem> list = uploader.parseRequest(request);
			for(FileItem fileItem : list){
				if(fileItem.isFormField()==false){
//					fileItem.write(new File(rootDirectory+"resources\\images\\"+fileItem.getName()) );
					fileItem.write(new File(rootDirectory+"resources\\images\\"+recipesService.getlastOneRecipies().getId()+".jpg" ) );
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return "redirect:/user/home";
	}

}
