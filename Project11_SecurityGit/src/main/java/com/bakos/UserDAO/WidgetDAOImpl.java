package com.bakos.UserDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;


//import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bakos.Service.UserService;
import com.bakos.UserDTO.CulinaryRecipes;
import com.bakos.UserDTO.MostPopularRecipesThisDay;
import com.bakos.UserDTO.Statistics;

@Repository
@Transactional
public class WidgetDAOImpl implements WidgetDAO {
	
	@PersistenceContext
	private EntityManager manager;	
	
	@Autowired
	UserService userService;

	@Override
	@SuppressWarnings("unchecked")	
	public List<Integer> lastMont(String year_month) {
		System.out.println("year_month="+year_month);
	
		Date todayDay = new Date();
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(todayDay);
	    
	    List<Integer> listOfDays = new ArrayList<Integer>();
	    listOfDays.add(0);
	    	Query month = null;
			
			if(  year_month.equals("undefined") ){
				if( (calendar.get(Calendar.MONTH)+1) > 9){
					month = manager.createQuery("Select x.date From Users u INNER JOIN u.culinaryRecipes c INNER JOIN c.statistics x WHERE SUBSTRING(x.date, 6, 2) = :pattern AND u.id= :id");
					month.setParameter("pattern", (calendar.get(Calendar.MONTH)+1)+"");		
					month.setParameter("id", userService.findUserByUsername().getId());
				}else{
					month = manager.createQuery("Select x.date From Users u INNER JOIN u.culinaryRecipes c INNER JOIN c.statistics x WHERE SUBSTRING(x.date, 7, 1) = :pattern AND u.id= :id");
					month.setParameter("pattern", (calendar.get(Calendar.MONTH)+1)+"");	
					month.setParameter("id", userService.findUserByUsername().getId());
				}				
			}
			else{
				month = manager.createQuery("Select x.date From Users u INNER JOIN u.culinaryRecipes c INNER JOIN c.statistics x WHERE SUBSTRING(x.date, 1, 7) = :pattern AND u.id= :id");
				month.setParameter("pattern", year_month);	   
				month.setParameter("id", userService.findUserByUsername().getId());	 
			}

			List<Date> result = month.getResultList();
			
			Calendar cal = Calendar.getInstance();
			
			int ile=0;

			for(int j=2; j<=31; j++){
				for(int i=0; i<result.size(); i++){
			
					cal.setTime(result.get(i));
					int day = cal.get(Calendar.DAY_OF_MONTH);	
					if(day == j ){
						ile++;
					}				
				}
				listOfDays.add(ile);
				ile = 0;
			}	
	    	return listOfDays;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MostPopularRecipesThisDay> mostPopularRecipesThisDay(String searchingDate){
		
		
		Query daysTab = manager.createQuery("SELECT c.id FROM Users u INNER JOIN u.culinaryRecipes c INNER JOIN c.statistics x WHERE u.id= :id AND  SUBSTRING(x.date, 1, 10)= :searchingDate");			
							
		daysTab.setParameter("id", userService.findUserByUsername().getId());	
		daysTab.setParameter("searchingDate", searchingDate);		
		
		List<Integer> statisticsThisDay = daysTab.getResultList();
		Set<Integer> uniqueGas = new HashSet<Integer>(statisticsThisDay);
		List<Integer> uniqueId = new ArrayList<Integer>(uniqueGas);
		List<Integer> copyStatisticsThisDay = daysTab.getResultList();
		List<MostPopularRecipesThisDay> list = new ArrayList<MostPopularRecipesThisDay>();
		
		int counter = 0;
		
		for( int i=0; i<uniqueId.size(); i++ ){
			
			for( int j=0; j<copyStatisticsThisDay.size(); j++ ){
				if(uniqueId.get(i).intValue() == copyStatisticsThisDay.get(j).intValue()){
					counter++;
				}
			}
			Query queryName = manager.createQuery("SELECT x.name FROM CulinaryRecipes x WHERE x.id= :id");
			queryName.setParameter("id", uniqueId.get(i));
			List<Integer> values = new ArrayList<Integer>();
			values.add(counter);
			list.add(new MostPopularRecipesThisDay(values, (String) queryName.getSingleResult()));
			counter = 0;
		}
		
		return list;
	}

}
