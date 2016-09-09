package com.bakos.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class MostPopularRecipesThisDay {
	
	private List<Integer> values = new ArrayList<Integer>();
	private String text;
	
	
	public MostPopularRecipesThisDay(){
		
	}


	public MostPopularRecipesThisDay(List<Integer> values, String text) {
		this.values = values;
		this.text = text;
	}


	public List<Integer> getValues() {
		return values;
	}


	public void setValues(List<Integer> values) {
		this.values = values;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}



}
