(function(){
    angular.module('NAJTek')
        .directive('ntFieldLabel', ['AppConstant', function (AppConstant) {
            return {
                restrict: 'AE',
                transclude: true,
                scope: {
                    labelFor: '@',
                    label: '@',
                    fieldType: '@',
                    orientation: '@',
                    field: '=',
                    fullWidth: '@'
                },
                templateUrl: AppConstant.HTML_PATH_PUBLIC_PREFIX + 'tags/field-label/fieldLabel.html',
                link: function ($scope, $element, $attrs, ctrls) {},
                controller: function($scope) {
                    $scope.getHorizontalContentClass = function() {
                        var isStatic = $scope.fieldType === 'checkbox' ||
                                $scope.fieldType === 'static';
                        return {
                            'form-control-static' : isStatic,
                            'col-sm-8 col-md-8 col-lg-8': !$scope.fullWidth,
                            'col-sm-9 col-md-9 col-lg-9': $scope.fullWidth
                        };
                    };
                }
            };
        }]);
})();