<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div></div><a id="backToTopic" href='<spring:url value="/user/frendsSide/friendsMainSide"></spring:url>' class="btn btn-primary">
	<span class="glyphicon-hand-left glyphicon"></span>
	back
</a>

<div ng-controller="friendsMainCtrl">
	<h2>${searchingUser.login}</h2>
	<h2>${searchingUser.address}</h2>
	<h2>${searchingUser.mail}</h2>
	
	<a id="addUserToFriend" href='<spring:url value="/user/frendsSide/autocomplete/autocompleteShowUser/addUserToFriend/${searchingUser.id}"></spring:url>' class="btn btn-primary">
		<span class="glyphicon glyphicon-plus"></span>
		Send invitation
	</a>
</div>