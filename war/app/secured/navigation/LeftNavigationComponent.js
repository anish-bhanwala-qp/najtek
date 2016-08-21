function LeftNavigationController(LeftNavigationService) {
    var self = this;

    self.leftNavLinks = LeftNavigationService.getLeftNavLinks();

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

    LeftNavigationService.onLeftNavChange(function(leftNavLinks) {
        self.leftNavLinks = leftNavLinks;
    });
}

angular.module('NAJTek')
    .component('ntLeftNavigationComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'navigation/leftNavigation.html'
        },
        controller : LeftNavigationController,
        controllerAs : 'leftNavCtrl'
    });
