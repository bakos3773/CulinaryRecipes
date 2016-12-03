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

<h2><b><i>A R T I C L E S</i></b></h2>

<table id="articleTable" class="table table-striped table-bordered" cellspacing="10" width="100%">
  <thead>
    <tr>
      <th>Data dodania</th>
      <th>Artykul</th>
    </tr>
  </thead>
  <tbody>

     <c:forEach items="${y}" var="x">
       <tr>
		  <td>${x.date}</td>
	      <td>${x.name}</td>     
	           </tr>
     </c:forEach>


  </tbody>
</table>

<script type="text/javascript">
$(document).ready(function() {
    $('#articleTable').DataTable();
} );
</script>   
