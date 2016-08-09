angular
		.module('NAJTek')
		.controller(
				'LoginController',
				[
						'AppConstant',
						'$scope',
						'AuthenticationService',
						'$location',
						'Notification',
						function(AppConstant, $scope, AuthenticationService,
								$location, Notification) {

							AuthenticationService.authenticate();
							$scope.credentials = {};
							$scope.login = function() {
								AuthenticationService
										.authenticate(
												$scope.credentials,
												function(success, user) {
													if (success) {
														Notification.success('Logged-in successfully!');	
														console.log('Logged-in: '
																+ JSON
																		.stringify(user));
														$location.path("/home");
														$scope.error = false;
													} else {
														alert('Error');
														$scope.error = true;
													}
												});
							};
						} ]);