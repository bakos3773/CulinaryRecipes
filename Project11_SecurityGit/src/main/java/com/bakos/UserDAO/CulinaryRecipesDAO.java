package com.bakos.UserDAO;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bakos.UserDTO.Articles;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;

public interface CulinaryRecipesDAO {
	
	public void addCulinaryRecipe(CulinaryRecipes culinaryRecipes);

//	@Cacheable("culinaryRecipes") - nie moze to byc wylaczone bo nie zaktualizuje swiezo dodanego przepisu tylko wczyta z pamieci podrecznej 
//	Adnotacja @Cacheable szuka najpierw wpisu w pami�ci podr�cznej i pomija wywo-
//	�anie metody w przypadku jego odnalezienia. Je�li wpis nie zostanie znaleziony, metoda
//	jest wywo�ywana, a w pami�ci podr�cznej zapisywana jest warto�� zwr�cona przez
//	metod�. Adnotacj� @Cacheable mo�emy sprawi�, �e wiadomo�� zostanie zapisana w pami�ci podr�cznej,
//	a dzi�ki temu unikniemy zb�dnych zapyta� do bazy
	
	@CachePut("culinaryRecipes")
//	 Adnotacja @CachePut z kolei nie sprawdza nigdy pasuj�cych warto�ci w pami�ci
//	 cache, zawsze wywo�uje metod�, a zwr�con� przez ni� warto�� zapisuje w pami�ci
//	 podr�cznej
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
