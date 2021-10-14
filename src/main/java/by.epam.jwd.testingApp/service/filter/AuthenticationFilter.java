package by.epam.jwd.testingApp.service.filter;

import by.epam.jwd.testingApp.service.cookieService.CookieManager;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractUserService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.UserService;
import by.epam.jwd.testingApp.service.parameterParserServise.Parser;
import by.epam.jwd.testingApp.service.parameterParserServise.parsersImpl.UserIdParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private FilterConfig config = null;
    private boolean isActive = false;

    public static final String IS_ACTIVE_PARAM = "active";
    public static final String VALUE_FOR_ACTIVE = "TRUE";

    public static final int SESSION_TIME_INTERVAL = 60 * 60 * 2;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        String act = config.getInitParameter(IS_ACTIVE_PARAM);
        if (act != null){
            isActive = (act.toUpperCase().equals(VALUE_FOR_ACTIVE));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if(isActive){
            try {
                HttpServletRequest request = (HttpServletRequest) servletRequest;

                Parser<Integer> userIdParser = new UserIdParser();
                Integer userId = userIdParser.parsing(request);
                if(userId == null){
                    CookieManager cookieManager = CookieManager.getInstance();
                    String temp = cookieManager.findValueByName(request,AttributeNames.USER_ID);
                    if(temp == null) {
                        userId=null;
                    }else{
                        userId = Integer.parseInt(temp);
                        request.getSession().setMaxInactiveInterval(SESSION_TIME_INTERVAL);
                        request.getSession().setAttribute(AttributeNames.USER_ID,userId);
                    }
                }

                AbstractUserService userService = new UserService();
                User user = userService.selectEntityById(userId);
                if(user == null){
                    servletRequest.setAttribute(AttributeNames.USER_ROLE, null);
                }
                else{
                    servletRequest.setAttribute(AttributeNames.USER_ROLE, user.getRoleId());
                    servletRequest.setAttribute(AttributeNames.NICK_NAME, user.getName());
                }
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
