'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
	'ngRoute',
	'ngTouch',
	'ngAnimate',
	'myApp.filters',
	'myApp.services',
	'myApp.directives',
	'myApp.controllers'
]).
config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: 'LoginCtrl'});
	$routeProvider.otherwise({redirectTo: '/login'});
}]);
