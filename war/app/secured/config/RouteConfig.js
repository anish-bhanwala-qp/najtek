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
                    leftNavLinks: [{title: 'Organizations', url: 'organizations'}]
                }
            }).state('organizations.users', {
                url: '/:organizationId/users',
                template:  '<nt-users-component></nt-users-component>',
                ncyBreadcrumb: {
                  label: 'Users'
                },
                data: {
                    leftNavLinks: [
                        {title: 'Schools', url: 'organizations.schools({organizationId: #organizationId})'},
                        {title: 'Users', url: 'organizations.users({organizationId: #organizationId})', selected: true}
                    ]
                }
            }).state('organizations.schools', {
                url: '/:organizationId/schools',
                template: '<nt-schools-component></nt-schools-component>',
                ncyBreadcrumb: {
                    label: 'Schools'
                },
                data: {
                    leftNavLinks: [
                        {title: 'Schools', url: 'organizations.schools({organizationId: #organizationId})', selected: true},
                        {title: 'Users', url: 'organizations.users({organizationId: #organizationId})'}
                    ]
                }
            }).state('organizations.schools.schoolSetting', {
                url: '/:schoolId',
                template: '<nt-school-setting-component></nt-school-setting-component>',
                ncyBreadcrumb: {
                    label: 'Setting'
                },
                data: {
                    leftNavLinks: [
                            {title: 'Setting', readOnly: true},
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
                        {title: 'Setting', readOnly: true},
                        {title: 'Staff', readOnly: true}
                    ]
                }
            });
});