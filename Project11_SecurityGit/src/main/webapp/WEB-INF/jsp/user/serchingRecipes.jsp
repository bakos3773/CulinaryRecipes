<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style type="text/css">
.row {

  border:3px solid #EEE;
}
</style>    
<table id="recipeTable" class="table inventory_related" cellspacing="0" width="100%">
<thead>
</thead>
<tfoot>
</tfoot>
<tbody>
		
	<c:forEach items="${serchingRecipes}" var="recipe">
		<tr>
			<td>			
				<div class="row">
					<a href="<spring:url value="/user/recipes/show/${recipe.id}"></spring:url>">
						<div class="col-sm-3 " style="padding-top: 10px; background-color: red;">
							<img src="<c:url value="/user/recipes/images/${recipe.id}"></c:url>" style="max-width: 150px" alt="image"/>					
						</div>					
						<div class="col-sm-9 " style="padding-left: 20px; padding-top: 20px; background-color: blue;">
							<div align="center"><h3><b>${recipe.name}</b></h3></div>
							<br/>
							<div><span class="glyphicon glyphicon-time"></span> ${recipe.timeToPrepare}min &nbsp; <span class="glyphicon glyphicon-stats"></span> ${recipe.level}</div>
						</div>
					</a>
				</div>	
			</td>
		</tr>
	</c:forEach>				
		

</tbody>
</table>    