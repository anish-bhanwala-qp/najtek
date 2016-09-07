function LeftNavigationController(LeftNavigationService) {
    var self = this;

    self.leftNavLinks = LeftNavigationService.getLeftNavLinks();

    var selectedTab;

    self.selectTab = function(navLink) {
        if (navLink.readOnly) {
            return false;
        }
        selectedTab = navLink.title;
    };

    self.getCssClass = function(navLink) {
        return {
            active: selectedTab == navLink.title,
            disabled: navLink.readOnly
        };
    };

    function setFirstTabAsActive() {
        if (self.leftNavLinks && self.leftNavLinks.length) {
            self.selectTab(self.leftNavLinks[0]);
        }
    }

    function setActiveTab() {
        setFirstTabAsActive();
        for (var i=0; i < self.leftNavLinks.length; i++) {
            if (self.leftNavLinks[i].selected) {
                self.selectTab(self.leftNavLinks[i]);
            }
        }
    }

    setActiveTab();

    LeftNavigationService.onLeftNavChange(function(leftNavLinks) {
        self.leftNavLinks = leftNavLinks;
        setActiveTab();
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
