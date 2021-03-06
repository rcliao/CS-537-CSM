'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', []).
  value('version', '0.1')
  .factory('Resources', function($resource) {
  	return $resource('/csm/rest/:table/:operation/:name/:term',
  	{

  	},
  	{
  		login:
  		{
  			method: 'POST',
  			params: {table: 'Account', operation: 'login'}
  		},
      getSchedules:
      {
        method: 'GET',
        params: { table: 'GET', operation: 'schedules' },
        isArray: true
      },
      dropCourse:
      {
        method: 'DELETE',
        params: { table: 'GET', operation:'enroll' }
      },
      enrollCourse: {
        method: 'POST',
        params: { table: 'GET', operation:'enroll' }
      },
      getCourseEvents: {
        method: 'GET',
        params: { table: 'GET', operation: 'userSchedules' },
        isArray: true
      },
      getParkingLots: {
        method: 'GET',
        params: { table: 'ParkingLot', operation: 'getAvailableSpots' },
        isArray: true
      },
      sendEmail: {
        method: 'POST',
        params: { table: 'Email', operation: 'sendEmail' }
      },
      takeSpot: {
        method: 'POST',
        params: { table: 'ParkingLot', operation: 'decrementAvailableSpots' }
      },
      freeSpot: {
        method: 'POST',
        params: { table: 'ParkingLot', operation: 'incrementAvailableSpots' }
      },
      getAnnouncements: {
        method: 'GET',
        params: { table: 'Announcement', operation: 'list' },
        isArray: true
      },
      postAnnouncement: {
        method: 'POST',
        params: { table: 'Announcement', operation: 'new' }
      },
      deleteAnnouncement: {
        method: 'DELETE',
        params: { table: 'Announcement', operation: 'delete' }
      }
  	});
  })
  .factory('Auth', function(Resources, $rootScope, $cookieStore){

    var accessLevels = routingConfig.accessLevels,
        userRoles = routingConfig.userRoles;

    $rootScope.user = $cookieStore.get('user') || { username: '', role: userRoles.public };

    $rootScope.accessLevels = accessLevels;
    $rootScope.userRoles = userRoles;

    return {
      authorize: function(accessLevel, role) {
        if(role === undefined)
          role = $rootScope.user.role;

        return accessLevel & role;
      },
      isLoggedIn: function(user) {
        if(user === undefined)
          user = $rootScope.user;

        return user.role === userRoles.student || user.role === userRoles.admin;
      },
      login: function(user, successCallBack, errorCallBack) {
        Resources.login(
          {
            'username': user.username,
            // this btoa function will encode String to Base64
            'password': user.password
          },
          function() {
            // success callback
            $rootScope.user = user;
            $rootScope.user.role = userRoles.student;
            $cookieStore.put('user', $rootScope.user);

            successCallBack();
          },
          function(response) {
            if (response.status == 401) {
              // 401 error case
              errorCallBack('Username and password does not match');
            } else if (response.status == 404) {
              errorCallBack('Username does not exist');
            } else if (response.status == 500) {
              errorCallBack('Oops, there is something wrong with server.');
            }
          }
        );
      },
      logout: function(callback) {
        $cookieStore.remove('user');
        $rootScope.user = {username: '', role: userRoles.public};

        callback();
      },
      accessLevels: accessLevels,
      userRoles: userRoles
    };
  });
