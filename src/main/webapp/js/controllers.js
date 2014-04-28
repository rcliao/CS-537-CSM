'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
	.controller('MainCtrl', ['$scope', function($scope) {
	  	$scope.init = function() {
			$('.ui.sidebar')
				.sidebar()
			;
			$('.ui.dropdown')
				.dropdown()
			;
		};

		// functions related to the menu control
		$scope.showMenu = function() {
			$('.ui.sidebar')
				.sidebar('show')
			;
		};
		$scope.closeMenu = function() {
			$('.ui.sidebar')
				.sidebar('hide')
			;
		};
		$scope.toggleMenu = function() {
			$('.ui.sidebar')
				.sidebar('toggle')
			;
		};
	}])
	.controller('LoginCtrl', ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {
		$scope.login = function() {
			$scope.loading = true;
			$http.get('/rest/login')
				.success(function() {
					$scope.loading = false;
				})
				.error(function() {
					$scope.loading = false;
					$scope.loginError = true;

					$timeout($scope.setStatusBack, 2000);
				});
		};

		$scope.setStatusBack = function() {
			$scope.loginError = false;
		};
	}]);
