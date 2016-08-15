angular.module('NAJTek').config(function($stateProvider, $urlRouterProvider, AppConstant) {

  $urlRouterProvider.otherwise("/manageSchool");
  //
  // Now set up the states
  $stateProvider
    .state('manageSchool', {
      url: '/manageSchool',
      template:  '<nt-manage-school-component></nt-manage-school-component>'
    });
});