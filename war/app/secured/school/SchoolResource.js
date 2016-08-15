angular.module('NAJTek').factory('School',
    [ '$resource', 'AppConstant', function($resource, AppConstant) {
        return $resource(AppConstant.REST_SECURED_PREFIX + 'school/:id', {
            id : '@id'
        });
    } ]);