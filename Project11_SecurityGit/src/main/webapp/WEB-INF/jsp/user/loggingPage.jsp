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
<link href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
<script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js" ></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-sanitize.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
<link href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css" rel="stylesheet"/>		

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">		  
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>
<script src = "http://cdn.zingchart.com/zingchart.min.js" ></script>
<script src = "http://cdn.zingchart.com/angular/zingchart-angularjs.js"></script>
<script src= "https://cdn.zingchart.com/zingchart.min.js"></script>

    <script src="http://code.highcharts.com/highcharts.js"></script>
	 

			
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

		<script src = "http://cdn.zingchart.com/zingchart.min.js" ></script>
		
		<script src = "http://cdn.zingchart.com/angular/zingchart-angularjs.js"></script>

<style type="text/css">
body
{
	background-image: url("/ProjectSecurityGit/resources/images/tlo2.jpg");
	color: white;
}
a{
font-family: fantasy;
}
#firtLevel{
position: relative;
top: 50px;
}
.container{
 margin-top: 50px;
 position: absolute;
}
.navbar-inverse .navbar-nav > li > a:hover {
   color: blue; /*Change active text color here*/
}

#secondLevel{
width:100%;
position: absolute;
        margin:0 auto;
        padding:0;
        list-style-type:none;
        background-color:#FDF;
}
 /* naprawa wysokosci elementu - ol */
 #secondLevel:after {
   content:'';
   display:block;
   clear:both;
 }

 /* wyglad element�w - li */
 #secondLevel > ul > li {
   float:left;
 }



 /* wyglad elementow - a */
 #secondLevel > ul > li > a {
   display:block;
   padding:15px;
   text-decoration:none;
   text-align:center;
   color:#000;
   background-color:#DDD;
   box-shadow:0 0 0 1px #FFF inset;
   background-image:linear-gradient(#FFF 0%, #EEE 30%, #DDD 70%, #FFF 100%);
 }

 /* wyglad elementow - a - po najechaniu kursorem myszki na element - li */
#secondLevel > li:hover > a {
   color:#00F;
   background-color:#FFF;
   background-image:none;
 }

 /********************************************************************** */

.dropdown-large {
  position: static !important;
}
.dropdown-menu-large {
  margin-left: 16px;
  margin-right: 16px;
  padding: 20px 0px;
}
.dropdown-menu-large > li > ul {
  padding: 0;
  margin: 0;
}
.dropdown-menu-large > li > ul > li {
  list-style: none;
}
.dropdown-menu-large > li > ul > li > a {
  display: block;
  padding: 3px 20px;
  clear: both;
  font-weight: normal;
  line-height: 1.428571429;
  color: #333333;
  white-space: normal;
}
.dropdown-menu-large > li ul > li > a:hover,
.dropdown-menu-large > li ul > li > a:focus {
  text-decoration: none;
  color: #262626;
  background-color: #f5f5f5;
}
.dropdown-menu-large .disabled > a,
.dropdown-menu-large .disabled > a:hover,
.dropdown-menu-large .disabled > a:focus {
  color: #999999;
}
.dropdown-menu-large .disabled > a:hover,
.dropdown-menu-large .disabled > a:focus {
  text-decoration: none;
  background-color: transparent;
  background-image: none;
  filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);
  cursor: not-allowed;
}
.dropdown-menu-large .dropdown-header {
  color: #428bca;
  font-size: 18px;
}
@media (max-width: 768px) {
  .dropdown-menu-large {
    margin-left: 0 ;
    margin-right: 0 ;
  }
  .dropdown-menu-large > li {
    margin-bottom: 30px;
  }
  .dropdown-menu-large > li:last-child {
    margin-bottom: 0;
  }
  .dropdown-menu-large .dropdown-header {
    padding: 3px 15px !important;
  }
}


</style>
<!-- *******************************************************-->
<script type="text/javascript">

$(document).ready(function() {
    $('.dropdown-toggle').dropdown()
});

var app = angular.module('myApp', ['ngSanitize', 'zingchart-angularjs']);


app.factory('WidgetService', function ($http, $log, $q) {
    
	var listVisitPerMonth = [];
	
	var d = $q.defer();
	
    var getListVisitPerMonth = function (x) {    	
    	return $http.get('/ProjectSecurityGit/user/lastMonth/'+x).then(function(response) {		   
			return response.data;
		});	  	
    };
    var ratingRecipe = function (idRecipe, rate) {    	    	
    	$http.get('/ProjectSecurityGit/user/recipes/'+idRecipe+'/rate/'+rate).then(function(response){
				
		});      	
    };    
    
    return{
    	getListVisitPerMonth: getListVisitPerMonth,
    	ratingRecipe: ratingRecipe
    };
    
});



