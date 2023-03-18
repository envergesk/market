angular.module('market').controller('ordersController', function ($scope, $http){

    const contextPath = 'http://localhost:5555/core/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v1/orders/' + $localStorage.marketGuestCartId).then(function (response) {
            console.log(response.data);
            $scope.orders = response.data;
        });
    }

    $scope.loadOrders();
});