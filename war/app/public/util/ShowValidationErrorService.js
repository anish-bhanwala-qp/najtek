angular.module('NAJTek')
    .factory('ShowValidationErrorService', [
        function() {
            var factory = {};

            factory.process = function(response, form) {
                if (response.status != 400) {
                    return;
                }
                var errors = response.data;
                /*Clear error for all fields*/
                angular.forEach(form, function (formField) {
                    if (typeof formField === 'object' && formField.hasOwnProperty('$modelValue')) {
                        form[formField.$name].$dirty = false;
                        form[formField.$name].$setValidity('customError', true);
                    }
                });
                /*Add validation errors to fields*/
                angular.forEach(errors, function (error) {
                    var fieldName = error.fieldName;
                    var errorMessage = error.errorMessage;

                    if (form[fieldName]) {
                        form[fieldName].$dirty = true;
                        form[fieldName].$setValidity('customError', false);
                        form[fieldName].errorMessage = errorMessage;
                    }
                });
            }

             return factory;
        }]);