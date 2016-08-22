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
                    //
                }
            };
        }]);
})();