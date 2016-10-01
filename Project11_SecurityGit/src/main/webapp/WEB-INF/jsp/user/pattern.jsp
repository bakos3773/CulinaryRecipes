<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- <script src="../../js/mainPage.js"></script> -->
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
			<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
			
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>		
		
<style type="text/css">
body
{
	background-image: url("/ProjectSecurityGit/resources/images/tlo.jpg");
	color: white;
}
</style>			
</head>
<!-- ******************************************************************  BODY  ************************************* -->
	<body  style="background-color: olive;">
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand">LogoStrony</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li><a href='<spring:url value="/"></spring:url>'><span class="glyphicon glyphicon-home"></span> Home</a></li>
		      <li><a href="mailto:p.bakowski1@gmail.com?subject=Przepisnik"><span class="glyphicon glyphicon-earphone"></span> Kontakt</a></li>
		    </ul>
		    <ul class="nav navbar-nav navbar-right">
		      <li><a href='<spring:url value="/register"></spring:url>'><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
		      <li><a href='<spring:url value="/logging"></spring:url>'><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
		    </ul>
		  </div>
		</nav>
		<div class="container">
			<tiles:insertAttribute name="body" />		
		</div>

<!-- **************************************************************  templates ******************************************	 -->

	</body>
</html>