angular.module('NAJTek')
    .config([ '$httpProvider', 'AppConstant',
        function($httpProvider, AppConstant) {
           /* $routeProvider.when('/home', {
                    templateUrl : AppConstant.HTML_PATH_SECURED_PREFIX
                            + 'home.html'
                }).when('/login', {
                    templateUrl : AppConstant.HTML_PATH_PUBLIC_PREFIX
                            + 'login/login.html'
                }).otherwise('/home');*/

            $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        }]);