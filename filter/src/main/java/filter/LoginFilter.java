package filter;

import model.User;
import util.UserDBService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {

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
        if(httpServletRequest.getMethod().equals("GET")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            User user = UserDBService.getUserDBService().getUserByName(servletRequest.getParameter("name"));
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            if(user == null) {
                httpServletRequest.getSession().setAttribute("name", "");
                httpServletResponse.sendRedirect("./login?message=auth error");
                httpServletRequest.getSession().setAttribute("role", "");
            } else if(user.getPassword().equals(servletRequest.getParameter("password"))) {
                httpServletRequest.getSession().setAttribute("name", user.getName());
                httpServletRequest.getSession().setAttribute("role", user.getRole());
                if(user.getRole().equals(adminRole)) {
                    httpServletResponse.sendRedirect("./admin/users");
                }
                if(user.getRole().equals(userRole)) {
                    httpServletResponse.sendRedirect("./user");
                }
            } else {
                httpServletRequest.getSession().setAttribute("name", "");
                httpServletResponse.sendRedirect("./login?message=auth error");
                httpServletRequest.getSession().setAttribute("role", "");
            }
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
