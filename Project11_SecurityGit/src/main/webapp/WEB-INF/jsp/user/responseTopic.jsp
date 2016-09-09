<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<div ng-controller="answerTopicController">
	<div id="backToSections">
		<a id="backToTopic"
			href='<spring:url value="/user/forum/section/${id_section}"></spring:url>'
			class="btn btn-primary"> <span
			class="glyphicon-hand-left glyphicon"></span> back
		</a>
	</div>
	<div id="backToHome">
		<a href='<spring:url value="/user/home"></spring:url>'
			class="btn btn-primary"> <span
			class="glyphicon glyphicon-menu-up"></span> Home
		</a>
	</div>
	<div id="answerTopic">
		<button class="btn btn-primary" data-toggle="modal"
			data-target="#gridSystemModal" ng-click="updateTopicAnswer()">answer</button>
	</div>

	<div style="color: white;">
		<div style="background-color: black;">
			<h3>
				<b>${forumTheme.themes}</b>
			</h3>
		</div>
	</div>

	<div style="color: white;">
		<table class="table table-condensed">
			<thead>
				<tr>
					<th>Autor</th>
					<th>Wiadomosc</th>
					<th><span style="padding-left: 35px;">Data</span></th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="items in allTopicAnswer" style="height: 100px;">
					<td>{{items.author}}</a>
					</td>
					<td style="width: 65%;">{{items.message}}</td>
					<td><span style="padding-left: 35px;">{{items.date}}</span></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>


<!------------------ Modal ----------------------->
<form:form id="formSetTopicAnswer" commandName="formThemesAnswer"
	action="">
	<div id="gridSystemModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="gridModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<form:textarea path="message" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" id="addTopicAnswer" class="btn btn-primary"
						value="Add">
				</div>
			</div>
		</div>
	</div>
</form:form>
<!---------------------------------------------->
<script type="text/javascript">
$(document).ready(function(){
	$('#addTopicAnswer').click(function(){
		 $('#formSetTopicAnswer').attr('action', document.URL+"/setMessage");
	});
	
	
  });
</script>
<script type="text/javascript">
app.controller("answerTopicController", function($scope, $http, $log){
	
	$scope.allTopicAnswer = [];
	
 	$http.get(document.URL).then(function(response){
		$scope.allTopicAnswer = response.data;
		$log.info($scope.allTopicAnswer);
	});
	
	$scope.updateTopicAnswer = function(){		
	 	$http.get("user/forum/aaa").then(function(response){
					$scope.allTopicAnswer = response.data;
					
		}); 		
	};	
	
	$scope.eja = function(){
		alert("eja");
	}
	
});
</script>