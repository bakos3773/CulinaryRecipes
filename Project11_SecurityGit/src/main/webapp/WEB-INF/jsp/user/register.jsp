<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript" src="/ProjectSecurityGit/resources/js/jqueryFile.js"></script>

<div class="jumbotron" align="center"><h2><span style="color: black;">REGISTRATION</span></h2></div>
<form:form modelAttribute="user" class="form-horizontal registrationForm" role="form">
	<form:errors path="*" cssClass="alert alert-danger" element="div"/>
    <div class="form-group">
      <label class="control-label col-sm-2" for="login">Login:</label>
      <div class="col-sm-8">
        <form:input id="login" path="login" class="form-control" placeholder="Put Login"/><br/>
		<form:errors path="login" cssClass="text-danger"/>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="password">Password:</label>
      <div class="col-sm-8">
        <form:password id="password" path="password" class="form-control" placeholder="Put password"/><br/>
        <form:errors path="password" cssClass="text-danger"/>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="password">Password again:</label>
      <div class="col-sm-8">
        <input type="password" id="password_again" name="password_again" class="form-control" placeholder="Repeat password"/>
      </div>
    </div>    
    <div class="form-group">
      <label class="control-label col-sm-2" for="username">username:</label>
      <div class="col-sm-8">
        <form:input id="username" path="username" class="form-control" placeholder="Put username"/><br/>
        <form:errors path="username" cssClass="text-danger"/>        
      </div>
    </div>    
    <div class="form-group">
      <label class="control-label col-sm-2" for="surname">Surname:</label>
      <div class="col-sm-8">
        <form:input id="surname" path="surname" class="form-control" placeholder="Put surname"/><br/>
        <form:errors path="surname" cssClass="text-danger"/>
      </div>
    </div>  
    <div class="form-group">
      <label class="control-label col-sm-2" for="mail">Mail:</label>
      <div class="col-sm-8">
        <form:input id="mail" path="mail" class="form-control" placeholder="Put mail"/><br/>
        <form:errors path="mail" cssClass="text-danger"/>
      </div>
    </div>     
    <div class="form-group">
      <label class="control-label col-sm-2" for="address">Address:</label>
      <div class="col-sm-8">
        <form:input id="address" path="address" class="form-control" placeholder="Put address"/><br/>
        <form:errors path="address" cssClass="text-danger"/>
      </div>
    </div>   
    <div class="form-group">
      <label class="control-label col-sm-2" for="role">Role:</label>
      <div class="col-sm-8">
        <form:input id="role" path="role" class="form-control" value="ROLE_USER" readonly="true"/><br/>
      </div>
    </div>           
	<input type="submit" class="btn btn-primary btn-lg" value="Add to database"/> 
</form:form>
