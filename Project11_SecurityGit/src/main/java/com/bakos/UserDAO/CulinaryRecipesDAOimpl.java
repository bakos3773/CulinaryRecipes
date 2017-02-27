package com.bakos.UserDAO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.UserService;
import com.bakos.UserDAO.SpringData.CulinaryRecipesJpaRepository;
import com.bakos.UserDTO.Articles;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;
import com.bakos.UserDTO.RecipesComments;
import com.bakos.UserDTO.Statistics;
import com.bakos.UserDTO.Users;

@Repository
@Transactional
public class CulinaryRecipesDAOimpl implements CulinaryRecipesDAO {

	@PersistenceContext(unitName="base")
	@Qualifier(value = "manager")
	private EntityManager manager;

	@Autowired
	UserService userService;

	@Autowired
	CulinaryRecipesService recipesService;

	String rootDirectory = System.getProperty("catalina.home");

	@Autowired
	CulinaryRecipesJpaRepository culinaryRecipesJpaRepository;

	@Override
	public void addCulinaryRecipe(CulinaryRecipes culinaryRecipes) {

		CulinaryRecipes newCulinaryRecipes = new CulinaryRecipes(
				culinaryRecipes.getType(), culinaryRecipes.getName(),
				culinaryRecipes.getComponents(),
				culinaryRecipes.getHowToPerform(),
				culinaryRecipes.getIsPrivateRecipe(),
				culinaryRecipes.getLevel(), culinaryRecipes.getTimeToPrepare());
		newCulinaryRecipes.setDate(new Date());

		Users user = userService.findUserByUsername();
		newCulinaryRecipes.setUser(user);

		
		culinaryRecipesJpaRepository.save(newCulinaryRecipes);
		manager.merge(user);
	}

