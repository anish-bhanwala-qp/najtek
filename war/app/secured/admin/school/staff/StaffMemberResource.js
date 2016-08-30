angular.module('NAJTek')
    .factory('StaffMember', [ '$resource', 'AppConstant', '$log', '$cacheFactory',
        function($resource, AppConstant, $log, $cacheFactory) {
            var cache = $cacheFactory('StaffMemberCache');

            var interceptor = {
                response: function (response) {
                    cache.remove(response.config.url);
                    $log.log('cache removed', response.config.url);
                    return response.data;
                }
            };
            var resourceURL = AppConstant.REST_SECURED_PREFIX +
                'organization/:organizationId/school/:schoolId/staffMember/:id';

            return $resource(resourceURL, {
                    organizationId: '@organizationId',
                    schoolId: '@schoolId',
                    id: '@id'
                }, {
                    get:    { method: 'GET', cache: cache },
                    query:  { method: 'GET', cache: cache, isArray: true },
                    save:   { method: 'POST', interceptor: interceptor },
                    remove: { method: 'DELETE', interceptor: interceptor }
            });
        } ]);