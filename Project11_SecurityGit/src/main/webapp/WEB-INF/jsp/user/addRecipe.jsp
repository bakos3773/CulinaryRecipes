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
			<form:select id="type" path="type" class="form-control">
				<form:option value="Ciasta">Ciasta</form:option>
				<form:option value="Dania miesne">Dania miesne</form:option>
				<form:option value="Desery">Desery</form:option>
				<form:option value="Dla dzieci">Dla dzieci</form:option>
				<form:option value="Kasza i ryz">Kasza i ryz</form:option>
				<form:option value="Makarony">Makarony</form:option>
				<form:option value="Maczne">Maczne</form:option>
				<form:option value="Salatki">Salatki</form:option>
				<form:option value="Sosy">Sosy</form:option>
				<form:option value="Pasty">Pasty</form:option>
				<form:option value="Pieczywo">Pieczywo</form:option>
				<form:option value="Pizze">Pizze</form:option>
				<form:option value="Przekaski">Przekaski</form:option>
				<form:option value="Grzyby">Grzyby</form:option>
				<form:option value="Swiateczne">Swiateczne</form:option>
				<form:option value="Warzywa">Warzywa</form:option>
				<form:option value="Wegetarianskie">Wegetarianskie</form:option>
				<form:option value="Zupy">Zupy</form:option>
				<form:option value="Przetwory">Przetwory</form:option>
				<form:option value="Ryby i owoce morza">Ryby i owoce morza</form:option>				
				<form:option value="Na grilla">Na grilla</form:option>
				<form:option value="Napoje">Napoje</form:option>
				<form:option value="Alkohole">Alkohole</form:option>		
				<form:option value="Kuchnie swiata">Kuchnie swiata</form:option>
				<form:option value="Diety">Diety</form:option>
				<form:option value="Inne przepisy">Inne przepisy</form:option>				
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
			<form:textarea id="components" path="components" class="form-control" rows="4" placeholder="Enter components"/>
	      </div>
	    </div>
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="howToPerform">How to perform:</label>
	      <div class="col-sm-6">
			<form:textarea id="howToPerform" path="howToPerform" class="form-control" rows="6" placeholder="Enter how to perform:"/>
	      </div>
	    </div> 
	    
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="timeToPrepare">Time to prepare:</label>
	      <div class="col-sm-6">
			<form:input id="timeToPrepare" path="timeToPrepare" class="form-control" placeholder="Enter time to prepare"/>
	      </div>
	    </div>	    
	    
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="level">Level:</label>
	      <div class="col-sm-6">	                   
			<form:select id="level" path="level" class="form-control">
				<form:option value="latwy">latwy</form:option>
				<form:option value="sredni">sredni</form:option>
				<form:option value="trudny">trudny</form:option>			
			</form:select>			
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