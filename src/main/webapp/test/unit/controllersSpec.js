'use strict';

/* jasmine specs for controllers go here */
describe('controllers', function(){
  var controller;
  var scope;

  beforeEach(module('myApp'));

  describe('MainCtrl', function() {
    beforeEach(inject(function($rootScope, $controller) {
      scope = $rootScope.$new();
      controller = $controller('MainCtrl', {
        '$scope': scope
      });
    }));

    it('should have MainCtrl', function($controller) {
      expect(controller).toBeDefined();
    });
  });

  describe('GETCtrl', function($controller) {
    beforeEach(inject(function($rootScope, $controller) {
      scope = $rootScope.$new();
      controller = $controller('GETCtrl', {
        '$scope': scope
      });
    }));

    it('should have terms', function() {
      expect(scope.terms).toBeDefined();
    });
  });
});

describe('play_ground', function() {
  it('1 should be 1', function() {
    expect(true).toBe(true);
  });

  it('1 + 1 should be 2', function() {
    expect(1 + 1).toBe(2);
  });

  it('test variable a is 1', function() {
    var a = 1;

    expect(a).toBe(1);
  });

  it('test variable a is not 0' , function() {
    var a=1;

    expect(1).not.toBe(0);
  });
});
