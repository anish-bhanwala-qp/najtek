package najtek.web.organization;

import najtek.database.common.AppDatabase;
import najtek.database.dao.user.OrganizationDao;
import najtek.domain.user.Organization;

import najtek.infra.validation.ControllerValidationHandler;
import najtek.infra.validation.ValidationError;
import najtek.web.APISecuredController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrganizationController extends APISecuredController {

	@Autowired
	private OrganizationDao organizationDao;

    @Autowired
    private ControllerValidationHandler controllerValidationHandler;

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
	public ResponseEntity createOrganization(@Valid @RequestBody Organization organization,
                                           BindingResult bindingResult) {
		System.out.println("Creating Organization " + organization.getName());

		if (bindingResult.hasErrors()) {
		    return controllerValidationHandler.getValidationErrorResponse(bindingResult);
        }
		organization.setDefaultDatabase(AppDatabase.maindb);

		/*if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getUsername()
					+ " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}*/

		//organizationDao.insert(organization);

		return ResponseEntity.ok(organization);
	}
}
