<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>Settings</h1>

<div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
          <h3 align="center">CONTROL PANEL</h3>
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body" style="background-color: black;">
        Switch off user panel: 
        <a href='<spring:url value="/admin/changeRole/true"></spring:url>'>yes</a> / 
        <a href='<spring:url value="/admin/changeRole/false"></spring:url>'>no</a>
        <hr/>
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
		  ADD AERTICLE
		</button>
      </div>
    </div>
  </div>
</div>

<div>
<table class="table table-inverse">
  <thead>
    <tr>
      
      <th>Login</th>
      <th>Username</th>
      <th>Surname</th>
      <th>Address</th>
      <th>Email</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><c:out value="${user.login}"/></td>
      <td><c:out value="${user.username}"/></td>
      <td><c:out value="${user.surname}"/></td>
      <td><c:out value="${user.address}"/></td>
      <td><c:out value="${user.mail}"/></td>
    </tr>
  </tbody>
</table>
</div>

<!-- Modal -->
<form:form modelAttribute="article" class="form-horizontal registrationForm" role="form">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body" style="background-color: black;">
    <div class="form-group">
      <label class="control-label col-sm-2" for="name">name:</label>
      <div class="col-sm-8">
        <form:input id="name" path="name" class="form-control" placeholder="Put name"/><br/>     
      </div>
    </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <input type="submit" class="btn btn-primary btn-lg" value="Add to database"/> 
      </div>
    </div>
  </div>
</div>
</form:form>