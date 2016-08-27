package najtek.web.organization;

import najtek.database.common.AppDatabase;
import najtek.domain.user.Organization;
import najtek.domain.user.OrganizationService;
import najtek.infra.utility.RESTResponse;
import najtek.web.APISecuredController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrganizationController extends APISecuredController {
	private static Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	private OrganizationService organizationService;

    @Autowired
    private RESTResponse restResponse;

	@RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
	public ResponseEntity getOrganization(@PathVariable("id") long id) {
		logger.info("Fetching organization with id " + id);
		Organization organization = organizationService.getById(id);
		if (organization == null) {
            logger.info("organiztion with id " + id + " not found");
		}
		return restResponse.getSuccessResponse(organization);
	}

	@RequestMapping(value = "/organization", method = RequestMethod.GET)
	public ResponseEntity getAllOrganization() {
		logger.info("Fetching organization All");
		List<Organization> organizations = organizationService.getOrganizationList();
		if (organizations == null) {
			logger.info("organizations not found");
		}
		return restResponse.getSuccessResponse(organizations);
	}

	@RequestMapping(value = "/organization", method = RequestMethod.POST)
	public ResponseEntity createOrganization(@Valid @RequestBody Organization organization,
                                           BindingResult bindingResult) {
		logger.info("Creating Organization " + organization.getName());

		if (bindingResult.hasErrors()) {
		    return restResponse.getValidationErrorResponse(bindingResult,
                    "generic.validation.errors");
        }

        if (organizationService.getByName(organization.getName(), false) != null) {
            FieldError fieldError = new FieldError("Organization",
                    "name", "organization.add.name.already.exists.error");
            return restResponse.getValidationErrorResponse("generic.validation.errors",
                    fieldError);
        }

		organization.setDefaultDatabase(AppDatabase.maindb);

		organizationService.add(organization);

		return restResponse.getSuccessResponse(organization, "organization.add.success");
	}
}
