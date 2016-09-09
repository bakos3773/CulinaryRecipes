<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

		<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
		<script src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
		<script src="http://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	
			
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">


<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th>ID</th>
      <th>Login</th>
      <th>Username</th>
      <th>Surname</th>
      <th>Address</th>
      <th>Email</th>
      <th>EDIT</th>
    </tr>
  </thead>
  <tbody>
 <c:forEach items="${users}" var="user">
     <tr>
	  <td><c:out value="${user.id}"/></td>
      <td><c:out value="${user.login}"/></td>
      <td><c:out value="${user.username}"/></td>
      <td><c:out value="${user.surname}"/></td>
      <td><c:out value="${user.address}"/></td>
      <td><c:out value="${user.mail}"/></td>
      <td><a href='<spring:url value="/admin/delete/${user.id}"></spring:url>'>Delete</a></td>
    </tr>
 </c:forEach>
  </tbody>
</table>

<script type="text/javascript">
$(document).ready(function() {
    $('#example').DataTable();
} );
</script>