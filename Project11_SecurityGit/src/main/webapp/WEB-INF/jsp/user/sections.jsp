<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>	
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>	
<script src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
			
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<div id="newTopic">
					<button class="btn btn-primary" data-toggle="modal" data-target="#myModal" ng-click="updateSections()">New Section</button>
				</div>			
			</security:authorize>

			<div id="backToHome">
				<a href='<spring:url value="/user/home"></spring:url>' class="btn btn-primary">
					<span class="glyphicon glyphicon-menu-up"></span>
					Home
				</a>
			</div>

	  	<div id="tableBody" style="color: white;">
	  		<table class="table table-inverse" style='table-layout:fixed;'>
	  			<thead>
		  			<tr>
		  				<td style="width: 55%;"><h3>Forum</h3></td>
		  				<td><h3>Tematy</h3></td>
		  				<td><h3>Posty</h3></td>
		  				<td><h3>Ostatni post</h3></td>
		  			</tr>
	  			</thead>
	  			<tbody>
	  				<tr ng-repeat="items in allSections">
	  					<td style="width: 55%;"> <a href="<spring:url value="/user/forum/section/{{items.id}}"></spring:url>">{{items.sections}}</a> </td>	  				
	  					<td>{{items.howManyThemes}}</td>
	  					<td>{{items.howManyPosts}}</td>
	  					<td>{{items.lastPost}}</td>	  						  					
	  				</tr>

	  			</tbody>
	  		</table>
	  	</div>


	  	
