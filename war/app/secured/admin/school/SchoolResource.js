angular.module('NAJTek')
    .factory('School', [ '$resource', 'AppConstant', '$log', '$cacheFactory',
        function($resource, AppConstant, $log, $cacheFactory) {
            var cache = $cacheFactory('SchoolCache');

            var interceptor = {
                response: function (response) {
                    cache.remove(response.config.url);
                    $log.log('cache removed', response.config.url);
                    return response.data;
                }
            };
            return $resource(AppConstant.REST_SECURED_PREFIX + 'organization/:organizationId/school/:id', {
                    organizationId : '@organizationId',
                    id: '@id'
                }, {
                    get:    { method: 'GET', cache: cache },
                    query:  { method: 'GET', cache: cache, isArray: true },
                    save:   { method: 'POST', interceptor: interceptor },
                    remove: { method: 'DELETE', interceptor: interceptor }
            });
        } ]);