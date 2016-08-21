function NavBarController($scope, AppConstant, AuthenticationService) {
    var self = this;

    self.title = AppConstant.APP_TITLE;

    self.navLinks = [];

    var selectedTab;

    self.selectTab = function(navLink) {
        selectedTab = navLink.title;
    };

    self.getCssClass = function(navLink) {
        if (!selectedTab) {
            selectedTab = navLink.title;
        }
        return selectedTab == navLink.title ? 'active' : '';
    };

    function init() {
        AuthenticationService.getAuthenticatedUser(function(authenticatedUser) {
            angular.extend(self.navLinks, authenticatedUser.navLinks);
        });
    }

    init();
}

angular.module('NAJTek')
    .component('ntNavbarComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'navigation/navBar.html'
        },
        controller : NavBarController,
        controllerAs : 'navCtrl'
    });
