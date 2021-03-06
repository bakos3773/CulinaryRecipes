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
    width: 20vw;
    height: 50vh;
    padding: 0px;
}
.sizeWidth2 {
    width: 30vw;
    height: 50vh;	
    padding: 0px;
}
.sizeWidth3 {
    width: 15vw;
    height: 35vh;    
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

  #pudelko {
    width: 20vw;
     height: 50vh;
    margin:0 auto;

    -webkit-perspective:400px;
  }
      
#pudelko2 {
	 width: 30vw;
	 height: 50vh;		
	 margin:0 auto;
	 -webkit-perspective:400px;
}  
      
#pudelko3 {
	width: 15vw;
	height: 35vh;		
	margin:0 auto;
	-webkit-perspective:400px;
}            

      #karta {
        position:relative;
        width:100%;
        height:100%;
        text-align:center;
        transition:1s;
        -webkit-transition:1s;
        transform-style:preserve-3d;
        -webkit-transform-style:preserve-3d;
      }

      #pudelko:hover > #karta {
        transform:rotateY(180deg);
        -webkit-transform:rotateY(180deg);
      }
      #pudelko2:hover > #karta {
        transform:rotateY(180deg);
        -webkit-transform:rotateY(180deg);
      }      
      #pudelko3:hover > #karta {
        transform:rotateY(180deg);
        -webkit-transform:rotateY(180deg);
      }        

      #przod {
        position:absolute;
        width:100%;
        height:100%;
   
        box-shadow:0 0 4px 8px #FFF inset;
        backface-visibility:hidden;
        -webkit-backface-visibility:hidden;
      }

      #tyl {
        position:absolute;
        width:100%;
        height:100%;
        line-height:30px;
        
        padding-top:20px;
        color:#FFF;
        font-size:1.0em;
        background-color:#000;
        box-shadow:0 0 4px 8px #FFF inset;
        transform:rotateY(180deg);
        -webkit-transform:rotateY(180deg);
        backface-visibility:hidden;
        -webkit-backface-visibility:hidden;
      } 
</style>   
<div ng-controller="homeController">    
   <div id="recipeView"> 
	   <ul>
		      <li><a href="" ng-click="setView('big', '2')"><span class="glyphicon glyphicon-th-large"></span></a></li>
		      
		      <li><a href="" ng-click="setView('normal', '4')"><span class="glyphicon glyphicon-th-list"></span></a></li>
		      
		      <li><a href="" ng-click="setView('small', '6')"><span class="glyphicon glyphicon-th"></span></a></li>
	
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
		    </label>&nbsp;	
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Potrawy miesne" checked="checked">Potrawy miesne
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Desery" checked="checked">Desery
		    </label>&nbsp;	    
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Dla dzieci" checked="checked">Dla dzieci
		    </label>&nbsp;
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Grzyby" checked="checked">Grzyby
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Kasza i ryz" checked="checked">Kasza i ryz
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Makarony" checked="checked">Makarony
		    </label>&nbsp;		    
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Maczne" checked="checked">Maczne
		    </label>&nbsp;
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Na grilla" checked="checked">Na grilla
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Pasty" checked="checked">Pasty
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Przetwory" checked="checked">Przetwory
		    </label>&nbsp;		    
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Ryby i owoce morza" checked="checked">Ryby i owoce morza
		    </label>&nbsp;
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Salatki" checked="checked">Salatki
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Sosy" checked="checked">Sosy
		    </label>&nbsp;	
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Pieczywo" checked="checked">Pieczywo
		    </label>&nbsp;		    
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Pizze" checked="checked">Pizze
		    </label>&nbsp;
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Przekosaski" checked="checked">Przekosaski
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Swiateczne" checked="checked">Swiateczne
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Warzywa" checked="checked">Warzywa
		    </label>&nbsp;		    
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Wegetarianskie" checked="checked">Wegetarianskie
		    </label>&nbsp;
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Zupy" checked="checked">Zupy
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Diety" checked="checked">Diety
		    </label>&nbsp;		
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Napoje" checked="checked">Napoje
		    </label>&nbsp;		    
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Alkohole" checked="checked">Alkohole
		    </label>&nbsp;
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Kuchnie swiata" checked="checked">Kuchnie swiata
		    </label>&nbsp;	
		    <label>
		      <input type="checkbox" name="typeOfRecipes" value="Inne przepisy" checked="checked">Inne przepisy
		    </label>&nbsp;		
		    <input type="submit" value="OK">	    
        </form>

      </div>
    </div>
  </div>
