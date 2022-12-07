angular.module('app',[]).controller('indexController', function($scope, $http){
    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/market/api/v1/products').then(function (response){
            console.log(response.data);
            $scope.productsList = response.data;
        });
    }

    $scope.showProductInfo = function (productId) {
        $http.get('http://localhost:8189/market/api/v1/products/' + productId).then(function (response){
            alert(response.data.title);
        });
    }

    $scope.deleteProduct = function (productId) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + productId).then(function (response){
            $scope.loadProducts();
        });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8189/market/api/v1/cart/add/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8189/market/api/v1/cart').then(function (response){
            console.log(response.data);
            $scope.cart = response.data;
        });
    }

    $scope.clearCart = function () {
        $http.get('http://localhost:8189/market/api/v1/cart/clear').then(function (response){
            $scope.loadCart();
        });
    }

    $scope.removeFromCart = function (productId) {
        $http.get('http://localhost:8189/market/api/v1/cart/remove/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.loadProducts();
    $scope.loadCart();
});