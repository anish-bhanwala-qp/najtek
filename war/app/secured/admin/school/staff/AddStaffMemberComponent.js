function AddStaffMemberController($scope, School, ShowValidationErrorService) {
	var self = this;

	self.staffMember = new StaffMember();
	self.staffMember.organizationId = self.resolve.organization.id;

	self.submit = function(addStaffMemberForm) {
		self.staffMember.$save(function(result) {
            console.log(result);
            self.modalInstance.close(result);
        }, function (errorResult) {
            ShowValidationErrorService.process(errorResult, addStaffMemberForm);
        });
	};

	self.reset = function() {
		self.staffMember = new StaffMember();
		$scope.myForm.$setPristine();
	};

    self.cancel = function () {
        self.modalInstance.dismiss('cancel');
    };
}

angular.module('NAJTek')
    .component('ntAddStaffMemberComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/school/addStaffMember.html'
        },
        controller : AddStaffMemberController,
        controllerAs : 'addStaffMemberCtrl',
        bindings: {
            modalInstance: '<',
            resolve: '<'
        }
    });