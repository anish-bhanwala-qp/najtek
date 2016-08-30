function SchoolSettingController(School, $stateParams, $uibModal, LeftNavigationService) {
	var self = this;
    var leftNavLinks = [
        {
            title: 'Setting',
            url: 'organizations.schools.schoolSetting({' +
                    'organizationId: ' + $stateParams.organizationId + ', ' +
                    'schoolId:' +  $stateParams.schoolId + '})'
        },
        {
            title: 'Staff',
            url: 'organizations.schools.staffMember({' +
                    'organizationId: ' + $stateParams.organizationId + ', ' +
                    'schoolId:' +  $stateParams.schoolId + '})'
        }];

    LeftNavigationService.resetLeftNav(leftNavLinks);

	self.school = School.get({id: $stateParams.schoolId, organizationId: $stateParams.organizationId});

	self.school.$promise.then(function() {
	    //self.school.schoolSetting = StaffMember.query({schoolId: self.school.id});
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
            self.school.SchoolSetting.push(member);
        });
    };
}

angular.module('NAJTek')
    .component('ntSchoolSettingComponent', {
        templateUrl : function(AppConstant) {
            return AppConstant.HTML_PATH_SECURED_PREFIX
                    + 'admin/school/setting/schoolSetting.html'
        },
        controller : SchoolSettingController,
        controllerAs : 'schoolSettingCtrl'
    });