app.controller("MainController", function($scope, $http, $log, WidgetService) {	
	
	$scope.recipes = {};
	$scope.recipiesComments = [];
	$scope.WidgetService = WidgetService;
	
	var getAllLastMonth = function(){
		$http.get('/ProjectSecurityGit/user/lastMonth').then(function(response) {
			$scope.lastMonth = response.data;	
			 $log.info($scope.lastMonth); 
			 alert("wpisanie zmiennych do tablicy");
			 return $scope.lastMonth;
		});		
	}
	
	$scope.addCommentToThisRecipipe = function(id){
		$http.post('/ProjectSecurityGit/user/recipes/addComment/'+id, document.getElementById("conmment").value).then(function(response){
		});
	}
	
	$scope.loadAllRecipesComments = function(id){
		$http.get('/ProjectSecurityGit/user/recipes/loadAllRecipesComments/'+id).then(function(response){
			 $scope.recipiesComments = response.data; 
			 console.log($scope.recipiesComments); 
		});
	}	
	/* ***************************************************************************	RATING experiment */
    $scope.rating = 0;
    $scope.ratings = [{
        current: 3,
        max: 5
    }];
    

    $scope.getSelectedRating = function (rating) {
        console.log(rating);
    }

/* ***************************************************************************   */  	
});	

app.directive('starRating', function (WidgetService) {
    return {
        restrict: 'EA',
        template: '<ul class="rating">' +
            '<li ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)">' +
            '\u2605' +
            '</li>' +
            '</ul>',
        scope: {
            ratingValue: '=',
            max: '=',
            onRatingSelected: '&',
            readonly: '=?'
        },
        link: function (scope, elem, attrs, $http) {

            var updateStars = function () {
                scope.stars = [];
                for (var i = 0; i < scope.max; i++) {
                    scope.stars.push({
                        filled: i < scope.ratingValue
                    });
                }
               
            };

            scope.toggle = function (index) {
            	
                if (scope.readonly == undefined || scope.readonly === false){
                    scope.ratingValue = index + 1;
                    scope.onRatingSelected({
                        rating: index + 1
                    });
                    alert("Twoja ocena "+scope.ratingValue);
                    scope.addRatingToRecipe(scope.ratingValue)
                  }    
            };
            
            scope.addRatingToRecipe = function(rate){
            	var href = window.location.pathname;
            	var idRecipe =  href.substr(href.lastIndexOf('/') + 1);      
            	WidgetService.ratingRecipe(idRecipe, rate);
            };

            scope.$watch('ratingValue', function (oldVal, newVal) {
                if (newVal) {
                    updateStars();                    
                }
                
            });
        }
    }
});

app.controller("homeController", function($scope, $http, $log, WidgetService) {	
	
	$scope.WidgetService = WidgetService;
	
	$scope.recipes = [];
	$scope.typeOfView = "normal";
	
    $scope.rating = 0;
    $scope.ratings = [{
        current: 3,
        max: 5
    }];	


	var loadAllRecipes = function(){
		$http.get('/ProjectSecurityGit/user/recipes/loadAllRecipes').then(function(response){
			/* console.log(response.data); */
			$scope.recipes = response.data;
		});
	}	
	
	loadAllRecipes();	
	
  $scope.showTypes = function() {
	  $scope.listAllTypes = [];
	  var typeOfRecipes = document.forms.namedItem("typeRecipes");
	  var i;	  	  
	  for(i=0; i<typeOfRecipes.length; i++){
		  if(typeOfRecipes[i].checked){			
			  $scope.listAllTypes.push(typeOfRecipes[i].value);
		  }
	  }	 
	   $http.post("recipes/checkedTypes", $scope.listAllTypes).success(function($dane){    	    	 
	    	 $scope.recipes = $dane;
	    	 console.log( $scope.recipes);
	  }); 
  	};
  
  	
	$scope.dropdownType = function(type){
		   $http.get("recipes/dropdownTypes/"+type).success(function($dane){
			    $scope.recipes = $dane;
		    	 console.log( $scope.recipes);
		  }); 		  
	}
	
	$scope.setView = function(view){
		$scope.typeOfView = view;
	}
	
	$scope.searchRecipe = function(searchingTxt){
		$http.get('/ProjectSecurityGit/user/recipes/serchingRecipes/'+searchingTxt).then(function(response) {
				console.log(response);
		});
	}

	
});
</script>	

