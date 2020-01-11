package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user")
public class UserFilter implements Filter {

    private FilterConfig filterConfig = null;
    private final String adminRole = "admin";
    private final String userRole = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        String role = (String) httpServletRequest.getSession().getAttribute("role");
        if(adminRole.equals(role)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if(userRole.equals(role)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.sendRedirect("./login?message=auth error");
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
