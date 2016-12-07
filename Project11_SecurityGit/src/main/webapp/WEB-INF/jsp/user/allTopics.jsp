<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<div id="backToSections" ng-controller="forumControllerThemes">
	<a href='<spring:url value="/user/forum"></spring:url>' class="btn btn-primary">
		<span class="glyphicon-hand-left glyphicon"></span>
		back
	</a>
</div>
<div id="backToHome">
	<a href='<spring:url value="/user/home"></spring:url>' class="btn btn-primary">
		<span class="glyphicon glyphicon-menu-up"></span>
		Home
	</a>
</div>
<div id="newTopic">
	<button class="btn btn-primary" data-toggle="modal" data-target="#myModalNewTopic" ng-click="newTopic()">New Topic</button>
</div>

	  	<div id="tableBody" style="color: white;">
	  		<table class="table table-inverse" style='table-layout:fixed;'>
	  			<thead>
		  			<tr>
		  				<td style="width: 55%;"><h3>Tematy</h3></td>
		  				<td><h4>Odpowiedzi</h4></td>
		  				<td><h4>Autor</h4></td>
		  				<td><h4>Wyswietlen</h4></td>
		  				<td><h4>Ostatni post</h4></td>
		  			</tr>
	  			</thead>
	  			<tbody>
	  			<%-- 	<tr ng-repeat="items in allTopics">
	  					<td style="width: 55%;"> <a href="<spring:url value="/user/forum/section/{{items.id}}/allTopics"></spring:url>">{{items.themes}}</a> </td>	  				
	  					<td>{{items.numberOfAnswers}}</td>
	  					<td>{{items.author}}</td>
	  					<td>{{items.numberOfViews}}</td>
	  					<td>{{items.lastPost}}</td>	  						  					
	  				</tr> --%>
					<c:forEach items="${allTopics}" var="items">
						<tr>
	  					<td style="width: 55%;" ng-controller="forumControllerThemes"> <a class="setTopic" href="<spring:url value="/user/forum/section/${id_section}/theme/${items.id}"></spring:url>">${items.themes}</a> </td>	  				
	  					<td>${items.numberOfAnswers}</td>
	  					<td>${items.author}</td>
	  					<td>${items.numberOfViews}</td>
	  					<td>${items.lastPost}</td>
						</tr>
					</c:forEach>
	  			</tbody>
	  		</table>
	  	</div>
	  	<br/>
	  	<br/>
<!-- <div style="color: white;" ng-controller="forumControllerThemes">{{getIDsection()}}</div> -->
<!------------ Modal ----------->
<%-- <form:form id="formSetTopic" action="" commandName="forumThemes" method="POST">
	<div class="modal fade" id="myModalNewTopic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Add new topic</h4>
	      </div>
	      <div class="modal-body">
	       	<form:input path="themes"/>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	    	<input id="addTopic" type="submit" value="Add"/> 
	      </div>
	    </div>
	  </div>
	</div>
</form:form> --%>

<form:form id="formSetTopic" commandName="forumThemes" action=""  method="POST">
<div class="modal fade" id="myModalNewTopic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="background-color: gray;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridModalLabel">Add new topic</h4>
				</div>
				<div class="modal-body">
					<form:textarea path="themes" class="form-control custom-control" rows="4" style="resize:none; background-color: black;" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" id="addTopic" class="btn btn-primary"
						value="Add">
				</div>
			</div>
		</div>
	</div>
</form:form>
<!------------------------------------------->
<script type="text/javascript">
$(document).ready(function(){ 
	$('#addTopic').click(function(){
		 $('#formSetTopic').attr('action', document.URL); 		
	});
/*    	$('.setTopic').click(function(){
		 $(this).attr('href', document.URL+"/themes/${items.id}" );
	});   */
  });
</script>
<script type="text/javascript">
app.controller("forumControllerThemes", function($scope){

	
});
</script>