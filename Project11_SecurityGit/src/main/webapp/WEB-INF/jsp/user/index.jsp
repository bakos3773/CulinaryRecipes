<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-animate.js"></script>
		<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.14.0.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-sanitize.js"></script>
		
		<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
		<script src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	
		<script src="http://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script>	 

			
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<style type="text/css">
#wrapper {    	

    overflow: hidden; /* will contain if #first is longer than #second */
}
#first {
  
    float:left; /* add this */
}
#second {
	width: 300px;
    float: left; /* if you don't want #second to wrap below #first */
}
</style>
	
<script type="text/javascript">

var appTwo = angular.module('appTwo', []);

</script>	

<style type="text/css">
body
{
	background-image: url("/ProjectSecurity/resources/images/tlo.jpg");
	color: white;
}
</style>
</head>
<!-- ******************************************************************  BODY  ************************************* -->
	<body ng-app="appTwo">
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
			<div id="wrapper">
			<div id="first" class="col-md-8">
				<tiles:insertAttribute name="content"/>
			</div>
			<!-- ****************************************************************************************************************** -->
				<div id="second">
					  <ul class="nav nav-tabs">
					    <li class="active"><a data-toggle="tab" href="#home">Najnowsze</a></li>
					    <li><a data-toggle="tab" href="#menu1">Najpopularniejsze</a></li>
					  </ul>
					
					  <div id="second" class="tab-content" align="left" style="padding-left: 10px; padding-top: 10px;">
					    <div id="home" class="tab-pane fade in active">
					      <ul>
						      <c:forEach items="${latest}" var="recipe">
						      		<li><a href='<spring:url value="/getRecipeById/${recipe.id}"></spring:url>' style="color: background;"><c:out value="${recipe.name}"/></a></li>		      
						      </c:forEach>
					      </ul>
					    </div>
					    <div id="menu1" class="tab-pane fade">
					      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
					    </div>
					  </div>
				</div>
			</div>	
		</div>

<!-- **************************************************************  templates ******************************************	 -->

	</body>
</html>


<!-- **************************************************************  templates ******************************************	 -->

<!-- *****************************************************************  KARUZELA  ************************************** -->
