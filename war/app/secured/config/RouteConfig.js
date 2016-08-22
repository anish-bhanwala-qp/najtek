angular.module('NAJTek').config(function($stateProvider, $urlRouterProvider, AppConstant) {

  $urlRouterProvider.otherwise("/organizations");
  //
  // Now set up the states
  $stateProvider
    .state('organizations', {
        url: '/organizations',
        template:  '<nt-organizations-component></nt-organizations-component>'
    }).state('organizations.schools', {
        url: '/:organizationId/schools',
        template: '<nt-schools-component></nt-schools-component>'
    });
});