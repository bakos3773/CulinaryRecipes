<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form action="/ProjectSecurityGit/user/recipes/uploadPost?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="post">
     	<input type="file" name="file" accept="image/jpeg"/> 
    	<input type="submit" class="btn btn-success btn-lg" value="Dodaj"/>
</form>