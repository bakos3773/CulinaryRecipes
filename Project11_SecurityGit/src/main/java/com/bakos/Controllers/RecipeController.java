package com.bakos.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.UserService;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;
import com.bakos.UserDTO.RecipesComments;
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
	
	@GetMapping(value="/loadAllRecipes", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CulinaryRecipes>> loadAllRecipes(){

		List<CulinaryRecipes> xxx = recipesService.getAllRecipies();
		return new ResponseEntity<List<CulinaryRecipes>>(xxx, HttpStatus.OK);
	}
	
	@GetMapping(value="/loadAllRecipesComments/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecipesComments>> loadAllRecipesComments(@PathVariable("id")int id){
		
		List<RecipesComments> recipe = recipesService.getAllRecipiesComments(id);
		HttpStatus status = recipe!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwww");
		
		return new ResponseEntity<List<RecipesComments>>(recipe, status);		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public ResponseEntity<CulinaryRecipes> read(@PathVariable(value = "id") int id) {


		CulinaryRecipes recipe = recipesService.read(id);		
		HttpStatus status = recipe!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<CulinaryRecipes>(recipe, status);
	}
	
	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/checkedTypes", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<List<CulinaryRecipes>> checkedTypes(@RequestBody List<String> pattern) {

		List<CulinaryRecipes> recipe = recipesService.checkedTypes(pattern);
		HttpStatus status = recipe!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<List<CulinaryRecipes>>(recipe, status);
	}	
	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/dropdownTypes/{type}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<CulinaryRecipes>> dropdownTypes(@PathVariable("type")String type) {

		List<String> pattern = new ArrayList<String>();
		pattern.add(type);
		List<CulinaryRecipes> recipe = recipesService.checkedTypes(pattern);
		HttpStatus status = recipe!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<List<CulinaryRecipes>>(recipe, status);
	}	
	
	@PostMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@ModelAttribute("recipe") CulinaryRecipes recipe,
			BindingResult result, @PathVariable("id") int id) {

		recipesService.updateRecipe(recipe, id);
	}
	
	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/addRecipe")
	public String addRecipeBefore(Model model) {

		model.addAttribute("latest", recipesService.getlast10Recipies());
		model.addAttribute("culinaryRecipes", new CulinaryRecipes());

		return "addRecipe";
	}
	
//	@PreAuthorize -  Jeœli u¿ytkownik posiada rolê ROLE_USER oraz dlugosc pola name klasy culinaryRecipes bedzie
//	wieksze(=)5, to wywo³anie metody bêdzie mo¿liwe, oraz jesli u¿ytkownik posiada rolê ROLE_ADMIN. W przeciwnym 
//	wypadku wyrzucony zostanie wyj¹tek zabezpieczeñ i metoda nie zostanie wywo³ana.
	@PreAuthorize("(hasRole('ROLE_USER') and #culinaryRecipes.name.length()>=5) or hasRole('ROLE_ADMIN')" )
	@PostMapping("/addRecipe")
	public String addRecipeAfter(@Valid @ModelAttribute("culinaryRecipes") CulinaryRecipes culinaryRecipes,
			BindingResult result, HttpServletRequest request, Model model) {		

		if(result.hasErrors()){
			return "addRecipe";
		}
		System.out.println("Controller dodawanie przepisu - opis!");
		recipesService.addCulinaryRecipe(culinaryRecipes);
		model.addAttribute("filterPattern", new FilterPattern());
		model.addAttribute("latest", recipesService.getlast10Recipies());

		return "redirect:/user/recipes/upload";
	}	
	
	@GetMapping("/myCulinaryRecipe")
	public String myCulinaryRecipe(Model model) {

		model.addAttribute("myRecipes", recipesService.getAllMyCulinaryRecipes());
		model.addAttribute("recipe", new CulinaryRecipes());
		model.addAttribute("latest", recipesService.getlast10Recipies());

		return "myCulinaryRecipe";
	}

//	W ramach alternatywy dla @ResponseBody metody kontrolerów mog¹ zwracaæ obiekty
//	ResponseEntity. Obiekty ResponseEntity, oprócz samego obiektu, który zostanie skon-
//	wertowany i zwrócony jako reprezentacja zasobu, zawieraj¹ metadane (takie jak nag³ówki
//	i kody statusu) dotycz¹ce odpowiedzi.
	@RequestMapping(method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public ResponseEntity<List<CulinaryRecipes>>updateAll() {

		List<CulinaryRecipes> allMyCulinaryRecipes = recipesService.getAllMyCulinaryRecipes();
		HttpStatus status = allMyCulinaryRecipes!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<List<CulinaryRecipes>>(allMyCulinaryRecipes, status);
	}

	@DeleteMapping("/remove/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable("id") int id) {

		recipesService.removeRecipe(id);
	}
	
	@GetMapping("/articles")
	public String articles(Model model){
		
		model.addAttribute("y", recipesService.getAllArticles() );
		
		return "articles";
	}
	
	@GetMapping("/show/{id}")
	public String show( @PathVariable("id")int id, Model model ){
		
		recipesService.setStatistics(id);
		model.addAttribute("recipes", recipesService.getRecipieById(id));
		return "getRecipeById";
	}	
	
	@GetMapping("/show/pdf/{id}")
	public ModelAndView showPDF( @PathVariable("id")int id, Model model ){
				
		return new ModelAndView("pdfView", "recipes", recipesService.getRecipieById(id)); 
	}
	
	@GetMapping("/save/{id}")
	public String saveRecipeOnDesctop(@PathVariable("id")int id) throws DocumentException, IOException{
		
		CulinaryRecipes recipe = recipesService.getRecipieById(id);
		
		new SaveSelectedRecesice().metoda(recipe);
		
		return "redirect:/user/recipes/show/"+recipe.getId();
	}
	
	@GetMapping("/statistics")
	public String statiscic( Model model ){

		return "statistics";
	}		
	
	@GetMapping("/upload")
	public String upload(Model model){

		return "upload";
	}

	
	@PostMapping("/uploadPost")
	public String onSubmit( HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file ) throws IllegalStateException, IOException{
		
		recipesService.saveImage(file);
		
        return "redirect:/user/home";
	}
	
	@GetMapping("/images/{imageId}")
	@ResponseBody
	public byte[] getImage(@PathVariable("imageId")String imageId) throws IOException{
		
		if(NumberUtils.isNumber(imageId)){			
			File fileFromServe = new File(rootDirectory + File.separator + "resources" + File.separator + "images"
					+ File.separator + imageId + ".jpg");
			
			return Files.readAllBytes(fileFromServe.toPath());		
		}
		
		return null;
	}
	
	@PostMapping("/addComment/{id}")
	public ResponseEntity<Void> addComment(@PathVariable("id")int id, @RequestBody String comment){
		
		recipesService.addComment(id, comment);
		return null;
	}
	
	@GetMapping("/serchingRecipes/{searchingTxt}")
	public String serchingRecipes(@PathVariable("searchingTxt")String searchingTxt, Model model){
		
		model.addAttribute("serchingRecipes", recipesService.getAllSearchingRecipies(searchingTxt));

		return "serchingRecipes";
	}
	
	@GetMapping("/{idRecipe}/rate/{rate}")
	public void ratingRecipe(@PathVariable("idRecipe")int idRecipe, @PathVariable("rate")Short rate){
		recipesService.ratingRecipe(rate, idRecipe);
	}
	
}
