package najtek.infra.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by anish on 14/8/16.
 */
@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationEventListener.class);

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent authenticationEvent) {
        Authentication authentication = authenticationEvent.getAuthentication();
        String auditMessage = "Login attempt with username: " +
                authentication.getName() + "\t\tSuccess: " + authentication.isAuthenticated();
        logger.info(auditMessage);
        logger.info(authenticationEvent.toString());

        System.out.println(auditMessage);
        System.out.println(authenticationEvent.toString());
    }
}