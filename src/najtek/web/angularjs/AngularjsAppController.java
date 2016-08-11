package najtek.web.angularjs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UrlPathHelper;

@Controller
@RequestMapping(value = "/ang-app/**")
public class AngularjsAppController {
	private UrlPathHelper urlPathHelper;

	private UrlPathHelper getUrlPathHelper() {
		if (urlPathHelper == null) {
			urlPathHelper = new UrlPathHelper();
		}
		return urlPathHelper;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String resolve(HttpServletRequest request) {
		System.out.println("********************************"
				+ getUrlPathHelper().getPathWithinApplication(request));
		return getUrlPathHelper().getPathWithinApplication(request).replace(
				"/ang-app/", "");
	}
}
