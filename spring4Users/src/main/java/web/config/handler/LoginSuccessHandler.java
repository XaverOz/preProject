package web.config.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
;import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    public final String userAuthority = "USER";
    public final String adminAuthority = "ADMIN";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if(authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals(adminAuthority))) {
            httpServletResponse.sendRedirect("/users");
            return;
        }
        if(authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals(userAuthority))) {
            httpServletResponse.sendRedirect("/user");
        }
    }
}
