angular.module('NAJTek').factory(
		'AuthenticationService',
		[
				'$http',
				'$location',
				'AppConstant',
				function($http, $location, AppConstant) {
					var factory = {};
					var authenticatedUser = {navLinks: []};

					function getAuthenticationHeader(credentials) {
						return credentials ? {
							authorization : "Basic "
									+ btoa(credentials.username + ":"
											+ credentials.password)
						} : {};
					}

					factory.getAuthenticatedUser = function(callback) {
					    if (authenticatedUser.id) {
					        return authenticatedUser;
					    }
                        $http.get(AppConstant.REST_PREFIX + 'user')
                            .success(function(data) {
                                angular.extend(authenticatedUser, data.principal);
                                console.log(authenticatedUser);
                                callback && callback(authenticatedUser);
                            }).error(function(error) {
                                console.log(error);
                            });
                        return authenticatedUser;
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
					
					factory.logout = function(callback) {
						 $http.post('/logout', {}).finally(function(data) {
							 console.log(data);
							    callback(true);
							  });
					};

					return factory;
				} ]);
