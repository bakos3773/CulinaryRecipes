<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
    	
 		<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>	
		
		<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">		  
		<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>						
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>				
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
		<script type="text/javascript" src="/ProjectSecurityGit/resources/js/jqueryFile.js"></script>
		   
<script type="text/javascript">

</script>
<style type="text/css">
.inventory_related thead {
  display: none;
}
.sizeWidth {
    width: 30vw;
    height: 50vh;
    padding: 0px;
}
.sizeWidth2 {
    width: 40vw;
    padding: 0px;
}
.sizeWidth3 {
    width: 20vw;
    padding: 0px;
}
#recipeView > ul > li {
list-style-type:none;
   float:left;
 }
#recipeView > ul > li > a {
   display:block;
   padding:10px;
   text-decoration:none;
   text-align:center;
   color:#000;
   background-color:#DDD;
   box-shadow:0 0 0 1px #FFF inset;
   background-image:linear-gradient(#FFF 0%, #EEE 30%, #DDD 70%, #FFF 100%);
 }
#recipeView > ul > li:hover > a {
   color:#00F;
   background-color:#FFF;
   background-image:none;
 }
</style>   
<div ng-controller="homeController">    
   <div id="recipeView"> 
	   <ul>
		      <li><a href="" ng-click="setView('big')"><span class="glyphicon glyphicon-th-large"></span></a></li>
		      
		      <li><a href="" ng-click="setView('normal')"><span class="glyphicon glyphicon-th-list"></span></a></li>
		      
		      <li><a href="" ng-click="setView('small')"><span class="glyphicon glyphicon-th"></span></a></li>
	
	    </ul>
		<h1 align="center"><span style="font-family: fantasy;">All Recipes</span></h1>
	</div>
<div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
          <h4 align="right">FILTRY</h4>
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body" style="background-color: black;">
        SELECT TYPE:
        
        <form id="typeRecipes" ng-submit="showTypes()">
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Ciasta" checked="checked">Ciasta
		    </label>		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Potrawy miesne" checked="checked">Potrawy miesne
		    </label>		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Surowki" checked="checked">Surowki
		    </label>		    
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Zupy" checked="checked">Zupy
		    </label>			    
		    <input type="submit" value="OK">	    
        </form>

      </div>
    </div>
  </div>
</div>

<table id="recipeTable" class="table inventory_related" cellspacing="0" width="100%">
<thead>
<!-- 	<tr>
		<th></th>
		<th></th>
		<th></th>
	</tr> -->
</thead>
<tfoot>
<!-- 	<tr>
		<th></th>
		<th></th>
		<th></th>
	</tr> -->
</tfoot>
<tbody>


		<tr ng-repeat="recipe in recipes | orderBy:'date':true" ng-if="$index%3==0" ng-show="typeOfView=='normal'">
			<td>
				<span ng-if="recipes[$index].id">
					<a href="<spring:url value="/user/recipes/show/{{recipes[$index].id}}"></spring:url>">
	 				<img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index].id}}" class="sizeWidth"/>
	 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index].name}}</span></b></h4></p>
		 			</a>
		 			<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
				</span>
			</td>
			<td>
				<span ng-if="recipes[$index+1].id">
					<a href="<spring:url value="/user/recipes/show/{{recipes[$index+1].id}}"></spring:url>">
	 				<img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+1].id}}" class="sizeWidth"/>
	 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+1].name}}</span></b></h4></p>
		 			</a>
		 			<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
				</span>
			</td>			
			<td>
				<span ng-if="recipes[$index+2].id">
					<a href="<spring:url value="/user/recipes/show/{{recipes[$index+2].id}}"></spring:url>">
	 				<img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+2].id}}" class="sizeWidth"/>
	 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+2].name}}</span></b></h4></p>
		 			</a>
		 			<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
				</span>
			</td>			
		</tr>



		<tr ng-repeat="recipe in recipes" ng-if="$index%2==0"  ng-show="typeOfView=='big'">
			<td>
				<span ng-if="recipes[$index].id">
					<a href="<spring:url value="/user/recipes/show/{{recipes[$index].id}}"></spring:url>">
	 				<img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index].id}}" class="sizeWidth2"/>
	 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index].name}}</span></b></h4></p>
		 			</a>
		 			<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
				</span>
			</td>
			<td>
				<span ng-if="recipes[$index+1].id">
					<a href="<spring:url value="/user/recipes/show/{{recipes[$index+1].id}}"></spring:url>">
	 				<img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+1].id}}" class="sizeWidth2"/>
	 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+1].name}}</span></b></h4></p>
		 			</a>
		 			<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
				</span>
			</td>
		</tr>


		<tr ng-repeat="recipe in recipes" ng-if="$index%4==0"  ng-show="typeOfView=='small'">
			<td>
				<span ng-if="recipes[$index].id">
					<a href="<spring:url value="/user/recipes/show/{{recipes[$index].id}}"></spring:url>">
	 				<img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index].id}}" class="sizeWidth3"/>
	 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index].name}}</span></b></h4></p>
		 			</a>
		 			<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
				</span>
			</td>
			<td>
				<span ng-if="recipes[$index+1].id">
					<a href="<spring:url value="/user/recipes/show/{{recipes[$index+1].id}}"></spring:url>">
	 				<img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+1].id}}" class="sizeWidth3"/>
	 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+1].name}}</span></b></h4></p>
		 			</a>
		 			<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
				</span>
			</td>			
			<td>
				<span ng-if="recipes[$index+2].id">
					<a href="<spring:url value="/user/recipes/show/{{recipes[$index+2].id}}"></spring:url>">
	 				<img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+2].id}}" class="sizeWidth3"/>
	 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+2].name}}</span></b></h4></p>
		 			</a>
		 			<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
				</span>
			</td>	
			<td>
				<span ng-if="recipes[$index+3].id">
					<a href="<spring:url value="/user/recipes/show/{{recipes[$index+3].id}}"></spring:url>">
	 				<img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+3].id}}" class="sizeWidth3"/>
	 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+3].name}}</span></b></h4></p>
		 			</a>
		 			<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
				</span>
			</td>					
		</tr>

</tbody>
</table>
</div>