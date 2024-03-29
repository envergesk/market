angular.module('market').controller('cartController', function ($scope, $http, $location, $localStorage){

    const contextPath = 'http://localhost:5555/cart/';
    const coreContextPath = 'http://localhost:5555/core/';

    $scope.loadCart = function () {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.marketGuestCartId).then(function (response) {
            console.log(response.data);
            $scope.cart = response.data;
        });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.marketGuestCartId + '/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.removeFromCart = function (productId) {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.marketGuestCartId + '/remove/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marketGuestCartId + '/add/' + productId).then(function (response) {
        });
    }

    $scope.createOrder = function () {
        $http.post(coreContextPath + 'api/v1/orders/').then(function (response) {
            alert('Order created');
            $scope.loadCart();
        });
    }

    $scope.loadCart();
});