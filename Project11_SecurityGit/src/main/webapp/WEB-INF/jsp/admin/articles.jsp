<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

		<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
		<script src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
		<script src="http://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	
			
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

<h2><b><i>A R T I C L E S</i></b></h2>
<div ng-controller="articlesCtrl">
<table id="example" class="table table-striped table-bordered" cellspacing="10" width="100%">
  <thead>
    <tr>
      <th>Data dodania</th>
      <th>Artykul</th>
      <th>EDIT</th>
    </tr>
  </thead>
  <tbody>
       <tr ng-repeat="x in allArtivles">
		  <td>{{x.date}}</td>
	      <td ng-bind-html="x.name"> </td> 
	      <td><button type="button" class="btn btn-danger btn-lg" ng-click="removeItem(x.id)">Delete</button></td>    
	   </tr>
  </tbody>
</table>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $('#example').DataTable();
} );
</script>   
<script type="text/javascript">
app.controller("articlesCtrl", function($scope, $http, $log){
	
	var refreshRecipes = function(){
		$http.get('/ProjectSecurity/admin/allArticles').then(function(response) {
				$scope.allArtivles = response.data;
				
		});
	};
	
	refreshRecipes();
	
	$scope.removeItem = function(id){
 		$http.put('/ProjectSecurity/admin/articles/remove/'+id)
		.success(function(data){	
			$log.info(data);
			refreshRecipes();
		});  		
	}
	
});
</script>