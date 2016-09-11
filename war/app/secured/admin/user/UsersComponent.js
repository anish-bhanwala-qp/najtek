function UsersController(User, Organization, $stateParams, $uibModal) {
	var self = this;

	self.organization = Organization.get({id: $stateParams.organizationId});

	self.organization.$promise.then(function() {
	    self.organization.users = User.query({organizationId: self.organization.id});
	});

    self.showAdd = function() {
        var modalInstance = $uibModal.open({
            animation: true,
            component: 'ntAddEditUserComponent',
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

    self.showEdit = function(selectedUser) {
        var modalInstance = $uibModal.open({
            animation: true,
            component: 'ntAddEditUserComponent',
            resolve: {
                organization: function () {
                    return self.organization;
                },
                user: function() {
                    return selectedUser;
                }
            }
        });

        modalInstance.result.then(function (user) {
            angular.extend(selectedUser, user);
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