<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
		<script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js" ></script>
	<!-- 	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.js"></script> -->
		<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-animate.js"></script>
		<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.14.0.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-sanitize.js"></script>
		
<script src = "http://cdn.zingchart.com/zingchart.min.js" ></script>

<script src = "http://cdn.zingchart.com/angular/zingchart-angularjs.js"></script>
		
		<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>	
		<script src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	
		<script src="http://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script>	 

			
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">



<script type="text/javascript">
/* $( document ).ready(function() {
	$('[data-submenu]').submenupicker();
}); */

var app = angular.module('myApp', ['ngSanitize', 'zingchart-angularjs']);


app.factory('WidgetService', function ($http,  $log, $q) {
    
	var listVisitPerMonth = [];
	
	var d = $q.defer();
	
    var getListVisitPerMonth = function (x) {
    	
    	return $http.get('/ProjectSecurityGit/user/lastMonth/'+x).then(function(response) {
			
			/*d.resolve(response.data);*/
			/*$log.info(d.promise.$$state.value);*/
			/* alert("x="+x); */			
			listVisitPerMonth = response.data;
			 /* return response.data;
			 /* alert("aaa after"); */			   
			return response.data;
		});	  	
    };
    

    
    return{
    	
    	getListVisitPerMonth: getListVisitPerMonth
    };

    
});



app.controller("MainController", function($scope, $http, $log) {
	
	alert("jestem kontroler login");
	
	var getAllLastMonth = function(){
		$http.get('/ProjectSecurityGit/user/lastMonth').then(function(response) {
			$scope.lastMonth = response.data;	
			 $log.info($scope.lastMonth); 
			 alert("wpisanie zmiennych do tablicy");
			 return $scope.lastMonth;
		});		
	}

});	
</script>	
<style type="text/css">
body
{
	background-image: url("/ProjectSecurityGit/resources/images/tlo2.jpg");
	color: white;
}
.container{
 margin-top: 70px;
}
.navbar-inverse .navbar-nav > li > a:hover {
   color: blue; /*Change active text color here*/
}
</style>
</head>
<body ng-app="myApp">

		<nav class="navbar navbar-inverse navbar-fixed-top">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" style="color: purple;">[ <security:authentication property="principal.username"/> ]</a>
		    </div>
		    <ul class="nav navbar-nav">
		    <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
			      <li><a href='<spring:url value="/user/home"></spring:url>'><span class="glyphicon glyphicon-home"></span> Home</a></li>
			      <li><a href='<spring:url value="/user/recipes/myCulinaryRecipe"></spring:url>'><span class="glyphicon glyphicon-heart"></span> My Recipe</a></li>
		    </security:authorize>
		      <security:authorize access="hasRole('ROLE_USER')">
		      		<li><a href='<spring:url value="/user/settings"></spring:url>'><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
		      </security:authorize>
		      
		      <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_SHADOW')">
		      		<li><a href='<spring:url value="/admin/settings"></spring:url>'  style="color: red;"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
		      </security:authorize>
			  <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
	      			<li><a href='<spring:url value="/user/addRecipe"></spring:url>'><span class="glyphicon glyphicon-pushpin"></span> Add Recipe</a></li>

	     	   </security:authorize>
		      <security:authorize access="hasRole('ROLE_USER')">
	      			<li><a href='<spring:url value="/user/recipes/articles"></spring:url>'>Articles</a></li>
		      </security:authorize>			      
		           	   
		      <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_SHADOW')">
		      		<li><a href='<spring:url value="/admin/articles"></spring:url>'  style="color: red;"><span class="glyphicon glyphicon-cog"></span> Articles</a></li>
		      </security:authorize>	   
		       
		      <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
		      		<li><a href="<spring:url value="/user/forum"></spring:url>"><span class="glyphicon glyphicon-comment"></span> Forum</a></li>
		      </security:authorize>
		      		 	   
		    </ul>
		      <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_SHADOW')">
			    <ul class="nav navbar-nav">
			     	 <li><a href='<spring:url value="/admin/allUsers"></spring:url>' style="color: red;"><span class="glyphicon glyphicon-sunglasses"></span> All Users</a></li>
			    </ul>
		      </security:authorize>
   		      <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_SHADOW')">
			    <ul class="nav navbar-nav navbar-right">
			    
<!-- 					<form class="navbar-search pull-left" action="">
                      <input type="text" class="search-query span2" placeholder="Search">
                    </form>			  -->   
					 
					    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">					      
					      <span class="glyphicon glyphicon-envelope"></span>
					    </button>					    
					    <ul class="dropdown-menu">
					    
					      <li><a href="#" data-toggle="modal" data-target="#myModal">Wyslij wiadomosc</a></li>
					      <li><a href="#">Odczytaj wiadomosc</a></li>
						  <li class="divider"></li>
						  
							<li class="dropdown dropdown-submenu">
					          <a tabindex="0" href="#" class="dropdown-toggle" data-toggle="dropdown" data-submenu>Historia</a>
					          <ul class="dropdown-menu">
					          	<li><a tabindex="0" href="#">Second level link</a></li>			      
							  </ul>							  
				    		</li>
				    		
		    			</ul>
			      	<li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out"></span> LogOut</a></li>
			    </ul>
 		      </security:authorize>
		  </div>
		</nav>	
		<div class="container">
			<tiles:insertAttribute name="body"/>
		</div>		
		
<!-- *********************************************  COMPONENTS  ******************************************************	 -->
<!-- Modal -->
<form:form commandName="message" cssClass="form-horizontal">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Napisz wiadomosc</h4>
      </div>
      <div class="modal-body">
      
			...	    
	    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>	
</form:form>
<!-- ***************************************************************************************************************	 -->
</body>
</html>