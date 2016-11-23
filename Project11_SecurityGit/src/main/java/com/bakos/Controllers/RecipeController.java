package com.bakos.Controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.UserService;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;
import com.bakos.pdf.SaveSelectedRecesice;
import com.itextpdf.text.DocumentException;

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
	public ResponseEntity<CulinaryRecipes> read(@PathVariable(value = "id") int id) {

		CulinaryRecipes recipe = recipesService.read(id);		
		HttpStatus status = recipe!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<CulinaryRecipes>(recipe, status);
	}
	
//	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/checkedTypes", method = RequestMethod.POST)
	public String checkedTypes(@ModelAttribute("filterPattern") FilterPattern filterPattern, RedirectAttributes model) {
//		 RedirectAttributes  - Mo�emy zatem umie�ci� obiekt Spitter w sesji przed wykonaniem przekierowania,
//		a nast�pnie pobra� z sesji po jego wykonaniu
//		model.addAttribute("recipes", recipeService.checkedTypes(filterPattern));
		System.out.println("Wszedlem do checkedTypes");
		model.addFlashAttribute("recipes", recipesService.checkedTypes(filterPattern));

		return "redirect:/home";
	}	

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@ModelAttribute("recipe") CulinaryRecipes recipe,
			BindingResult result, @PathVariable("id") int id) {

		recipesService.updateRecipe(recipe, id);
	}
	
	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String addRecipeBefore(Model model) {

		model.addAttribute("culinaryRecipes", new CulinaryRecipes());

		return "addRecipe";
	}
	
//	@PreAuthorize -  Je�li u�ytkownik posiada rol� ROLE_USER oraz dlugosc pola name klasy culinaryRecipes bedzie
//	wieksze(=)5, to wywo�anie metody b�dzie mo�liwe, oraz jesli u�ytkownik posiada rol� ROLE_ADMIN. W przeciwnym 
//	wypadku wyrzucony zostanie wyj�tek zabezpiecze� i metoda nie zostanie wywo�ana.
	@PreAuthorize("(hasRole('ROLE_USER') and #culinaryRecipes.name.length()>=5) or hasRole('ROLE_ADMIN')" )
	@RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
	public String addRecipeAfter(
			@Valid @ModelAttribute("culinaryRecipes") CulinaryRecipes culinaryRecipes, BindingResult result, HttpServletRequest request,
			Model model) {		

		if(result.hasErrors()){
			return "addRecipe";
		}
		System.out.println("Controller dodawanie przepisu - opis!");
//		recipesService.addCulinaryRecipe(culinaryRecipes);
		model.addAttribute("filterPattern", new FilterPattern());

		return "redirect:/user/recipes/upload";
	}	
	
	@RequestMapping(value = "/myCulinaryRecipe", method = RequestMethod.GET)
	public String myCulinaryRecipe(Model model) {

		model.addAttribute("myRecipes", recipesService.getAllMyCulinaryRecipes());
		model.addAttribute("recipe", new CulinaryRecipes());

		return "myCulinaryRecipe";
	}

//	W ramach alternatywy dla @ResponseBody metody kontroler�w mog� zwraca� obiekty
//	ResponseEntity. Obiekty ResponseEntity, opr�cz samego obiektu, kt�ry zostanie skon-
//	wertowany i zwr�cony jako reprezentacja zasobu, zawieraj� metadane (takie jak nag��wki
//	i kody statusu) dotycz�ce odpowiedzi.
	@RequestMapping(method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public ResponseEntity<List<CulinaryRecipes>>updateAll() {

		List<CulinaryRecipes> allMyCulinaryRecipes = recipesService.getAllMyCulinaryRecipes();
		HttpStatus status = allMyCulinaryRecipes!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<List<CulinaryRecipes>>(allMyCulinaryRecipes, status);
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
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
		model.addAttribute("recipes", recipesService.getRecipieById(id));
		return "getRecipeById";
	}	
	
	@RequestMapping(value="/show/pdf/{id}", method=RequestMethod.GET)
	public ModelAndView showPDF( @PathVariable("id")int id, Model model ){
				
		return new ModelAndView("pdfView", "recipes", recipesService.getRecipieById(id)); 
	}
	
	@RequestMapping(value="/save/{id}", method=RequestMethod.GET)
	public String saveRecipeOnDesctop(@PathVariable("id")int id) throws DocumentException, IOException{
		
		CulinaryRecipes recipe = recipesService.getRecipieById(id);
		
		new SaveSelectedRecesice().metoda(recipe);
		
		return "redirect:/user/recipes/show/"+recipe.getId();
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
		
		recipesService.saveImage(file);
		
        return "redirect:/user/home";
	}
	
	@RequestMapping(value="/images/{imageId}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable("imageId")String imageId) throws IOException{
		
		File fileFromServe = new File(rootDirectory+File.separator + "resources" + File.separator + "images"
				+ File.separator + imageId + ".jpg");
		
		return Files.readAllBytes(fileFromServe.toPath());
	}
}
