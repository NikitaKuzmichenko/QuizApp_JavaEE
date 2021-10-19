package by.epam.jwd.testingApp.service.filter;

import by.epam.jwd.testingApp.service.cookieService.CookieManager;
import by.epam.jwd.testingApp.controller.mapping.AttributeNames;
import by.epam.jwd.testingApp.entity.User;
import by.epam.jwd.testingApp.exceptions.ServiceException;
import by.epam.jwd.testingApp.service.entitiesService.abstractService.AbstractUserService;
import by.epam.jwd.testingApp.service.entitiesService.serviceImpl.UserService;
import by.epam.jwd.testingApp.service.parameterParser.Parser;
import by.epam.jwd.testingApp.service.parameterParser.parsersImpl.UserIdParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

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

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
