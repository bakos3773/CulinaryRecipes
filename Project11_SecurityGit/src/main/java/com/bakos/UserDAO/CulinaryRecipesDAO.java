package com.bakos.UserDAO;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bakos.UserDTO.Articles;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;
import com.bakos.UserDTO.RecipesComments;

public interface CulinaryRecipesDAO {

	public void addCulinaryRecipe(CulinaryRecipes culinaryRecipes);

	// @Cacheable("culinaryRecipes") - nie moze to byc wylaczone bo nie
	// zaktualizuje swiezo dodanego przepisu tylko wczyta z pamieci podrecznej
	// Adnotacja @Cacheable szuka najpierw wpisu w pamiêci podrêcznej i pomija
	// wywo³anie metody w przypadku jego odnalezienia. Jeœli wpis nie zostanie
	// znaleziony, metoda
	// jest wywo³ywana, a w pamiêci podrêcznej zapisywana jest wartoœæ zwrócona
	// przez metodê. Adnotacj¹ @Cacheable mo¿emy sprawiæ, ¿e wiadomoœæ zostanie
	// zapisana w pamiêci podrêcznej,
	// a dziêki temu unikniemy zbêdnych zapytañ do bazy

	@CachePut("culinaryRecipes")
	// Adnotacja @CachePut z kolei nie sprawdza nigdy pasuj¹cych wartoœci w
	// pamiêci cache, zawsze wywo³uje metodê, a zwrócon¹ przez ni¹ wartoœæ zapisuje w
	// pamiêci podrêcznej
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

	public void setStatistics(int id);

	public boolean isItMyRecipe(int id);

	public void saveImage(MultipartFile file);

	public void addComment(int idRecpe, String comment);

	public List<RecipesComments> getAllRecipiesComments(int id);

	public List<CulinaryRecipes> getAllSearchingRecipies(String searchingText);

	public void ratingRecipe(Short rate, int idRecipe);

}
