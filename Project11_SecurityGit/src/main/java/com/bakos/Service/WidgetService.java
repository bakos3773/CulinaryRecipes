package com.bakos.Service;

import java.util.List;

import com.bakos.UserDTO.MostPopularRecipesThisDay;

public interface WidgetService{
	
	public List<Integer> lastMont(String year_month);
	public List<MostPopularRecipesThisDay> mostPopularRecipesThisDay(String day);
}
