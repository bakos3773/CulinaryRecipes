	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<div ng-controller="sliderCtrl">	
	<div id="carousel-example-generic" class="carousel slide">
	  <!-- Wskazniki w postaci kropek -->
	  <ol class="carousel-indicators">
	    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="3"></li>	    
	  </ol>
	
	  <!-- Slajdy -->
	  <div class="carousel-inner">
	    <div class="item active" align="center">
	      <img src="<c:url value="/user/recipes/images/{{tabLastRecipes[0].id}}"></c:url>" style="max-width: 300px; max-height: 300px;" alt="image"/>
	      <!-- Opis slajdu -->
	      <div class="carousel-caption">
	        <h3>{{tabLastRecipes[0].name}}</h3>
	        <p>pierwszego slajdu</p>
	      </div>
	    </div>
	
	    <div class="item" align="center">
	       <img src="<c:url value="/images/{{tabLastRecipes[1].id}}"></c:url>" style="max-width: 300px; max-height: 300px;" alt="image"/>
	      <!-- Opis slajdu -->
	      <div class="carousel-caption">
	        <h3>{{tabLastRecipes[1].name}}</h3>
	        <p>drugiego slajdu</p>
	      </div>
	    </div>
	
	    <div class="item" align="center">
	       <img src="<c:url value="/images/{{tabLastRecipes[2].id}}"></c:url>" style="max-width: 300px; max-height: 300px;" alt="image"/>
	      <!-- Opis slajdu -->
	      <div class="carousel-caption">
	        <h3>{{tabLastRecipes[2].name}}</h3>
	        <p>trzeciego slajdu</p>
	      </div>
	    </div>
	    
	    <div class="item" align="center">
	       <img src="<c:url value="/images/{{tabLastRecipes[3].id}}"></c:url>" style="max-width: 300px; max-height: 300px;" alt="image"/>
	      <!-- Opis slajdu -->
	      <div class="carousel-caption">
	        <h3>{{tabLastRecipes[3].name}}</h3>
	        <p>czwartego slajdu</p>
	      </div>
	    </div>	    
	
	  </div>
	
	  <!-- Strzalki do przewijania -->
	  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
	    <span class="icon-prev"></span>
	  </a>
	  <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
	    <span class="icon-next"></span>
	  </a>
	</div>
</div>

<script type="text/javascript">

appTwo.controller('sliderCtrl', function($scope, $http, $log){
	
	$scope.tabLastRecipes = [];
	
	
	var imageInsideSliderFunction = function(){		
		
		 $http.get("/ProjectSecurityGit/aaaa").then(function(response){
			 $scope.tabLastRecipes = response.data;
				$log.info( $scope.tabLastRecipes);
		});	 
	}
	
	imageInsideSliderFunction();
	
});
</script>