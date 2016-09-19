<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
        
        <form:form method="POST" commandName="filterPattern" action="/ProjectSecurityGit/user/checkedTypes">
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

<c:forEach items="${recipes}" var="recipe">
<div class="img">
	<a href="<spring:url value="/user/recipes/show/${recipe.id}"></spring:url>">
		<img src="<c:url value="/user/recipes/images/${recipe.id}"></c:url>" style="max-width: 700px; max-height: 700px;" alt="image"/>
  	</a>
		<c:out value="${recipe.name}"/>
		<p><span class="glyphicon glyphicon-time"></span> 45min &nbsp; <span class="glyphicon glyphicon-stats"></span> latwe</p>
		
		
</div>	
</c:forEach>

<style>
div.img {
    margin: 5px;
    float: left;
    width: 180px;
}	

div.img:hover {
    border: 2px solid #777;
}

div.img img {
    width: 100%;
    height: auto;
}

div.desc {
    padding: 15px;
    text-align: center;
}
</style>