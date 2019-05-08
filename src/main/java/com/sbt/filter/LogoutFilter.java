package com.sbt.filter;

import com.sbt.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.sbt.utils.ClassNameUtil.getCurrentClassName;

public class LogoutFilter implements Filter {
    private static final String ATTRIBUTE_NAME_USER = "user";
    private static final String PARAMETER_NAME_REDIRECT_TO = "from";
    private static final String BASE_PAGE = "index.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //NOP
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpRequest.getSession(true);
        User user = new User();
        session.setAttribute(ATTRIBUTE_NAME_USER, user);
        logger.info("LOGOUT-OK: user: '" + user);
        String locationRedirectTo = httpRequest.getParameter(PARAMETER_NAME_REDIRECT_TO);
        if (locationRedirectTo != null) {
            locationRedirectTo = locationRedirectTo.replace(";" , "?");
            logger.debug("LOGOUT sendRedirect to " + locationRedirectTo);
            httpResponse.sendRedirect(locationRedirectTo);
        } else {
            logger.debug("LOGOUT sendRedirect to BASE_PAGE = " + BASE_PAGE);
            httpResponse.sendRedirect(BASE_PAGE);
        }
    }

    @Override
    public void destroy() {
        //NOP
    }
}
