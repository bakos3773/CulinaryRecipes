<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
<div ng-controller="recipeCtrl">
<%-- 	<c:forEach items="${myRecipes}" var="recipe"> --%>
<div  ng-repeat="recipe in allRecipe">
		<hr/>
		<div>
				<hr/>
			<div><img src="<c:url value="/user/recipes/images/{{recipe.id}}"></c:url>" style="max-width: 300px; max-height: 300px;" alt="image"/></div>
			<div><h4><b>Type:</b>	<c:out value="{{recipe.type}}"/></h4> </div>
			<div><h4><b>Name:</b>	<i><c:out value="{{recipe.name}}"/></i></h4> </div>
			<div><h4><b>Components:</b>	<i><c:out value="{{recipe.components}}"/></i></h4> </div>
			<div><h4><b>How to perform:</b>	<i><c:out value="{{recipe.howToPerform}}"/></i></h4> </div>
		</div>
		<button type="button" class="btn btn-primary btn-lg" ng-click="getRecipe(recipe.id)">
		  Edit
		</button>
		<button type="button" class="btn btn-danger btn-lg" ng-click="removeItem(recipe.id)">
			Delete
		</button>
</div>
<%-- 	</c:forEach> --%>

<!-- ************************************************************************************************ -->
<form:form class="form-horizontal" role="form" modelAttribute="recipe" action="{{recipeUrl}}" method="POST">
<div class="modal fade" id="editRecipeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Edit</h4>
      </div>
      <div class="modal-body">
      
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="type">Type:</label>
	      <div class="col-sm-6">
			<%-- <form:input id="type" path="type" class="form-control" placeholder="Enter type"/> --%>
			<form:select id="type" path="type" class="form-control">
				<form:option value="{{recipes.type}}">{{recipes.type}}</form:option>
			</form:select>			
	      </div>
	      
	    </div>
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="name">Name:</label>
	      <div class="col-sm-6">
			<form:input id="name" path="name" class="form-control" value="{{recipes.name}}"/>
	      </div>
	    </div>
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="components">Components:</label>
	      <div class="col-sm-6">
			<form:textarea id="components" path="components" class="form-control" rows="2"/>
	      </div>
	    </div>
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="howToPerform">How to perform:</label>
	      <div class="col-sm-6">
			<form:textarea id="howToPerform" path="howToPerform" class="form-control" rows="6" value="dupa sraka" />
			
	      </div>
	    </div> 
	    <div class="checkbox">
		    <label>
		    	<form:checkbox id="isPrivateRecipe" path="isPrivateRecipe" value="{{recipes.isPrivateRecipe}}"/> Private Recipe
		    </label>
	    </div>    	    	

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button id="btnUpdate" class="btn btn-success"  type="submit">add</button>
      </div>
    </div>
  </div>
</div>
</form:form>

</div>
<script type="text/javascript">
app.controller("recipeCtrl", function($scope, $http, $log){
	
 	$scope.recipe = {
			id: "",
			type: "",
			name: "",
			components: "",
			howToPerform: "",
			isPrivateRecipe: ""
			
	};
 	/* $scope.allRecipe = []; */

	var refreshRecipes = function(){
			$http.get('/ProjectSecurityGit/user/recipes').then(function(response) {
					$scope.allRecipe = response.data;		
			});
	};
	
	refreshRecipes();
	
	$scope.getRecipe = function(id){		
		$scope.recipeUrl = "/ProjectSecurityGit/user/recipes/"+id;		
		$http({
				method: 'GET',
				url: '/ProjectSecurityGit/user/recipes/'+id})
				.then(function(response){
					$scope.recipes = response.data;
					$log.info($scope.recipes);					
					
					document.getElementById('components').innerHTML = $scope.recipes.components;
					document.getElementById('howToPerform').innerHTML = $scope.recipes.howToPerform;
				});
		
		$("#editRecipeModal").modal(function() {
		});
		
	}
	
 	$scope.removeItem = function(id){
 		$http.put('/ProjectSecurityGit/user/recipes/remove/'+id)
		.success(function(data){	
			$log.info(data);
			refreshRecipes();
		}); 
	} 

	$('#btnUpdate').click(function() {	
		$('#editRecipeModal').modal('hide');
		refreshRecipes();
		$scope.$apply();
	});
	

	
});
</script>