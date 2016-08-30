angular.module('NAJTek')
    .factory('UserSearch', [ '$resource', 'AppConstant', '$log',
        function($resource, AppConstant, $log) {

            return $resource(AppConstant.REST_SECURED_PREFIX + 'user/search', {}, {
                    query:  { method: 'POST', isArray: true }
            });
        } ]);