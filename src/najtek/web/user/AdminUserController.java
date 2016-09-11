package najtek.web.user;

import najtek.domain.user.UserService;
import najtek.infra.user.User;
import najtek.infra.utility.RESTResponse;
import najtek.infra.validation.ValidationException;
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
 * Created by anish on 7/9/16.
 */
@RestController
public class AdminUserController extends APISecuredController {
    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RESTResponse restResponse;

    @RequestMapping(value = "/organization/{organizationId}/user", method = RequestMethod.GET)
    public ResponseEntity getUserListForOrganization(
            @PathVariable("organizationId") long organizationId) {
        logger.info("Fetching user list for organization");
        List<User> users = userService.findUsersWithOrganizationId(organizationId);

        return restResponse.getSuccessResponse(users);
    }

    @RequestMapping(value = "/organization/{organizationId}/user", method = RequestMethod.POST)
    public ResponseEntity createUser(@Valid @RequestBody User user,
                                     BindingResult bindingResult) {
        logger.info("Creating user " + user.getFirstName());

        if (bindingResult.hasErrors()) {
            return restResponse.getValidationErrorResponse(bindingResult,
                    "generic.validation.errors");
        }

        try {
            user = userService.addUser(user);
        } catch (ValidationException ex) {
            return restResponse.getValidationErrorResponse("generic.validation.errors",
                    ex.getFieldErrors());
        }

        return restResponse.getSuccessResponse(user, "user.add.success");
    }

    @RequestMapping(value = "/organization/{organizationId}/user/{id}", method = RequestMethod.POST)
    public ResponseEntity updateUser(@Valid @RequestBody User user,
                                     @PathVariable("id") long id,
                                     BindingResult bindingResult) {
        logger.info("Update user " + user.getUsername());

        if (bindingResult.hasErrors()) {
            return restResponse.getValidationErrorResponse(bindingResult,
                    "generic.validation.errors");
        }

        try {
            user = userService.updateUser(user);
        } catch (ValidationException ex) {
            return restResponse.getValidationErrorResponse("generic.validation.errors",
                    ex.getFieldErrors());
        }

        return restResponse.getSuccessResponse(user, "user.edit.success");
    }
}
