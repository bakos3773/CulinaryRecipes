package com.bakos.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bakos.UserDAO.CulinaryRecipesDAO;
import com.bakos.UserDTO.Articles;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;
import com.bakos.UserDTO.RecipesComments;

@Service
public class CulinaryRecipesServiceImpl implements CulinaryRecipesService {

	private static final Logger logger = LoggerFactory
			.getLogger(CulinaryRecipesServiceImpl.class);

	@Autowired
	CulinaryRecipesDAO recipeDAO;

	@Override
	public void addCulinaryRecipe(CulinaryRecipes culinaryRecipes) {
		logger.info("Calling a method: addCulinaryRecipe");
		recipeDAO.addCulinaryRecipe(culinaryRecipes);
	}

	@Override
	public List<CulinaryRecipes> getAllRecipies() {
		logger.info("Calling a method: getAllRecipiesByUserId");
		return recipeDAO.getAllRecipies();
	}

	@Override
	public List<CulinaryRecipes> getAllMyCulinaryRecipes() {
		logger.info("Calling a method: getAllMyCulinaryRecipes");
		return recipeDAO.getAllMyCulinaryRecipes();
	}

	@Override
	public List<CulinaryRecipes> checkedTypes(List<String> filterPattern) {
		logger.info("Calling a method: checkedTypes");
		return recipeDAO.checkedTypes(filterPattern);
	}

	@Override
	public CulinaryRecipes getRecipieById(int id) {
		logger.info("Calling a method: getRecipieById");
		return recipeDAO.getRecipieById(id);
	}

	@Override
	public void updateRecipe(CulinaryRecipes recipe, int id) {
		logger.info("Calling a method: updateRecipe");
		recipeDAO.updateRecipe(recipe, id);
	}

	@Override
	public CulinaryRecipes read(int id) {
		logger.info("Calling a method: read");
		return recipeDAO.read(id);
	}

	@Override
	public void removeRecipe(int id) {
		logger.info("Calling a method: removeRecipe");
		recipeDAO.removeRecipe(id);
	}

	@Override
	public List<CulinaryRecipes> getlast10Recipies() {
		logger.info("Calling a method: getlast10Recipies");
		return recipeDAO.getlast10Recipies();
	}

	@Override
	public void addArticle(Articles article) {
		logger.info("Calling a method: addArticle");
		recipeDAO.addArticle(article);
	}

	@Override
	public List<Articles> getAllArticles(){
		logger.info("Calling a method: getAllArticles");		
		return recipeDAO.getAllArticles();
	}

	@Override
	public void removeArticle(int id) {
		logger.info("Calling a method: removeArticle");		
		recipeDAO.removeArticle(id);
	}

	@Override
	public void setStatistics(int id) {
		logger.info("Calling a method: setStatistics");				
		recipeDAO.setStatistics(id);
	}

	@Override
	public CulinaryRecipes getlastOneRecipies() {
		logger.info("Calling a method: getlastOneRecipies");		
		return recipeDAO.getlastOneRecipies();
	}

	@Override
	public boolean isItMyRecipe(int id) {
		logger.info("Calling a method: isItMyRecipe");
		return recipeDAO.isItMyRecipe(id);
	}

	@Override
	public void saveImage(MultipartFile file) {
		logger.info("Calling a method: saveImage");
		recipeDAO.saveImage(file);		
	}

	@Override
	public void addComment(int idRecpe, String comment) {
		recipeDAO.addComment(idRecpe, comment);
		
	}

	@Override
	public List<RecipesComments> getAllRecipiesComments(int id) {
		return recipeDAO.getAllRecipiesComments(id);
	}

	@Override
	public List<CulinaryRecipes> getAllSearchingRecipies(String searchingText) {		
		return recipeDAO.getAllSearchingRecipies(searchingText);
	}

	@Override
	public void ratingRecipe(Short rate, int idRecipe){
		recipeDAO.ratingRecipe(rate, idRecipe);
	}

	@Override
	public Short ratingAverage(int id) {		
		return recipeDAO.ratingAverage(id);
	}


}
