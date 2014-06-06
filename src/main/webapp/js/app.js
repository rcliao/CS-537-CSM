'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
	'ngRoute',
	'ngTouch',
	'ngAnimate',
	'ngResource',
	'ngCookies',
	'ui.bootstrap',
	'google-maps',
	'myApp.filters',
	'myApp.services',
	'myApp.directives',
	'myApp.controllers'
]).
config(['$routeProvider', function($routeProvider) {
	// init the access level used for route provider
    var access = routingConfig.accessLevels;

	$routeProvider.when(
		'/login',
		{
			templateUrl: 'partials/login.html',
			controller: 'LoginCtrl',
			access: access.public
		}
	);
	$routeProvider.when(
		'/home',
		{
			templateUrl: 'partials/home.html',
			access: access.public
		}
	);
	$routeProvider.when(
		'/get',
		{
			templateUrl: 'partials/get.html',
			controller: 'GETCtrl',
			access: access.student
		}
	);
	$routeProvider.when(
		'/parking',
		{
			templateUrl: 'partials/parking.html',
			controller: 'ParkingCtrl',
			access: access.student
		}
	);
	$routeProvider.when(
		'/schedule',
		{
			templateUrl: 'partials/schedule.html',
			controller: 'ScheduleCtrl',
			access: access.student
		}
	);
	$routeProvider.when(
		'/email',
		{
			templateUrl: 'partials/email.html',
			controller: 'EmailCtrl',
			access: access.student
		}
	);
	$routeProvider.when(
		'/announcements',
		{
			templateUrl: 'partials/announcements.html',
			controller: 'AnnonCtrl',
			access: access.student
		}
	);
	$routeProvider.when(
		'/admin',
		{
			templateUrl: 'partials/admin.html',
			controller: 'AdminCtrl',
			access: access.student
		}
	);
	$routeProvider.otherwise({redirectTo: '/login'});
}])
.run(['$rootScope', '$location', 'Auth', function ($rootScope, $location, Auth) {
	// whenever the route changes, check the access level, if not logged, redirect to home page
	$rootScope.$on('$routeChangeStart', function (event, next) {
		$rootScope.error = null;
		if (!Auth.authorize(next.access)) {
			if(Auth.isLoggedIn()) $location.path('/');
			else                  $location.path('/login');
		}
	});

	$rootScope.appInitialized = true;
}]);
