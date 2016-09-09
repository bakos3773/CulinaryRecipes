<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
			<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
			<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.js"></script>
			<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-animate.js"></script>
			<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.14.0.js"></script>
			
			<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
			
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
		
<style type="text/css">
body
{
	background-image: url("/ProjectSecurity/resources/images/up.jpg");
	color: white;
}
</style>			
</head>
<body>

		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#" style="color: red;">ADMIN PANEL</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li><a href='<spring:url value="/admin/allUsers"></spring:url>' style="color: red;">All Users</a></li>
		    </ul>
		    <ul class="nav navbar-nav navbar-right">
		      <li><a href="<c:url value="/logout" />" style="color: red;"><span class="glyphicon glyphicon-log-out"></span> LogOut</a></li>
		    </ul>
		  </div>
		</nav>	
		<div class="container">
			<tiles:insertAttribute name="body"/>
		</div>		
</body>
</html>