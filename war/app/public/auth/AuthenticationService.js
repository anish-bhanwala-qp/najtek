angular.module('NAJTek').factory(
		'AuthenticationService',
		[
				'$http',
				'$location',
				'AppConstant',
				function($http, $location, AppConstant) {
					var factory = {};
					var authenticatedUser = {};

					function getAuthenticationHeader(credentials) {
						return credentials ? {
							authorization : "Basic "
									+ btoa(credentials.username + ":"
											+ credentials.password)
						} : {};
					}

					factory.login = function(credentials, callback) {
                        $http.post(AppConstant.REST_PREFIX + 'login', credentials)
                            .success(function(data) {
                                authenticatedUser = data;
                                callback && callback(true, data);
                            }).error(function() {
                                callback && callback(false);
                            });
                    };

					factory.authenticate = function(credentials, callback) {

						var headers = getAuthenticationHeader(credentials);

						$http.get(AppConstant.REST_PREFIX + 'user', {
							headers : headers
						}).success(function(data) {
							/*
							 * if (data.name) { $rootScope.authenticated = true; }
							 * else { $rootScope.authenticated = false; }
							 */
							authenticatedUser = data;
							callback && callback(true, data);
						}).error(function() {
							callback && callback(false);
						});

					};
					
					factory.getAuthenticatedUser = function() {
						return authenticatedUser;
					};
					
					factory.logout = function(callback) {
						 $http.post('/logout', {}).finally(function(data) {
							 console.log(data);
							    callback(true);
							  });
					};

					return factory;
				} ]);
