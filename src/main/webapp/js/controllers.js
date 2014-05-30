/*global $:false */
(function() {
	'use strict';

	/* Controllers */
	angular.module('myApp.controllers', [])
		.controller('MainCtrl', ['$scope', '$rootScope', 'Auth',
			function($scope, $rootScope, Auth) {
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

				$scope.logout = function() {
					var logoutCallback = function() {

					};

					Auth.logout(logoutCallback);
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
							'name': $scope.query,
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
		])
		.controller('EmailCtrl', ['$scope', 'Resources',
			function($scope) {
				$scope.init = function() {
					var advancedEditor;

					advancedEditor = new Quill('.advanced-wrapper .editor-container', {
						modules: {
							'toolbar': {
								container: '.advanced-wrapper .toolbar-container'
							},
							'link-tooltip': true,
							'image-tooltip': true,
							},
							theme: 'snow'
						});

					advancedEditor.addStyles({
						'body': { 'color': '#fff' }
					});

					advancedEditor.on('selection-change', function(range) {
						return console.info('advanced', 'selection', range);
					});

					advancedEditor.on('text-change', function(delta, source) {
					var sourceDelta, targetDelta;
					console.info('advanced', 'text', delta, source);
					if (source === 'api') {
						return;
					}
					sourceDelta = advancedEditor.getContents();
					});
				};
			}
		]);
}());