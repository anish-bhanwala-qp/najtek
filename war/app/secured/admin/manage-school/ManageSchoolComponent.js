function ManageSchoolController(Organization, School, $scope) {
	var self = this;

	self.schools = [{id: 1, name: 'first', organization_id: 1},
    	        {id: 2, name: 'second', organization_id: 2}];

	self.organizations = [{id: 1, name: 'org1', schools: [self.schools[0]]},
	        {id: 2, name: 'org2', schools: [self.schools[1]]}];

}

angular.module('NAJTek').component(
    'ntManageSchoolComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX +
                    'admin/manage-school/index.html'
        },
        controller : ManageSchoolController,
        controllerAs : 'msCtrl'
    });