<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="background-color: black; min-height: 300px; border-style: inset solid; ">
	<div style="padding-left: 20px; padding-top: 20px;">
		<img src="<c:url value="/resources/images/${recipe.id}.jpg"></c:url>" style="max-width: 300px; max-height: 300px;" alt="image"/>
		<p><h4><b>Type:</b>	<c:out value="${recipe.type}"/></h4> </p>
		<p><h4><b>Name:</b>	<i><c:out value="${recipe.name}"/></i></h4> </p>
		<p><h4><b>Components:</b>	<i><c:out value="${recipe.components}"/></i></h4> </p>
		<p><h4><b>How to perform:</b>	<i><c:out value="${recipe.howToPerform}"/></i></h4> </p>
	</div>	
</div>
