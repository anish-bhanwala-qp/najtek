angular.module('NAJTek').factory('School',
		[ '$resource', 'AppConstant', function($resource, AppConstant) {
			return $resource(AppConstant.REST_SECURED_PREFIX + 'organization/:organizationId/school/:id', {
                    organizationId : '@organizationId',
                    id: '@id'
                }, {
                   query: {
                       method: 'GET',
                       isArray: true,
                       cache: true
                   },
                   get: {
                       method: 'GET',
                       cache: true
                   }
                });
		} ]);