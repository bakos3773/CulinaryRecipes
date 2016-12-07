<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    

		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
				
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>		
<script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js" ></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-sanitize.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
<link href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css" rel="stylesheet"/>		
<script src = "http://cdn.zingchart.com/zingchart.min.js" ></script>
<script src = "http://cdn.zingchart.com/angular/zingchart-angularjs.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">		  
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>

<script type="text/javascript">
$(document).ready(function() {

        $('#datetimepicker10').datetimepicker({
            viewMode: 'years',
            format: 'YYYY-MM'
        });
	
 	$("#showWidgets li").click(function(){
		if( $('.box').eq($(this).index()).text() =="Show All Month"){		
			
  			angular.element(document.getElementById('MainControllerSettings')).scope().test();
 			angular.element(document.getElementById('MainControllerSettings')).scope().$apply();   
 			$(".circleWidget").hide();
 			$(".lineaWidget").show();
		}
		if( $('.box').eq($(this).index()).text() =="This Day"){			
			
    			angular.element(document.getElementById('MainControllerSettings')).scope().zingChartCircle();
   			angular.element(document.getElementById('MainControllerSettings')).scope().$apply();
   			$(".lineaWidget").show();
 			$(".circleWidget").hide(); 
		}		
	}); 
});
</script>


<h1>Settings</h1>


<div id="MainControllerSettings" ng-controller="MainControllerSettings">

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

<div>
  <!-- Nav tabs -->
  <ul id="showWidgets" class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#month" aria-controls="month" role="tab" data-toggle="tab" class="box" ng-click="czas()">Show All Month</a></li>
    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab" class="box" ng-click="zingChartCircle()">Today</a></li>
    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab" class="box">Settings</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
   	<div role="tabpanel" class="tab-pane active" id="month" ng-show="true">    	
 		<div>
           Change date: 
		    <div class="col-sm-6" style="height:130px; width: 200px" >
		        <div class="form-group">
		            <div class='input-group date' id='datetimepicker10'>
		                <input type='text'  id="date" name="date" class="form-control" style="background-color: black;"/>
		                <span class="input-group-addon">
		                    <span class="glyphicon glyphicon-calendar">
		                    </span>
		                </span>
		            </div>
		        </div>
		    </div>        
            <button ng-click="czas()">show</button>
		</div>
		<!-- <div id="resizable" style="float: left" ng-if="loaded===false">
		     <div zingchart id="chart-1" class="lineaWidget" zc-json="myJson" zc-width="100%" zc-height="100%"></div>
		</div>    		  
		<nvd3 options="options" data="data"></nvd3>   -->
    </div>

    <div role="tabpanel" class="tab-pane" id="messages" ng-if="loaded===false"> <!-- loaded===false -->
    	<div ng-if="mostPopiulatToday.length">
    		<div zingchart id="chart-1" zc-json="myJsonCircle" zc-width="100%" zc-height="400px"></div>
    	</div>
    	<div ng-if="!mostPopiulatToday.length">
    		<span align="center"><h2><b>No Events</b></h2></span>
    	</div>    		
    </div>
    <div role="tabpanel" class="tab-pane" id="settings">3</div>
  </div>
</div>
	
</div>
<script type="text/javascript">


app.controller("MainControllerSettings", function($scope, $http, $log, WidgetService) {	 	
	
	
	$scope.mostPopularRecipesThisDay = function(){
		$http.get("/ProjectSecurityGit/widget/mostPopularRecipesThisDay").then(function(list){
			
			$log.info(list.data);
			$scope.mostPopiulatToday = list.data;
			$log.info($scope.mostPopiulatToday);
		});
	}
	$scope.mostPopularRecipesThisDay();
	var zingchartWidget = function(dane){
        $scope.myJson = {        		
                title : {
                  text : "Most viewed recipes this day",
                  fontSize : 16,
                  fontColor : "#fff"
                },
                backgroundColor: "#2bbb9a",
                globals: {
                    shadow: true,
                    fontFamily: "Arial"
                },
                type: "line",
                scaleX: {
                    maxItems: 31,
                    minItems:28,
                    lineColor: "white",
                    lineWidth: "1px",
                    tick: {
                        lineColor: "white",
                        lineWidth: "1px"
                    },
                    item: {
                        fontColor: "white"
                    },
                    guide: {
                        visible: false
                    }
                },
                scaleY: {
                    lineColor: "white",
                    lineWidth: "1px",
                    tick: {
                        lineColor: "white",
                        lineWidth: "1px"
                    },
                    guide: {
                        lineStyle: "solid",
                        lineColor: "#249178"
                    },
                    item: {
                        fontColor: "white"
                    },
                },
                tooltip: {
                    visible: false
                },
                crosshairX: {
                    lineColor: "#fff",
                    scaleLabel: {
                        backgroundColor: "#fff",
                        fontColor: "#323232"
                    },
                    plotLabel: {
                        backgroundColor: "#fff",
                        fontColor: "#323232",
                        text: "%v",
                        borderColor: "transparent"
                    }
                },
                plot: {
                    lineWidth: "2px",
                    lineColor: "#FFF",
                    aspect: "spline",
                    marker: {
                        visible: false
                    }
                },
                series: [{
                	
                    values: [3,4,1,0,0,2] //dane

                }]
            }  		
	}
	

   $scope.zingChartCircle = function(){
	   $scope.loaded = false;
	   $scope.myJsonCircle = {
	            globals: {
	                shadow: false,
	                fontFamily: "Verdana",
	                fontWeight: "100"
	            },
	            type: "pie",
	            backgroundColor: "#fff",

	            legend: {
	                layout: "x5",
	                position: "50%",
	                borderColor: "transparent",
	                marker: {
	                    borderRadius: 10,
	                    borderColor: "transparent"
	                }
	            },
	            tooltip: {
	                text: "%v requests"
	            },
	            plot: {
	                refAngle: "-90",
	                borderWidth: "0px",
	                valueBox: {
	                    placement: "in",
	                    text: "%npv %",
	                    fontSize: "15px",
	                    textAlpha: 1,
	                }
	            },
	            series: $scope.mostPopiulatToday
	        };

   } 
   
   $scope.actualYYYYMM = "";
   var actual_year_month = function(){
		var today = new Date();
		var yyyy = today.getFullYear();
		var mm = today.getMonth()+1;
		
		if(mm<10) {
		    mm='0'+mm
		} 

		today = yyyy+'-'+mm;
		
		$scope.actualYYYYMM = today;
   }   
   
   actual_year_month();
	
	$scope.czas = function(){    	 
   	    WidgetService.getListVisitPerMonth(document.getElementById("date").value).then(function(dane){

   	    	console.log(dane);
   	    	zingchartWidget(dane);
   	    	$scope.loaded = true;
   	    })    	 
	}
	
	$scope.loaded = false;
    
    WidgetService.getListVisitPerMonth().then(function(dane){
    	zingchartWidget(dane);	
    	$scope.loaded = true;
    })
    
/*     var mostPopularRecipesThisDay = function(){
    	$http.get("/ProjectSecurityGit/widget/mostPopularRecipesThisDay").then(function(dane){
    		$log.info(dane);
    	});
    } */
    
});

/* $(function() {
    $("#resizable").resizable({
        handles: 'ne, se, sw, nw'
    });	  
}); */

</script>

<style type="text/css">
#resizable {
  height: 100px;
  width: 600px;
  padding : 5px;
  float: left;
}
#resizable2 {
  width: 300px;
  padding : 5px;
  float: left;
}
body {
	color: white;
	
}
.table-condensed{
	background-color: black;
}

</style>