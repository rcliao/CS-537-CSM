'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
  .controller('MainCtrl', ['$scope', function($scope) {
  	$scope.init = function() {
		$('.ui.sidebar')
			.sidebar()
		;
	};

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
  .controller('MyCtrl2', [function() {

  }]);
