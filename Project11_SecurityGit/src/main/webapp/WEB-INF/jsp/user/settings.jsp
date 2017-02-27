<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    

		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
				
<link href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
<script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js" ></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-sanitize.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
<link href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css" rel="stylesheet"/>		

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">		  
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>
<script src = "http://cdn.zingchart.com/zingchart.min.js" ></script>
<script src = "http://cdn.zingchart.com/angular/zingchart-angularjs.js"></script>
<script src= "https://cdn.zingchart.com/zingchart.min.js"></script>

    <script src="http://code.highcharts.com/highcharts.js"></script>

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
#chartLineFirstTab{
	height: 300px;
}
#chart-1-graph-id0-legend-frame{
left: 50px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {

        $('#datetimepicker10').datetimepicker({
            viewMode: 'years',
            format: 'YYYY-MM'
        });
	
 	$("#showWidgets li").click(function(){
		if( $('.box').eq($(this).index()).text() =="Show All Month"){		
			
  			
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

<div id="MainControllerSettings" ng-controller="MainControllerSettings">

<div>
  <!-- Nav tabs -->
  <ul id="showWidgets" class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#month" aria-controls="month" role="tab" data-toggle="tab" class="box" ng-click="czas()">Show All Month</a></li>
    <li role="presentation"><a href="#day" aria-controls="day" role="tab" data-toggle="tab" class="box" ng-click="zingChartCircle()">Today</a></li>
    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab" class="box">Settings</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
   	<div role="tabpanel" class="tab-pane active" id="month" ng-show="true">    	
 		<div>
 		<br/>
		    <div class="col-sm-6" style="width: 200px;" >
		        <div class="form-group">
		            <div class='input-group date' id='datetimepicker10'>
		                <input type="text"  id="date" name="date" class="form-control" style="background-color: black;" value="{{actualYYYYMM}}"/>
		                <span class="input-group-addon">
		                    <span class="glyphicon glyphicon-calendar">
		                    </span>
		                </span>
		            </div>
		        </div>
		    </div>        
            <button ng-click="czas()">show</button>
		</div>
<!-- #####################################################################################################################	 -->	

		<div ng-if="loaded===true">
			<chart id="chartLineFirstTab" title="Most viewed your recipes in month" xData="lineChartXData" yData="lineChartYData" xName="Day" yName="Visits"></chart>
		</div>		

<!-- #####################################################################################################################	 -->		
		<nvd3 options="options" data="data"></nvd3>
    </div>

    <div role="tabpanel" class="tab-pane" id="day" ng-if="loaded===false"> <!-- loaded===false -->
<!-- &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&	 -->
    	 <div ng-if="mostPopiulatToday.length">
    		 <div zingchart id="chart-1" zc-json="myJsonCircle" zc-width="100%" zc-height="400px"></div>
    	 </div>
    	<div ng-if="!mostPopiulatToday.length">
    		<span align="center"><h2><b>No Events</b></h2></span>
    	</div>  
<!-- &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&	 -->    	  		
    </div>
    <div role="tabpanel" class="tab-pane" id="settings">
		<table class="table table-inverse">
		  <thead>
		    <tr>
		      
		      <th>Login</th>
		      <th>Username</th>
		      <th>Surname</th>
		      <th>Address</th>
		      <th>Email</th>
		      <th>#</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <td><c:out value="${user.login}"/></td>
		      <td><c:out value="${user.username}"/></td>
		      <td><c:out value="${user.surname}"/></td>
		      <td><c:out value="${user.address}"/></td>
		      <td><c:out value="${user.mail}"/></td>
		      <td><a href="#" class="btn btn-danger">Change</a></td>
		    </tr>
		  </tbody>
		</table>    
    </div>
  </div>
</div>
	
</div>


<script type="text/javascript">

app.directive('chart', function () {
    return {
        restrict:'E',
        template:'<div></div>',
        transclude:true,
        replace:true,
        scope: '=',
        link:function (scope, element, attrs) {
            /* console.log('oo',attrs,scope[attrs.formatter]) */
            var opt = {
                chart:{
                    renderTo:element[0],
                    type:'line',
                    marginRight:130,
                    marginBottom:40
                },
                title:{
                    text:attrs.title,
                    x:-20 //center
                },
                subtitle:{
                    text:attrs.subtitle,
                    x:-20
                },
                xAxis:{
                    //categories:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                    tickInterval:1,
                    title:{
                        text:attrs.xname
                    }
                },
                plotOptions:{
                    lineWidth:0.5
                },
                yAxis:{
                    title:{
                        text:attrs.yname
                    },
                    tickInterval:(attrs.yinterval)?new Number(attrs.yinterval):null,
                    max:attrs.ymax,
                    min: attrs.ymin
                },
                tooltip:{
                    formatter:scope[attrs.formatter]||function () {
                        return '<b>' + this.y + '</b>'
                    }
                },
                legend:{
                    layout:'vertical',
                    align:'right',
                    verticalAlign:'top',
                    x:-10,
                    y:100,
                    borderWidth:0
                },
                series:[
                    {
                    	
                    }
                ]
            }


            //Update when charts data changes
            scope.$watch(function (scope) {
                return JSON.stringify({
                    xAxis:{
                        categories:scope[attrs.xdata]
                        },
                    series:scope[attrs.ydata]
                });
            }, function (news) {
//                if (!attrs) return;
                news = JSON.parse(news)
                if (!news.series)return;
                angular.extend(opt,news)
                var chart = new Highcharts.Chart(opt);
            });
        }
    }

})

app.controller("MainControllerSettings", function($scope, $http, $log, WidgetService) {

	   $scope.loaded = false;
	   $scope.actualYYYYMM = "";	
	
	$scope.mostPopularRecipesThisDay = function(){
		$http.get("/ProjectSecurityGit/widget/mostPopularRecipesThisDay").then(function(list){
			console.log(list.data);
			$scope.mostPopiulatToday = list.data;
		});
	}

 var zingchartWidget = function(dane){ 
        var data = {
					"xData": ["1", "2", "3", "4", "5", "6","7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
					          ],
					"yData":[{
		                "name": document.getElementById("date").value,
		                "data": dane
	            	}]
	            }
	            
	            $scope.lineChartYData=data.yData
	            $scope.lineChartXData=data.xData  	 
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
	                position: "1%",
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
   
	$scope.czas = function(){
	
     	    WidgetService.getListVisitPerMonth(document.getElementById("date").value).then(function(dane){
	   	    	zingchartWidget(dane);
	   	    	$scope.loaded = true;
     	    });     	 
	}
   
   var actual_year_month = function(){
		var today = new Date();
		var yyyy = today.getFullYear();
		var mm = today.getMonth()+1;
		
		if(mm<10) {
		    mm='0'+mm
		} 

		today = yyyy+'-'+mm;
		
		$scope.actualYYYYMM = today;		
		
		WidgetService.getListVisitPerMonth(today).then(function(dane){
   	    	zingchartWidget(dane);
   	    	$scope.loaded = true;
 	    });
   }
   
   
   
   $scope.mostPopularRecipesThisDay();	
   	actual_year_month();

    
});


/* $(document).ready(function(){ 
     $("#resizable").resizable({
        handles: 'ne, se, sw, nw'
    });	
      
    
});*/ 

</script>

