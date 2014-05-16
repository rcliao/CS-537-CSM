/*global $:false */
(function() {
	'use strict';

	/* Controllers */
	angular.module('myApp.controllers', [])
		.controller('MainCtrl', ['$scope', '$rootScope',
			function($scope, $rootScope) {
			  	$scope.init = function() {
					$('.ui.sidebar')
						.sidebar()
					;
					$('.ui.dropdown')
						.dropdown()
					;
				};

				// watch for the location changes and hide the sidebar
				$rootScope.$on('$locationChangeSuccess', function(){
					$('.ui.sidebar')
						.sidebar('hide')
					;
				});

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
			}
		])
		.controller('LoginCtrl', ['$scope', 'Resources', '$timeout',
			function($scope, Resources, $timeout) {
				$scope.login = function() {
					// should be a directive
					$scope.loading = true;
					Resources.login(
						{},
						$scope.user,
						function() {
							$scope.loading = false;
						},
						function(err) {
							$scope.loading = false;
							$scope.loginError = true;
							$scope.loginErrorMsg = err;

							$timeout($scope.setStatusBack, 2000);
						}
					);
				};

				$scope.setStatusBack = function() {
					$scope.loginError = false;
				};
			}
		])
		.controller('GETCtrl', ['$scope', 'Resources', '$timeout',
			function($scope, Resources, $timeout) {
				
			}
		]);
}());