</head>
<body ng-app="myApp">
<div id="firtLevel">
		<nav class="navbar navbar-inverse navbar-fixed-top">
		  <div class="container-fluid">
		  <security:authorize access="hasRole('ROLE_USER')">
			    <div class="navbar-header">
			      <a class="navbar-brand" style="color: purple;">[ <security:authentication property="principal.username"/> ]</a>
			    </div>
		   </security:authorize>
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
	      			<li><a href='<spring:url value="/user/recipes/addRecipe"></spring:url>'><span class="glyphicon glyphicon-pushpin"></span> Add Recipe</a></li>

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
   		      <ul class="nav navbar-nav">
	   		      <form ng-controller="homeController" class="navbar-form ng-pristine ng-valid" role="search">
					        <div class="form-group">
					          <input type="text" ng-model="searching" class="form-control" placeholder="Search" style="width: 140px;">
					        </div>
					        
					        <a href="<spring:url value="/user/recipes/serchingRecipes/{{searching}}"></spring:url>" type="submit"  ng-click="searchRecipe(searching)" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></a>
				  </form>
			  </ul>
			    <ul class="nav navbar-nav navbar-right">
					 
					    <!--<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">					      
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
				    		
		    			</ul> -->
			      	<li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out"></span></a></li>
			    </ul>
 		      </security:authorize>
		  </div>
		 	
		</nav>
			
		<div id="secondLevel" ng-controller="homeController"><!-- -------------------------------------------------------------------------------------- -->
 			<div class="collapse navbar-collapse js-navbar-collapse">
				<ul class="nav navbar-nav">
				           
			      <li><a href="#">Ciasta</a></li>
			      <li><a href="#">Dania miesne</a></li>
			      <li><a href="#">Desery</a></li>
			      <li><a href="#">Dla dzieci</a></li>
			      <li><a href="#">Grzyby</a></li>
			      <li><a href="#">Kasza i ryz</a></li>
			      <li><a href="#">Makarony</a></li>
			      <li><a href="#">Maczne</a></li>
			      <li><a href="#">Na grilla</a></li>
			      
					<li class="dropdown dropdown-large">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Rozwin <b class="caret"></b></a>
               
						<ul class="dropdown-menu dropdown-menu-large row">
							<li class="col-sm-3">
								<ul>
									<li class="dropdown-header"><a href="" ng-click="dropdownType('Ciasta')">Ciasta</a></li>
									<li class="dropdown-header"><a href="" ng-click="dropdownType('Dania miesne')">Dania miesne</a></li>
									<li class="dropdown-header"><a href="">Desery</a></li>
									<li class="dropdown-header"><a href="">Dla dzieci</a></li>
									<li class="divider"></li>
									<li class="dropdown-header"><a href="">Kasza i ryz</a></li>
									<li class="dropdown-header"><a href="">Makarony</a></li>
									<li class="dropdown-header"><a href="">Maczne</a></li>
								</ul>
							</li>
							<li class="col-sm-3">
								<ul>
									<li class="dropdown-header"><a href="">Salatki</a></li>
									<li class="dropdown-header"><a href="">Sosy</a></li>
									<li class="divider"></li>
									<li class="dropdown-header"><a href="">Pasty</a></li>
									<li class="dropdown-header"><a href="">Pieczywo</a></li>
									<li class="dropdown-header"><a href="">Pizze</a></li>
									<li class="dropdown-header"><a href="">Przekaski</a></li>
									<li class="dropdown-header"><a href="">Grzyby</a></li>
								</ul>
							</li>
							<li class="col-sm-3">
								<ul>
									<li class="dropdown-header"><a href="" >Swiateczne</a></li>
									<li class="dropdown-header"><a href="">Warzywa</a></li>
									<li class="dropdown-header"><a href="">Wegetarianskie</a></li>
									<li class="dropdown-header"><a href="" ng-click="dropdownType('Zupy')">Zupy</a></li>
									<li class="divider"></li>
									<li class="dropdown-header"><a href="">Przetwory</a></li>
									<li class="dropdown-header"><a href="">Ryby i owoce morza</a></li>
								</ul>
							</li>
							<li class="col-sm-3">
								<ul>
									<li class="dropdown-header"><a href="">Na grilla</a></li>
									<li class="dropdown-header"><a href="">Napoje</a></li>																										
									<li class="dropdown-header"><a href="">Alkohole</a></li>		
									<li class="divider"></li>							
									<li class="dropdown-header"><a href="">Kuchnie swiata</a></li>
									<li class="dropdown-header"><a href="">Diety</a></li>
									<li class="dropdown-header"><a href="">Inne przepisy</a></li>								
								</ul>
							</li>
						</ul>						
					</li>
				
					
			 	</ul>		
			</div>	
		</div><!-- -------------------------------------------------------------------------------------------------------->
		
		<div class="container">
			<tiles:insertAttribute name="body"/>
		</div>		
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