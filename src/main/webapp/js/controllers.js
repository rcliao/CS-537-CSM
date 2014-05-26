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
			$http.post('/csm/rest/login', $scope.user)
				.success(function() {
					$scope.loading = false;
				})
				.error(function() {
					$scope.loading = false;
					$scope.loginError = true;

					$timeout($scope.setStatusBack, 2000);
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
		.controller('LoginCtrl', ['$scope', 'Resources', '$timeout', 'Auth', '$location',
			function($scope, Resources, $timeout, Auth, $location) {
				if ($scope.user.role > 1) {
					$location.path('/home');
				}

				$scope.login = function() {
					// should be a directive
					$scope.loading = true;
					Auth.login(
						$scope.user,
						function() {
							$scope.loading = false;
							$location.path('/home');
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
				$scope.terms = [
					'Summer 2014',
					'Fall 2014',
					'Spring 2015'
				];

				$scope.term = $scope.terms[0];

				$scope.searchStatus = 'search';

				$scope.init = function() {
					$('.ui.accordion')
						.accordion()
					;

					$('.ui.quarter.dropdown')
						.dropdown()
					;
				};

				$scope.searchCourses = function() {
					$scope.searchStatus = 'loading';

					Resources.getSchedules(
						{
							'courseName': $scope.query,
							'term': $scope.term
						},
						function(data) {
							$scope.courses = data;

							$scope.searchStatus = 'search';
						},
						function(err) {
							$scope.errorMsg = err;

							$scope.searchStatus = 'warning';

							$timeout(function() {
								$scope.searchStatus = 'search';
							}, 2000);
						}
					);
				};
			}
		]);
}());
