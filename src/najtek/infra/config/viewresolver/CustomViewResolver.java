package najtek.infra.config.viewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class CustomViewResolver implements ViewResolver {

    private ViewResolver thymeleafResolver;
    private ViewResolver jspResolver;

    public void setJspResolver(ViewResolver jspResolver) {
        this.jspResolver = jspResolver;
    }

    public void setThymeleafResolver(ViewResolver thymeleafResolver) {
        this.thymeleafResolver = thymeleafResolver;
    }

	@Override
	public View resolveViewName(String arg0, Locale arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

    /*public View resolveViewName(String viewName, Locale locale) throws Exception {
        if (isThymeleafView(viewName)) {
            return thymeleafResolver.resolveViewName(viewName, locale);
        } else {
            return jspResolver.resolveViewName(viewName, locale);
        }
    }

    private boolean isThymeleafView(String viewName) {
	
    }*/
}

