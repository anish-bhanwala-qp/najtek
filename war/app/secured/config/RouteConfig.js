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
            }).state('organizations.schools.schoolSetting', {
                url: '/:schoolId',
                template: '<nt-school-setting-component></nt-school-setting-component>',
                ncyBreadcrumb: {
                    label: 'Setting'
                },
                data: {
                    leftNavLinks: [
                            {title: 'Staff', readOnly: true},
                            {title: 'Staff', readOnly: true}
                        ]
                }
            }).state('organizations.schools.staffMember', {
                url: '/:schoolId/staffMember',
                    template: '<nt-staff-members-component></nt-staff-members-component>',
                    ncyBreadcrumb: {
                    label: 'Staff Members'
                },
                data: {
                    leftNavLinks: [
                        {title: 'Staff', url: true},
                        {title: 'Staff', readOnly: true}
                    ]
                }
            });
});