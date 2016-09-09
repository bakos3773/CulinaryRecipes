package com.bakos.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bakos.UserDAO.UserDAO;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {		
		
		if(userDAO == null){
			
			return true;
		}		
		
		boolean isRight = userDAO.UserByLoginExist(login);
		if(isRight){
			return false;
		}
		else{
			return true;
		}
		
	}

}
