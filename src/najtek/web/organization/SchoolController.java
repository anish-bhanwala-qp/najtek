package najtek.web.organization;

import najtek.database.common.AppDatabase;
import najtek.database.dao.school.SchoolDao;
import najtek.database.dao.user.OrganizationDao;
import najtek.domain.school.School;
import najtek.domain.user.Organization;
import najtek.infra.utility.RESTResponse;
import najtek.web.APISecuredController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by anish on 23/8/16.
 */
@RestController
public class SchoolController extends APISecuredController {

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private RESTResponse restResponse;

    @RequestMapping(value = "/organization/{organizationId}/school/{id}", method = RequestMethod.GET)
    public ResponseEntity getSchoolForOrganization(@PathVariable("organizationId") long organizationId,
                                                   @PathVariable("id") long id) {
        System.out.println("Fetching organization with id " + id);
        Organization organization = organizationDao.selectById(organizationId);
        School school = schoolDao.selectById(id, organization.getId());

        return restResponse.getSuccessResponse(school);
    }

    @RequestMapping(value = "/organization/{organizationId}/school", method = RequestMethod.GET)
    public ResponseEntity getSchoolsForOrganizations(@PathVariable("organizationId") long organizationId) {
        System.out.println("Fetching organization All");
        Organization organization = organizationDao.selectById(organizationId);
        List<School> schools = schoolDao.selectByOrganizationId(organization.getId());

        return restResponse.getSuccessResponse(schools);
    }

    @RequestMapping(value = "/organization/{organizationId}/school", method = RequestMethod.POST)
    public ResponseEntity createSchool(@Valid @RequestBody School school,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return restResponse.getValidationErrorResponse(bindingResult,
                    "generic.validation.errors");
        }

        Organization organization = organizationDao.selectById(school.getOrganizationId());
        school.setOrganizationId(organization.getId());

        schoolDao.insert(school);

        return restResponse.getSuccessResponse(school, "school.add.success");
    }
}
