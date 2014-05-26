'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
	'ngRoute',
	'ngTouch',
	'ngAnimate',
	'ngResource',
	'ngCookies',
	'ui.bootstrap',
	'ngMockE2E',
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
			access: access.student
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
			access: access.student
		}
	);
	$routeProvider.when(
		'/schedule',
		{
			templateUrl: 'partials/schedule.html',
			access: access.student
		}
	);
	$routeProvider.when(
		'/email',
		{
			templateUrl: 'partials/email.html',
			access: access.student
		}
	);
	$routeProvider.when(
		'/announcements',
		{
			templateUrl: 'partials/announcements.html',
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
}])
.run(function($httpBackend) {
	//Escape string to be able to use it in a regular expression
	function regEsc(str) {
		return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
	};

	//When backend receives a request to the views folder, pass it through
    $httpBackend.whenGET(
    	RegExp(
    		regEsc(
    			'partials/'
    		)
    	)
    ).passThrough();

	$httpBackend.whenPOST('/csm/rest/login').respond(function(method, url, data){
		var user = JSON.parse(data);
		if (user.username === 'eliao' && user.password === 'abcd') {
			return [200, {success: 'true'}, {}];
		} else {
			return [401, {}, {}];
		}
	});
});
