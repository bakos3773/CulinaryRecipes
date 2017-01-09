package com.bakos.UserDAO.SpringData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bakos.UserDTO.CulinaryRecipes;

public interface CulinaryRecipesJpaRepository extends JpaRepository<CulinaryRecipes, Integer>{

	@Query(value="SELECT * FROM CulinaryRecipes z WHERE  isPrivateRecipe IS NULL", nativeQuery=true)
	List<CulinaryRecipes> findAllWhereIsPrivateRecipeISNull();
	
	@Query(value="SELECT *  FROM CulinaryRecipes as c INNER JOIN Users as u ON c.users_id=u.id WHERE u.id= ?1", nativeQuery=true)
	List<CulinaryRecipes> findAllMyCulinaryRecipes(int id);
	
	CulinaryRecipes findById(int id);
	
	@Query(value="Select * from CulinaryRecipes z WHERE isPrivateRecipe IS NULL ORDER BY date DESC LIMIT 10", nativeQuery=true)
	List<CulinaryRecipes> findLast10Recipies();
	
	@Modifying
	@Query("UPDATE CulinaryRecipes x SET "
						+ "x.name= :name, x.type= :type, x.components= :components, "
						+ "x.howToPerform =:howToPerform, x.isPrivateRecipe= :isPrivateRecipe "
						+ "WHERE x.id= :id")
	void updateCulinaryRecipe(@Param("name") String name, @Param("type") String type, @Param("components") String components, @Param("howToPerform") String howToPerform, @Param("isPrivateRecipe") String isPrivateRecipe, @Param("id") int id);
	
	void removeById(int id);
	
	@Query(value="SELECT * FROM CulinaryRecipes WHERE name LIKE:searchingText", nativeQuery=true)
	List<CulinaryRecipes> findAllSearchingRecipies(@Param("searchingText")String searchingText);
}