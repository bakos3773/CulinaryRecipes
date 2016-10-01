package com.bakos.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bakos.Service.CulinaryRecipesService;
import com.bakos.Service.ForumService;
import com.bakos.Service.UserService;
import com.bakos.Service.WidgetService;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.Forum;
import com.bakos.UserDTO.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-Dispatcher-context.xml")
@WebAppConfiguration
public class KlasaTestujaca {
	
	@Autowired
	UserService userService;
	@Autowired
	CulinaryRecipesService culinaryService;
	@Autowired
	ForumService forumService;
	@Autowired
	WidgetService widgetService;
	
	private List<Users> allUsers;
	private List<CulinaryRecipes> allRecipes;
	private List<Forum> forumSections;

	@Before
	public void setUp() throws Exception {
		
		allUsers = this.userService.getAllUsers();
		allRecipes = this.culinaryService.getAllRecipies();
		forumSections = this.forumService.getAllSections();
	}

@Test
public void test(){
	
	Assert.assertNotNull(this.allUsers);
	Assert.assertNotNull(this.allRecipes);
	Assert.assertNotNull(this.forumSections);	
}
	@Test
	public void testUser() {
		
	Users user = userService.getUserLogin("pablo");
	Assert.assertEquals("Dupaczewski", user.getSurname());
	Assert.assertNotNull("Object is not null", userService.getUserLogin("dupa") );		
    Assert.assertTrue(userService.UserByLoginExist("kotek"));		
	}
	
	@Test
	public void testRecipes() {
		Assert.assertEquals("uytu", culinaryService.getRecipieById(237).getName());
		Assert.assertEquals(10, culinaryService.getlast10Recipies().size());
	}
}