	@Override
	public List<CulinaryRecipes> getAllRecipies() {

		return culinaryRecipesJpaRepository.findAllWhereIsPrivateRecipeISNull();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CulinaryRecipes> getAllMyCulinaryRecipes() {

		return culinaryRecipesJpaRepository
				.findAllMyCulinaryRecipes(userService.findUserByUsername()
						.getId());
	}

	@SuppressWarnings("unchecked")
	public List<CulinaryRecipes> checkedTypes(List<String> filterPattern) {
		String allTypes = "";

		for (int i = 0; i < filterPattern.size(); i++) {
			allTypes += "'" + filterPattern.get(i) + "',";
		}
		allTypes = allTypes.substring(0, allTypes.length() - 1);

		Query query = manager
				.createQuery("SELECT c FROM CulinaryRecipes c WHERE c.isPrivateRecipe IS NULL AND c.type IN ("
						+ allTypes + ")");
		List<CulinaryRecipes> lista = query.getResultList();

		return lista;
	}

	@Override
	public CulinaryRecipes getRecipieById(int id) {
		return culinaryRecipesJpaRepository.findById(id);
	}

	@Override
	public void updateRecipe(CulinaryRecipes recipe, int id) {

		culinaryRecipesJpaRepository.updateCulinaryRecipe(recipe.getName(),
				recipe.getType(), recipe.getComponents(),
				recipe.getHowToPerform(), recipe.getIsPrivateRecipe(), id);

	}

	@Override
	public CulinaryRecipes read(int id) {

		return culinaryRecipesJpaRepository.findById(id);
	}

	@Override
	public void removeRecipe(int id) {

		culinaryRecipesJpaRepository.removeById(id);
	}

	@Override
	public List<CulinaryRecipes> getlast10Recipies() {

		return culinaryRecipesJpaRepository.findLast10Recipies();
	}

	@Override
	public void addArticle(Articles article) {

		manager.persist(article);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Articles> getAllArticles() {
		Query query = manager.createQuery("SELECT x FROM Articles x");
		List<Articles> articles = query.getResultList();
		return articles;
	}

	@Override
	public void removeArticle(int id) {
		Query query = manager
				.createQuery("DELETE FROM Articles x WHERE x.id= :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public CulinaryRecipes getlastOneRecipies() {
		Query query = manager
				.createQuery("Select z from CulinaryRecipes z WHERE  z.isPrivateRecipe IS NULL "
						+ "ORDER BY z.id DESC");
		List<CulinaryRecipes> lista = query.setMaxResults(1).getResultList();

		return lista.get(0);
	}

	@Override
	public void setStatistics(int id) {
		System.out.println("USTAWIAM PRZEPIS NA DZIS");
		if (!this.isItMyRecipe(id)) {

			Statistics statistics = new Statistics();
			statistics.setCounter(1);
			statistics.setDate(new Date());
			statistics.setCulinaryRecipes(getRecipieById(id));

			manager.persist(statistics);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isItMyRecipe(int id) {

		Query query = manager
				.createQuery("SELECT c.id FROM Users u INNER JOIN u.culinaryRecipes c WHERE u.id= :id");
		query.setParameter("id", userService.findUserByUsername().getId());
		List<CulinaryRecipes> lista = query.getResultList();

		if (lista.contains(id)) {
			return true;
		} else
			return false;

	}

	@Override
	public void saveImage(MultipartFile file) {
		if (!file.isEmpty()) {

			try {

				File dir = new File(rootDirectory + File.separator
						+ "resources" + File.separator + "images");
				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(dir.getAbsoluteFile()
						+ File.separator
						+ recipesService.getlastOneRecipies().getId() + ".jpg");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));

				stream.write(file.getBytes());
				stream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addComment(int idRecpe, String comment) {

		Query query = manager
				.createQuery("SELECT x FROM CulinaryRecipes x WHERE x.id= :id");
		CulinaryRecipes culinaryRecipes = (CulinaryRecipes) query.setParameter(
				"id", idRecpe).getSingleResult();
		RecipesComments recipesComments = new RecipesComments();
		recipesComments.setComment(comment);
		recipesComments.setDate(new Date());
		recipesComments.setIdCommentator(userService.findUserByUsername()
				.getId());
		recipesComments.setNameCommentator(userService.findUserByUsername()
				.getLogin());
		recipesComments.setCulinaryRecipes(culinaryRecipes);
		manager.persist(recipesComments);
	}

	@Override
	public List<RecipesComments> getAllRecipiesComments(int id) {

		Query query = manager
				.createQuery("SELECT c FROM CulinaryRecipes u INNER JOIN u.recipeComments c WHERE u.id= :id");
		query.setParameter("id", id);
		List<RecipesComments> lista = query.getResultList();

		return lista;
	}

	@Override
	public List<CulinaryRecipes> getAllSearchingRecipies(String searchingText) {

		return culinaryRecipesJpaRepository.findAllSearchingRecipies("%"
				+ searchingText + "%");
	}

	@Override
	public void ratingRecipe(Short rate, int idRecipe) {
		CulinaryRecipes recipe = (CulinaryRecipes) manager
				.createQuery(
						"SELECT c FROM CulinaryRecipes c WHERE c.id= :idRecipe")
				.setParameter("idRecipe", idRecipe).getSingleResult();

		recipe.setCounterRatings(recipe.getCounterRatings() + 1);
		recipe.getCollectionRatings().add(rate);

		float all = 0;
		for (Short x : recipe.getCollectionRatings()) {
			all += x;
		}

		recipe.setAvgRaings((int) (Math.round((all / recipe.getCounterRatings()))));

		manager.merge(recipe);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CulinaryRecipes> getMostPopularRecipies() {
		List<CulinaryRecipes> mostPopulat = manager.createQuery("SELECT c FROM CulinaryRecipes c "
				+ "WHERE c.id IN("
					+ "SELECT s.culinaryRecipes.id "
					+ "FROM Statistics s "
					+ "GROUP BY s.culinaryRecipes.id "
					+ "ORDER BY COUNT(s.culinaryRecipes.id) DESC"
				+ " )").setMaxResults(10).getResultList();

		return mostPopulat;
	}

}
