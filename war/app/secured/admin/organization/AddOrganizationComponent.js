function AddOrganizationController(Organization, ShowValidationErrorService) {
	var self = this;

	self.organization = new Organization();

	self.submit = function(addOrgForm) {
		self.organization.$save(function(result) {
            console.log(result);
            self.modalInstance.close(result);
        }, function (errorResult) {
            ShowValidationErrorService.process(errorResult, addOrgForm);
        });
	};

	self.reset = function() {
		self.organization = new Organization();
		$scope.myForm.$setPristine();
	};

    self.cancel = function () {
        self.modalInstance.dismiss('cancel');
    };
}

angular.module('NAJTek')
    .component('ntAddOrganizationComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/organization/addOrganization.html'
        },
        controller : AddOrganizationController,
        controllerAs : 'addOrgCtrl',
        bindings: {
            modalInstance: '<'
        }
    });