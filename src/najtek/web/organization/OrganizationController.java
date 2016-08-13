package najtek.web.organization;

import najtek.database.common.AppDatabase;
import najtek.database.dao.user.OrganizationDao;
import najtek.domain.user.Organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationController {
	/*
	 * @RequestMapping(value = "/organization", method = RequestMethod.GET)
	 * public ResponseEntity<List<User>> listAllUsers() { List<User> users =
	 * userService.findAllUsers(); if(users.isEmpty()){ return new
	 * ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to
	 * return HttpStatus.NOT_FOUND } return new
	 * ResponseEntity<List<User>>(users, HttpStatus.OK); }
	 */

	// -------------------Retrieve Single
	// User--------------------------------------------------------
	
	@Autowired
	private OrganizationDao organizationDao;

	@RequestMapping(value = "/organization/{id}", 
			method = RequestMethod.GET)
	public Organization getOrganization(@PathVariable("id") long id) {
		System.out.println("Fetching organization with id " + id);
		Organization organization = organizationDao.selectById(id);
		if (organization == null) {
			System.out.println("organiztion with id " + id + " not found");
			//return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return organization;
	}

	@RequestMapping(value = "/organization", method = RequestMethod.POST)
	public Organization createOrganization(@RequestBody Organization organization) {
		System.out.println("Creating Organization " + organization.getName());
		organization.setDefaultDatabase(AppDatabase.maindb);

		/*if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getUsername()
					+ " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}*/

		organizationDao.insert(organization);

		return organization;
	}
}
