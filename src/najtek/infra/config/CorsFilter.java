package najtek.infra.config;

import najtek.infra.utility.AlertMessageService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by anish on 15/8/16.
 */
@WebFilter(urlPatterns = {"/*"})
public class CorsFilter extends OncePerRequestFilter {

    private static final String EXPOSE_HEADERS = "Access-Control-Expose-Headers";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.addHeader(EXPOSE_HEADERS,
                AlertMessageService.ERROR_HEADER + "," + AlertMessageService.SUCCESS_HEADER);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
