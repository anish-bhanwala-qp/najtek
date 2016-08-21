function OrganizationController(Organization, $scope, LeftNavigationService, $uibModal) {
	var self = this;
	var leftNavLinks = [{title: 'Organizations', readOnly: true}];

	LeftNavigationService.resetLeftNav(leftNavLinks);

    self.organizations = Organization.query();

    self.showAdd = function() {
        var modalInstance = $uibModal.open({
              animation: true,
              //template: '<nt-add-organization-component></nt-add-organization-component>',
              component: 'ntAddOrganizationComponent'
            });

        modalInstance.result.then(function (organization) {
            self.organizations.push(organization);
        });
    };

	self.fetchAllOrganizations = function() {
		self.organizations = Organization.query();
	};
}

angular.module('NAJTek')
    .component('ntOrganizationComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/organization/organizations.html'
        },
        controller : OrganizationController,
        controllerAs : 'orgCtrl'
    });