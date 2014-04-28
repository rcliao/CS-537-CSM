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
			.sidebar('toggle')
		;
	}
  }])
  .controller('MyCtrl2', [function() {

  }]);
