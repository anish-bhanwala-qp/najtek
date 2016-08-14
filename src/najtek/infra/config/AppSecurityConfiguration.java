package najtek.infra.config;

import najtek.infra.authentication.AuthenticationService;
import najtek.infra.authentication.TokenAuthenticationFilter;
import najtek.infra.user.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                    .and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/secured/**", "/app/secured/**").fullyAuthenticated()
                    .antMatchers("/api/public/**", "/app/public/**").permitAll()
                    .anyRequest().fullyAuthenticated()
                .and()
                    .formLogin()
                    .loginPage("/app/public/index.html")
                    .permitAll()
                    //.failureHandler(failureHandler())
                    /*.successHandler(successHandler())
                .and()
                    .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler())
                    .authenticationEntryPoint(authenticationEntryPoint())
                .and()*/;
                    //.addFilter(getTokenAuthenticationFilter());
    }

   /* private Filter getTokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(authenticationService(), "/logout");
    }

    private AuthenticationService authenticationService() {
        return new AuthenticationService(authenticationManager);
    }*/

    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse,
                                                Authentication authentication)
                    throws IOException, ServletException {
                httpServletResponse.getWriter().append("OK");
                httpServletResponse.setStatus(200);
            }
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse,
                                                AuthenticationException e)
                    throws IOException, ServletException {
                httpServletResponse.getWriter().append("Authentication failure");
                httpServletResponse.setStatus(401);
            }
        };
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse,
                               AccessDeniedException e) throws IOException, ServletException {
                httpServletResponse.getWriter().append("Access denied");
                httpServletResponse.setStatus(403);
            }
        };
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse,
                                 AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.getWriter().append("Not authenticated");
                httpServletResponse.setStatus(401);
            }
        };
    }

    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain)
                    throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                        .getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                    String token = csrf.getToken();
                    if (cookie == null || token != null
                            && !token.equals(cookie.getValue())) {
                        cookie = new Cookie("XSRF-TOKEN", token);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }


	/*public void configure(HttpSecurity http) throws Exception {
		http
		.userDetailsService(userDetailsService())
		.httpBasic()
			.and()
        .authorizeRequests()
            .antMatchers("/secured*", "/ang-app/secured*").fullyAuthenticated()
            .antMatchers("/ang-app/public*", "/public*", "/resources*").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .and()
        *//*.formLogin()
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
            .and()*//*
        .logout()
            .permitAll()
            .and()
            .csrf().disable();
	}*/

	/*@Configuration
    @Order(1)
	public static class APISecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .csrf().disable()
                        .antMatcher("/api/secured*//**")
                        .antMatcher("/app/secured*//**").authorizeRequests()
                        .antMatchers("/api/public*//**").permitAll()
                        .anyRequest().fullyAuthenticated()
                    .and()
                        .formLogin()
                        .loginProcessingUrl("/login")
                        .permitAll()
                        .successHandler(successHandler())
                        .failureHandler(failureHandler())
                    .and()
                        .exceptionHandling()
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint())
                    .and()
                        .addFilter(getTokenAuthenticationFilter());
        }

        private Filter getTokenAuthenticationFilter() {
            return new TokenAuthenticationFilter(authenticationService(), "/logout");
        }

        private AuthenticationService authenticationService() {
            return new AuthenticationService(authenticationManager);
        }

        private AuthenticationSuccessHandler successHandler() {
            return new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                    HttpServletResponse httpServletResponse,
                                                    Authentication authentication)
                        throws IOException, ServletException {
                    httpServletResponse.getWriter().append("OK");
                    httpServletResponse.setStatus(200);
                }
            };
        }

        private AuthenticationFailureHandler failureHandler() {
            return new AuthenticationFailureHandler() {
                @Override
                public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                    HttpServletResponse httpServletResponse,
                                                    AuthenticationException e)
                        throws IOException, ServletException {
                    httpServletResponse.getWriter().append("Authentication failure");
                    httpServletResponse.setStatus(401);
                }
            };
        }

        private AccessDeniedHandler accessDeniedHandler() {
            return new AccessDeniedHandler() {
                @Override
                public void handle(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse,
                                   AccessDeniedException e) throws IOException, ServletException {
                    httpServletResponse.getWriter().append("Access denied");
                    httpServletResponse.setStatus(403);
                }
            };
        }

        private AuthenticationEntryPoint authenticationEntryPoint() {
            return new AuthenticationEntryPoint() {
                @Override
                public void commence(HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse,
                                     AuthenticationException e) throws IOException, ServletException {
                    httpServletResponse.getWriter().append("Not authenticated");
                    httpServletResponse.setStatus(401);
                }
            };
        }

        private Filter csrfHeaderFilter() {
            return new OncePerRequestFilter() {
                @Override
                protected void doFilterInternal(HttpServletRequest request,
                                                HttpServletResponse response,
                                                FilterChain filterChain)
                        throws ServletException, IOException {
                    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                            .getName());
                    if (csrf != null) {
                        Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                        String token = csrf.getToken();
                        if (cookie == null || token != null
                                && !token.equals(cookie.getValue())) {
                            cookie = new Cookie("XSRF-TOKEN", token);
                            cookie.setPath("/");
                            response.addCookie(cookie);
                        }
                    }
                    filterChain.doFilter(request, response);
                }
            };
        }

        private CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }
    }*/

    /*@Configuration
    @Order(2)
    public static class FormSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources*//**", "/app/public*//**");
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    *//*.and()
                    .formLogin().permitAll()*//*
                    .and()
                    .logout()
                    .permitAll();
        }
    }*/
}
