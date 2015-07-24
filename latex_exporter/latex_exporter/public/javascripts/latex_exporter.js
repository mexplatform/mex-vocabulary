
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
  $scope.latexTableView;
  $scope.columns = [];
  $scope.table = [[]];
  $scope.title = [];


  console.log($scope.table);

  var indexLines = 0;
  var indexColumns = 0;

  $scope.newLine = function () {
    if ($scope.lines.length > 0) {
      indexLines++;
      $scope.lines.push({ index: indexLines, val: $scope.algorithms });
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

  $scope.updateTable = function (line, column, data) {
    for (var i = 0; i <= indexLines; i++) {
      for (var j = 0; j <= indexColumns + 1; j++) {
        if (typeof $scope.table[i] == 'undefined') {
          $scope.table[i] = [];
        }
        if (typeof $scope.table[i][j] == 'undefined') {
          $scope.table[i][j] = '-';
        }
        if (i == line && j == column) {
          $scope.table[line][column] = data;
        }
      }
    }
    $scope.showTable();
  };


  $scope.showTable = function () {  
    $http({
      method: 'post',
      url: 'makeLatexTable',
      data: { table: $scope.table},
    }).success(function (data, status, headers, config) {
      $scope.latexTableView = data;
      console.log(data);
    }).error(function (data, status, headers, config) {
    });
  };




  $('#uploadForm').submit(function () {
    $(this).ajaxSubmit({
      error: function (xhr) {
        status('Error: ' + xhr.status);
      },
      success: function (response) {
        $scope.algorithms = $.parseJSON(response);
        indexLines = 0;
        indexColumns = 0;
        $scope.lines.push({ index: indexLines, val: $scope.algorithms });
        $scope.columns.push(indexColumns);
        $scope.$apply();
      }
    });
    //Very important line, it disable the page refresh.
    return false;
  });



}]);

