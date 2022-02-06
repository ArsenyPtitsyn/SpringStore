angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.fillTable = function () {
        $http.get(contextPath + 'products')
        .then(function (response) {
            $scope.ProductsList = response.data;
        });
    };

    $scope.fillTable();
});