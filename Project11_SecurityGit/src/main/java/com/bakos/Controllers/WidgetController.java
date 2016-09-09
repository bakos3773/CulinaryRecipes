package com.bakos.Controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bakos.Service.WidgetService;
import com.bakos.UserDTO.MostPopularRecipesThisDay;

@Controller
public class WidgetController {
	
	@Autowired
	WidgetService widgetService;

	@RequestMapping(value="/user/lastMonth/{selectedDate}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	@ResponseBody
	public List<Integer>lastMonth(@PathVariable("selectedDate") String selectedDate){
		
		if(selectedDate.equals("undefined")){
			System.out.println("Nic nie wybrales");
			
		}
		else{
			System.out.println("Wszedleeeeeeeeeeeeeeeeem: "+ selectedDate);			
		}
		
		return widgetService.lastMont(selectedDate);
	}
	
	@RequestMapping(value="/user/lastMonth", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	@ResponseBody
	public List<Integer>lastMonthh(@PathVariable("selectedDate") String selectedDate){


		return widgetService.lastMont(selectedDate);
	}
	
	// DOKONCZ ROBIC!!!
	@RequestMapping(value="/widget/mostPopularRecipesThisDay", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	@ResponseBody
	public List<MostPopularRecipesThisDay> mostPopularRecipesThisDay(){

		Date date = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    
	    
		return widgetService.mostPopularRecipesThisDay(day);
	}	
}
