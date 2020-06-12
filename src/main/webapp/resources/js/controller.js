var cartApp = angular.module('cartApp', []); //모듈이름은 cartApp, 의존하는 라이브러리는 없음

cartApp.controller("cartCtrl", function($scope, $http) { //이 컨트롤러가 수행이 되면 scope와 http 모듈이 의존성 주입이 됨 (http는 rest API를 위해)
	

	$scope.initCartId = function(cartId) {
		$scope.cartId = cartId;
		$scope.refreshCart(); //refreshCart()가 호출이 됨
		
	};

	$scope.refreshCart = function() {
		
		$http.get('/eStore/api/cart/' + $scope.cartId).then( //웹서버가 받게 됨
				function successCallback(response) {
					$scope.cart = response.data;
					$scope.total = $scope.calGrandTotal();
				});
		
	};

	$scope.clearCart = function() {
				
		$http({
			method : 'DELETE',
			url : '/eStore/api/cart/' + $scope.cartId
		}).then(function successCallback() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});

	};

	$scope.addToCart = function(productId) {
		
		$http.put('/eStore/api/cart/cartItem/' + productId).then( //then이 callback function (응답이 왔을 때 아래 함수들을 수행함)
		function successCallback() { //short format
			alert("Product successfully added to the cart!");
			$scope.refreshCart();
		}, function errorCallback() {
			alert("Adding to the cart failed!")
		});
		

	};
	
	$scope.plustItem = function(product) {
		
		
		$http.put('/eStore/api/cart/plusItem/' + product.id).then( //then이 callback function (응답이 왔을 때 아래 함수들을 수행함)
		function successCallback() { //short format
			alert("Product successfully added to the cart!");
			$scope.refreshCart();
		}, function errorCallback() {
			alert("Adding to the cart failed!")
		});
		

	};
	
	$scope.minusItem = function(product) {
		
		$http.put('/eStore/api/cart/minusItem/' + product.id).then( //then이 callback function (응답이 왔을 때 아래 함수들을 수행함)
				function successCallback() { //short format
					alert("Product successfully deleted from the cart!");
					$scope.refreshCart();
				}, function errorCallback() {
					alert("Deleting from the cart failed!")
				});
	};


	$scope.removeFromCart = function(product) {
		
		$http({ //long format
			method : 'DELETE',
			url : '/eStore/api/cart/cartItem/' + product.id
		}).then(function successCallback() { //then이후 함수가 두개있는데 두개가 있으면 첫번째 것이 successCallbackFunction이고 두번째 것이 errorCallbackFunction
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	$scope.calGrandTotal = function() {
		var grandTotal = 0;

		for (var i = 0; i < $scope.cart.cartItems.length; i++) {
			grandTotal += $scope.cart.cartItems[i].totalPrice;
		}

		return grandTotal;
	};
	
		
	
});