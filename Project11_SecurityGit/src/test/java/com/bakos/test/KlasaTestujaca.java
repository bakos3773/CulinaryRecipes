package com.bakos.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bakos.Service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-Dispatcher-context.xml")
@WebAppConfiguration
public class KlasaTestujaca {
	
	
//	UserService userService;

	

	@Before
	public void setUp() throws Exception {
		
		
	}



	@Test
	public void test() {
	
		System.out.println("aaa");
	}

}
