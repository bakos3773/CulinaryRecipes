package com.bakos.Controllers;

import static org.junit.Assert.*;

import org.easymock.*;
import org.easymock.internal.MocksControl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.bakos.Controllers.MainController;
import com.bakos.Controllers.UserController;
import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.UserService;
import com.bakos.UserDTO.CulinaryRecipes;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-Controller-context.xml")
@WebAppConfiguration
public class ControllersTest {

	@Autowired
	private WebApplicationContext wac;
//	@Autowired
//	MockHttpSession session;
	private MockMvc mockMvc;
//	@Mock
//	private UserService service;
//	@Mock
//	private CulinaryRecipesService recipesService;
//	@InjectMocks
//	private MainController mainController;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
//		MockitoAnnotations.initMocks(this);
//		this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
	}

	@Test
	public void test() throws Exception {
//		this.mockMvc.perform(get("/")).andExpect(model().attributeExists("latest")).andExpect(status().isOk()).andExpect(view().name("index"));		
//		this.mockMvc.perform(get("/getRecipeById/234")).andExpect(model().attributeExists("recipe"));
		
//		MainController mainController = new MainController();
//		MockMvc mock = MockMvcBuilders.standaloneSetup(mainController).build();
//		mock.perform(get("/")).andExpect(model().attributeExists("latest"));		
	}
	
	@Test
	public void testMainController() throws Exception{
		
		this.mockMvc.perform(get("/")).andExpect(model().attributeExists("latest")).andExpect(status().isOk()).andExpect(view().name("index"));
		
		this.mockMvc.perform(get("/getRecipeById/{id}", 234)).andExpect(model().attributeExists("recipe", "latest"))
		.andExpect(status().isOk()).andExpect(view().name("mainPageShowRecipe"));
		
		this.mockMvc.perform(get("/getlast10Recipies")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(print()).andReturn();
	}

	@Test
	public void testUserController() throws Exception{
		
		this.mockMvc.perform(get("/user/home")).andExpect(model().attributeExists("recipes", "filterPattern"))
		.andExpect(status().isOk()).andExpect(view().name("home"));
		
//		this.mockMvc.perform(get("/user/settings"))
//		.andExpect(status().isOk()).andExpect(view().name("settings"));
		
//		this.mockMvc.perform(post("/user/checkedTypes"))
//		.andExpect(model().attributeExists("recipes"))
//		.andExpect(status().isOk());
	}
	
	@Test
	public void testRecipesController() throws Exception{
		
		this.mockMvc.perform(get("/user/recipes/addRecipe")).andExpect(model().attributeExists("culinaryRecipes"))
		.andExpect(status().isOk()).andExpect(view().name("addRecipe"));		
	}
}
