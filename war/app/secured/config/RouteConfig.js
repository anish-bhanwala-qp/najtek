angular.module('NAJTek').config(function($stateProvider, $urlRouterProvider, AppConstant) {

  $urlRouterProvider.otherwise("/organizations");
  //
  // Now set up the states
  $stateProvider
    .state('organizations', {
      url: '/organizations',
      template:  '<nt-organization-component></nt-organization-component>'
    });
});