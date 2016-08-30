package najtek.web.organization.school;

import najtek.domain.school.School;
import najtek.domain.school.SchoolService;
import najtek.infra.utility.RESTResponse;
import najtek.web.APISecuredController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by anish on 23/8/16.
 */
@RestController
public class SchoolController extends APISecuredController {
    final private Logger logger = LoggerFactory.getLogger(SchoolController.class);         

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private RESTResponse restResponse;

    @RequestMapping(value = "/organization/{organizationId}/school/{id}", method = RequestMethod.GET)
    public ResponseEntity getSchoolForOrganization(@PathVariable("organizationId") long organizationId,
                                                   @PathVariable("id") long id) {
        logger.info("Fetching school with id " + id);
        School school = schoolService.getSchoolById(organizationId, id);

        return restResponse.getSuccessResponse(school);
    }

    @RequestMapping(value = "/organization/{organizationId}/school", method = RequestMethod.GET)
    public ResponseEntity getSchoolListForOrganization(
            @PathVariable("organizationId") long organizationId) {
        logger.info("Fetching school list for organization");
        List<School> schools = schoolService.getSchoolListByOrganizationId(organizationId);

        return restResponse.getSuccessResponse(schools);
    }

    @RequestMapping(value = "/organization/{organizationId}/school", method = RequestMethod.POST)
    public ResponseEntity createSchool(@Valid @RequestBody School school,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return restResponse.getValidationErrorResponse(bindingResult,
                    "generic.validation.errors");
        }

        schoolService.add(school);

        logger.info("************School list cache Size: " +
                schoolService.getSchoolListByOrganizationId(school.getOrganizationId()).size());

        return restResponse.getSuccessResponse(school, "school.add.success");
    }
}
