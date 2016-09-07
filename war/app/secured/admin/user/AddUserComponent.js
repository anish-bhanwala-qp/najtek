function AddUserController($scope, User, UserSearch, ShowValidationErrorService) {
	var self = this;

	self.user = new User();
	self.user.organizationId = self.resolve.organization.id;

	self.submit = function(addUserForm) {
		self.user.$save(function(result) {
            console.log(result);
            self.modalInstance.close(result);
        }, function (errorResult) {
            ShowValidationErrorService.process(errorResult, addUserForm);
        });
	};

	self.reset = function() {
		self.user = new User();
		$scope.myForm.$setPristine();
	};

    self.cancel = function () {
        self.modalInstance.dismiss('cancel');
    };

    self.searchUsers = function(username) {
        return UserSearch.query({username: username})
                .$promise.then(function(response) {
                    return response;
                });
    };
}

angular.module('NAJTek')
    .component('ntAddUserComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/user/addUser.html'
        },
        controller : AddUserController,
        controllerAs : 'addUserCtrl',
        bindings: {
            modalInstance: '<',
            resolve: '<'
        }
    });