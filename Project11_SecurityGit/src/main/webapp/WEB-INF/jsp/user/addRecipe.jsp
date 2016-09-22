<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1 align="justify"><b>Put Your cooking recipe:</b></h1><hr/>

<div align="justify">
	<form:form  modelAttribute="culinaryRecipes" action="/ProjectSecurityGit/user/recipes/addRecipe" method="POST" class="form-horizontal" role="form">
	<form:errors path="*" element="div" cssClass="errors" />
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="type">Type:</label>
	      <div class="col-sm-6">
			<%-- <form:input id="type" path="type" class="form-control" placeholder="Enter type"/> --%>
			<form:select id="type" path="type" class="form-control">
				<form:option value="Ciasta">Ciasta</form:option>
				<form:option value="Potrawy miesne">Potrawy miesne</form:option>
				<form:option value="Surowki">Surowki</form:option>
				<form:option value="Zupy">Zupy</form:option>
				<form:option value="cdn...">Cdn...</form:option>
			</form:select>			
	      </div>
	      
	    </div>
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="name">Name:</label>
	      <div class="col-sm-6">
			<form:input id="name" path="name" class="form-control" placeholder="Enter name"/>
	      </div>
	    </div>
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="components">Components:</label>
	      <div class="col-sm-6">
			<form:textarea id="components" path="components" class="form-control" rows="2" placeholder="Enter components"/>
	      </div>
	    </div>
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="howToPerform">How to perform:</label>
	      <div class="col-sm-6">
			<form:textarea id="howToPerform" path="howToPerform" class="form-control" rows="6" placeholder="Enter how to perform:"/>
	      </div>
	    </div> 
	    <div class="checkbox">
		    <label>
		    	<form:checkbox id="isPrivateRecipe" path="isPrivateRecipe" value="true"/> Private Recipe
		    </label>
	    </div>
	    
	             	    	
		<input class="btn btn-success btn-lg"  type="submit" value="Dalej"/>
	</form:form>
</div>