angular.module('NAJTek')
    .run(['$state', '$rootScope', 'LeftNavigationService',
        function($state, $rootScope, LeftNavigationService) {
            $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
                LeftNavigationService.resetLeftNav($state.current.data.leftNavLinks || []);
            });
    }]);