<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">
$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
</script>    
    
<div ng-controller="MainController">

	<hr/>
	<div align="center" >
 	<img src="<c:url value="/user/recipes/images/${recipes.id}"></c:url>" style="max-width: 300px; max-height: 300px;" alt="image"/> 
		 <div><h4><b>Type:</b>	<c:out value="${recipes.type}"/> </h4> </div>
		<div><h4><b>Name:</b><i> <c:out value="${recipes.name}"/></i></h4> </div>
 		<div><h4><b>Components:</b>	<i><c:out value="${recipes.components}"/></i></h4> </div>
		<div><h4><b>How to perform:</b>	<i><c:out value="${recipes.howToPerform}"/></i></h4> </div> 
		<p>
			<a href='<spring:url value="/user/home"></spring:url>' class="btn btn-primary">
				<span class="glyphicon-hand-left glyphicon"></span>
				back
			</a>
			<a href='<spring:url value="/user/recipes/save/${recipes.id}"></spring:url>' class="btn btn-primary">Save to PDF</a>
			<a href='<spring:url value="/user/recipes/show/pdf/${recipes.id}.pdf"></spring:url>' class="btn btn-danger">Print PDF</a>
			<button class="btn btn-primary" data-toggle="modal" data-target="#addCommentToRecipe">Add Comment</button> 
			<%-- href='<spring:url value="/user/recipes/addComment/${recipes.id}"></spring:url>' --%>
		</p>
	</div>
	
	<%-- ng-click="addCommentToThisRecipipe('${recipes.id}')" --%>
<!----------- Modal ------------------->

<div class="modal fade" id="addCommentToRecipe" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="background-color: gray;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
      
		 <div class="input-group">
		    <textarea id="conmment" class="form-control custom-control" rows="4" style="resize:none; background-color: black;"></textarea>     
		    <span ng-click="addCommentToThisRecipipe('${recipes.id}')" class="input-group-addon btn btn-primary" data-dismiss="modal" data-toggle="tooltip" data-placement="right" title="Add comment to this Culinary recipe">ADD</span>
		</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>	
      </div>
    </div>
  </div>
</div>	

</div>