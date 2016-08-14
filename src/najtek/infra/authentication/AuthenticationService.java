package najtek.infra.authentication;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationService {
   /* @Autowired
    private ApplicationContext applicationContext;*/

    private final AuthenticationManager authenticationManager;
    //private final TokenManager tokenManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        //this.tokenManager = tokenManager;
    }

   /* @PostConstruct
    public void init() {
        System.out.println(" *** AuthenticationServiceImpl.init with: " + applicationContext);
    }*/

    public boolean authenticate(String login, String password) {
        System.out.println(" *** AuthenticationServiceImpl.authenticate");

        // Here principal=username, credentials=password
        Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);
        try {
            authentication = authenticationManager.authenticate(authentication);
            // Here principal=UserDetails (UserContext in our case), credentials=null (security reasons)
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.getPrincipal() != null) {
                UserDetails userContext = (UserDetails) authentication.getPrincipal();
                /*TokenInfo newToken = tokenManager.createNewToken(userContext);
                if (newToken == null) {
                    return null;
                }
                return newToken;*/
                return true;
            }
        } catch (AuthenticationException e) {
            System.out.println(" *** AuthenticationServiceImpl.authenticate - FAILED: " + e.toString());
        }
        return false;
    }

    /*@Override
    public boolean checkToken(String token) {
        System.out.println(" *** AuthenticationServiceImpl.checkToken");

        UserDetails userDetails = tokenManager.getUserDetails(token);
        if (userDetails == null) {
            return false;
        }

        Authentication securityToken = new PreAuthenticatedAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(securityToken);

        return true;
    }

    @Override
    public void logout(String token) {
        UserDetails logoutUser = tokenManager.removeToken(token);
        System.out.println(" *** AuthenticationServiceImpl.logout: " + logoutUser);
        SecurityContextHolder.clearContext();
    }*/

    public UserDetails currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
    }

}
