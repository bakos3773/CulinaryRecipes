package com.bakos.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.UserService;
import com.bakos.UserDAO.SpringData.UserJpaRepository;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/Dispatcher-context.xml", "file:src/main/webapp/WEB-INF/databaseConnection.xml"})
@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:createUsers-db.sql" ),
//	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:destroyUsers-db.sql" )
})

@ActiveProfiles("testing")
public class UserControllerTest {
	
//	
//	@PersistenceContext(unitName="testPersistance")
//	@Qualifier("entityManagerForEmbeddedDB")
//	EntityManager entity;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Mock
	UserService userService;
	
	@Mock
	CulinaryRecipesService recipeService;

	
	@InjectMocks
	UserController userController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/user/");
        viewResolver.setSuffix(".jsp");
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).setViewResolvers(viewResolver).build();
		
	}
	
	@After
	public void destroyDB(){
	}
	
	@Test
	public void testUserController() throws Exception{		
		assertEquals( "class com.bakos.Controllers.UserController", userController.getClass().toString() );
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testShowHome() throws Exception{
		
		CulinaryRecipes one = new CulinaryRecipes("Zupy", "Kremowa z porem", "woda, maka, pory", "dodac wszystko", "null","latwy", 20);
		CulinaryRecipes two = new CulinaryRecipes("Zupy", "Grzybowa z maslem", "woda, serek, grzyby", "dodac wszystko", "null","trudny", 30);
		List<CulinaryRecipes> recipes = Arrays.asList(one, two);
		
		assertNotNull("The Array recipes from testShowHome method is Empty!", recipes);
		assertNotSame("This same recipies", one, two);
		
		when(recipeService.getlast10Recipies()).thenReturn(recipes);
		
		this.mockMvc.perform(get("/user/home"))
		.andExpect(status().isOk())
		.andExpect(view().name("home"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/user/home.jsp"))
		.andExpect(model().attribute("latest", hasSize(2)))
		.andExpect(model().attribute("latest", hasItem(allOf(
				hasProperty("type", is("Zupy")),
				hasProperty("name", is("Kremowa z porem")),
				hasProperty("components", is("woda, maka, pory")),
				hasProperty("howToPerform", is("dodac wszystko")),
				hasProperty("isPrivateRecipe", is("null")),
				hasProperty("level", is("latwy")),
				hasProperty("timeToPrepare", is(20))
				))))
		.andExpect(model().attribute("latest", hasItem(allOf(
				hasProperty("type", is("Zupy")),
				hasProperty("name", is("Grzybowa z maslem")),
				hasProperty("components", is("woda, serek, grzyby")),
				hasProperty("howToPerform", is("dodac wszystko")),
				hasProperty("isPrivateRecipe", is("null")),
				hasProperty("level", is("trudny")),
				hasProperty("timeToPrepare", is(30))
				))));	
		
		verify(recipeService, times(1)).getlast10Recipies();
		verify(recipeService, times(1)).getMostPopularRecipies();
		verifyNoMoreInteractions(recipeService);		
	}	
	
	@Test
	public void testShowSettings() throws Exception{
		
		
		Users user = new Users("ala", "12345", "Alica", "Kielbasa", "Polna 30", "ala@wp.pl", "ROLE_USER" );
//		User user2 = jdbcTemplate.queryForObject("SELECT * FROM Users WHERE id = ?", requiredType)
		
		when(userService.findUserByUsername()).thenReturn(user);
		
		assertNotNull( userService.findUserByUsername());
		
		this.mockMvc.perform(get("/user/settings"))
		.andExpect(status().isOk())
		.andExpect(view().name("settings"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/user/settings.jsp"))
		.andExpect(model().attribute("user", instanceOf(Users.class)));	
		
		verify(userService, times(2)).findUserByUsername();
		verifyNoMoreInteractions(userService);
	}


//	@Test
//	public void getAll(){
//		String selectQuery = "SELECT * from Users";
//		List<Map<String, Object>> users = jdbcTemplate.queryForList(selectQuery);
//		
//		for( Map<String, Object> user: users ){
//			for(Map.Entry<String, Object> entry : user.entrySet()){
//				System.out.println("USER ->"+ entry.getKey()+" - "+ entry.getValue());
//			}
//		}
//		
//		Users user = (Users) jdbcTemplate.queryForObject("SELECT * FROM Users WHERE id=1", Users.class);
//		System.out.println("------------------- "+ ((Users)user).getLogin());
//	}
	
}