</div>

<table id="recipeTable" class="table inventory_related" cellspacing="0" width="100%">
<tbody>

		<tr ng-repeat="recipe in recipes | orderBy:'date':true" ng-if="$index%3==0" ng-show="typeOfView=='normal'">
			<td>
				<span ng-if="recipes[$index].id">
					<a ng-click="ratingRecipe(recipes[$index].id);" href="<spring:url value="/user/recipes/show/{{recipes[$index].id}}"></spring:url>">		 				
					    <div id="pudelko">
					      <div id="karta">
					        <div id="przod"><img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index].id}}" class="sizeWidth"/></div>
					        <div id="tyl">				        
					        	<span class="glyphicon glyphicon-time"></span> {{recipes[$index].timeToPrepare}}min &nbsp; <span class="glyphicon glyphicon-stats"></span> {{recipes[$index].level}}
					        </div>
					      </div>
					    </div>		 		
		 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index].name}}</span></b></h4></p>
		 			</a>		 			
				</span>
			</td>
			<td>
				<span ng-if="recipes[$index+1].id">
					<a ng-click="ratingRecipe(recipes[$index+1].id);" href="<spring:url value="/user/recipes/show/{{recipes[$index+1].id}}"></spring:url>">		 				
					    <div id="pudelko">
					      <div id="karta">
					        <div id="przod"><img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+1].id}}" class="sizeWidth"/></div>
					        <div id="tyl"><span class="glyphicon glyphicon-time"></span> {{recipes[$index+1].timeToPrepare}}min &nbsp; <span class="glyphicon glyphicon-stats"></span> {{recipes[$index+1].level}}</div>
					      </div>
					    </div>		 		
		 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+1].name}}</span></b></h4></p>
		 			</a>		 			
				</span>
			</td>			
			<td>
				<span ng-if="recipes[$index+2].id">
					<a ng-click="ratingRecipe(recipes[$index+2].id);" href="<spring:url value="/user/recipes/show/{{recipes[$index+2].id}}"></spring:url>">		 				
					    <div id="pudelko">
					      <div id="karta">
					        <div id="przod"><img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+2].id}}" class="sizeWidth"/></div>
					        <div id="tyl"><span class="glyphicon glyphicon-time"></span> {{recipes[$index+2].timeToPrepare}}min &nbsp; <span class="glyphicon glyphicon-stats"></span> {{recipes[$index+2].level}}</div>
					      </div>
					    </div>		 		
		 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+2].name}}</span></b></h4></p>
		 			</a>		 			
				</span>
			</td>			
		</tr>



		<tr ng-repeat="recipe in recipes" ng-if="$index%2==0"  ng-show="typeOfView=='big'">
			<td>
				<span ng-if="recipes[$index].id">
					<a ng-click="ratingRecipe(recipes[$index].id);" href="<spring:url value="/user/recipes/show/{{recipes[$index].id}}"></spring:url>">		 				
					    <div id="pudelko2">
					      <div id="karta">
					        <div id="przod"><img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index].id}}" class="sizeWidth2"/></div>
					        <div id="tyl"><span class="glyphicon glyphicon-time"></span> {{recipes[$index].timeToPrepare}}min &nbsp; <span class="glyphicon glyphicon-stats"></span> {{recipes[$index].level}}</div>
					      </div>
					    </div>		 		
		 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index].name}}</span></b></h4></p>
		 			</a>		 			
				</span>
			</td>
			<td>
				<span ng-if="recipes[$index+1].id">
					<a ng-click="ratingRecipe(recipes[$index+1].id);" href="<spring:url value="/user/recipes/show/{{recipes[$index+1].id}}"></spring:url>">		 				
					    <div id="pudelko2">
					      <div id="karta">
					        <div id="przod"><img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+1].id}}" class="sizeWidth2"/></div>
					        <div id="tyl"><span class="glyphicon glyphicon-time"></span> {{recipes[$index+1].timeToPrepare}}min &nbsp; <span class="glyphicon glyphicon-stats"></span> {{recipes[$index+1].level}}</div>
					      </div>
					    </div>		 		
		 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+1].name}}</span></b></h4></p>
		 			</a>		 			
				</span>
			</td>
		</tr>


		<tr ng-repeat="recipe in recipes" ng-if="$index%4==0"  ng-show="typeOfView=='small'">
			<td>
				<span ng-if="recipes[$index].id">
					<a ng-click="ratingRecipe(recipes[$index].id);" href="<spring:url value="/user/recipes/show/{{recipes[$index].id}}"></spring:url>">		 				
					    <div id="pudelko3">
					      <div id="karta">
					        <div id="przod"><img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index].id}}" class="sizeWidth3"/></div>
					        <div id="tyl"><span class="glyphicon glyphicon-time"></span> {{recipes[$index].timeToPrepare}}min &nbsp; <span class="glyphicon glyphicon-stats"></span> {{recipes[$index].level}}</div>
					      </div>
					    </div>		 		
		 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index].name}}</span></b></h4></p>
		 			</a>		 			
				</span>
			</td>
			<td>
				<span ng-if="recipes[$index+1].id">
					<a ng-click="ratingRecipe(recipes[$index+1].id);" href="<spring:url value="/user/recipes/show/{{recipes[$index+1].id}}"></spring:url>">		 				
					    <div id="pudelko3">
					      <div id="karta">
					        <div id="przod"><img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+1].id}}" class="sizeWidth3"/></div>
					        <div id="tyl"><span class="glyphicon glyphicon-time"></span> {{recipes[$index+1].timeToPrepare}}min &nbsp; <span class="glyphicon glyphicon-stats"></span> {{recipes[$index+1].level}}</div>
					      </div>
					    </div>		 		
		 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+1].name}}</span></b></h4></p>
		 			</a>		 			
				</span>
			</td>
			<td>
				<span ng-if="recipes[$index+2].id">
					<a ng-click="ratingRecipe(recipes[$index+2].id);" href="<spring:url value="/user/recipes/show/{{recipes[$index+2].id}}"></spring:url>">		 				
					    <div id="pudelko3">
					      <div id="karta">
					        <div id="przod"><img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+2].id}}" class="sizeWidth3"/></div>
					        <div id="tyl"><span class="glyphicon glyphicon-time"></span> {{recipes[$index+2].timeToPrepare}}min &nbsp; <span class="glyphicon glyphicon-stats"></span> {{recipes[$index+2].level}}</div>
					      </div>
					    </div>		 		
		 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+2].name}}</span></b></h4></p>
		 			</a>		 			
				</span>
			</td>	
			<td>
				<span ng-if="recipes[$index+3].id">
					<a ng-click="ratingRecipe(recipes[$index+3].id);" href="<spring:url value="/user/recipes/show/{{recipes[$index+3].id}}"></spring:url>">		 				
					    <div id="pudelko3">
					      <div id="karta">
					        <div id="przod"><img src="/ProjectSecurityGit/user/recipes/images/{{recipes[$index+3].id}}" class="sizeWidth3"/></div>
					        <div id="tyl"><span class="glyphicon glyphicon-time"></span> {{recipes[$index+3].timeToPrepare}}min &nbsp; <span class="glyphicon glyphicon-stats"></span> {{recipes[$index+3].level}}</div>
					      </div>
					    </div>		 		
		 				<p><h4><b><span style="max-width: 80%; text-overflow: ellipsis; break-word: break-word ; display: block;  overflow: hidden">{{recipes[$index+3].name}}</span></b></h4></p>
		 			</a>		 			
				</span>
			</td>					
		</tr>

</tbody>
</table>
	<div ng-init="paginationIndexFunction();">
	    <ul class="pagination" ng-repeat="n in [].constructor(paginationIndex) track by $index">
	         <li ><a href="" ng-click="loadAllRecipes($index +1)"> {{ $index +1 }}</a></li>  
	    </ul>
	</div>
</div>