package najtek.web.home;


import najtek.domain.user.UserService;
import najtek.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/homeRedirect", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView homeRedirect() {
        userService.addToCache(getCurrentUser());
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home() {
        return new ModelAndView("secured/home");
    }
}
