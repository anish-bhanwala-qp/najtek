function SchoolController(School, Organization, $stateParams, LeftNavigationService, $uibModal) {
	var self = this;

	var leftNavLinks = [{title: 'Schools', readOnly: true}];
	LeftNavigationService.resetLeftNav(leftNavLinks);

	self.organization = Organization.get({id: $stateParams.organizationId});

	self.organization.$promise.then(function() {
	    self.organization.schools = School.query({organizationId: self.organization.id});
	});

    self.showAdd = function() {
        var modalInstance = $uibModal.open({
            animation: true,
            component: 'ntAddSchoolComponent',
            resolve: {
                organization: function () {
                  return self.organization;
                }
            }
        });

        modalInstance.result.then(function (school) {
            self.organization.schools.push(school);
        });
    };
}

angular.module('NAJTek')
    .component('ntSchoolsComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/school/schools.html'
        },
        controller : SchoolController,
        controllerAs : 'schoolCtrl'
    });