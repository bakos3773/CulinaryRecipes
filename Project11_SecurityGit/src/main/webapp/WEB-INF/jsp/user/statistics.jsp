<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">

app.directive('wtNvd3LineChart', function ($filter) {
    return {
        restrict: 'A',
        replace: true,
        templateUrl: 'one.html',
        scope: {
          data: '=data',
          showLegend: '@',
          showTimeRange: '=?',
          timeAxisFormat: '=?'
        },
        controller: function ($scope) {
          var filter = $filter('date');
          var numberFilter = $filter('number');

          $scope.xAxisTickFormatFunction = function () {
            return function (d) {
              return filter(d, $scope.timeAxisFormat);
            };
          };

          $scope.yAxisTickFormatFunction = function () {
            return function (d) {
              if (d > 999) {
                var value;
                var scale;
                if (d < 999999) {
                  value = Math.round(d/1000);
                  scale = 'k';
                } else {
                  value = Math.round(d/1000000);
                  scale = 'm';
                }
                return numberFilter(value) + scale;
              } else {
                return numberFilter(d);
              }
            };
          };

          $scope.xFunction = function () {
            return function (d) {
              return d.timestamp;
            };
          };
          $scope.yFunction = function () {
            return function (d) {
              return d.value;
            };
          };
        },
        link: function postLink(scope, element, attrs) {
          if (!_.has(attrs, 'showTimeRange')) {
            scope.showTimeRange = true;
          }

          scope.timeAxisFormat = scope.timeAxisFormat || 'HH:mm';

          scope.$watch('data', function (data) {
            if (data && data[0] && data[0].values && (data[0].values.length > 1)) {
              var timeseries = _.sortBy(data[0].values, function (item) {
                return item.timestamp;
              });

              var start = timeseries[0].timestamp;
              var end = timeseries[timeseries.length - 1].timestamp;
              scope.start = start;
              scope.end = end;
            }
          });
        }
      };
    });
</script>


<div><h1>Cos ma byc ponizej</h1></div>
<div wtNvd3LineChart></div>

<script type="text/ng-template" id="one.html">
<div class="bar-chart">
    <div style="text-align: right;">
        <span ng-if="showTimeRange && start && end">{{start|date:'HH:mm:ss'}} - {{end|date:'HH:mm:ss'}}</span>&nbsp;
    </div>
    <nvd3-line-chart
            data="data"
            xAxisTickFormat="xAxisTickFormatFunction()"
            yAxisTickFormat="yAxisTickFormatFunction()"
            x="xFunction()"
            y="yFunction()"
            showXAxis="true"
            showYAxis="true"
            reduceXTicks="true"
            forcey="[0,100]"
            transitionduration="0"
            useInteractiveGuideline="true"
            showLegend="{{showLegend}}"
            tooltips="true">
    </nvd3-line-chart>
</div>
</script>