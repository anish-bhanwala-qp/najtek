function AddSchoolController($scope, School, ShowValidationErrorService) {
	var self = this;

	self.school = new School();
	self.school.organizationId = self.resolve.organization.id;

	self.submit = function(addSchoolForm) {
		self.school.$save(function(result) {
            console.log(result);
            self.modalInstance.close(result);
        }, function (errorResult) {
            ShowValidationErrorService.process(errorResult, addSchoolForm);
        });
	};

	self.reset = function() {
		self.school = new School();
		$scope.myForm.$setPristine();
	};

    self.cancel = function () {
        self.modalInstance.dismiss('cancel');
    };
}

angular.module('NAJTek')
    .component('ntAddSchoolComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/school/addSchool.html'
        },
        controller : AddSchoolController,
        controllerAs : 'addSchoolCtrl',
        bindings: {
            modalInstance: '<',
            resolve: '<'
        }
    });