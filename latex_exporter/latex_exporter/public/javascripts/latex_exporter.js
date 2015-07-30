
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
  
  var columns = [];
  var lines = [];
  
  // $scope.execution;


  console.log($scope.table);

  var indexLines = 0;
  var indexColumns = 0;

  $scope.newLine = function () {
    if ($scope.lines.length > 0) {
      indexLines++;
      // console.log($scope.table[0][0]);
      $scope.lines.push({ index: indexLines, val: $scope.execution });
    }
  };

  $scope.newColumn = function () {
    if ($scope.columns.length > 0) {
      indexColumns++;
      $scope.columns.push(indexColumns);
    }
  };

  $scope.updateTitle = function (line, data) {
    $scope.title[line] = data;
  };

  $scope.updateTable = function () {
    for (var i = 0; i <= indexLines; i++) {
      for (var j = 0; j <= indexColumns; j++) {
        if (typeof $scope.table[i] == 'undefined') {
          $scope.table[i] = [];
        }
        if (typeof $scope.table[i][j] == 'undefined') {
          $scope.table[i][j] = '-';
        }
       
          $scope.table[i][j] = getValueFromResponse(lines[i], columns[j]); 
        
      }
    }
    $scope.showTable();
  };


  var getValueFromResponse = function(algorithm, measure){
    
    // return $scope.execution[algorithm].measures[measure].val;
    
    for(var algorithmVal in $scope.execution){
      if (algorithmVal == algorithm){
        // console.log(algorithm);
        for (var measureVal in $scope.execution[algorithmVal].measures){
          // console.log($scope.execution[algorithmVal].measures[measureVal]);
          if($scope.execution[algorithmVal].measures[measureVal].prop==measure)
            return $scope.execution[algorithmVal].measures[measureVal].val;
        }
        
      }
    }
    
  };
   

  $scope.showTable = function () {
    $http({
      method: 'post',
      url: 'makeLatexTable',
      data: { table: $scope.table },
    }).success(function (data, status, headers, config) {
      $scope.latexTableView = data;
      console.log(data);
    }).error(function (data, status, headers, config) {
    });
  };


  $scope.updateTableColumns = function(index, measure){
    columns[index] = measure;
    $scope.updateTable();
  };
  
  $scope.updateTableLines = function(index, algorithm){
    lines[index] = algorithm;
    $scope.updateTable();
  };

  $scope.makeFirstLine = function (execution) {
    indexLines = 0;
    $scope.execution = execution; 
    indexColumns = 0;
    $scope.lines.push({ index: indexLines, val: execution });
    $scope.columns.push(indexColumns);
    
    // get measures values
    $scope.measures = [];
    for(var ex in execution){
      if(ex!="name"){
        for (var measure in execution[ex].measures){
          console.log(execution[ex].measures[measure].prop);
          $scope.measures.push(execution[ex].measures[measure].prop);
        }
          return;
      }
    }
  };


  $('#uploadForm').submit(function () {
    $(this).ajaxSubmit({
      error: function (xhr) {
        status('Error: ' + xhr.status);
      },
      success: function (response) {
        // $scope.algorithms = $.parseJSON(response);
        $scope.response = $.parseJSON(response);
        indexLines = 0;
        indexColumns = 0;
        // $scope.lines.push({ index: indexLines, val: $scope.algorithms });
        // $scope.columns.push(indexColumns);
        $scope.$apply();
      }
    });
    //Very important line, it disable the page refresh.
    return false;
  });



}]);

