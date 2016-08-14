angular.module('NAJTek').config(
		[
				'$provide',
				'$httpProvider',
				'$injector',
				function($provide, $httpProvider, $injector) {
					function isAuthenticationDeniedForTemplate(response) {
						return response.status === 401 && /\.html$/.test(response.config.url);
					}
					
					$provide.factory('myHttpInterceptor', [ '$q', '$rootScope',
							function($q, $rootScope) {
								function showSpinner() {
									$rootScope.currentPromise = $q.defer();
								}
								
								function hideSpinner(reject) {
									if (typeof $rootScope.currentPromise != 'undefined') {
										if (reject) {
											$rootScope.currentPromise.reject();
										} else {
											$rootScope.currentPromise.resolve();	
										}										
									}
								}
								
								return {
									// optional method
									'request' : function(config) {
										// do something on success
										showSpinner();
										return config;
									},

									// optional method
									'requestError' : function(rejection) {
										// do something on error
										return $q.reject(rejection);
									},

									// optional method
									'response' : function(response) {
										// do something on success
										hideSpinner(false);
										return response;
									},

									// optional method
									'responseError' : function(rejection) {
										// do something on error
										hideSpinner(true);

										if (isAuthenticationDeniedForTemplate(rejection)) {
											//injected manually to get around circular dependency problem.
					                        var AuthService = $injector.get('Authentication');
											window.location = '/n/app/public/index.html#/login';
							            }
										return $q.reject(rejection);
									}
								};
							} ]);

					$httpProvider.interceptors.push('myHttpInterceptor');

				} ]);