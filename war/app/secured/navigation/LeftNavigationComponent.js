function LeftNavigationController(LeftNavigationService) {
    var self = this;

    self.leftNavLinks = LeftNavigationService.getLeftNavLinks();

    var selectedTab;

    function setFirstTabAsActive() {
        if (self.leftNavLinks && self.leftNavLinks.length) {
            selectedTab = self.leftNavLinks[0].title;
        }
    }

    setFirstTabAsActive();

    self.selectTab = function(navLink) {
        selectedTab = navLink.title;
    };

    self.getCssClass = function(navLink) {
        return selectedTab == navLink.title ? 'active' : '';
    };

    LeftNavigationService.onLeftNavChange(function(leftNavLinks) {
        self.leftNavLinks = leftNavLinks;
        setFirstTabAsActive();
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
