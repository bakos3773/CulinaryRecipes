package com.bakos.Controllers;

import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert.*;

import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.EmailService;
import com.bakos.Service.UserService;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.Users;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/Dispatcher-context.xml", "file:src/main/webapp/WEB-INF/databaseConnection.xml"})
//@SqlGroup({
//	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create-db.sql" )
//})
public class MainControllerTest{

	private MockMvc mockMvc;
	
	@InjectMocks
	MainController mainController;
	
	@Mock
	CulinaryRecipesService recipesService;
	
	@Mock
	UserService userService;
	
	@Mock
	EmailService emailService;
	
	List<CulinaryRecipes> recipes;
	
	@Before
	public void setUp(){		
		
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/user/");
        viewResolver.setSuffix(".jsp");
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).setViewResolvers(viewResolver).build();
		
		CulinaryRecipes one = new CulinaryRecipes("Zupy", "Kremowa z porem", "woda, maka, pory", " dodac wszystko", "null","latwy", 20);
		CulinaryRecipes two = new CulinaryRecipes("Zupy", "Grzybowa z maslem", "woda, serek, grzyby", " dodac wszystko", "null","trudny", 30);
		
		this.recipes = Arrays.asList(one, two);
	}
	
	@Test
	public void testMainController() throws Exception{		
		assertEquals( "class com.bakos.Controllers.MainController", mainController.getClass().toString() );
	}	
	
	@Test
	public void testContactMethodOfMainController() throws Exception{
		
		verifyZeroInteractions(recipesService);
		
		this.mockMvc
		.perform(get("/contact"))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/jsp/user/contact.jsp"))
		.andExpect(view().name("contact"));
	}
	
	@Test
	public void show_Main_View_and_check_model_content() throws Exception{		
		
		CulinaryRecipes one = new CulinaryRecipes("Zupy", "Kremowa z porem", "woda, maka, pory", " dodac wszystko", "null","latwy", 20);
		CulinaryRecipes two = new CulinaryRecipes("Zupy", "Grzybowa z maslem", "woda, serek, grzyby", " dodac wszystko", "null","trudny", 30);
		
		List<CulinaryRecipes> recipes = Arrays.asList(one, two);
		
		assertNotNull(recipes);
		assertNotNull( recipesService.getlast10Recipies());
		assertNotNull( recipesService.getMostPopularRecipies());
		
		when(recipesService.getlast10Recipies()).thenReturn(recipes);
		when(recipesService.getMostPopularRecipies()).thenReturn(recipes);
		
		this.mockMvc
		.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("latest", hasSize(2)))
		.andExpect(model().attribute("popular", hasSize(2)))
		.andExpect(forwardedUrl("/WEB-INF/jsp/user/index.jsp"))		
		.andExpect(view().name("index"));		
		
		verify(recipesService, times(2)).getlast10Recipies();
		verify(recipesService, times(2)).getMostPopularRecipies();
		
		verifyNoMoreInteractions(recipesService);		
	}
	
	@Test
	public void testGetRecipeById() throws Exception{
		Integer id = 1;
		
		when(recipesService.getlast10Recipies()).thenReturn(this.recipes);
		when(recipesService.getMostPopularRecipies()).thenReturn(this.recipes);
		when(recipesService.getRecipieById(id)).thenReturn(new CulinaryRecipes());
		
		mockMvc.perform(get("/getRecipeById/{id}", id))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/jsp/user/mainPageShowRecipe.jsp"))
		.andExpect(view().name("mainPageShowRecipe"))
		.andExpect(model().attribute("latest", hasSize(2)))
		.andExpect(model().attribute("popular", hasSize(2)))		
		.andExpect(model().attribute("recipe", instanceOf(CulinaryRecipes.class)));
		
		verify(recipesService, times(1)).getlast10Recipies();
		verify(recipesService, times(1)).getMostPopularRecipies();
		verify(recipesService, times(1)).getRecipieById(id);
		
		verifyNoMoreInteractions(recipesService);
	}
	
	@Test
	public void testGetCulinaryRecipesToHtml() throws Exception{
		
		when(recipesService.getlast10Recipies()).thenReturn(this.recipes);
		
		this.mockMvc.perform(get("/getlast10Recipies"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].name", is("Kremowa z porem")))
		.andExpect(jsonPath("$[0].level", is("latwy")))
		.andExpect(jsonPath("$[0].timeToPrepare", is(20)))
		.andExpect(jsonPath("$[1].name", is("Grzybowa z maslem")))
		.andExpect(jsonPath("$[1].level", is("trudny")))
		.andExpect(jsonPath("$[1].timeToPrepare", is(30)));
		
		verify(recipesService, times(1)).getlast10Recipies();
		verifyNoMoreInteractions(recipesService);
	}
}
