'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', []).
  value('version', '0.1')
  .factory('Resources', function($resource) {
  	return $resource('/csm/rest/:table',
  	{

  	},
  	{
  		login:
  		{
  			method: 'POST',
  			params: {table: 'login'}
  		},
      getSchedules:
      {
        method: 'GET',
        params: {table: 'schedules'}
      },
      getCourses:
      {
        method: 'GET',
        params: {table: 'courses'}
      },
      dropCourse:
      {
        method: 'DELETE',
        params: {table: 'courses'}
      },
      enrollCourse: {
        method: 'POST',
        params: {table: 'courses'}
      },
      swapCourses: {
        method: 'POST',
        params: {table: 'swap'}
      },
      getWatchList: {
        method: 'GET',
        params: {table: 'watchList'}
      },
      addToWatchList: {
        method: 'POST',
        params: {table: 'watchList'}
      },
      deleteFromWatchList: {
        method: 'DELETE',
        params: {table: 'watchList'}
      }
  	});
  });
