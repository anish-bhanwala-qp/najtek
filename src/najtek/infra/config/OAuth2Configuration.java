package najtek.infra.config;

import najtek.infra.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Created by anish on 14/8/16.
 */
/*@EnableAuthorizationServer
@Configuration*/
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    // This is required for password grants, which we specify below as one of the
    // {@literal authorizedGrantTypes()}.
    @Autowired
    AuthenticationManagerBuilder authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        return new JwtAccessTokenConverter();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        // Workaround for https://github.com/spring-projects/spring-boot/issues/1801
        endpoints.authenticationManager(new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication)
                    throws AuthenticationException {
                return authenticationManager.getOrBuild()
                        .authenticate(authentication);
            }
        })
        .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //clients.withClientDetails(customUserDetailsService);
/*
        clients.inMemory().withClient("android-" + applicationName)
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .authorities("ROLE_USER").scopes("write").resourceIds(applicationName)
                .secret("123456");*/
    }
}