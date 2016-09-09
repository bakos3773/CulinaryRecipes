package com.bakos.UserDAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bakos.UserDTO.Articles;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.FilterPattern;
import com.bakos.UserDTO.Statistics;
import com.bakos.UserDTO.Users;


@Repository
@Transactional
public class CulinaryRecipesDAOimpl implements CulinaryRecipesDAO {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	UserDAO userDAO;

	@Override
	public void addCulinaryRecipe(CulinaryRecipes culinaryRecipes) {

		CulinaryRecipes newCulinaryRecipes = new CulinaryRecipes(
				culinaryRecipes.getType(), culinaryRecipes.getName(),
				culinaryRecipes.getComponents(),
				culinaryRecipes.getHowToPerform(),
				culinaryRecipes.getIsPrivateRecipe());

		Users user = userDAO.findUserByUsername();
		newCulinaryRecipes.setUser(user);

		manager.persist(newCulinaryRecipes);
		manager.merge(user);
		//
		//
		// manager.refresh(newCulinaryRecipes);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CulinaryRecipes> getAllRecipies() {

		Query query = manager
				.createQuery("Select z from CulinaryRecipes z WHERE  z.isPrivateRecipe IS NULL");
		List<CulinaryRecipes> lista = query.getResultList();

		return lista;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CulinaryRecipes> getAllMyCulinaryRecipes() {

		Query query = manager
				.createQuery("SELECT c FROM Users u INNER JOIN u.culinaryRecipes c WHERE u.id= :id");
		query.setParameter("id", userDAO.findUserByUsername().getId());
		List<CulinaryRecipes> lista = query.getResultList();

		return lista;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CulinaryRecipes> checkedTypes(FilterPattern filterPattern) {
		String allTypes = "";

		for (int i = 0; i < filterPattern.getAllTypes().size(); i++) {
			allTypes += "'" + filterPattern.getAllTypes().get(i) + "',";
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
		Query query = manager
				.createQuery("SELECT DISTINCT c FROM CulinaryRecipes c WHERE c.id= :arg1");
		query.setParameter("arg1", id);

		return (CulinaryRecipes) query.getSingleResult();
	}

	@Override
	public void updateRecipe(CulinaryRecipes recipe, int id) {

		Query query = manager
				.createQuery("UPDATE CulinaryRecipes x SET "
						+ "x.name= :name, x.type= :type, x.components= :components, "
						+ "x.howToPerform =:howToPerform, x.isPrivateRecipe= :isPrivateRecipe "
						+ "WHERE x.id= :id");

		query.setParameter("name", recipe.getName());
		query.setParameter("type", recipe.getType());
		query.setParameter("components", recipe.getComponents());
		query.setParameter("howToPerform", recipe.getHowToPerform());
		query.setParameter("isPrivateRecipe", recipe.getIsPrivateRecipe());
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public CulinaryRecipes read(int id) {
		Query query = manager
				.createQuery("SELECT DISTINCT x FROM CulinaryRecipes x WHERE x.id= :id");
		query.setParameter("id", id);

		return (CulinaryRecipes) query.getSingleResult();
	}

	@Override
	public void removeRecipe(int id) {

		Query query = manager
				.createQuery("DELETE FROM CulinaryRecipes x WHERE x.id= :id");
		query.setParameter("id", id);
		query.executeUpdate();

	}

	@Override
	public List<CulinaryRecipes> getlast10Recipies() {
		Query query = manager
				.createQuery("Select z from CulinaryRecipes z WHERE  z.isPrivateRecipe IS NULL "
						+ "ORDER BY z.id DESC");
		@SuppressWarnings("unchecked")
		List<CulinaryRecipes> lista = query.setMaxResults(10).getResultList();

		return lista;
	}

	@Override
	public void addArticle(Articles article) {
		
		manager.persist(article);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Articles> getAllArticles(){
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
		
		if( !this.isItMyRecipe(id)){
			
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
		query.setParameter("id", userDAO.findUserByUsername().getId());
		List<CulinaryRecipes> lista = query.getResultList();
		
		if( lista.contains(id) ){
			return true;
		}
		else return false;
		
	}

}
