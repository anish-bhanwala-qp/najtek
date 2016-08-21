package najtek.web.home;


import com.google.gson.Gson;
import najtek.infra.user.User;
import najtek.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController extends BaseController {

    @RequestMapping(value = "/homeRedirect", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView homeRedirect() {
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home() {
        return new ModelAndView("secured/home");
    }
}
