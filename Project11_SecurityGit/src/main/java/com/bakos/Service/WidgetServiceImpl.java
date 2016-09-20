package com.bakos.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakos.UserDAO.WidgetDAO;
import com.bakos.UserDTO.MostPopularRecipesThisDay;

@Service
public class WidgetServiceImpl implements WidgetService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(WidgetServiceImpl.class);	
	
	@Autowired
	WidgetDAO widgetDAO;

	@Override
	public List<Integer> lastMont(String year_month) {
		logger.info("Calling a method: lastMonth");

		return widgetDAO.lastMont(year_month);
		
	}

	@Override
	public List<MostPopularRecipesThisDay> mostPopularRecipesThisDay(int day) {
		logger.info("Calling a method: mostPopularRecipesThisDay");
		return widgetDAO.mostPopularRecipesThisDay(day);
	}



}
