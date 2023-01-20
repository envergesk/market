angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/market/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    console.log($http);
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    }

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.marketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.marketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.authCheck = function () {
        $http.get('http://localhost:8189/market/auth_check').then(function (response){
           alert(response.data.value);
        });

    if ($localStorage.marketUser) {
        try {
            let jwt = $localStorage.marketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!");
                delete $localStorage.marketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketUser.token;
    }
}

$scope.loadProducts = function () {
    $http.get('http://localhost:8189/market/api/v1/products').then(function (response) {
        console.log(response.data);
        $scope.productsList = response.data;
    });
}

$scope.showProductInfo = function (productId) {
    $http.get('http://localhost:8189/market/api/v1/products/' + productId).then(function (response) {
        alert(response.data.title);
    });
}

$scope.deleteProduct = function (productId) {
    $http.delete('http://localhost:8189/market/api/v1/products/' + productId).then(function (response) {
        $scope.loadProducts();
    });
}

$scope.addToCart = function (productId) {
    $http.get('http://localhost:8190/market-carts/api/v1/cart/add/' + productId).then(function (response) {
        $scope.loadCart();
    });
}

$scope.loadCart = function () {
    $http.get('http://localhost:8190/market-carts/api/v1/cart').then(function (response) {
        console.log(response.data);
        $scope.cart = response.data;
    });
}

$scope.clearCart = function () {
    $http.get('http://localhost:8190/market-carts/api/v1/cart/clear').then(function (response) {
        $scope.loadCart();
    });
}

$scope.removeFromCart = function (productId) {
    $http.get('http://localhost:8190/market-carts/api/v1/cart/remove/' + productId).then(function (response) {
        $scope.loadCart();
    });
}

$scope.loadProducts();
$scope.loadCart();
})
;