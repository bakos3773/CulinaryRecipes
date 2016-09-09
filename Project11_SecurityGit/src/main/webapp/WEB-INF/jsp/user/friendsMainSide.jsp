<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	
<script src="http://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script>	 

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-animate.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-sanitize.js"></script>
	
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<title>Insert title here</title>

<style type="text/css">
#textBody{
position: absolute;
	top: 50px;
	width: 100%;
	min-height: 30%;
	height:auto;
}
#caly_blok{
	min-height: 30%;
	height:auto;
}
#autocompleteSearching{
	position: absolute;
	left: 200px;
	top: 20px;
}
</style>
</head>
<body ng-controller="friendsMainCtrl">
    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand" href="#page-top">Start Bootstrap</a>
                <div id="autocompleteSearching" class="ui-widget">
				  <input id="searchingUser" placeholder="Searching...">
			  		<a id="showUserBtn" href='' class="btn btn-primary">
						<span class="glyphicon glyphicon-search"></span>
					</a>
				</div>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li class="page-scroll">
                        <c:if test="${invitationsExist==true}"><a href="#" data-toggle="modal" data-target="#myModal"><span style="color: red;" class="glyphicon glyphicon-user"></span></a></c:if>
                    	<c:if test="${invitationsExist==false}"><a href="#" data-toggle="modal" data-target="#myModal"><span style="color: black;" class="glyphicon glyphicon-user"></span></a></c:if>
                    </li>
                    <li class="page-scroll">
                        <a href="#about">About</a>
                    <li class="page-scroll">
                        <a href="#contact">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

	<div id="textBody">
		<tiles:insertAttribute name="body" />
	</div>

<script type="text/javascript">
var app = angular.module('app', []);
app.controller('friendsMainCtrl', function($scope) {
});
</script>

<script type="text/javascript">
  $(document).ready(function(){	  

		/* -------------------------------------------------------------------------------------------   */
		$('#showUserBtn').on("click",function(){
			 $(this).attr('href', "/ProjectSecurity/user/frendsSide/autocomplete/autocompleteShowUser/"+document.getElementById("searchingUser").value);
		});
		
	/* -------------------------------------------------------------------------------------------   */
	  var availableTags = [];
	  
	  $.getJSON("/ProjectSecurity/user/frendsSide/autocomplete/getAllUsers", function(cos){
		  availableTags = cos;		  
		  setAuto(cos);
	  });
	  
	  var setAuto = function(x){
		  availableTags = x;
		  
		  $( "#searchingUser" ).autocomplete({
	            source: availableTags
	          });
	  }
		/* -------------------------------------------------------------------------------------------   */   
  });
</script>  

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
	      <ul>
	        <c:forEach items="${listOfInvitation}" var="invitations">        
	        	<li style="color: black;">${invitations.login} <a href="<spring:url value=""></spring:url>"  class="btn btn-primary">add</a><a href="<spring:url value="/user/frendsSide/ignoreFriend/${invitations.id}"></spring:url>"  class="btn btn-primary">ignore</a></li>
	        </c:forEach>
	      </ul>       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div> 
</body>
</html>