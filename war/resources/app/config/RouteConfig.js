angular
		.module('NAJTek')
		.config(
				function($routeProvider, $httpProvider) {

					$routeProvider.when('/', {
						templateUrl : '/n/resources/app/index.html',
						controller : 'home'
					}).when('/login', {
						templateUrl : 'login.html',
						controller : 'LoginController'
					}).otherwise('/');

					$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

				})