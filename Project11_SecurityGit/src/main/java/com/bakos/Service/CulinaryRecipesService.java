package com.bakos.Service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bakos.UserDTO.Articles;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;
import com.bakos.UserDTO.RecipesComments;

@Service
public interface CulinaryRecipesService {
	

	public void addCulinaryRecipe(CulinaryRecipes culinaryRecipes);

	
	public List<CulinaryRecipes> getAllRecipies();

	public List<CulinaryRecipes> getlast10Recipies();
	
	public CulinaryRecipes getlastOneRecipies();

	public CulinaryRecipes getRecipieById(int id);

	public List<CulinaryRecipes> getAllMyCulinaryRecipes();

	public List<CulinaryRecipes> checkedTypes(List<String> filterPattern);

	public void updateRecipe(CulinaryRecipes recipe, int id);

	public CulinaryRecipes read(int id);

	public void removeRecipe(int id);

	
	public void addArticle(Articles article);
	
	public List<Articles> getAllArticles();
	
	public void removeArticle(int id);
	
	public void setStatistics(int id );
	
	public boolean isItMyRecipe(int id);
	
	public void saveImage(MultipartFile file);
	
	public void addComment(int idRecpe, String comment);
	
	public List<RecipesComments> getAllRecipiesComments(int id);
	
	public List<CulinaryRecipes> getAllSearchingRecipies(String searchingText);
	
	public void ratingRecipe(Short rate, int idRecipe);
	
	public Short ratingAverage(int id);
	
}
