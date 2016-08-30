package najtek.web.user;

import najtek.domain.user.UserService;
import najtek.infra.user.User;
import najtek.infra.utility.RESTResponse;
import najtek.web.APISecuredController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anish on 31/8/16.
 */
@RestController
public class UserSearchController extends APISecuredController {
    private static final Logger logger = LoggerFactory.getLogger(UserSearchController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RESTResponse restResponse;

    @RequestMapping(value = "/user/search", method = RequestMethod.POST)
    public ResponseEntity search(@RequestBody User user) {
        logger.info("Search users" + user.getUsername());
        if (StringUtils.isEmpty(user.getUsername()) || user.getUsername().length() <= 5) {
            return restResponse.getSuccessResponse(new ArrayList<>());
        }

        List<User> userList = userService.findUsersWithUsernameLike(user.getUsername());

        return restResponse.getSuccessResponse(userList);
    }
}
