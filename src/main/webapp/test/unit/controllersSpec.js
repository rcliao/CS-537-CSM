'use strict';

/* jasmine specs for controllers go here */
describe('controllers', function(){
  beforeEach(angular.mock.module('myApp.controllers'));

  it('should have MainCtrl',function(){
    var mainCtrl =$controllers('MainCtrl');
    expect(mainCtrl).toBeDefined();
  });
});

describe('play_ground', function() {
  it('1 should be 1', function(){
    expect(true).toBe(true);
  });

  it('1+1 should be 2' , function(){
    expect(1+1).toBe(2);
  });

  it('test veriable a is 1' , function(){
    var a=1;

    expect(a).toBe(1);
  });

  it('test variable a is not 0' , function() {
    var a=1;

    expect(1).not.toBe(0);
  });
});
