<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	

<script type="text/javascript" src="/ProjectSecurityGit/resources/js/jqueryFile.js"></script>
<div style="background-color: black;">
	<div class="col-md-4 col-md-offset-4 jumbotron">
	      <form class="form-signin loggingForm" name="f" action="/ProjectSecurityGit/login" method="POST">
	        <h2 class="form-signin-heading"><span style="color: black;">Please sign in</span></h2>
	        <label for="username" class="sr-only">Login</label>
	        <input type="text" id="username" name='username' class="form-control" placeholder="Put your login" required autofocus>
	        <label for="password" class="sr-only">Password</label>
	        <input type="password" id="password" name='password' class="form-control" placeholder="Password" required>
	        <div class="checkbox">
	          <label>
	            <input id="remember" name="remember-me" type="checkbox"/>
	            <label for="remember" class="inline">Remember me</label>
	          </label>
	        </div>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      </form>
	</div>
</div>