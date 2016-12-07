<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-animate.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-sanitize.js"></script>
    <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.3.0.js"></script>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">    
<style type="text/css">
.rating {
    color: #a9a9a9;
    margin: 0;
    padding: 0;
}
ul.rating {
    display: inline-block;
}
.rating li {
    list-style-type: none;
    display: inline-block;
    padding: 1px;
    text-align: center;
    font-weight: bold;
    cursor: pointer;
}
.rating .filled {
    color: red;
}

#commentsDiv> ul > li{
list-style-type: none;
}

#caly {

  border:3px solid #EEE;
}

#caly h1 {
  background:url('http://webkod.pl/img/lewy_cien2d.png') left bottom no-repeat, url('http://webkod.pl/img/prawy_cien2d.png') right bottom no-repeat;
  margin:0 -14px;
  padding-bottom:11px;
}

#caly span {
  display:block;
  text-align:center;
  font-size:20px;
  padding:10px;
  background-color:olive;
  border-radius:6px 6px 0 0;
}

#caly p {
  margin:4px 15px 15px;
  padding:15px;
  border-radius:0 0 6px 6px;

}
</style>
<script type="text/javascript">
$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
</script>    
    
<div ng-controller="MainController" ng-init="loadAllRecipesComments(${recipes.id})">

<div style="color: white;">
	<div class="row">
		<div class="col-sm-3 col-md-4 col-lg-4" style="padding-top: 10px;">
			<p><a href="">Autor: XXX</a></p>
			<p><h2><b><i><span style="font-family: cursive;">${recipes.name}</span></i></b></h2></p>
			<br/>
			<p><h3><b><span style="font-family: cursive;">Skladniki</span></b></h3></p>
			<div>${recipes.components}</div>
			<br/>
			<p><button class="btn btn-danger">Lista zakupow</button></p>
		</div>
		<div class="col-sm-9 col-md-8 col-lg-8" style="padding-left: 100px; padding-top: 20px;">
			<div><img src="<c:url value="/user/recipes/images/${recipes.id}"></c:url>" style="max-width: 450px; max-height: 450px;" alt="image"/></div><br/> 
			<div><p><span class="glyphicon glyphicon-time"></span> ${recipes.timeToPrepare}min &nbsp; <span class="glyphicon glyphicon-stats"></span> ${recipes.level}</p>
			    <div>
				    <span ng-repeat="rating in ratings">{{rating.current}} out of {{rating.max}}
				        <div star-rating rating-value="rating.current" max="rating.max" on-rating-selected="getSelectedRating(rating)"></div>
				   </span>	
				   <button>Ocen</button>
				</div>		
			</div>
			<br/>
			<p>
				<a href='<spring:url value="/user/recipes/show/pdf/${recipes.id}.pdf"></spring:url>' class="btn btn-danger">Wydrukuj</a>
				<a href='<spring:url value="/user/recipes/save/${recipes.id}"></spring:url>' class="btn btn-primary">Zapisz PDF</a>
				<button class="btn btn-primary" data-toggle="modal" data-target="#addCommentToRecipe">Add Comment</button> 			
			</p>			
		</div>	
	</div>
	<p>${recipes.howToPerform}</p>
	<br/>
	<hr/>
	<div id="commentsDiv">
	<p><h4><b>KOMENTARZE</b></h4></p>
 		<ul ng-repeat="comments in recipiesComments">
			<li>
				  <div id="caly">
				    <h3>
				      <span><span align="left"><a href="" style="color: black;">{{comments.nameCommentator}}</a></span>  	<i>{{comments.date | date:"dd/MM/yyyy HH:mm:ss"}}</i></span>
				    </h3>
				
				    <p>
				      {{comments.comment}}
				    </p>
				  </div>			
			</li>
		</ul>
	</div>
</div>

<!----------- Modal ------------------->

<div class="modal fade" id="addCommentToRecipe" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="background-color: gray;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
      
		 <div class="input-group">
		    <textarea id="conmment" class="form-control custom-control" rows="4" style="resize:none; background-color: black;"></textarea>     
		    <span ng-click="addCommentToThisRecipipe('${recipes.id}')" class="input-group-addon btn btn-primary" data-dismiss="modal" data-toggle="tooltip" data-placement="right" title="Add comment to this Culinary recipe">ADD</span>
		</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>	
      </div>
    </div>
  </div>
</div>	

</div>