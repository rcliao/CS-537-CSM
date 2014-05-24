'use strict';

/* jasmine specs for controllers go here */
describe('controllers', function(){
  beforeEach(module('myApp.controllers'));


  it('should have MainCtrl', inject(function($controller) {
    //spec body
    var mainCtrl = $controller('MainCtrl');
    expect(mainCtrl).toBeDefined();
  }));

  it('should GETCtrl' inject(function($controller) {
    //spec body
    var getCtrl = $controller('getCtrl');
    expect(getCtrl).toBeDefined();
  }));
});

describe('GETCtrl', function() {
  // set up the controllers
  beforeEach(module('myApp.controllers'));
});