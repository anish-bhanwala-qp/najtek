package najtek.infra.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.validation.Validator;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@EnableWebMvc
@Configuration
@ComponentScan({ "najtek.web", "najtek.infra.user", "najtek.infra.validation" })
public class AppWebConfiguration extends WebMvcConfigurerAdapter
		implements ApplicationContextAware {

	private static final String UTF8 = "UTF-8";
	private static final String ANGULARJS_PATH_PREFIX = "/app/";

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

    @Bean
    public Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/app/**")
                .addResourceLocations("/app/");
    }

	@Override
	public void addInterceptors(InterceptorRegistry interceptRegistry) {
		interceptRegistry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName(
				ANGULARJS_PATH_PREFIX + "public/index.html");
		registry.addViewController("/home").setViewName(
				ANGULARJS_PATH_PREFIX + "secured/home.html");
	}

	private HandlerInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");

		return localeChangeInterceptor;
	}

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

	/*@Bean
	public ViewResolver nonAngularjsHtmlViewResolver() {
		return createViewResolver(nonAngularjsHtmlTemplateResolver(), "text/html",
				"*.html");
	}

	*//*@Bean
	public ViewResolver angularjsJavascriptViewResolver() {
		return createViewResolver(angularjsJavascriptTemplateResolver(),
				"application/javascript", "*.js");
	}*//*

	private ViewResolver createViewResolver(ITemplateResolver templateResolver,
			String contentType, String viewName) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine(templateResolver));
		resolver.setContentType(contentType);
		resolver.setCharacterEncoding(UTF8);
		resolver.setViewNames(array(viewName));
		return resolver;
	}

	private TemplateEngine templateEngine(ITemplateResolver templateResolver) {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);
		return engine;
	}

	private ITemplateResolver angularjsHtmlTemplateResolver() {
		return createTemplateResolver(ANGULARJS_PATH_PREFIX, TemplateMode.HTML);
	}

	private ITemplateResolver nonAngularjsHtmlTemplateResolver() {
		return createTemplateResolver("/WEB-INF/views/", TemplateMode.HTML);
	}

	private ITemplateResolver angularjsJavascriptTemplateResolver() {
		return createTemplateResolver(ANGULARJS_PATH_PREFIX,
				TemplateMode.JAVASCRIPT);
	}

	private ITemplateResolver createTemplateResolver(String prefix,
			TemplateMode mode) {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix(prefix);
		resolver.setTemplateMode(mode);
		return resolver;
	}

	public String[] array(String... args) {
		return args;
	}*/
}
