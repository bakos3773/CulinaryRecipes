<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div style="background-color: black;">
	<div class="col-md-4 col-md-offset-4 jumbotron">
	      <form class="form-signin registrationForm" name="f" action="/ProjectSecurity/login" method="POST">
	        <h2 class="form-signin-heading">Please sign in</h2>
	        <label for="username" class="sr-only">Login</label>
	        <input type="text" id="username" name='username' class="form-control" placeholder="Put your login" required autofocus>
	        <label for="password" class="sr-only">Password</label>
	        <input type="password" id="password" name='password' class="form-control" placeholder="Password" required>
	        <div class="checkbox">
	          <label>
	            <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>
	            <label for="remember_me" class="inline">Remember me</label>
	          </label>
	        </div>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      </form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$(".registrationForm").validate({
		rules: {
			password: {
				required : true,
				minlength : 4
			},			
			username: {
				required : true,
				minlength : 4
			}			
		},
		highlight : function(element){
			$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		unhighlight : function(element){
			$(element).closest('.form-group').removeClass('has-error').addClass('has-success');			
		}		
	});
});
</script>