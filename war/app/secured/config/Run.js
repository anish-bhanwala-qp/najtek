angular.module('NAJTek')
    .run(['$state', '$rootScope', 'LeftNavigationService', '$log',
        function($state, $rootScope, LeftNavigationService, $log) {
            $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
                $log.info(toParams);
                if ($state.current.data && $state.current.data.leftNavLinks) {
                    var navLinks = $state.current.data.leftNavLinks;
                    angular.forEach(toParams, function(value, key) {
                      for (var i=0; i < navLinks.length; i++) {
                        var navLink = navLinks[i];
                        navLink.url = navLink.url.replace('#' + key, value);
                      }
                    });
                    LeftNavigationService.resetLeftNav(navLinks);
                }
            });
    }]);