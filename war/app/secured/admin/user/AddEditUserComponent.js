function AddEditUserController($scope, User, UserSearch, ShowValidationErrorService) {
	var self = this;

	self.user = new User();
	if (self.resolve.user) {
	    angular.extend(self.user, self.resolve.user);
	}
	self.user.organizationId = self.resolve.organization.id;

	self.submit = function(addUserForm) {
		self.user.$save(function(result) {
            console.log(result);
            self.modalInstance.close(result);
        }, function (errorResult) {
            ShowValidationErrorService.process(errorResult, addUserForm);
        });
	};

	function isEditingUser() {
	    return (self.user.id > 0);
	}

	self.headerText = function() {
	    if (isEditingUser()) {
	        return 'Edit User';
	    }
	    return 'Add User';
	}

	self.submitButtonText = function() {
        if (isEditingUser()) {
            return 'Edit';
        }
        return 'Add';
    }

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
    .component('ntAddEditUserComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/user/addEditUser.html'
        },
        controller : AddEditUserController,
        controllerAs : 'addUserCtrl',
        bindings: {
            modalInstance: '<',
            resolve: '<'
        }
    });