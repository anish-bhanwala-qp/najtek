(function(){
    angular.module('NAJTek')
        .directive('ntPanel', ['AppConstant', function (AppConstant) {
            return {
                restrict: 'AE',
                transclude: true,
                scope: {
                    header: '@',
                    panelType: '@',
                    contentTable: '@'
                },
                templateUrl: AppConstant.HTML_PATH_PUBLIC_PREFIX + 'tags/panel/panel.html',
                link: function ($scope, $element, $attrs, ctrls) {},
                controller: function($scope) {
                    $scope.getCssClass = function() {
                        if ($scope.panelType) {
                            return 'panel-' + $scope.panelType;
                        }
                        return 'panel-default';
                    };
                }
            };
        }]);
})();