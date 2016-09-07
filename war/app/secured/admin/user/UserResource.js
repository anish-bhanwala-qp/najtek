angular.module('NAJTek')
    .factory('User', [ '$resource', 'AppConstant', '$log', '$cacheFactory',
        function($resource, AppConstant, $log, $cacheFactory) {
            var cache = $cacheFactory('UserCache');

            var interceptor = {
                response: function (response) {
                    cache.remove(response.config.url);
                    $log.log('cache removed', response.config.url);
                    return response.data;
                }
            };
            return $resource(AppConstant.REST_SECURED_PREFIX + 'organization/:organizationId/user/:id', {
                    organizationId: '@organizationId',
                    id: '@id'
                }, {
                    query:  { method: 'GET', cache: cache, isArray: true },
                    save:   { method: 'POST', interceptor: interceptor },
                    remove: { method: 'DELETE', interceptor: interceptor }
            });
        } ]);