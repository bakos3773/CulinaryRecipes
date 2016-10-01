<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>	
<script src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	
<script src="http://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script>	 

<script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js" ></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-animate.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-sanitize.js"></script>
	
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<title>Insert title here</title>
<style type="text/css">
*{
	padding: 0;
	margin: 0;
}
body{
	background-image:  url("/ProjectSecurityGit/resources/images/tlo3.jpg");
	background-attachment: fixed;
}
a
{
	color: teal;
	border-bottom: 1px dotted gray;
}
a:hover
{
	text-decoration: none;
	color: #1ED224;
}
#container{

	width: 1004px;
	margin: 0 auto;
}
/* GORNA CZESC KONTENERA */
#forumHeader{
	background-image: url("/ProjectSecurityGit/resources/images/background_header.jpg");
	width: 1000px;
	height: 120px;
	position: relative;
	
}
#second_level{
	position: absolute;
	left: 250px;
	bottom: 15px;
}
#wlasciwe_gorne_menu li{
	list-style-type: none;
	margin:0px 8px;
	float: left;
    border-style: inset;
    border-right: thick double #ff0000;
}
#wlasciwe_gorne_menu li a{
	display:inline-block;
	float: left;
	border-bottom: none;
	padding: 0px 24px;
	font-size: 15px;
	font-family: fantasy;	
}
#wlasciwe_gorne_menu li a:HOVER{
	background-color: #2B1A1A;
	border-style: outset;
}
#newTopic{
	position: absolute;
	right:50px;
	top: 80px;
}
#backToSections{
	position: absolute;
	left: 10px;
	top: 80px;
}
#answerTopic{
	position: absolute;
	right:50px;
	top:80px;
}
#backToHome{
	position: absolute;
	left: 120px;
	top: 80px;
}
#connectWithFrends{
	position: absolute;
	top: 80px;
	right:150px;
}
</style>
<script type="text/javascript">
var app = angular.module("myApp", []);


app.controller("forumController", function($scope, $http, $log){
	
	$scope.allSections = [];
	
	$http.get("/ProjectSecurityGit/user/forum/getAllSections").then(function(response){
		$scope.allSections = response.data;
	});
	
	$scope.updateSections = function(){		
		$http.get("/ProjectSecurityGit/user/forum/getAllSections").then(function(response){
					$scope.allSections = response.data;
		});
	};
	
});

</script>
</head>
<body ng-controller="forumController">
<div align="center">
	<div id="container">
		<div id="forumHeader">
			<div id="first_level">
				<nav class="navbar navbar-default">
				  <div class="container-fluid">
				    <!-- Brand and toggle get grouped for better mobile display -->
				    <div class="navbar-header">
				      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				        <span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				      </button>
				      <a class="navbar-brand" href="#">Brand</a>
				    </div>
				
				    <!-- Collect the nav links, forms, and other content for toggling -->
				    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				      <ul class="nav navbar-nav">
				      	<li class="dropdown">
				          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">HOme Page <span class="caret"></span></a>
				          <ul class="dropdown-menu">
				            <li><a href="#">Action</a></li>
				            <li><a href="#">Another action</a></li>
				            <li><a href="#">Something else here</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">Separated link</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">One more separated link</a></li>
				          </ul>
				        </li>
				        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
				        <li><a href="#">Link</a></li>

				      </ul>
				      <form class="navbar-form navbar-right" role="search">
				        <div class="form-group">
				          <input type="text" class="form-control" placeholder="Search">
				        </div>
				        <button type="submit" class="btn btn-default">Submit</button>
				      </form>
		<!-- 		      <ul class="nav navbar-nav navbar-right">
				        <li><a href="#">Home</a></li>
				        <li class="dropdown">
				          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
				          <ul class="dropdown-menu">
				            <li><a href="#">Action</a></li>
				            <li><a href="#">Another action</a></li>
				            <li><a href="#">Something else here</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">Separated link</a></li>
				          </ul>
				        </li>
				      </ul> -->
				    </div><!-- /.navbar-collapse -->
				  </div><!-- /.container-fluid -->
				</nav>			
			</div>
			<div id="second_level">
				<ul id="wlasciwe_gorne_menu">
					<li><a href="#">Add Subject</a></li>
					<li><a href="#">link 2</a></li>
					<li><a href="#">link 3</a></li>
					<li><a href="#">link 4</a></li>
				</ul>
			</div>

	<div id="connectWithFrends">
		<a href='<spring:url value="/user/frendsSide/friendsMainSide"></spring:url>' class="btn btn-info">
			<span class="glyphicon glyphicon-globe"></span>
			Friends
		</a>
	</div>

		</div>
		<!-- koniec div header -->	
	
		<tiles:insertAttribute name="body"/>
	</div>
	<!-- koniec div container -->
</div>

<!------------ Modal ----------->
  <form:form action="/ProjectSecurityGit/user/forum/setSection" commandName="forum" method="POST">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
	      </div>
	      <div class="modal-body">
	       	<form:input path="sections"/>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	    	<input type="submit" value="Add"/> 
	      </div>
	    </div>
	  </div>
	</div>
</form:form>

</body>
</html>