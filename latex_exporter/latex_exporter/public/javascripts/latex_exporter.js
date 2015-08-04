
var main = angular.module('main', ["ngRoute"]);

main.config(['$routeProvider',
  function ($routeProvider) {
    $routeProvider.
      when('/partial', {
      templateUrl: '/partial/part1',
      controller: 'partCtrl'
    }).
      otherwise({
      redirectTo: '/'
    });
  }]);

main.controller('rootCtrl', ['$scope', '$http', function ($scope, $http) {
  $scope.lines = [];
  $scope.response = [];
  $scope.latexTableView;
  $scope.columns = [];
  $scope.table = [[]];
  $scope.title = [];
  $scope.thumbsUp = false;
  $scope.thumbsDown = false;
  
  var indexLines = 0;
  var indexColumns = 0;

  $scope.newLine = function () {
    if ($scope.lines.length > 0) {
      $scope.lines.push(indexLines);
      indexLines++;
    }
  };

  $scope.newColumn = function () {
    if ($scope.columns.length > 0) {
      $scope.columns.push(indexColumns);
      indexColumns++;
    }
  };

  $scope.updateTitle = function (line, data) {
    $scope.title[line] = data;
  };

  $scope.updateTable = function () {
    for (var i = 1; i <= indexLines; i++) {
      for (var j = 1; j <= indexColumns; j++) {
        if (typeof $scope.table[i] == 'undefined') {
          $scope.table[i] = [];
        }
        if (typeof $scope.table[i][j] == 'undefined') {
          $scope.table[i][j] = '-';
        }
          $scope.table[i][j] = getValueFromResponse($scope.table[i][0], $scope.table[0][j]); 
      }
    }
    
  };


  var getValueFromResponse = function(execution, measure){
    for(var executionVal in $scope.configuration.executions){
      if ($scope.configuration.executions[executionVal].label == execution){
        for (var measureVal in $scope.configuration.executions[executionVal].measures){
          if($scope.configuration.executions[executionVal].measures[measureVal].prop==measure)
            return $scope.configuration.executions[executionVal].measures[measureVal].val;
        }
      }
    }
    
  };
   

  $scope.showTable = function () {
    $scope.table[0][0] = "-";
    $http({
      method: 'post',
      url: 'makeLatexTable',
      data: { table: $scope.table },
    }).success(function (data, status, headers, config) {
      $scope.latexTableView = data;
      
    }).error(function (data, status, headers, config) {
    });
  };


  $scope.updateTableColumns = function(index, val){
    index++;
    
      if (typeof $scope.table[0] == 'undefined') {
          $scope.table[0] = [];
        }
        if (typeof $scope.table[0][index] == 'undefined') {
          $scope.table[0][index] = '-';
        }
     $scope.table[0][index] = val.prop;
      $scope.updateTable();
    $scope.showTable();
     
  };
  
  $scope.updateTableLines = function(index, val){
    index++;
    
        if (typeof $scope.table[index] == 'undefined') {
          $scope.table[index] = [];
        }
        if (typeof $scope.table[index][0] == 'undefined') {
          $scope.table[index][0] = '-';
        }
        $scope.table[index][0] = val.label;
         $scope.updateTable();
    $scope.showTable();
  };

  $scope.makeFirstLine = function (execution) {
    clearTable();
    indexLines = 1;
    indexColumns = 1;
    $scope.execution = execution; 
    $scope.lines.push(0);
    $scope.columns.push(0);
  };
  
  function clearTable(){
            indexLines = 0;
        indexColumns = 0;
        $scope.columns = [];
        $scope.lines = [];        
          $scope.table = [[]];
            $scope.latexTableView = "";
          
  }


  $('#uploadForm').submit(function () {
    $(this).ajaxSubmit({
      error: function (xhr) {
        status('Error: ' + xhr.status);
          $scope.thumbsDown = true;
          $scope.thumbsUp = false;

      },
      success: function (response) {
        $scope.response = $.parseJSON(response);
        
        if(typeof $scope.response[0] != 'undefined'){
          
          $scope.thumbsUp = true;
          $scope.thumbsDown = false;
        }
        else{
          $scope.thumbsDown = true;
          $scope.thumbsUp = false;
        }

        indexLines = 0;
        indexColumns = 0;
        clearTable();
        $scope.$apply();
      }
    });
    //Very important line, it disable the page refresh.
    return false;
  });



}]);

