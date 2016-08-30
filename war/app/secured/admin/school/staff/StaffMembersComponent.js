function StaffMembersController(StaffMember, School, $stateParams, $uibModal) {
	var self = this;

	self.school = School.get({id: $stateParams.schoolId, organizationId: $stateParams.organizationId});

	self.school.$promise.then(function() {
	    self.school.staffMembers = StaffMember.query({
	                        schoolId: self.school.id,
	                        organizationId: self.school.organizationId
	                    });
	});

    self.showAdd = function() {
        var modalInstance = $uibModal.open({
            animation: true,
            component: 'ntAddStaffMemberComponent',
            resolve: {
                school: function () {
                  return self.school;
                }
            }
        });

        modalInstance.result.then(function (member) {
            self.school.StaffMembers.push(member);
        });
    };
}

angular.module('NAJTek')
    .component('ntStaffMembersComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/school/staff/staffMembers.html'
        },
        controller : StaffMembersController,
        controllerAs : 'staffMembersCtrl'
    });