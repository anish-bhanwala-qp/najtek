package najtek.infra.init;


import najtek.infra.config.*;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "DEBUG");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                AppDatabaseConfiguration.class,
                AuthenticationManagerConfiguration.class,
                AppSecurityConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { AppWebConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
