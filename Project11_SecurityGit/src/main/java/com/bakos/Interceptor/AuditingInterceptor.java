package com.bakos.Interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
@Component
public class AuditingInterceptor extends  HandlerInterceptorAdapter{

	Logger logger = Logger.getLogger("auditLogger");
	private String user;
	private String recipeName;
	
	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'o' hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		
		return formatter.format(calendar.getTime());
	}
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if( request.getRequestURI().endsWith("/user/recipes/addRecipe") && request.getMethod().equals("POST") ){
			this.user = request.getRemoteUser();
			this.recipeName = request.getParameterValues("name")[0];
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if( request.getRequestURI().endsWith("/user/recipes/addRecipe") && response.getStatus()==302 ){
			logger.info(String.format("Nowy przepis [%s] dodany przez [%s] dnia [%s]", recipeName, user, getCurrentTime()));
		}
		
	}
}
