function LoginController($window, AuthenticationService, Notification, AppConstant) {
    var self = this;

    self.credentials = {};

    self.submit = function() {
        AuthenticationService.authenticate(self.credentials,  function(success, user) {
            if (success) {
                Notification.success('Logged-in successfully!');
                console.log('Logged-in: ' + JSON.stringify(user));
                $window.location.href = AppConstant.
            } else {
               Notification.success('Invalid Username or Password');
            }
        });
    };
}

angular.module('NAJTek').component(
    'ntLoginComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_PUBLIC_PREFIX +
                    'login/login.html'
        },
        controller : LoginController,
        controllerAs : 'loginCtrl'
    });