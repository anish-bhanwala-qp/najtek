function NavBarController($scope, AppConstant) {
    var self = this;

    self.title = AppConstant.APP_TITLE;

    self.navLinks = [{title: 'Manage School', link: '#manageSchool'}];
    self.selectedTab = self.navLinks[0].title;

    self.selectTab = function(navLink) {
        scope.selectedTab = navLink.title;
    }

    self.getCssClass = function(navLink) {
        return self.selectedTab == navLink.title ? 'active' : '';
    }
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
