angular.module('NAJTek')
    .run(['$state', '$rootScope', 'LeftNavigationService', '$log',
        function($state, $rootScope, LeftNavigationService, $log) {
            $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
                $log.info(toParams);
                if ($state.current.data && $state.current.data.leftNavLinks) {
                    var navLinksCopied = angular.copy($state.current.data.leftNavLinks);
                    angular.forEach(toParams, function(value, key) {
                      for (var i=0; i < navLinksCopied.length; i++) {
                        var navLink = navLinksCopied[i];
                        navLink.url = navLink.url.replace('#' + key, value);
                      }
                    });
                    LeftNavigationService.resetLeftNav(navLinksCopied);
                }
            });
    }]);