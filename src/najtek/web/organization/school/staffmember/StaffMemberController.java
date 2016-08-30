package najtek.web.organization.school.staffmember;

import najtek.domain.school.staffmember.SchoolStaffMember;
import najtek.domain.school.staffmember.SchoolStaffMemberService;
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
 * Created by anish on 29/8/16.
 */
@RestController
public class StaffMemberController extends APISecuredController {
    final private Logger logger = LoggerFactory.getLogger(StaffMemberController.class);

    @Autowired
    private SchoolStaffMemberService schoolStaffMemberService;

    @Autowired
    private RESTResponse restResponse;

    @RequestMapping(value = "/organization/{organizationId}/school/{schoolId}/staffMember/{id}",
            method = RequestMethod.GET)
    public ResponseEntity getStaffMemberById(@PathVariable("organizationId") long organizationId,
                                             @PathVariable("schoolId") long schoolId,
                                             @PathVariable("id") long id) {
        logger.info("Fetching Staff Member with id " + id);
        SchoolStaffMember schoolStaffMember = schoolStaffMemberService.getStaffMemberById(id, organizationId, schoolId);

        return restResponse.getSuccessResponse(schoolStaffMember);
    }

    @RequestMapping(value = "/organization/{organizationId}/school/{schoolId}/staffMember",
            method = RequestMethod.GET)
    public ResponseEntity getStaffMemberListForSchool(@PathVariable("organizationId") long organizationId,
                                                      @PathVariable("schoolId") long schoolId) {
        logger.info("Fetching school list for organization");
        List<SchoolStaffMember> schoolStaffMemberList =
                schoolStaffMemberService.getStaffMemberListBySchoolId(schoolId, organizationId);

        return restResponse.getSuccessResponse(schoolStaffMemberList);
    }

    @RequestMapping(value = "/organization/{organizationId}/school/{schoolId}/staffMember",
            method = RequestMethod.POST)
    public ResponseEntity addStaffMember(@Valid @RequestBody SchoolStaffMember schoolStaffMember,
                                         @PathVariable("organizationId") long organizationId,
                                         @PathVariable("schoolId") long schoolId,
                                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return restResponse.getValidationErrorResponse(bindingResult,
                    "generic.validation.errors");
        }

        schoolStaffMemberService.addStaffMemberToSchool(schoolStaffMember, organizationId);

        logger.info("************Staff member list cache Size: " +
                schoolStaffMemberService.getStaffMemberListBySchoolId(schoolId, organizationId).size());

        return restResponse.getSuccessResponse(schoolStaffMember, "school.staffmember.add.success");
    }
}
