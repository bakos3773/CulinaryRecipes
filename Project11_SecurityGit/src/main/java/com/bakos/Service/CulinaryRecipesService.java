package com.bakos.Service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bakos.UserDTO.Articles;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;

@Service
public interface CulinaryRecipesService {
	

	public void addCulinaryRecipe(CulinaryRecipes culinaryRecipes);

	
	public List<CulinaryRecipes> getAllRecipies();

	public List<CulinaryRecipes> getlast10Recipies();
	
	public CulinaryRecipes getlastOneRecipies();

	public CulinaryRecipes getRecipieById(int id);

	public List<CulinaryRecipes> getAllMyCulinaryRecipes();

	public List<CulinaryRecipes> checkedTypes(FilterPattern filterPattern);

	public void updateRecipe(CulinaryRecipes recipe, int id);

	public CulinaryRecipes read(int id);

	public void removeRecipe(int id);

	
	public void addArticle(Articles article);
	
	public List<Articles> getAllArticles();
	
	public void removeArticle(int id);
	
	public void setStatistics(int id );
	
	public boolean isItMyRecipe(int id);
	
}
