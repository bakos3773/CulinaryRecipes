<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">
$(document).ready(function() {
	  $('#example').dataTable( {
		    "iDisplayLength": 2,
		    bFilter: false,
		    bInfo: false,
		    "bLengthChange": false
		} );	  
} );
</script>
<style type="text/css">
.inventory_related thead {
  display: none;
}
.sizeWidth {
    width: 30vw;
    /* height: 50vh; */
}
</style>   
    
<h1 align="center">All Recipes</h1>

<div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
          <h4 align="right">FILTRY</h4>
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body" style="background-color: black;">
        SELECT TYPE: 
        
        <form:form action="/ProjectSecurityGit/user/recipes/checkedTypes" method="POST" commandName="filterPattern" >
		    <div class="checkbox">
		      <label><form:checkbox path="allTypes" value="Ciasta" checked="checked"/> Ciasta</label>
		    </div>
		    <div class="checkbox">
		      <label><form:checkbox path="allTypes" value="Potrawy miesne"  checked="checked"/> Potrawy miesne</label>
		    </div>
		    <div class="checkbox">
		      <label><form:checkbox path="allTypes" value="Surowki"  checked="checked"/> Surowki</label>
		    </div>
		    <div class="checkbox">
		      <label><form:checkbox path="allTypes" value="Zupy"  checked="checked"/> Zupy</label>
		    </div>	
		    <input type="submit" value="OK">
        </form:form>

      </div>
    </div>
  </div>
</div>

<table id="example"  class="table inventory_related" cellspacing="0" width="100%">
<thead>
	<tr>
		<th></th>
		<th></th>
		<th></th>
	</tr>
</thead>
<tfoot>
	<tr>
		<th></th>
		<th></th>
		<th></th>
	</tr>
</tfoot>
<tbody>
	
		<c:forEach items="${recipes}" step="3" varStatus="status">
		<tr>
			<td><c:if test="${not empty recipes[status.index].id}"><a href="<spring:url value="/user/recipes/show/${recipes[status.index].id}"></spring:url>"><img src="/ProjectSecurityGit/user/recipes/images/${recipes[status.index].id}" class="sizeWidth"/><p><p><h4><b>${recipes[status.index].name}</b></h4></p></a><p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p></c:if></td>
			<td><c:if test="${not empty recipes[status.index+1].id}"><a href="<spring:url value="/user/recipes/show/${recipes[status.index+1].id}"></spring:url>"><img src="/ProjectSecurityGit/user/recipes/images/${recipes[status.index+1].id}" class="sizeWidth"/><p><p><h4><b>${recipes[status.index+1].name}</b></h4></p></a><p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p></c:if></td>
			<td><c:if test="${not empty recipes[status.index+2].id}"><a href="<spring:url value="/user/recipes/show/${recipes[status.index+2].id}"></spring:url>"><img src="/ProjectSecurityGit/user/recipes/images/${recipes[status.index+2].id}" class="sizeWidth"/><p><p><h4><b>${recipes[status.index+2].name}</b></h4></p></a><p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p></c:if></td>
		</tr>
		</c:forEach>
		
	
</tbody>
</table>