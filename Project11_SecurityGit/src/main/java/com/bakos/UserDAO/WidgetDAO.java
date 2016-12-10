package com.bakos.UserDAO;

import java.util.List;
import com.bakos.UserDTO.MostPopularRecipesThisDay;

public interface WidgetDAO {

	public List<Integer> lastMont(String year_month);
	public List<MostPopularRecipesThisDay> mostPopularRecipesThisDay(String day);
}