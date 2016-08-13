angular.module('NAJTek').factory(
		'OrganizationService',
		[
				'$http',
				'$location',
				'AppConstant',
				function($http, $location, AppConstant) {
					var factory = {};

					factory.authenticate = function(credentials, callback) {

						var headers = getAuthenticationHeader(credentials);

						$http.get(AppConstant.REST_PREFIX + 'user', {
							headers : headers
						}).success(function(data) {
							/*
							 * if (data.name) { $rootScope.authenticated = true; }
							 * else { $rootScope.authenticated = false; }
							 */
							callback && callback(true, data);
						}).error(function() {
							callback && callback(false);
						});

					}
					
					factory.logout = function(callback) {
						 $http.post(AppConstant.REST_PREFIX + 'logout', {}).finally(function(data) {
							 console.log(data);
							    callback(true);
							  });
					}

					return factory;
				} ]);
