angular.module('NAJTek').factory('Organization',
		[ '$resource', 'AppConstant', function($resource, AppConstant) {
			return $resource(AppConstant.REST_SECURED_PREFIX + 'organization/:id', {
				id : '@id'
			});
		} ]);