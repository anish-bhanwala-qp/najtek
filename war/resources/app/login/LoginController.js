angular.module('NAJTek').controller(
		'LoginController',
		[
				'$scope',
				'AuthenticationService',
				function($scope, AuthenticationService) {

					AuthenticationService.authenticate();
					$scope.credentials = {};
					$scope.login = function() {
						AuthenticationService.authenticate($scope.credentials,
								function(success, user) {
									if (success) {
										alert('Logged In: ' + JSON.stringify(user));
										$location.path("/");
										$scope.error = false;
									} else {
										alert('Error');
										$scope.error = true;
									}
								});
					};
				} ]);