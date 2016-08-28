angular.module('NAJTek')
    .config(function($stateProvider, $urlRouterProvider, AppConstant) {
        $urlRouterProvider.otherwise("/organizations");

        $stateProvider
            .state('organizations', {
                url: '/organizations',
                template:  '<nt-organizations-component></nt-organizations-component>',
                ncyBreadcrumb: {
                  label: 'Organizations'
                },
                data: {
                    leftNavLinks: [{title: 'Organizations', readOnly: true}]
                }
            }).state('organizations.schools', {
                url: '/:organizationId/schools',
                template: '<nt-schools-component></nt-schools-component>',
                ncyBreadcrumb: {
                    label: 'Schools'
                },
                data: {
                    leftNavLinks: [{title: 'Schools', readOnly: true}]
                }
            });
});