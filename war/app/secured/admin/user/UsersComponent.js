function UsersController(User, Organization, $stateParams, $uibModal) {
	var self = this;

	self.organization = Organization.get({id: $stateParams.organizationId});

	self.organization.$promise.then(function() {
	    self.organization.users = User.query({organizationId: self.organization.id});
	});

    self.showAdd = function() {
        var modalInstance = $uibModal.open({
            animation: true,
            component: 'ntAddUserComponent',
            resolve: {
                organization: function () {
                  return self.organization;
                }
            }
        });

        modalInstance.result.then(function (user) {
            self.organization.users.push(user);
        });
    };
}

angular.module('NAJTek')
    .component('ntUsersComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/user/users.html'
        },
        controller : UsersController,
        controllerAs : 'usersCtrl'
    });