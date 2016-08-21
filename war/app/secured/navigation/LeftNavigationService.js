angular.module('NAJTek')
    .factory('LeftNavigationService', [
        '$http',
        '$location',
        'AppConstant',
        function($http, $location, AppConstant) {
            var leftNavLinks = [];
            var listeners = [];

            var factory = {};

            factory.onLeftNavChange = function(callback) {
                listeners.push(callback);
            };

            factory.getLeftNavLinks = function() {
                return leftNavLinks;
            };

            factory.resetLeftNav = function(newLeftNavLinks) {
                leftNavLinks.length = 0;
                angular.extend(leftNavLinks, newLeftNavLinks);
                for (var i=0; i < listeners.length; i++) {
                    listeners[i](leftNavLinks);
                }
            };

            return factory;
    }]);
