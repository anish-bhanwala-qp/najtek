angular.module('NAJTek').factory(
		'AuthenticationService',
		[
				'$http',
				'$location',
				function($http, $location) {
					var factory = {};

					function getAuthenticationHeader(credentials) {
						return credentials ? {
							authorization : "Basic "
									+ btoa(credentials.username + ":"
											+ credentials.password)
						} : {};
					}

					factory.authenticate = function(credentials, callback) {

						var headers = getAuthenticationHeader(credentials);

						$http.get('/n/user', {
							headers : headers
						}).success(function(data) {
							/*if (data.name) {
								$rootScope.authenticated = true;
							} else {
								$rootScope.authenticated = false;
							}*/
							callback && callback(true, data);
						}).error(function() {
							callback && callback(false);
						});

					}

					return factory;
				} ]);
