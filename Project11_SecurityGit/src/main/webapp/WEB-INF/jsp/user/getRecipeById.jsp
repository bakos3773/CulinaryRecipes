<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<hr/>
	<div align="center">	
	<img src="<c:url value="/resources/images/${recipes.id}.jpg"></c:url>" style="max-width: 300px; max-height: 300px;" alt="image"/>
		 <div><h4><b>Type:</b>	<c:out value="${recipes.type}"/> </h4> </div>
		<div><h4><b><i><c:out value="${recipes.name}"/></i></b></h4> </div>
 		<div><h4><b>Components:</b>	<i><c:out value="${recipes.components}"/></i></h4> </div>
		<div><h4><b>How to perform:</b>	<i><c:out value="${recipes.howToPerform}"/></i></h4> </div> 
		<p>
			<a href='<spring:url value="/user/home"></spring:url>' class="btn btn-primary">
				<span class="glyphicon-hand-left glyphicon"></span>
				back
			</a>
		</p>
	</div>
