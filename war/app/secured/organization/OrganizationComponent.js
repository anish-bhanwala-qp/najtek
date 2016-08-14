function OrganizationController(Organization, $scope) {
	var self = this;
	self.organization = new Organization();

	self.organizations = [];

	self.fetchAllOrganizations = function() {
		self.organization = Organization.query();
	};

	self.createOrganization = function() {
		self.organization.$save(function(result) {
			console.log(result);
			//self.fetchAllOrganizations();
		});
	};
	
	self.submit = function() {
		self.createOrganization();
	}

	self.reset = function() {
		self.organization = new Organization();
		$scope.myForm.$setPristine();
	};
}

angular.module('NAJTek').component(
		'organizationComponent',
		{
			templateUrl : function(AppConstant) {
				return AppConstant.HTML_PATH_SECURED_PREFIX
						+ 'organization/addOrganization.html'
			},
			controller : OrganizationController,
			controllerAs : 'orgCtrl'
		});