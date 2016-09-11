angular.module('NAJTek').factory(
    'UserRoleService', [ '$log', function($log) {
        var userRoles = [
            {"id":0,"role":"Admin","userId":0},
            {"id":0,"role":"Staff Member","userId":0},
            {"id":0,"role":"Parent","userId":0}
            ]
        var factory = {};

        factory.getUserRoles = function() {
            return angular.copy(userRoles);
        };

        return factory;
    }]);
