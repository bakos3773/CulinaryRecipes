 $(document).ready(function(){
	 
	$(".loggingForm").validate({
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
	
	$(".registrationForm").validate({
		rules: {
			login: {
				required : true,
				minlength : 4
			},
			password: {
				required : true,
				minlength : 4
			},
			password_again: {	
				required : true,
				minlength : 4,
				equalTo: "#password"
			},			
			username: {
				required : true,
				minlength : 4
			},
			surname: {
				required : true,
				minlength : 4
			},
			mail: {
				required : true,
				email : true
			},			
			address: {	
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
	
	$('#addTopicAnswer').click(function(){
		 $('#formSetTopicAnswer').attr('action', document.URL+"/setMessage");
	});	
	
	  $('#recipeTable').dataTable( {
		    "iDisplayLength": 2,
		    bFilter: false,
		    bInfo: false,
		    "bLengthChange": false  
	  } );
   
}); 