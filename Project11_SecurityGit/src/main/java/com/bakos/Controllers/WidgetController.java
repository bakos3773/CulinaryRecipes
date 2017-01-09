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
import org.springframework.web.bind.annotation.RestController;

import com.bakos.Service.WidgetService;
import com.bakos.UserDTO.MostPopularRecipesThisDay;


//@Controller
@RestController
public class WidgetController {
	
	@Autowired
	WidgetService widgetService;

	@RequestMapping(value="/user/lastMonth/{selectedDate}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
//	@ResponseBody
	public List<Integer>lastMonth(@PathVariable("selectedDate") String selectedDate){
		
		if(selectedDate.equals("undefined")){
			System.out.println("Nic nie wybrales");
			
		}
		else{
			System.out.println("Wszedleeeeeeeeeeeeeeeeem: "+ selectedDate);			
		}
		List<Integer>lastMonth = widgetService.lastMont(selectedDate);
		return lastMonth;
	}
	
	@RequestMapping(value="/user/lastMonth", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	@ResponseBody
	public List<Integer>lastMonthh(@PathVariable("selectedDate") String selectedDate){


		return widgetService.lastMont(selectedDate);
	}
	
	// DOKONCZ ROBIC!!!
	@RequestMapping(value="/widget/mostPopularRecipesThisDay", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
//	@ResponseBody
	public List<MostPopularRecipesThisDay> mostPopularRecipesThisDay(){

		Date date = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    String dateToPass = "";
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    
	    if( day<10 ){
	    	dateToPass = year+"-"+month+"-0"+day;
	    }
	    else{
	    	dateToPass = year+"-"+month+"-"+day;
	    }	    
	    
	    List<MostPopularRecipesThisDay> x = widgetService.mostPopularRecipesThisDay(dateToPass);
		return x;
	}	
}
